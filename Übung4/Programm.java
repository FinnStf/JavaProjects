package Übung4;

public class Programm {

	public static void main(String[] args) {
		int a = 0;
		try {
			a = binomKoeff(-1, 0);
			System.out.println(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static int binomKoeff(int n, int k) throws Exception {
		if (n < 0 || k < 0 || k > n) {
			throw new IllegalArgumentException("Für die Werte muss gelten: (n>=0 && k>=0) und (n>=k)");
		}
		if (k == 0 || n == k) {
			return 1;
		} else
			return binomKoeff(n - 1, k - 1) + binomKoeff(n - 1, k);
	}

}
