package be.rafvdl.rfid.api.command;

public class Flag {

	private byte subcarrier = 0x00;
	private byte datarate = 0x00;
	private byte inventory = 0x00;
	private byte protocol = 0x00;

	// inventory flag = 0
	private byte select = 0x00;
	private byte address = 0x00;

	// inventory flag = 1
	private byte afi = 0x00;
	private byte slots = 0x00;

	private byte option = 0x00;
	private byte rfu = 0x00;

	public byte getData() {
		byte i = 0x00;
		i += subcarrier;
		i <<= 1;
		i += datarate;
		i <<= 1;
		i += inventory;
		i <<= 1;
		i += protocol;
		i <<= 1;

		if (inventory == 0x00) {
			i += select;
			i <<= 1;
			i += address;
			i <<= 1;
		} else {
			i += afi;
			i <<= 1;
			i += slots;
			i <<= 1;
		}

		i += option;
		i <<= 1;
		i += rfu;
		return i;
	}

	public static Flag fromData(byte data) {
		Flag flag = new Flag();

		flag.subcarrier = (byte) ((data >> 7) & 0x01);
		flag.datarate = (byte) ((data >> 6) & 0x01);
		flag.inventory = (byte) ((data >> 5) & 0x01);
		flag.protocol = (byte) ((data >> 4) & 0x01);

		if (flag.inventory == 0) {
			flag.select = (byte) ((data >> 3) & 0x01);
			flag.address = (byte) ((data >> 2) & 0x01);
		} else {
			flag.afi = (byte) ((data >> 3) & 0x01);
			flag.slots = (byte) ((data >> 2) & 0x01);
		}
		flag.option = (byte) ((data >> 1) & 0x01);
		flag.rfu = (byte) ((data) & 0x01);

		return flag;
	}

	public byte getSubcarrier() {
		return subcarrier;
	}

	public Flag setSubcarrier(byte subcarrier) {
		this.subcarrier = subcarrier;
		return this;
	}

	public byte getDatarate() {
		return datarate;
	}

	public Flag setDatarate(byte datarate) {
		this.datarate = datarate;
		return this;
	}

	public byte getInventory() {
		return inventory;
	}

	public Flag setInventory(byte inventory) {
		this.inventory = inventory;
		return this;
	}

	public byte getProtocol() {
		return protocol;
	}

	public Flag setProtocol(byte protocol) {
		this.protocol = protocol;
		return this;
	}

	public byte getSelect() {
		return select;
	}

	public Flag setSelect(byte select) {
		this.select = select;
		return this;
	}

	public byte getAddress() {
		return address;
	}

	public Flag setAddress(byte address) {
		this.address = address;
		return this;
	}

	public byte getAfi() {
		return afi;
	}

	public Flag setAfi(byte afi) {
		this.afi = afi;
		return this;
	}

	public byte getSlots() {
		return slots;
	}

	public Flag setSlots(byte slots) {
		this.slots = slots;
		return this;
	}

	public byte getOption() {
		return option;
	}

	public Flag setOption(byte option) {
		this.option = option;
		return this;
	}

	public byte getRfu() {
		return rfu;
	}

	public Flag setRfu(byte rfu) {
		this.rfu = rfu;
		return this;
	}

}
