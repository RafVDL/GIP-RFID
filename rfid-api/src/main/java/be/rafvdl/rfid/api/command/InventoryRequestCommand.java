package be.rafvdl.rfid.api.command;

public class InventoryRequestCommand extends Command {

	private byte command;
	private byte masklength;

	public InventoryRequestCommand(byte command, byte masklength) {
		super((byte) 0x14);
		this.command = command;
		this.masklength = masklength;
	}

	@Override
	public byte[] getData() {
		byte[] d = new byte[3];
		d[0] = getFlags();
		d[1] = command;
		d[2] = masklength;
		return d;
	}

}
