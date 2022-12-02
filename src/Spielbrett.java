package src;

public class Spielbrett {
	private final int hoehe;
	private static int breite;
	private static Spielsteine[][] spielfeld;


	public Spielbrett(int h, int b) {

		hoehe = h;
		breite = b;
		spielfeld = new Spielsteine[hoehe][breite];
		entleeren();
	}

	public void entleeren() {
		for (int i = 0; i < hoehe; i++) {
			for (int j = 0; j < breite; j++) {
				spielfeld[i][j] = Spielsteine.LEER;
			}
		}
	}

	public void zeichne() {
		for (int i = 0; i < breite * 2 + 1; i++) {
			if (i % 2 != 1) {
				System.out.print("+");
			} else {
				System.out.print("-");
			}
		}
		System.out.println();
		for (int i = 0; i < hoehe; i++) {
			for (int j = 0; j < breite; j++) {
				System.out.print("|");
				System.out.print(spielfeld[i][j].toChar());
			}
			System.out.print("|");
			System.out.println();
			for (int f = 0; f < breite * 2 + 1; f++) {
				if (f % 2 != 1) {
					System.out.print("+");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}

		for (int i = 1; i <= breite; i++) {
			if (i < 10) {
				System.out.print(" " + i + "");
			}
			if (i == 10) {
				System.out.print(" " + i);
			} else if (i > 10) {
				System.out.print("" + i);
			}
		}
		System.out.println();
	}

//	public void setStein(int x,int y, Spielsteine n) {
//		for (int i = 0; i < hoehe; i++) {
//			for (int j = 0; j < breite; j++) {
//				if(i == x && j == y) {
//					spielfeld[i][j] = n;
//				}
//			}
//		}
//		
//		
//	}

	public int setzeStein(Spielsteine stein, int spalte) {
		int zeilennummer = hoehe - 1;
		for (int i = hoehe - 1; i >= 0; i--) {
			for (int j = 0; j < breite; j++) {
				if (spielfeld[i][j] == Spielsteine.LEER && j == spalte) {
					spielfeld[i][j] = stein;
					return zeilennummer;
				}

			}
			zeilennummer--;
			if (zeilennummer < 0) {
				System.out.println("Kann nicht mehr gesetzt werden");
				return -1;
			}
		}

		return zeilennummer;
	}

	public static int getBreite() {
		return breite;
	}
//
//	public boolean zugNochMoeglich() {
//		boolean zug = true;
//		for(int i = hoehe-1; i >=0;i-- ) {
//			for(int j = 0; j < breite; j++) {
//				if(spielfeld[i][j].toChar() == Spielsteine.LEER.toChar()) {
//					return true;
//				}
//				else zug = false;
//			}
//		}
//		return zug;
//			
//		}

	public boolean zugNochMoeglich() {
		for (int i = 0; i < breite; i++) {
			if (spielfeld[0][i] == Spielsteine.LEER) { // pr�fen ob in der obersten zeile noch ein stein setzbar ist,
														// wenn ja dan setzte , wenn nein nicht, weil nur ganz oben kann								// man keine setzen(deswegen hoehe = 0)
				return true;
			}
		}
		return false;
	}

	public boolean siegesZug(int zeile, int spalte) {
		Spielsteine s = spielfeld[zeile][spalte];
		int sum = 0;
		//Horizontal
		sum = zaehleSteineInRichtung(zeile,spalte,1,0,s)+zaehleSteineInRichtung(zeile,spalte,-1,0,s);
		if(sum > 4) return true;
		
		//vertikal
		sum = zaehleSteineInRichtung(zeile,spalte,0,1,s)+zaehleSteineInRichtung(zeile,spalte,0,-1,s);
		if(sum > 4) return true;
		//Diagonal l n r
	
		sum = zaehleSteineInRichtung(zeile,spalte+1,1,-1,s)+zaehleSteineInRichtung(zeile,spalte,-1,1,s);
		if(sum > 4) return true;
		
		//Diagonal r n l
		sum += zaehleSteineInRichtung(zeile,spalte+1,1,1,s)+zaehleSteineInRichtung(zeile,spalte,-1,-1,s);
		if(sum > 4) return true;
		
		else return false;
	}

	public int zaehleSteineInRichtung(int posY, int posX, int dx,int dy, Spielsteine stein) {
		if(posY >hoehe -1 || posY < 0 || posX >= breite  || posX < 0 || !spielfeld[posY][posX].equals(stein)) {
			return 0;
		}
		else {
			return 1+zaehleSteineInRichtung(posY+dy,posX+dx,dx,dy,stein);
		}
		//iterativ
//		int sum=0;
//		while(posY < hoehe && posY < 0 && posX < breite  && posX < 0 && !spielfeld[posY][posX].equals(stein)){
//			sum++;
//			posX +=dx;
//			posY+=dy;
//		}
//		return sum;
	}
	
}
