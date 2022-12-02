package src.Uebung4_3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame {

	private JTextArea chatVerlauf = new JTextArea();
	private JScrollPane sp = new JScrollPane(chatVerlauf);
	private JTextField chatEingabe = new JTextField();
	private JButton sendButton = new JButton("Senden");
	private String nachricht;
	private String benutzerName;
	private InetAddress serverAdresse;
	private UdpEchoClient client;

	public ChatGUI() {

		// JOptionPane

		JTextField benutzernameField = new JTextField("finn");
		JTextField serverAdresseField = new JTextField("localhost");

		ImageIcon icon = new ImageIcon("/Users/finnsteffan/eclipse-workspace/Vss/src/Uebung_3/Icon_Finn.jpg");
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		Object[] message = { "Benutzername: ", benutzernameField, "Serveradresse: ", serverAdresseField };

		int option = JOptionPane.showConfirmDialog(null, message, "Verbindungsaufbau", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, icon);

		// Werte von OptionPane speichern
		if (option == JOptionPane.OK_OPTION) {
			setzteBenutzerName(benutzernameField.getText());
			try {
				setzteServerAdresse(InetAddress.getByName(serverAdresseField.getText()));
				client = new UdpEchoClient(benutzerName, serverAdresse, this);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		

		// Fensteraufbau:
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(sp, BorderLayout.CENTER);
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
	

	public String getNachricht() {
		return nachricht;
	}


	public JTextArea getChatVerlauf() {
		return chatVerlauf;
	}


	public InetAddress getServerAdresse() {
		return serverAdresse;
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
			nachricht = m;
		}
	}

	public void setzteBenutzerName(String name) {
		if (name != null && name != "") {
			benutzerName = name;

		}
	}

	public void setzteServerAdresse(InetAddress adresse) {
		if (adresse != null) {
			serverAdresse = adresse;

		}
	}
	
	
	class SendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(getUserInput()!=null||getUserInput()!="") {
				setzteNachricht(getUserInput());
				clearEingabe();
				client.send();
				}
		}
	}

	class EnterListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
				if(getUserInput()!=null||getUserInput()!="") {
				setzteNachricht(getUserInput());
				clearEingabe();
				client.send();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}
}
