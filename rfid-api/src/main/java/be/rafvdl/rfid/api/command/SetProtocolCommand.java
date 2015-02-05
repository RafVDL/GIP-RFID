package be.rafvdl.rfid.api.command;

public class SetProtocolCommand extends WriteSingleCommand {

	private boolean first = true;
	private boolean fullpower;

	public SetProtocolCommand(boolean fullpower) {
		super();
		this.fullpower = fullpower;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	@Override
	public byte[] getData() {
		byte[] d = new byte[2 + (!first ? 2 : 0)];
		if (first) {
			d[0] = 0x01;
			d[1] = 0x21;
		} else {
			d[0] = 0x00;
			d[1] = (byte) (fullpower ? 0x21 : 0x31);
			d[2] = 0x01;
			d[3] = getFlags();
		}
		return d;
	}

}
