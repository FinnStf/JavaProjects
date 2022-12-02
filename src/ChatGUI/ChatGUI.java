package src.ChatGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.DatagramPacket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame {

	private JTextArea chatVerlauf = new JTextArea();
	private JTextField chatEingabe = new JTextField();
	private JButton sendButton = new JButton("Senden");
	private String nachricht;
	private String benutzerName;
	private String serverAdresse;

	public ChatGUI() {

		// JOptionPane

		JTextField benutzernameField = new JTextField();
		JTextField serverAdresseField = new JTextField();

		ImageIcon icon = new ImageIcon("/Users/finnsteffan/eclipse-workspace/Vss/src/Uebung_3/Icon_Finn.jpg");
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(100, 120, Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg); // transform it back

		Object[] message = { "Benutzername: ", benutzernameField, "Serveradresse: ", serverAdresseField };

		int option = JOptionPane.showConfirmDialog(null, message, "Verbindungsaufbau", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, icon);

		// Werte von OptionPane speichern
		if (option == JOptionPane.OK_OPTION) {
			setzteBenutzerName(benutzernameField.getText());
			setzteServerAdresse(serverAdresseField.getText());
		}

		// Fensteraufbau:
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(chatVerlauf, BorderLayout.CENTER);
		JPanel chatFuss = new JPanel();
		chatFuss.setLayout(new BorderLayout());
		chatFuss.add(chatEingabe, BorderLayout.CENTER);
		chatFuss.add(sendButton, BorderLayout.EAST);
		c.add(chatFuss, BorderLayout.SOUTH);
		chatVerlauf.setEditable(false);
		
		sendButton.addActionListener(new SendListener());
		chatEingabe.addKeyListener(new EnterListener());

		this.setPreferredSize(new Dimension(1000, 500));
		this.pack();

		this.setTitle("Chat-Client");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void anzeigen(String message) {
		chatVerlauf.append(message);
	}

	public void clearEingabe() {
		chatEingabe.setText("");
	}

	public String getUserInput() {
		return chatEingabe.getText();
	}

	public void setzteNachricht(String m) {
		if (m != null && m != "") {
			nachricht = m + "\n";
			chatVerlauf.append(nachricht);
		}
	}

	public void setzteBenutzerName(String name) {
		if (name != null && name != "") {
			benutzerName = name;

		}
	}

	public void setzteServerAdresse(String adresse) {
		if (adresse != null && adresse != "") {
			serverAdresse = adresse;

		}
	}
	
	
	class SendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setzteNachricht(getUserInput());
			clearEingabe();
		}
	}

	class EnterListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
				setzteNachricht(getUserInput());
				clearEingabe();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}
}
