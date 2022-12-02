package src;

public class WürfelModell extends Subject {
	private int aktuellerWert;
	
	
	public WürfelModell() {
	reset();
	}
	public void reset() {
		aktuellerWert = 0;
		benachrichtigen();
	}
	public void würfel() {
		aktuellerWert = (int)(Math.random()*6+1);
		benachrichtigen();
	}
	public int getAktuellerWert() {
		return aktuellerWert;
	}
}
