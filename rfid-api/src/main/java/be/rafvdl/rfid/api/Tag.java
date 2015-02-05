package be.rafvdl.rfid.api;

public class Tag {

	private final String uid;
	private final byte[] data;

	public Tag(String uid, byte[] data) {
		this.uid = uid;
		this.data = data;
	}

	public String getUid() {
		return uid;
	}

	public byte[] getData() {
		return data;
	}

}
