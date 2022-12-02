package Übung4;

public class Binom {

	public static void main(String[] args) {

		int anzahl_zeilen = 10;
		int anzahl_spalten = anzahl_zeilen;
		int[][] pascal = new int[anzahl_zeilen][anzahl_spalten];
		int k, n;

		pascal[0][0] = 1;

		for (n = 1; n < anzahl_zeilen; n++) {
			for (k = 0; k <= n; k++) {
				if (k == n || k == 0) {
					pascal[n][k] = 1;
				} else {
					pascal[n][k] = pascal[n - 1][k - 1] + pascal[n - 1][k];
				}
			}
		}
		for(int []spalte:pascal) {
			for(int ziffer:spalte) {
				System.out.print(ziffer + " ");
			}System.out.println();
		}
	}
}
