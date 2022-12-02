package src.Loesung4_3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Aufg4_3_EchoClientUDP_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public final static int SERVER_PORT = 4711;
	public final static String PROG_NAME = "EchoClientUDP";
	private JTextArea text;
	private JTextField inputField;
	private JButton sendButton;
	private DatagramSocket socket;
	private DatagramPacket sendPacket;
	private String userID;

	public static void main(String[] args) {
		final String server, user;

		if ((server = (String) JOptionPane.showInputDialog(null, "Please enter your chat server", PROG_NAME,
				JOptionPane.QUESTION_MESSAGE, null, null, "localhost")) == null || "".equals(server))
			return;

		if ((user = (String) JOptionPane.showInputDialog(null, "Please enter your user name", PROG_NAME,
				JOptionPane.QUESTION_MESSAGE, null, null, "smf")) == null || "".equals(user))
			return;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DatagramSocket preparedSocket = null;
				DatagramPacket preparedPacket = new DatagramPacket(new byte[512], 512);
				try {
					preparedSocket = new DatagramSocket();
					preparedPacket.setAddress(InetAddress.getByName(server));
					preparedPacket.setPort(SERVER_PORT);
				} catch (SocketException e) {
					JOptionPane.showMessageDialog(null, "Error creating socket");
					System.exit(-1);
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(null, "Could not resolve host " + server);
					System.exit(-1);
				}
				new Aufg4_3_EchoClientUDP_GUI(server, user, preparedSocket, preparedPacket);
			}
		});
	}

	public Aufg4_3_EchoClientUDP_GUI(String server, String user, DatagramSocket preparedSocket,
			DatagramPacket preparedPacket) {
		super(PROG_NAME + " (" + user + " at " + server + ")");
		userID = user;
		socket = preparedSocket;
		sendPacket = preparedPacket;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(text = new JTextArea(), BorderLayout.CENTER);
		text.setPreferredSize(new Dimension(400, 300));
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.add(inputField = new JTextField(), BorderLayout.CENTER);
		inputField.addActionListener(this);
		bottom.add(sendButton = new JButton("send"), BorderLayout.EAST);
		sendButton.addActionListener(this);
		add(bottom, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		inputField.requestFocus();
		(new EchoClientUDP_Updater()).start();
	}

//handler for actions from inputField and sendButton
	public void actionPerformed(ActionEvent event) {
		String line = inputField.getText();
		if (line.length() > 0)
			line = userID + ": " + line;
		sendPacket.setData(line.getBytes());
		sendPacket.setLength(line.length());
		try {
			socket.send(sendPacket);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "I/O-error while sending packet");
			System.exit(-1);
		}
		inputField.setText("");
		if (line.equals("")) {
			JOptionPane.showMessageDialog(this, "Stopping program after empty line");
			System.exit(0);
		}
	}

//thread to receive packets
	private class EchoClientUDP_Updater extends Thread {
		public void run() {
			DatagramPacket recvPacket = new DatagramPacket(new byte[512], 512);
			while (true) {
				try {
					recvPacket.setLength(512);
					socket.receive(recvPacket);
//JTextArea.append thread safe, no need of SwingUtilities.invokeLater 
					text.append(new String(recvPacket.getData(), 0, recvPacket.getLength()));
					text.append("\n");
				} catch (SocketTimeoutException e) {
					/* no packet */ } catch (IOException e) {
					JOptionPane.showMessageDialog(null, "I/O-error receiving packet");
					System.exit(-1);
				}
			}
		}
	}
}
