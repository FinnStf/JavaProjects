package Uebung3;

public class BitBlock {

    public int[] data;
    public int wordCount;
    private int usedBits;

    
    private BitBlock (int wordCount) {
        data = new int[wordCount];
        this.wordCount = wordCount;
        usedBits = -1;
    }

    
    /**
     * Stores the character data given by 
     * msg into a newly allocated bitBlock.
     **/
    public BitBlock (byte[] msg) {
        int n = msg.length;
        wordCount = n / 4 + ((n % 4) == 0 ? 0 : 1);
        data = new int[wordCount];
        usedBits = n * 8;
        for (int i = 0; i < n; i++) {
            data[i / 4] = data[i / 4] | (msg[i] << ((3 - (i % 4)) * 8));
        }
    }
    
    
    public BitBlock pad() {
        int ibs = usedBits;
        if (ibs % 8 == 0) ibs++;
        int inputWordSize = ibs / 32;
        if (ibs % 32 != 0) inputWordSize++;
        int rest = 512 - (ibs % 512);
        if (rest < 64) rest = rest + 512;
        int newWordSize = inputWordSize + (rest / 32);
        BitBlock result = new BitBlock(newWordSize);
        int i;
        for (i = 0; i < usedBits / 32; i++) {
            result.data[i] = data[i];
        }
        if (usedBits % 32 == 0) {
            result.data[i] = (1 << 31);
        } else {
            result.data[i] = data[i];
            if (usedBits % 8 == 0) {
                result.data[i] = result.data[i] | (1 << (31-(usedBits % 32)));
            }
        }
        result.data[result.wordCount - 1] = usedBits;
        return result;
    }
    
    
    
    public static String  arrayBits(int[] a) {
        String s = "";
        for (int c : a) {
            String t = Integer.toBinaryString(c);
            while (t.length() < 32) t = "0" + t;
            s = s + t;
        }
        return s;
    }
    
    public static String  arrayHex(int[] a) {
        String s = "";
        for (int c : a) s = s + Integer.toHexString(c);
        return s;
    }

    
    
    public String toString() {
        String s = "Number of Words: " + wordCount + "\n";
        s = s + "Number of Bits: " + usedBits + "\n";
        s = s + arrayBits(data) + "\n";
        s = s + arrayHex(data) + "\n";
        return s;
    }
    
    
    
    
    
}
