package be.rafvdl.rfid.api.command;

public class WriteContinousCommand extends Command {

	public WriteContinousCommand(byte[] data) {
		super((byte) 0x11);
		setData(data);
	}

}
