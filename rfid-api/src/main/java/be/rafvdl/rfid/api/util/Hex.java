package be.rafvdl.rfid.api.util;

import org.apache.commons.codec.DecoderException;

public class Hex {

	public static String toHexString(byte[] array) {
		return org.apache.commons.codec.binary.Hex.encodeHexString(array);
	}

	public static byte[] toByteArray(String s) {
		try {
			return org.apache.commons.codec.binary.Hex.decodeHex(s
					.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}

}
