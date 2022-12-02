package Übung1;

public class TuermeVonHanoi {
	static int count;

	public static void towerOfHanoi(int n, char startTurm, char zielTurm, char hilfsTurm) {
	

		if (n == 1) {
			System.out.println("Lege Scheibe 1 von Turm " + startTurm + " auf Turm " + zielTurm);
			count++;
			System.out.println(count);
			return;
		}
		towerOfHanoi(n - 1, startTurm, hilfsTurm, zielTurm);
		System.out.println("Lege Scheibe " + n + " von Turm " + startTurm + " auf Turm " + zielTurm);
		count++;
		towerOfHanoi(n - 1, hilfsTurm, zielTurm, startTurm);
		return;
	}

	public static void main(String args[]) {
		towerOfHanoi(7, 'A', 'C', 'B');
	
	}
}
