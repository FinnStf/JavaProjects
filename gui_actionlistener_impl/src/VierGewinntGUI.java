import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VierGewinntGUI extends JFrame {
	private static int hoehe = 6;
	private static int breite = 6;
	private JButton bposition[] = new JButton[breite];
	private JLabel position[][] = new JLabel[hoehe][breite];
	private JLabel spielsteine;
	private Spielbrett spielbrett;
	private Spielsteine aktStein;
	private JButton einwurf;
	private JLabel zug = new JLabel("");
	private JPanel status = new JPanel();

	public VierGewinntGUI() {
		Container c = getContentPane();
		this.setTitle("Vier gewinnt!");
		c.setLayout(new GridLayout(0, 1));
		spielbrett = new Spielbrett(hoehe, breite);
		aktStein = Spielsteine.ROT;
		c.add(status);
		status.add(zug);
		zug.setText("" + aktStein + ": am Zug!");
		zug.setFont(new Font("Stencil", Font.BOLD, 35));
		status.setBackground(Color.GREEN);
		zug.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel buttons = new JPanel();
		c.add(buttons);
		buttons.setLayout(new GridLayout(0, breite));

		for (int i = 0; i < breite; i++) {
			einwurf = new JButton("Einwurf");
			bposition[i] = einwurf;
			einwurf.addActionListener(new MyActionListener(i));
			buttons.add(einwurf);

		}

		for (int j = 0; j < hoehe; j++) {
			JPanel feld = new JPanel();
			GridLayout grid = new GridLayout(0, breite);
			feld.setLayout(grid);

			c.add(feld);

			feld.setBackground(Color.BLUE);
			spielsteine = new JLabel();
			for (int i = 0; i < breite; i++) {
				spielsteine = new JLabel();
				position[j][i] = spielsteine;
				feld.add(spielsteine);
				spielsteine.setIcon(new CircleIcon(Color.WHITE));
				spielsteine.setHorizontalAlignment(SwingConstants.CENTER);

			}
		}

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

	}

	public static void main(String args[]) {
		VierGewinntGUI a = new VierGewinntGUI();
		a.setVisible(true);
		a.setLocationRelativeTo(null);

	}

	class MyActionListener implements ActionListener {
		private int positbreite;

		public MyActionListener(int i) {
			positbreite = i;
		}

		public void actionPerformed(ActionEvent e) {

			int zeile = spielbrett.setzeStein(aktStein, positbreite);
			System.out.println(zeile);
			if (zeile != -1) {
				position[zeile][positbreite].setIcon(aktStein.toIcon());
				aktStein = (aktStein == Spielsteine.GELB) ? Spielsteine.ROT : Spielsteine.GELB;

			}

			if (spielbrett.zugNochMoeglich()) {
				zug.setText("" + aktStein + ": am Zug!");
			} else {
				zug.setText("Unentschieden");
				status.setBackground(Color.PINK);
				for (int i = 0; i < bposition.length; i++) {
					bposition[i].removeActionListener((bposition[i].getActionListeners())[0]);
				}
			}
			if (spielbrett.siegesZug(zeile, positbreite)) {
				aktStein = (aktStein == Spielsteine.GELB) ? Spielsteine.ROT : Spielsteine.GELB;
				zug.setText("Der Sieger ist " + aktStein);
				status.setBackground(aktStein.getColor());
				// for(int i = 0; i < breite;i++) {
				// bposition[i].setEnabled(false);
				//
				// }

				for (int i = 0; i < bposition.length; i++) {
					bposition[i].removeActionListener((bposition[i].getActionListeners())[0]);
				}

			}

		}

	}

}
