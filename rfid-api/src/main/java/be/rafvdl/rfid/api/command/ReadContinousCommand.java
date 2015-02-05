package be.rafvdl.rfid.api.command;

public class ReadContinousCommand extends Command {

	private byte start;
	private byte length;

	public ReadContinousCommand(byte start, byte length) {
		super((byte) 0x18);
		this.start = start;
		this.length = length;
	}

	@Override
	public byte[] getData() {
		byte[] d = new byte[4];

		d[0] = getFlags(); // High Data Rate
		d[1] = 0x23; // Unknown
		d[2] = start;
		d[3] = length;

		return d;
	}

}
