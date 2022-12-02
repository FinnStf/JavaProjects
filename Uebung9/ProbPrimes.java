package Uebung9;

import java.util.Random;

public class ProbPrimes {

    private static Random rand = new Random(System.currentTimeMillis());
    
    public static boolean isProbPrime1(int x) {
        if (x <= 1) return false;			// Algorithmus ist effizient Laufzeit O(1)	-->	O(1) < O(n^a)
        if (x == 2) return true;			// Algorithmus ist nicht mit signifikater Wahrscheinlichkeit richtig
        if ((x % 2) == 0) return false;		// P(a) = 0
        int y = rand.nextInt(x - 2) + 2;   // zieht eine Zahl zufällig gleichverteilt aus dem Intervall [2, x-1]
        return (x % y) != 0;
    }
    

    public static boolean isProbPrime2(int x) {// Algorithmus hat keine effiziente Laufzeit O(2^n)	-->	< O(n^a)
        if (x <= 1) return false;				// Algorithmus ist mit signifikater Wahrscheinlichkeit richtig
        if (x == 2) return true;				// Bei Primzahl P(7)  = 1
        for (int i = 0; i < x-2; i++) {			// Bei NichtPrim P(4) >= 0.5
            int y = rand.nextInt(x - 2) + 2;   // zieht eine Zahl zufällig gleichverteilt aus dem Intervall [2, x-1]
            if ((x % y) == 0) return false;
        }
        return true;
    }
    
    
    public static void main(String[] args) {
    	
    	
    	
        for (int i = 0; i < 100; i++) {
            System.out.println("i: "+ 4 + ": " + isProbPrime2(15));
        }
    }
    
    
    
}
