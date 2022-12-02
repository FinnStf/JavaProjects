package Uebung4;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Zeichenscanner scan = new Zeichenscanner();
		try {
			scan.scanDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scan.prepList();
		System.out.println(scan.ausgeben());
		
		//c) Benötigte Bits für ASCII Word n=8  -->> 7*8=56
		//d)
		System.out.println("\nInformationsgehalt von Wort der Länge 8 -->"+8*scan.getEntropie());
		System.out.println("\nMin-Entropie von Wort der Länge 8 -->"+8*scan.getMinEntropie());
		
		System.out.println("\nInfogehalt: ab = "+scan.calcWordInfo("ab"));
		System.out.println("\nInfogehalt: Woody = "+scan.calcWordInfo("Woody"));
		System.out.println("\nInfogehalt: einen = "+scan.calcWordInfo("einen"));
		System.out.println("\nInfogehalt: beherbergt = "+scan.calcWordInfo("beherbergt"));
		System.out.println("\nInfogehalt: Staatsbetrieben = "+scan.calcWordInfo("Staatsbetrieben"));
		System.out.println("\nInfogehalt: Bestandteil = "+scan.calcWordInfo("Bestandteil"));
		
		System.out.println("Die Wahrscheinlichkeit ist = "+ scan.calcP());
		System.out.println("Max Häufigkeit : "+ scan.getMaxInfo());
	}
}
