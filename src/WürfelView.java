package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WürfelView extends JFrame implements IObserver {
	private JLabel würfelJL = new JLabel("",SwingConstants.CENTER);
	private JButton würfelJB = new JButton("würfeln");
	private JButton beendenJB = new JButton("beenden");
	
	private WürfelModell modell;
	private WürfelController controller;
	
	public WürfelView(WürfelModell model) {
		this.modell = model;
		controller = new WürfelController(model, this);
		model.anmelden(this);
		
		
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(würfelJL, BorderLayout.CENTER);
		würfelJL.setPreferredSize(new Dimension(300, 300));
		JPanel panel = new JPanel(new FlowLayout());
		container.add(panel, BorderLayout.SOUTH);
		panel.add(würfelJB);
		panel.add(beendenJB);

		pack();
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("WürfelFenster");
	}

	public void anzeigen(int zahl) {
		würfelJL.setText(""+zahl);
	}
	public void aktualisieren(){
		anzeigen(modell.getAktuellerWert());
	}
	public void addWürfListener(ActionListener a) {
		würfelJB.addActionListener(a);
	}
	public void addBeendenListener(ActionListener a) {
		beendenJB.addActionListener(a);
	}
}
