package be.rafvdl.rfid.api.command;

public class FirmwareRequestCommand extends Command {

	public FirmwareRequestCommand() {
		super((byte) 0xFE);
	}

}
