package Übung3;

public class Iteration {

	long iter(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else {
			long fib1 = 0;
			long fib2 = 1;
			for (int i = 2; i <= n; i++) {
				long h = fib2;
				fib2 = fib2 + fib1;
				fib1 = h;
			}
			return fib2;
		}

	}

	public static void main(String[] args) {

	}
}
