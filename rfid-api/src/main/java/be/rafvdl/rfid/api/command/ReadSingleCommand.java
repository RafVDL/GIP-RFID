package be.rafvdl.rfid.api.command;

public class ReadSingleCommand extends Command {

	public ReadSingleCommand(byte[] data) {
		super((byte) 0x12);
		setData(data);
	}

}
