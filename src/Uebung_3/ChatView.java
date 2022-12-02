package src.Uebung_3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatView extends JFrame implements IObserver {
	private ChatModel model;
	private ChatController controller;

	private JTextArea chatVerlauf = new JTextArea();
	private JTextField chatEingabe = new JTextField();
	private JButton sendButton = new JButton("Senden");

	public ChatView(ChatModel model) {
		this.model = model;
		this.controller = new ChatController(model, this);
		model.anmelden(this);

		
		
		// JOptionPane
	
		JTextField benutzernameField = new JTextField();
		JTextField serverAdresseField = new JTextField();
		
		
		ImageIcon icon = new ImageIcon("/Users/finnsteffan/eclipse-workspace/Vss/src/Uebung_3/Icon_Finn.jpg");
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(100, 120,  Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg);  // transform it back
		
		Object[] message = {
		    "Benutzername: ", benutzernameField,
		    "Serveradresse: ", serverAdresseField
		};

		int option = JOptionPane.showConfirmDialog(
				null,
				message,
				"Verbindungsaufbau",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				icon);

		//Werte von OptionPane an Model weitergeben
		if(option==JOptionPane.OK_OPTION) {
			model.setzteBenutzerName(benutzernameField.getText());
			model.setzteServerAdresse(serverAdresseField.getText());
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

	@Override
	public void aktualisieren() {
		String s = model.getAktuellenWert();

		if (s != null) {
			anzeigen(s);
		}
	}

	public void addSendListener(ActionListener sl) {
		sendButton.addActionListener(sl);
	}

	public void addEnterListener(KeyListener kl) {
		chatEingabe.addKeyListener(kl);
	}
}
