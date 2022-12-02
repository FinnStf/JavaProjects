import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

class CircleIcon implements Icon {
	private Color farbe;
	public CircleIcon(Color g) {
		farbe = g;
	}
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(farbe);
		g.fillOval(x, y, getIconWidth(), getIconHeight());
	}

	public int getIconWidth() {
		return 50;
	}

	public int getIconHeight() {
		return 50;
	}
}