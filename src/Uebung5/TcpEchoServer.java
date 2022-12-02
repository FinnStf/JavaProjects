package src.Uebung5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class TcpEchoServer extends Thread{
	private final static int PORT = 4711;
	private static ServerSocket server;
	private int count;
	private Socket clientSocket;
	
	TcpEchoServer(int count, Socket clientSocket){
		this.count = count;
		this.clientSocket = clientSocket;
	}
	public void run() {
		//handle Input and Output
		System.out.println("Client-Thread "+ count +" gestartet ");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);
			String line;
			while((line = input.readLine())!=null) {
				output.println(line);
				System.out.println("Client: "+line);
			}
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server online on port: "+server.getLocalPort());
			int count = 0;
			while(System.in.available() == 0) {
				Socket client = server.accept();
				System.out.println("Verbindung zu Client: "+client.getInetAddress()+" hergestellt");
				(new TcpEchoServer(count++, client)).start();;
			}
			while (System.in.available() > 0)
				System.in.read(); // clean up
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (server != null) {
			try {
				// close the server socket
				server.close();
				System.out.println("--- Server stopped");
			} catch (IOException e) {
				/* can't do anything now */ }
		}
	}
}