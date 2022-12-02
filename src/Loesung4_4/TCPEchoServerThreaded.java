package src.Loesung4_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPEchoServerThreaded extends Thread {
	public final static int PORT = 4711;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			serverSocket.setSoTimeout(1000);

			System.out.println("---Startet threated server on port " + serverSocket.getLocalPort()
					+ " , waiting (end with new line)");

			int cnt = 0;
			while (System.in.available() == 0) {
				try {
					Socket client = serverSocket.accept();
					System.out.println("--- Got connection from " + client.getInetAddress() + " at port "
							+ client.getPort() + ", starting new Thread");
					(new TCPEchoServerThreaded(cnt++, client)).start();
				} catch (SocketTimeoutException e) {
					/* just check again */ }
			}
			while (System.in.available() > 0)
				System.in.read(); // clean up
		} catch (IOException e) {
			System.err.println("there was an exception in main: " + e.getMessage());
		}
		if (serverSocket != null) {
			try {
				// close the server socket
				serverSocket.close();
				System.out.println("--- Server stopped");
			} catch (IOException e) {
				/* can't do anything now */ }
		}
	}

	// thread to answer clients
	private int threadNumber;
	private Socket localClient;

	private TCPEchoServerThreaded(int number, Socket client) {
		threadNumber = number;
		localClient = client;
	}

	public void run() {
		try {
			// get handles to input and output streams
			BufferedReader in = new BufferedReader(new InputStreamReader(localClient.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(localClient.getOutputStream()));
			// send a welcome and every received line
			System.out.println("--- Started thread " + threadNumber);
			out.write("Welcome to EchoServerThreaded in thread " + threadNumber);
			out.newLine();
			out.flush();
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println("--- " + threadNumber + " Received: " + line);
				out.write(line);
				out.newLine();
				out.flush();
			}
			// client has closed his side, close the client connection socket
			System.out.println("--- " + threadNumber + " Client shutdown");
			localClient.close();
		} catch (IOException e) {
			System.err.println("server thread " + threadNumber + ": " + e.getMessage());
		}
	}
}
