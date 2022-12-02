package Uebung4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Zeichenscanner {
	//Hashmap wäre cooler gewesen --> automatische Sortierung und bessere Performance
	private ArrayList<Symbol> symbolList = new ArrayList<>();
	private BufferedReader in;
	private int countTotal = 0;
	private double entropie;
	private double minEntropie = 0;
	private double maxInfogehalt;
	
	

	public double getEntropie() {
		return entropie;
	}
	
	public double getMinEntropie() {
		for(Symbol s:symbolList) {
			if(s.getRelFrequency()>minEntropie) {
				minEntropie = s.getRelFrequency();
			}
		}
		maxInfogehalt= minEntropie;
		minEntropie = getBaseLog(2,(1.0 / minEntropie));
		return minEntropie;
	}
	
	public double calcWordInfo(String s) {
		char [] a = s.toCharArray();
		double infogehalt = 0;
		
		for(char c:a)
		infogehalt += symbolList.get(this.position(c)).getInfo();
		
		return infogehalt;
	}
	
	public static double getBaseLog(int x, double y) {
		//logx(y)
		return Math.log(y) / Math.log(x);
	}

	public void setEntropie(double entropie) {
		this.entropie = entropie;
	}

	public Zeichenscanner() {
		try {
			in = Files.newBufferedReader(Paths.get("/Users/finnsteffan/Desktop/text.txt"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public double calcP() {
		double erg = 0.0;
		for (Symbol symbol : symbolList) {
			erg += symbol.getRelFrequency();
		}
		return erg;
	}

	public void scanDocument() throws IOException {
		int symbolInt;
		int symbolPos;
		Symbol s;
		char symbolChar;

		while ((symbolInt = in.read()) != -1) {
			if (symbolInt != 32 && symbolInt >=20) {
				s = new Symbol((char) symbolInt);
				symbolChar = (char) symbolInt;
				countTotal++;
				if ((symbolPos = this.position(symbolChar)) == -1) {
					symbolList.add(s);
				} else {
					symbolList.get(symbolPos).increase();
				}
			}
		}
	}

	private int position(char c) {
		int pointer = 0;
		for (int i = 0; i < symbolList.size(); i++) {
			if (symbolList.get(i).getName() == c) {
				pointer = i;
				return pointer;
			}
		}
		return -1;
	}
	public double getMaxInfo() {
		return maxInfogehalt;
	}

	public void prepList() {
		Symbol s;
		double temp = 0;
		for (int i = 0; i < symbolList.size(); i++) {
			s = symbolList.get(i);
			s.relFrequency(countTotal);
			s.calcInfo();
			temp += (s.getInfo() * s.getRelFrequency());
		}
		entropie = temp;
	}

	public String ausgeben() {
		String erg = "Entropie = " + entropie+" count total= "+countTotal+"\n";
		for (int i = 0; i < symbolList.size(); i++) {
			erg += "Nr."+(i+1)+". "+symbolList.get(i).toString();
		}
		return erg;

	}
}
