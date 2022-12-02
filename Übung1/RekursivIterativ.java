package Übung1;

public class RekursivIterativ {

	public static void main(String[] args) {
		final long timeStart = System.nanoTime();
		for (int i = 1; i <= 100; i++) {
			rekursiv(i);
		}
		final long timeEnd = System.nanoTime();
		System.out.println("Laufzeit der rekuriven Schleife: " + (timeEnd - timeStart) + " Nanosek.");

		final long timeStart2 = System.nanoTime();
		for (int i = 1; i <= 100; i++) {
			iterativ(i);
		}
		final long timeEnd2 = System.nanoTime();
		System.out.println("Laufzeit der iterativen Schleife: " + (timeEnd2 - timeStart2) + " Nanosek.");

		System.out.println(iterativ(2));
		System.out.println(rekursiv(3));

	}

	public static double rekursiv(double n) {
		if (n <= 1)
			return n;
		else
			return 1 / n + rekursiv(n - 1);
	}

	public static double iterativ(int a) {
		double summe = 0;
		for (int k = 1; k <= a; k++) {
			summe += (double) 1 / k;

		}
		return summe;
	}

}
