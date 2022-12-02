package src.Uebung4_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpEchoClient {
	private final static int PORT = 4711;

	public static void main(String[] args) {
		try {
			Socket server = new Socket(InetAddress.getLocalHost(),PORT);
			
			//Handle Input und Output Streams
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter output = new PrintWriter(server.getOutputStream(),true);
			String line;
			System.out.println("Geben Sie einen Satz ein...(Bei leerer Eingabe wird Verbindung gestoppt)");
			while((line = input.readLine())!=null) {
				output.println(line);
			}
			if(server != null) {
				server.close();
				System.out.println("ServerSocket geschlossen ____ Verbindung beendet");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}




	}
}