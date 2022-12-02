package src.Uebung4_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpEchoClient{
	private String benutzername;
	private InetAddress serverAdresse;
	private ChatGUI view;
	private final static int PORT = 4711;
	private DatagramSocket serverSocket;

	public UdpEchoClient(String benutzername, InetAddress serverAdresse, ChatGUI view) {
		this.benutzername = benutzername;
		this.serverAdresse = serverAdresse;
		this.view = view;
		DatagramSocket serverSocket;
		try {
			serverSocket = new DatagramSocket();
			serverSocket.setSoTimeout(5000);
			this.serverSocket = serverSocket;
			view.getChatVerlauf().append("Verbindung zu Client mit IP: "+serverAdresse+" hergestellt"+"\n");
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		
	}

	public void send() {	
		String nachricht = view.getNachricht();
		
		try {
				InetAddress ipAdress = view.getServerAdresse();
				DatagramPacket outPacket = new DatagramPacket(nachricht.getBytes(), nachricht.getBytes().length, ipAdress, PORT);
				serverSocket.send(outPacket);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}