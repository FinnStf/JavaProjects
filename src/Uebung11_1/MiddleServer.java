package src.Uebung11_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class MiddleServer extends Thread {

	private static final int PROXYPORT = 4711;
	private static final int SERVERPORT = 4713;
	private static final String SERVERSDRESSE = "localhost";

	public static void main(String[] args) {

		try {
			ServerSocket proxy = new ServerSocket(PROXYPORT);
			proxy.setSoTimeout(1000);
			System.out.println("ProxyServer online on port: " + PROXYPORT + "wating for clients...");
			int cnt = 0;

			Socket client = proxy.accept();
			System.out.println("Got connection from " + client.getInetAddress() + " at port: " + client.getLocalPort());

			Socket clientServer = new Socket(SERVERSDRESSE, SERVERPORT);
			(new MiddleServer(cnt++, client, clientServer)).start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int threadNumber;
	private Socket socket1, socket2;

	private MiddleServer(int number, Socket s1, Socket s2) {
		threadNumber = number;
		socket1 = s1;
		socket2 = s2;
	}

	public void run() {
		
	}
}