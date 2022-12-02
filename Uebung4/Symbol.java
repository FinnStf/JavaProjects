package Uebung4;

public class Symbol {
	private char name;
	private int absFrequency;
	private double relFrequency;
	private double info;

	public Symbol(char name) {
		this.name = name;
		absFrequency = 1;
	}

	public void increase() {
		absFrequency++;
	}

	public void relFrequency(int charCount) {
		relFrequency = (double) absFrequency / charCount;
	}

	public void calcInfo() {
		if (relFrequency == 0) {
			info = 0;
		} else {

			info = getBaseLog(2,(1.0 / relFrequency));
		}

	}

	public static double getBaseLog(int x, double y) {
		//logx(y)
		return Math.log(y) / Math.log(x);
	}

	public char getName() {
		return name;
	}

	public double getRelFrequency() {
		return relFrequency;
	}

	public double getInfo() {
		return info;
	}

	public String toString() {
		return " Name: " + name + " absolte Häuf.: " + absFrequency + " rel. Häufigkeit: " + relFrequency
				+ " InfoGehalt: " + info + " \n";
	}

}
