package src.Uebung4_3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JTextArea;

public class UdpEchoServer{
	private final static int PORT = 4711;
	private static byte[] buf = new byte[512];
	private JTextArea chatVerlauf;
	
	public UdpEchoServer(JTextArea chatVerlauf) {
		this.chatVerlauf = chatVerlauf;
	}

	public void laufen() {

		try {
			
			DatagramSocket serverSocket = new DatagramSocket(PORT);
			DatagramPacket inPacket = new DatagramPacket(buf, buf.length);
			chatVerlauf.append("Started Server on .................. "+ serverSocket.getLocalPort()+"\n");
			
			while(true) {
				serverSocket.receive(inPacket);	
				String nachricht = new String(inPacket.getData());
				chatVerlauf.append("Server sagt: "+nachricht+"\n");
			}
		
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}