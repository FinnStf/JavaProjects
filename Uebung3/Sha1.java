package Uebung3;

public class Sha1 {

    
    
    private static final int rot5HighMask = 0b11111 << 27;
 
    
        
    public static int[] sha1(BitBlock message) {
        int msf[] = new int[16];
        int a0 = 0x67452301;
        int b0 = 0xEFCDAB89;
        int c0 = 0x98BADCFE;
        int d0 = 0x10325476;
        int e0 = 0xC3D2E1F0;
        int[] result = new int[5];
        int a1, b1, c1, d1, e1;
        int roundCounter;
        int globalWordCounter = 0;
        int i;    
        int z;

        message = message.pad();

        while (globalWordCounter < message.wordCount) {
            a1 = a0;
            b1 = b0;
            c1 = c0;
            d1 = d0;
            e1 = e0;

            for (i = 0; i < 16; i++, globalWordCounter++) {
                msf[i] = message.data[globalWordCounter];
            }
            

            for (roundCounter = 0; roundCounter < 16; roundCounter++) {
                z = e1 + ((b1 & c1) | ((~b1) & d1)) +
                    (((a1 & rot5HighMask) >>> 27) + (a1 << 5)) +
                    msf[roundCounter] +
                    0x5A827999;
                e1 = d1;
                d1 = c1;
                c1 = ((b1 & 3) << 30) + (b1 >>> 2);
                b1 = a1;
                a1 = z;
            }
            
            
            
            for (; roundCounter < 20; roundCounter++) {
                z = msf[(roundCounter - 16) % 16] ^
                        msf[(roundCounter - 14) % 16] ^
                        msf[(roundCounter - 8) % 16] ^
                        msf[(roundCounter - 3) % 16];
                    msf[roundCounter % 16] = (z << 1) | ((z & 0x80000000) >>> 31);
                
                z = e1 + ((b1 & c1) | ((~b1) & d1)) +
                    (((a1 & rot5HighMask) >>> 27) + (a1 << 5)) +
                    msf[roundCounter % 16] +
                    0x5A827999;
                e1 = d1;
                d1 = c1;
                c1 = ((b1 & 3) << 30) + (b1 >>> 2);
                b1 = a1;
                a1 = z;
            }
            
            
            for (; roundCounter < 40; roundCounter++) {
                z = msf[(roundCounter - 16) % 16] ^
                        msf[(roundCounter - 14) % 16] ^
                        msf[(roundCounter - 8) % 16] ^
                        msf[(roundCounter - 3) % 16];
                    msf[roundCounter % 16] = (z << 1) | ((z & 0x80000000) >>> 31);
                
                z = e1 + (b1 ^ c1 ^ d1) +
                    (((a1 & rot5HighMask) >>> 27) + (a1 << 5)) +
                    msf[roundCounter % 16] +
                    0x6ED9EBA1;
                e1 = d1;
                d1 = c1;
                c1 = ((b1 & 3) << 30) + (b1 >>> 2);
                b1 = a1;
                a1 = z;
            }
            

            for (; roundCounter < 60; roundCounter++) {

                z = msf[(roundCounter - 16) % 16] ^
                        msf[(roundCounter - 14) % 16] ^
                        msf[(roundCounter - 8) % 16] ^
                        msf[(roundCounter - 3) % 16];
                    msf[roundCounter % 16] = (z << 1) | ((z & 0x80000000) >>> 31);
                
                z = e1 + ((b1 & c1) | (b1 & d1) | (c1 & d1)) +
                    (((a1 & rot5HighMask) >>> 27) + (a1 << 5)) +
                    msf[roundCounter % 16] +
                    0x8F1BBCDC;
                e1 = d1;
                d1 = c1;
                c1 = ((b1 & 3) << 30) + (b1 >>> 2);
                b1 = a1;
                a1 = z;
            }
            

            for (; roundCounter < 80; roundCounter++) {
                z = msf[(roundCounter - 16) % 16] ^
                        msf[(roundCounter - 14) % 16] ^
                        msf[(roundCounter - 8) % 16] ^
                        msf[(roundCounter - 3) % 16];
                    msf[roundCounter % 16] = (z << 1) | ((z & 0x80000000) >>> 31);
                
                z = e1 + (b1 ^ c1 ^ d1) +
                    (((a1 & rot5HighMask) >>> 27) + (a1 << 5)) +
                    msf[roundCounter % 16] +
                    0xCA62C1D6;
                e1 = d1;
                d1 = c1;
                c1 = ((b1 & 3) << 30) + (b1 >>> 2);
                b1 = a1;
                a1 = z;
            }
            
            a0 = a0 + a1;
            b0 = b0 + b1;
            c0 = c0 + c1;
            d0 = d0 + d1;
            e0 = e0 + e1;
        }

        result[0] = a0;
        result[1] = b0;
        result[2] = c0;
        result[3] = d0;
        result[4] = e0;
        return result;
    }
    
    
    
}
