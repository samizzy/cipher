package com.samizzy.cipher;

public class Base64 {

	private static final int SIZE = 64;
	private static final int BUFFER_SIZE = 3;

	private static final char[] characterMap = getCharacterMap();

	public static String encode(String message) {
		StringBuilder encodedString = new StringBuilder();
		StringBuilder buffer = new StringBuilder();

		for (int i = 0;; i += BUFFER_SIZE) {
			System.out.println("i--> " + i);
			if (i > message.length()) {
				break;
			}
			if (i > 0) {

				for (int j = i - BUFFER_SIZE; j < i; j++)
					addBitsToBuffer(buffer, message.charAt(j));
				addEncodedString(encodedString, buffer);
				buffer.delete(0, buffer.length() + 1);
			}

		}
		return encodedString.toString();
	}

	public static void addEncodedString(StringBuilder encodedString, StringBuilder buffer) {

		for (int i = 0; i < buffer.length(); i += 6) {
			if (i > 0) {
				encodedString.append(characterMap[Integer.parseUnsignedInt(buffer.substring(i - 6, i), 2)]);
			}
		}

	}

	public static void addBitsToBuffer(StringBuilder buffer, char c) {
		System.out.println("c-> " + c);
		int g = (int) Math.pow(2D, 7D);
		int val;
		for (int i = 0; i < 8; i++) {
			val = ((byte) c & g) == g ? 1 : 0;
			buffer.append(val);
			g = g >> 1;
			// System.out.println("g--> "+g);
		}
		// System.out.println("buffer-->"+buffer);
	}

	public static void main(String[] args) {
		// System.out.println(characterMap);
		// System.out.println(Integer.parseUnsignedInt("1001101",2));

		// System.out.println(Integer.parseUnsignedInt("1011",2));
		// System.out.println("a".charAt(0)&64);
		System.out.println(encode("ManMana"));
	}

	private static char[] getCharacterMap() {
		char[] map = new char[SIZE];
		int position = 0;
		for (int i = 65; i < 65 + 26; i++)
			map[position++] = (char) i;

		for (int i = 97; i < 97 + 26; i++)
			map[position++] = (char) i;

		for (int i = 48; i < 58; i++)
			map[position++] = (char) i;

		map[position++] = "+".charAt(0);
		map[position++] = "/".charAt(0);
		return map;
	}
}
