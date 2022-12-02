import java.awt.Color;

public enum Spielsteine {
	GELB('o',new CircleIcon(Color.YELLOW)),ROT('x',new CircleIcon(Color.RED)),LEER(' ',new CircleIcon(Color.WHITE));
	private final char farbe; 
	private final CircleIcon grafik;
	
	
	Spielsteine(char f, CircleIcon g) {
		farbe = f;
		grafik = g;
	}
	public char toChar() {
		return farbe;
	}
	
	public CircleIcon toIcon() {
		return grafik;
	}
	
	public Color getColor() {
		if(this == GELB) {
			return Color.YELLOW;
		}
		else return Color.RED;
	}

}
