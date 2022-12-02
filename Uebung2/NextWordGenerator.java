package Uebung2;

import java.util.ArrayList;
import java.util.Arrays;

public class NextWordGenerator {

	public static char[] getNextWord(char[] lastWord, char[] alphabet) throws NullPointerException {
		if (alphabet == null || alphabet.length == 0 || lastWord == null || lastWord.length == 0) {
			throw new NullPointerException("das alphabet darf nicht null sein!");
		}
		Arrays.sort(alphabet);

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

	public static ArrayList<String> getDictionary(char[] word, char[] alphabet, int reps) throws NullPointerException {
		if (word == null || alphabet == null) {
			throw new NullPointerException("word or alphabet can't be null!");
		}
		char[] currentWord = word;
		ArrayList<String> dictionary = new ArrayList<>();
		for (int i = 0; i < reps; i++) {
			dictionary.add(String.valueOf(currentWord));
			currentWord = getNextWord(currentWord, alphabet);
		}
		return dictionary;
	}

	private static char[] generateAlphabet(int from, int to) {
		char[] alphabet = new char[to + 1 - from];
		for (int i = from, j = 0; i < to + 1; i++, j++) {
			alphabet[j] = (char) i;
		}
		return alphabet;
	}

	private static char[] combineAlphabets(char[] alphabet1, char[] alphabet2) {
		char[] combined = new char[alphabet1.length + alphabet2.length];
		int index = 0;
		for (char letter : alphabet1) {
			combined[index] = letter;
			index++;
		}
		for (char letter : alphabet2) {
			combined[index] = letter;
			index++;
		}
		return combined;
	}

	private static String crackPassword(char[] alphabet, String password, int tries) {
		ArrayList<String> possibleCombinations = getDictionary(new char[] { 0 }, alphabet, tries);
		int tried = 0;
		for (String word : possibleCombinations) {
			tried++;
			if (word.equals(password)) {
				return String.format("the password is: %s\nyou needed %d tries", word, tried);
			}
		}
		return "not found! you need more tries";
	}

	private static void printDictionary(ArrayList<String> dictionary) {
		for (int i = 0; i < dictionary.size(); i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.printf("%3s, ", dictionary.get(i));
		}
	}

	public static void main(String[] args) {

		// when complete alphabet can be used
		String password1 = crackPassword(generateAlphabet(32, 128), "fin", 1000000);
		System.out.println(password1 + "\n");

		// when the alphabet are only lower case letters
		String password2 = crackPassword(generateAlphabet(97, 122), "finn", 1000000);
		System.out.println(password2 + "\n");

		// when the alphabet are alpha numeric but all lowercase
		char[] a = generateAlphabet(97, 122);
		char[] b = generateAlphabet(48, 57);
		String password3 = crackPassword(combineAlphabets(a, b), "fi29", 1500000);
		System.out.println(password3 + "\n");

		// test 1:
		System.out.println("\n\nTEST_1");
		char[] alphabet1 = new char[] { '4', 'b', 'g', 'H', 'a', 'X', '?', '#', 'M', 'a' };
		char[] word1 = new char[] { '#' };
		ArrayList<String> dictionary1 = getDictionary(word1, alphabet1, 100);
		printDictionary(dictionary1);

		// test 2: case word is 99 -> 000 or 100 ?
		System.out.println("\n\nTEST_2");
		char[] alphabet2 = generateAlphabet(48, 57);
		char[] word2 = new char[] { '0' };
		ArrayList<String> dictionary2 = NextWordGenerator.getDictionary(word2, alphabet2, 100);
		printDictionary(dictionary2);

		// test 3:
		System.out.println("\n\nTEST_3");
		char[] alphabet3 = new char[] { 'a', 'b', 'c' };
		char[] word3 = new char[] {'a'};
		ArrayList<String> dictionary3 = getDictionary(word3, alphabet3, 100);
		printDictionary(dictionary3);

	}

}