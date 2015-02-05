package be.rafvdl.rfid.api;

import java.util.ArrayList;
import java.util.List;

import jssc.SerialPort;
import jssc.SerialPortException;
import be.rafvdl.rfid.api.command.FirmwareRequestCommand;
import be.rafvdl.rfid.api.command.VersionRequestCommand;

public class Reader {

	private final SerialPort serialPort;
	private final ReaderCommunication communication;
	private boolean opened = false;
	private boolean debug = false;

	public Reader(String port) {
		this.serialPort = new SerialPort(port);
		this.communication = new ReaderCommunication(this);
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}

	public ReaderCommunication getCommunication() {
		return communication;
	}

	public String getName() {
		getCommunication().sendCommand(new VersionRequestCommand());
		return getCommunication().waitForMessage();
	}

	public String getFirmwareVersion() {
		getCommunication().sendCommand(new FirmwareRequestCommand());
		return getCommunication().waitForMessage();
	}

	public List<Tag> getCurrentTags() {
		List<Tag> tags = new ArrayList<Tag>();
		getCommunication().sendRaw("010B000304140401000000");
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getCommunication().waitForMessage();
		String s = null;
		do {
			s = getCommunication().waitForMessage();
			if (s != null && !s.isEmpty() && s.length() > 3) {
				String uid = s.substring(3, s.length() - 1).split(",")[0];
				tags.add(new Tag(uid, null));
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (s != null && !s.isEmpty());
		return tags;
	}

	public void init() {
		if (!opened)
			return;

		try {
			getCommunication().sendCommand(new VersionRequestCommand());
			Thread.sleep(25);
			getCommunication().waitForMessage();

			Thread.sleep(100);

			getCommunication().sendCommand(new FirmwareRequestCommand());
			Thread.sleep(25);
			getCommunication().waitForMessage();

			Thread.sleep(100);

			getCommunication().sendRaw("010C00030410002101000000");
			Thread.sleep(25);
			getCommunication().waitForMessage();

			Thread.sleep(100);

			getCommunication().sendRaw("0109000304F0000000");
			Thread.sleep(25);
			getCommunication().waitForMessage();

			Thread.sleep(100);

			getCommunication().sendRaw("0109000304F1FF0000");
			Thread.sleep(25);
			getCommunication().waitForMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean open() {
		try {
			boolean success = serialPort.openPort();
			return opened = (success && serialPort.setParams(115200, 8, 1,
					SerialPort.PARITY_NONE));
		} catch (SerialPortException e) {
			if (debug)
				e.printStackTrace();
		}
		return opened = false;
	}

	public boolean close() {
		try {
			return !(opened = !serialPort.closePort());
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
