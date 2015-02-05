package be.rafvdl.rfid.api.command;

public class WriteSingleCommand extends Command {

	public WriteSingleCommand() {
		super((byte) 0x10);
	}

}
