package Uebung8;

import java.util.Arrays;
import java.util.Random;

public class OneTimePad {

	/**
	 * Liefert den String in Binärform als Byte-Array zurück. Dabei wird jedes
	 * Symbol gemäß der erweiterten ASCII-Tabelle mit 8 Bit als ein Byte codiert.
	 * Diese Methode ist die "Umkehrfunktion" zu {@link #getStringForm(String)}.
	 */

	public static byte[] getBinaryForm(String s) {
		char[] symbols = s.toCharArray();
		byte[] array = new byte[symbols.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = (byte) symbols[i];
		}
		return array;
	}

	/**
	 * Interpretiert das gegebene byte-Array als String. Dafür wird jedes Byte als
	 * Symbol gemäß der erweiterten ASCII Tabelle interpretiert. Diese Methode ist
	 * die "Umkehrfunktion" zu {@link #getBinaryForm(String)}.
	 */
	public static String getStringForm(byte[] c) {
		String erg = "";
		char[] symbols = new char[c.length];
		for (int i = 0; i < c.length; i++) {
			symbols[i] = (char) c[i];
		}
		for (char d : symbols) {
			erg += d;
		}
		return erg;
	}

	/**
	 * Produziert ein (pseudo-)zufälliges Array von bytes mit der gegebenen Länge.
	 */
	public static byte[] getRandomKey(int length) {
		byte[] key = new byte[length];
//		Random rd = new Random();
//		rd.nextBytes(key);

		for (byte b : key) {
			b = (byte)(Math.random()*128);
		}
		
		return key;	
	}

	/**
	 * Verschlüsselt die gegebene Nachricht msg mit dem gegebenen Schlüssel key
	 * gemäß dem Vorgehen von Vernams One-Time-Pad. Wirft eine
	 * IllegalArgumentException, falls eine der Eingaben null ist oder falls die
	 * Länge der Arrays nicht übereinstimmen.
	 */
	public static byte[] encode(byte[] msg, byte[] key) throws IllegalArgumentException {
		byte[] chiff = new byte[msg.length];
		if (msg == null || key == null || (msg.length != key.length)) {
			throw new IllegalArgumentException("FALSCHE EINGABE ");
		}
		for (int i = 0; i < key.length; i++) {
			chiff[i] = (byte) (msg[i] ^ key[i]);
		}
		return chiff;
	}

	/**
	 * Entschlüsselt den gegebenen Chiffretext chiffre mit dem gegebenen Schlüssel
	 * key gemäß dem Vorgehen von Vernams One-Time-Pad. Wirft eine
	 * IllegalArgumentException, falls eine der Eingaben null ist oder falls die
	 * Länge der Arrays nicht übereinstimmen.
	 */
	public static byte[] decode(byte[] chiffre, byte[] key) throws IllegalArgumentException {
		return encode(chiffre, key);
	}

	/**
	 * Gibt eine binäre Repräsentation des gegebenen byte-Arrays zurück. Dabei wird
	 * jedes byte mit 8 Symbolen aus {0, 1} repräsentiert (auch die führenden
	 * 0-en!).
	 */
	public static String toBinaryString(byte[] buf) {
		String erg = "";
		for (byte b : buf) {
			erg += toBinaryStringSingle(b);
		}
		return erg;
	}

	public static String toBinaryStringSingle(byte b) {
		StringBuilder sb = new StringBuilder();

		for (int i = 128; i >= 1; i = i >>> 1) {
			if ((b / i) >= 1) {
				sb.append("1");
				b -= i;
			} else {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	public static void testBasics() {
		String klartext = "gruess die oma";
		byte[] m = getBinaryForm(klartext);
		System.out.println("Klartext                     : " + klartext);
		System.out.println("Klartext (binär)             : " + toBinaryString(m));
		byte[] k = getRandomKey(m.length);
		System.out.println("Schlüssel                    : " + toBinaryString(k));
		byte[] c = encode(m, k);
		System.out.println("Chiffretext (binär)          : " + toBinaryString(c));
		System.out.println("Chiffretext                  : " + getStringForm(c));
		byte[] decoded = decode(c, k);
		System.out.println("Decodierter Klartext (binär) : " + toBinaryString(decoded));
		System.out.println(getStringForm(decoded));
		System.out.println("Decodierter Klartext         : " + getStringForm(decoded));
	}

	public static void main(String[] args) {
		testBasics();
	}

}
