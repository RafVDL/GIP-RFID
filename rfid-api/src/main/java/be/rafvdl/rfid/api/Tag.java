package be.rafvdl.rfid.api;

public class Tag {

	private final String uid;
	private final byte[] data;
	private final byte distance;

	public Tag(String uid, byte[] data, byte distance) {
		this.uid = uid;
		this.data = data;
		this.distance = distance;
	}

	public String getUid() {
		return uid;
	}

	public byte[] getData() {
		return data;
	}

	public byte getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return uid;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Tag) && ((Tag) obj).uid == this.uid
				&& ((Tag) obj).data == this.data
				&& ((Tag) obj).distance == this.distance;
	}

}
