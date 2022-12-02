package src.Uebung7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpEchoClient {
	private final static int SERVERPORT = 80;
	private final static String SERVERADRESS = "www.tombleek.de";

	public static void main(String[] args) {
		try {
			Socket server = new Socket();
			server.connect(new InetSocketAddress(SERVERADRESS, SERVERPORT), 1000);

			// Handle Input und Output Streams
			BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintWriter output = new PrintWriter(server.getOutputStream(), true);

			System.out.println("Connected to ----- " + server.getInetAddress().getHostAddress() + " at port "
					+ server.getPort() + " with Local Port: " + server.getLocalPort());
			/*
			 * send query
			 * with "\r\n" instead of "\n"
			 */
			output.print("GET / HTTP/1.1\r\n");
			output.print("Host: " + SERVERADRESS + "\r\n");
			output.print("\r\n");
			output.flush();
			
//			output.println("GET / HTTP/1.1");
//			output.println("Host: "+SERVERADRESS);
//			output.println("");
//			output.flush();
			
			server.shutdownOutput();

			System.out.println("--- Query sent, reply:"); // loop until shutdown
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			if (server != null) {
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