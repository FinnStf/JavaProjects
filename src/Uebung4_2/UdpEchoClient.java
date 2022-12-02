package src.Uebung4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpEchoClient {
	private static String nachricht;
	private final static int port = 4711;

	public static void main(String[] args) {

		System.out.println("Geben Sie eine Nachricht ein: ");
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			clientSocket.setSoTimeout(50);

			while (true) {

				nachricht = userInput.readLine();
				byte[] buffer = nachricht.getBytes();

				InetAddress ipAdress = InetAddress.getLocalHost();
				DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, ipAdress, port);
				clientSocket.send(outPacket);

				byte[] buffer2 = new byte[512];
				DatagramPacket inPacket = new DatagramPacket(buffer2, buffer2.length);
				clientSocket.receive(inPacket);
				String nachricht = new String(inPacket.getData());
				System.out.println(inPacket.getAddress() + " : " + nachricht);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}