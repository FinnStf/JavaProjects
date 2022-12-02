package Übung1;

public class Sortieren {

	public static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int j = i;
			while (j > 0) {
				if (a[j - 1] > a[j]) { // Elemente vertauschen
					int tmp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = tmp;
				}
				j--;
			}
		}
	}

}
