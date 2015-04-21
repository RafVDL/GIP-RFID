package be.rafvdl.rfid.api.command;

import org.junit.Test;

import be.rafvdl.rfid.api.util.Hex;

public class FlagTest {

	@Test
	public void testFromData() {
		byte data = 0x04;
		Flag flag = Flag.fromData(data);

		System.out.println("Subcarrier: " + flag.getSubcarrier());
		System.out.println("Datarate: " + flag.getDatarate());
		System.out.println("Inventory: " + flag.getInventory());
		System.out.println("Protocol: " + flag.getProtocol());
		System.out.println("Select: " + flag.getSelect());
		System.out.println("Address: " + flag.getAddress());
		System.out.println("Afi: " + flag.getAfi());
		System.out.println("Slots: " + flag.getSlots());
		System.out.println("Option: " + flag.getOption());
		System.out.println("Rfu: " + flag.getRfu());
	}

	@Test
	public void testToData() {
		Flag flag = new Flag();
		flag.setInventory((byte) 0x01).setSlots((byte) 0x01)
				.setOption((byte) 0x01);

		byte[] data = new byte[1];
		data[0] = flag.getData();
		System.out.println(Hex.toHexString(data));
	}

}
