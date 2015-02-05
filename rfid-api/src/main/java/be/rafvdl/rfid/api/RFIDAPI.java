package be.rafvdl.rfid.api;

import be.rafvdl.rfid.api.command.Command;

public class RFIDAPI {

	public static void main(String args[]) {
		System.out.println("Start");

		Reader reader = new Reader("COM4");
		reader.setDebug(true);
		if (!reader.open()) {
			System.err.println("Reader not found. Aborting");
			return;
		}

		try {
			reader.init();

			Thread.sleep(100);

			reader.getCommunication().sendRaw("010B000304140401000000");
			Thread.sleep(25);
			System.out.println(reader.getCommunication().waitForMessage());
			String s = null;
			do {
				System.out.println(s = reader.getCommunication()
						.waitForMessage());
				Thread.sleep(100);
			} while (s != null && !s.isEmpty());

			Thread.sleep(100);

			reader.getCommunication().sendCommand(new Command((byte) 0x17));
			Thread.sleep(25);
			System.out.println(reader.getCommunication().waitForMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		reader.close();

		System.out.println("End");
	}
}
