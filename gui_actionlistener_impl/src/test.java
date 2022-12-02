import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {
	private final static int breite = 9;
	private final static int hoehe = 5;

	public static void main(String[] args) {
		Spielsteine s = Spielsteine.GELB;
		

		Spielbrett a = new Spielbrett(hoehe, breite);
		a.zeichne();
		// System.out.println(a.setzeStein(Spielsteine.WEISS,4));
		// a.zeichne();
		int zeile, spalte;
		for (int i = 0; i <= 50; i++) {
			try {
				System.out.println("Spieler " + s + ": In welcher Spalte soll ein Stein gesetzt werden? [1:"
						+ Spielbrett.getBreite() + "]");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String str = "";
				System.out.print("> ");
				str = in.readLine();
				spalte = Integer.parseInt(str);
				zeile = a.setzeStein(s, spalte - 1);
				System.out.println("Ist zug m�glich ?"+a.zugNochMoeglich());
				System.out.println("Zeile: " + zeile);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			a.zeichne();
		}
		a.entleeren();
		a.zeichne();
		// System.out.println(a.setzeStein(Spielsteine.WEISS,4));
		// System.out.println(a.setzeStein(Spielsteine.WEISS,4));
		// System.out.println(a.setzeStein(Spielsteine.WEISS,4));

	}

}
