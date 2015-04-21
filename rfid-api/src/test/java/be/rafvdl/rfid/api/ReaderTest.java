package be.rafvdl.rfid.api;

import org.junit.Test;

public class ReaderTest {

	@Test
	public void test() {
		Reader reader = new Reader("COM4");
		reader.open();

		reader.setDebug(true);
		reader.init();

		for (Tag t : reader.getCurrentTags()) {
			System.out.println("Tag[" + t.getUid() + "]: " + t.getDistance());
		}

		reader.close();
	}

}
