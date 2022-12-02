package src.Uebung_2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		this.setPreferredSize(new Dimension(1500, 500));
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
