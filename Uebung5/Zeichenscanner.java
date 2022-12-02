package Uebung5;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Zeichenscanner {

	private BufferedReader in;
	private HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	private static String document = "";

	public Zeichenscanner() {
		try {
			in = Files.newBufferedReader(Paths.get("/Users/finnsteffan/Desktop/text.txt"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void scanDocument() throws IOException {
		char symbol;
		int ascii;

		while ((ascii = in.read()) != -1) {
			//if (ascii > 20 && ascii != 32) {
				document += (char) ascii;
				symbol = (char) ascii;
				if (!map.containsKey(symbol)) {
					map.put(symbol, 1);
				} else {
					map.put(symbol, map.get(symbol) + 1);
				}
			}
		//}

	}

	public static String replaceNumbers() {
		String erg = document.replaceAll("[1-9]", " ") + "\n";
		erg += document.replaceAll("[0]", " ") + "\n";
		
		return erg;
	}
	

	
	public String mapAusgeben() {
		String erg = "";
		int total = 0;
		for (Character key : map.keySet()) {
			total += map.get(key);
			erg += "Key: " + key + " Value: " + map.get(key) + "\n";
		}
		return erg += "\nZeichen insgesammt = " + total;
	}

	public static void main(String[] args) {
		Zeichenscanner scanner = new Zeichenscanner();
		try {
			scanner.scanDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(scanner.mapAusgeben());
		System.out.println(document);
		System.out.println(replaceNumbers());

	}
}
