package Uebung3;

import java.util.Arrays;

public class BruteForceAttack {
	private BitBlock bitblock;
	private Sha1 hashMaker;
	private int[] pswrd;

	public BruteForceAttack(BitBlock bitblock, Sha1 sha1Hash, int[] pswrd) {
		this.bitblock = bitblock;
		this.hashMaker = sha1Hash;
		this.pswrd = pswrd;
	}

	/**
	 * 
	 * @param sha1Hash target hash-value
	 * @param alphabet char[] from which possible words are generated from
	 * @return String which hash-value equals sha1Hash
	 */
	public String bruteForceCrack(char[] alphabet) {
		boolean cracked = false;
		char[] tempGuess = null;
		String guess = null;
		int[] guessHashed = new int[5];
		while (!cracked) {
			tempGuess = getNextWord(tempGuess, alphabet);
			guess = charToString(tempGuess);
			bitblock = new BitBlock(guess.getBytes());
			guessHashed = hashMaker.sha1(bitblock);
			if (Arrays.equals(guessHashed, pswrd)) {
				cracked = true;
			}
		}
		return guess;
	}

	/**
	 * 
	 * @param lastWord
	 * @param alphabet
	 * @return the next word(char[]) in the logical order of the alphabet
	 * @throws NullPointerException
	 */
	public static char[] getNextWord(char[] lastWord, char[] alphabet) throws NullPointerException {
		if (alphabet == null || alphabet.length == 0) {
			throw new NullPointerException("das lastWord oder alphabet ist null!");
		}
		Arrays.sort(alphabet);

		if (lastWord == null || lastWord.length == 0) {
			return new char[] { alphabet[0] };
		}
		int indexPointer = lastWord.length - 1;
		final char SMALLEST_CHARACTER = alphabet[0];
		final char LARGEST_CHARACTER = alphabet[alphabet.length - 1];

		while (indexPointer >= 0) {
			char letterPointer = lastWord[indexPointer];
			if (letterPointer == LARGEST_CHARACTER) {
				if (indexPointer != 0) {
					indexPointer--;
				} else {
					char[] newArray = new char[lastWord.length + 1];
					Arrays.fill(newArray, SMALLEST_CHARACTER);
					// optional:
					/*
					 * if(SMALLEST_CHARACTER == '0'){ newArray[0] =
					 * alphabet[Arrays.binarySearch(alphabet,SMALLEST_CHARACTER)+1]; }
					 */
					return newArray;
				}
			} else {
				int nextBiggerLetter = Arrays.binarySearch(alphabet, letterPointer) + 1;
				lastWord[indexPointer] = alphabet[nextBiggerLetter];
				Arrays.fill(lastWord, indexPointer + 1, lastWord.length, SMALLEST_CHARACTER);
				return lastWord;
			}
		}
		return null;
	}

	public static String charToString(char[] array) {
		String s = "";
		for (char a : array) {
			s += a;
		}
		return s;
	}

	/**
	 * 
	 * @param from ASCII value of first char in alphabet
	 * @param to   last ASCII value of last char in alphabet
	 * @return generates an alphabet with ASCII int values
	 */

}
