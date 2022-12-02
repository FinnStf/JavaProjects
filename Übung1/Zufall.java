package Übung1;

import java.util.Random;

public class Zufall {

	public static void main(String[] args) {
		int[] array = new int[5];
		for (int i = 0; i <= 4; i++) {
			Random random = new Random();
			array[i] = random.nextInt();
		}

	}
}