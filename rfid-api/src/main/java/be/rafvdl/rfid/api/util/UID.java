package be.rafvdl.rfid.api.util;

public class UID {

	public static String flip2(String s) {
		char[] c1 = s.toCharArray();
		char[] c2 = new char[c1.length];
		for (int i = 0; i < c1.length; i += 2) {
			c2[c1.length - 1 - i] = c1[i + 1];
			c2[c1.length - 2 - i] = c1[i];
		}
		return new String(c2);
	}

}
