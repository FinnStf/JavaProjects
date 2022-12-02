package Uebung1;

/**
 ** LargeInt A class to hold the data of a large integer.
 **/
public class LargeInt {

	/**
	 * Number of bits used in each element of the data array.
	 */
	public static int BITSPERWORD = 5;
	public static int WORD_RADIX = 1 << BITSPERWORD;
	/**
	 * Used to mask the used bits of an element of the data array.
	 */
	public static int STANDARD_USEBIT_MASK = WORD_RADIX - 1;
	/**
	 * Used to mask the bits of an element of the data array that are not actually
	 * used to store data.
	 */
	public static int STANDARD_CALCBIT_MASK = 0xFFFFFFFF ^ STANDARD_USEBIT_MASK;

	/**
	 ** An array to store the actual data of the integer. It is stored as follows:
	 * data[0] contains the least significant digit, data[usedWords-1] holds the
	 * most significant digit. Each digit is stored in little-endian format. For
	 * each digit, BITSPERWORD bits are used to store information about the integer.
	 * The remaining bits are reserved for computations.
	 **/
	private int[] data;

	/**
	 ** Contains the number of bits up to the leftmost bit that is set to one. If
	 * there is no bit that is set to one, then this value is set to 0.
	 **/
	private int bitSize;

	/**
	 ** The number of uint32 words in the data array. This value should be set once,
	 * when the BigInt is initialized and never be changed until it is destroyed.
	 **/
	private int wordSize;

	/**
	 ** The number of uint32 words in the data array which actually hold information
	 * about the integer. This means, that this value is wordSize minus the number
	 * of leading zero-words. If all words in the data array are set to 0, the value
	 * of this variable is 0.
	 **/
	private int usedWords;

	/**
	 ** Returns the number of leading zeroes of the given argument.
	 **
	 ** A leading zero is a binary digit of the given arg that is set to 0 and to the
	 * left of which only appear 0's.
	 **/
	public static int getNumberOfLeadingZeroes(int arg) {
		int mask = 0x80000000;
		int count = 0;

		while (((arg & mask) == 0) && (count < 32)) {
			mask = mask >> 1;
			count++;
		}
		return count;
	}

	/**
	 ** Checks for leading zeroes and, based on the number of these, computes
	 * usedWords and bitSize.
	 **/
	private void recomputeUsageVariables() {
		int i;
		int dataPointer;

		i = wordSize - 1;
		while ((data[i] == 0) && (i > 0))
			i--;

		bitSize = 32 - getNumberOfLeadingZeroes(data[i]) + (i * BITSPERWORD);
		if (bitSize == 0) {
			usedWords = 0;
		} else {
			usedWords = i + 1;
		}
	}

	/**
	 ** Returns TRUE if and only if this LargeInt is even.
	 **/
	public boolean IsEven() {
		if ((data[0] & 1) == 0)
			return true;
		else
			return false;
	}

	/**
	 ** Returns TRUE if and only if this LargeInt is odd.
	 **/
	public boolean IsOdd() {
		if ((data[0] & 1) == 1)
			return true;
		else
			return false;
	}

	/**
	 ** Initializes a new LargeInt with the given integer value.
	 **
	 ** @param value    The value that is to be passed to the new LargeInt.
	 ** @param wordSize The number of 32-Bit-words that shall used in the new
	 *                 LargeInt.
	 ** @return A LargeInt that has been initialized with the given value.
	 **/
	public LargeInt(int value, int wordSize) {
		data = new int[wordSize];
		this.wordSize = wordSize;

		if (value == 0) {
			usedWords = 0;
			bitSize = 0;
		} else {
			usedWords = 0;
			bitSize = 32 - getNumberOfLeadingZeroes(value);
			while (value > 0) {
				data[usedWords] = value & STANDARD_USEBIT_MASK;
				usedWords++;
				value = value >> BITSPERWORD;
			}
		}
	}

	private int max(int x, int y) {
		if (x > y)
			return x;
		else
			return y;
	}

	/**
	 ** Adds this LargeInt to the other one and returns the result. Does not change
	 * the value of this or the other LargeInt.
	 **
	 ** This algorithm treats the arguments as if they were both positive, i.e. the
	 * sign of the integers is ignored.
	 **
	 ** @param other The number that is to add to this one.
	 ** @return The sum of this and other. The word size of the result is exactly as
	 *         large as needed to hold the sum.
	 **
	 **/
	public LargeInt Add(LargeInt other) {
		int overhead = 0;
		int maxWordSize = max(this.wordSize, other.wordSize);
		int sum[] = new int[maxWordSize];

		for (int i = 0; i < maxWordSize; i++) {
			int val = this.data[i] + other.data[i] + overhead;
			if (val > WORD_RADIX) {
				overhead = val >> BITSPERWORD;
				val = val & STANDARD_USEBIT_MASK;
			} else {
				overhead = 0;
			}
			sum[i] += val;
		}
		return new LargeInt(dataValue(sum, maxWordSize), maxWordSize);
	}

	public int dataValue(int[] data, int wordSize) {
		int value = 0;
		int wordPointer = 0;
		for (int i = 0; i < data.length; i++) {
			while (data[i] > 0) {
				if (data[i] >= 16) {
					value += (16 << wordPointer);
					data[i] -= 16;
				}
				if (data[i] >= 8) {
					value += (8 << wordPointer);
					data[i] -= 8;
				}
				if (data[i] >= 4) {
					value += (4 << wordPointer);
					data[i] -= 4;
				}
				if (data[i] >= 2) {
					value += (2 << wordPointer);
					data[i] -= 2;
				}
				if (data[i] >= 1) {
					value += (1 << wordPointer);
					data[i] -= 1;
				}
			}
			wordPointer += wordSize;
		}
		return value;
	}

	String toBinaryString() {
		String s = "";
		int i = bitSize - 1;
		while (i >= 0) {
			int wordIndex = i / BITSPERWORD;
			int bitIndex = i % BITSPERWORD;
			int testBit = 1 << bitIndex;
			if ((data[wordIndex] & testBit) != 0) {
				s = s + "1";
			} else {
				s = s + "0";
			}
			i--;
		}
		return s;
	}

	// Verstehen Sie die untige main-Funktion bitte als Anstoß zum
	// Testen Ihres Codes. Fügen Sie weitere, sinnvolle Tests hinzu!
	public static void main(String[] args) {
		LargeInt x = new LargeInt(77777, 5);
		LargeInt y = new LargeInt(88888, 5);
		System.out.println(x.toBinaryString());
		System.out.println(y.toBinaryString());

		System.out.println(x.IsOdd());
		LargeInt z = x.Add(y);
		System.out.println(z.toBinaryString());
	}

}
