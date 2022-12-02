package Uebung2;

public class Dictonary {

	public static boolean isIncluded(char[] lastWord, char[] alphabet) {
		boolean included = true;
		int wordlength = lastWord.length;
		int count=0;
		while (included&&count<=wordlength) {
			for (char letter : lastWord) {
			count++;
				for (char ele : alphabet) {
					if (letter == ele) {
			
						included = true;
						break;
					}
					included=false;
				}

			}
		}
		return included;
	}

	public char[] getNextWord(char[] lastWord, char[] alphabet) throws Exception {
		if (!isIncluded(lastWord, alphabet)) {
			throw new Exception("Fehler: Das Wort muss im Alphabet vorhanden sein.");
		}
		if(alphabet.length == 0) {
			throw new Exception("Fehler: Das Alphabet darf nicht leer sein.");
		}
		int div = lastWord.length;
		/*
		 * calc position of new Letter in alphabet
		 */
		int nextIndex = div % (alphabet.length);
		char newLetter = alphabet[nextIndex];
		/*
		 * init new char[] and reassign values
		 */
		char[] nextWord = new char[div + 1];
		for (int i = 0; i < lastWord.length; i++) {
			nextWord[i] = lastWord[i];
		}
		nextWord[div] = newLetter;
		return nextWord;
	}

	public String toString(char[] word) {
		String s = "";
		for (char letter : word) {
			s += letter;
		}
		return s;
	}

	public static void main(String[] args) {
		Dictonary test = new Dictonary();

		char[] lastWord = { 'a','b'};
		char[] alphabet = { 'a', 'b', 'c' };

		try {
			System.out.println(test.getNextWord(lastWord, alphabet));
			for(int i=0;i<100;i++) {
				char[] temp = test.getNextWord(lastWord, alphabet);
				lastWord = temp;
				System.out.println(test.getNextWord(lastWord, alphabet));
			}
			
			//System.out.println(isIncluded(lastWord, alphabet));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

	}

}
