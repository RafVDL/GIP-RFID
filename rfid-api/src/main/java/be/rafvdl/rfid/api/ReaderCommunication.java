package be.rafvdl.rfid.api;

import jssc.SerialPortException;
import be.rafvdl.rfid.api.command.Command;
import be.rafvdl.rfid.api.util.Hex;

public class ReaderCommunication {

	private static final long TIMEOUT = 1000;

	private final Reader reader;

	public ReaderCommunication(Reader reader) {
		this.reader = reader;
	}

	public boolean sendRaw(String s) {
		if (reader.isDebug())
			System.out.println("-> " + s);

		try {
			return reader.getSerialPort().writeString(s);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendCommand(Command cmd) {
		return sendRaw(Hex.toHexString(cmd.getBytes()));
	}

	public String waitForMessage(long timeout) {
		String buffer = "";

		long start = System.currentTimeMillis();

		while (System.currentTimeMillis() - start < timeout) {
			String buf = null;
			try {
				buf = reader.getSerialPort().readString();
			} catch (SerialPortException e) {
				e.printStackTrace();
			}

			if (buf == null && !buffer.isEmpty())
				break;
			else if (buf != null) {
				start = System.currentTimeMillis();
				buffer += buf;
			}
		}

		if (reader.isDebug() && buffer != null && !buffer.isEmpty())
			System.out.println("<- " + buffer);

		return buffer;
	}

	public String waitForMessage() {
		return waitForMessage(TIMEOUT);
	}

}
