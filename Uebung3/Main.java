package Uebung3;

public class Main {

	private static int[] getIntArray(String hash, int wordsize) {
		int[] array = new int[wordsize];

		for (int i = 0; i < wordsize; i++) {
			array[i] = Integer.parseUnsignedInt(hash.substring(i*8,(i+1)*8),16);
		}
		return array;
	}

	private static char[] generateAlphabet(int from, int to) {
		char[] alphabet = new char[to + 1 - from];
		for (int i = from, j = 0; i < to + 1; i++, j++) {
			alphabet[j] = (char) i;
		}
		return alphabet;
	}

	public static void main(String[] args) {

		/*
		 * set a password
		 */
		String password = "efhg";

		/*
		 * generate an alphabet {A-Z == 65-90} {a-z == 97-122} {0-9 == 48-57}
		 */
		char[] alphabet = generateAlphabet(48, 57);

		/*
		 * hash password
		 */
		BitBlock bitblock = new BitBlock(password.getBytes());
		/**
		 * Aufgabe 1
		 */
		//int[] pswrdHash = Sha1.sha1(bitblock);
		/**
		 * Aufgabe 2
		 */
		int[] pswrdHash = getIntArray("d8daa4b16db733f969cd6eb48fc65b9c02a9bf7b", 5);

		/*
		 * guess the password
		 */
		Sha1 hashMaker = new Sha1();
		BruteForceAttack codeKnacker = new BruteForceAttack(bitblock, hashMaker, pswrdHash);

		long startTime = System.currentTimeMillis();

		String GuessedPswrd = codeKnacker.bruteForceCrack(alphabet);
		System.out.println(GuessedPswrd);

		long stopTime = System.currentTimeMillis();
		long time = stopTime - startTime;
		System.out.println("Zeit in ms: " + time + " Zeit in sek " + (time / 1000.00));
		/**
		 * 
		 * tabelle zu Aufgabe 1 (in Sekunden)
		 * 
		 * 
		 * zu Aufgabe 2
		 * 
		 * 65caa18f6f33d5e89493dc608eb0055126c34997
		 * Password: "kurz" Zeit:2,305 sek
		 * 
		 * d8daa4b16db733f969cd6eb48fc65b9c02a9bf7b
		 * 71619771, 159.308sek
		 */

	}

}
