package src.Uebung4_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpEchoServer {
	private final static int port = 4711;
	private static byte[] buf = new byte[512];

	public static void main(String[] args) {

		try {
			DatagramSocket serverSocket = new DatagramSocket(port);
			DatagramPacket inPacket = new DatagramPacket(buf, buf.length);
			System.out.println("Started Server on ___________ "+ serverSocket.getLocalPort());
			serverSocket.receive(inPacket);
			serverSocket.send(inPacket);
			serverSocket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}