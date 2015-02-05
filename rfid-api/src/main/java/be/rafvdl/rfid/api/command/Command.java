package be.rafvdl.rfid.api.command;

import java.io.ByteArrayOutputStream;

public class Command {

	private int length;
	private byte command1;
	private byte data[];
	private byte flags = 0;

	private Command() {
		length = 0;
		command1 = 0;
		data = new byte[0];
	}

	public Command(byte command1) {
		this();
		setFirstCommand(command1);
		calculateFields();
	}

	public Command addFlag(byte flag) {
		this.flags |= flag;
		return this;
	}

	private void calculateFields() {
		setLength(1 + 1 + 1 + 2 + 1
				+ (getData() == null ? 0 : getData().length) + 2);
	}

	public byte[] getBytes() {
		calculateFields();
		ByteArrayOutputStream bytes = new ByteArrayOutputStream(getLength());
		bytes.write(1);
		bytes.write((byte) (getLength() & 0xFF));
		bytes.write(0);
		bytes.write(0x03);
		bytes.write(0x04);
		bytes.write(getFirstCommand());
		int dl = getData() == null ? 0 : getData().length;
		for (int counter = 0; counter < dl; counter++)
			bytes.write(getData()[counter]);

		bytes.write(0x00);
		bytes.write(0x00);
		return bytes.toByteArray();
	}

	protected byte[] getData() {
		return data;
	}

	private int getFirstCommand() {
		return command1;
	}

	public byte getFlags() {
		return flags;
	}

	public int getLength() {
		return length;
	}

	public Command removeFlag(byte flag) {
		this.flags &= ~flag;
		return this;
	}

	protected void setData(byte data[]) {
		this.data = data;
	}

	private void setFirstCommand(byte command) {
		command1 = command;
	}

	private void setLength(int length) {
		this.length = length;
	}
}
