package src.EchoClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		/*
		 * Server besitzt ein ServerSocket der auf Anfragen
		 * von Clienten wartet...
		 * Sobald er eine Anfrage erhält erzeugt er ein Socket 
		 * um eine Kommunikation zu ermöglichen
		 */
	System.out.println("Waiting for clients...");
	
	try {
		ServerSocket serverSocket = new ServerSocket(7411);
		Socket socket = serverSocket.accept();
		if(socket!=null)
			System.out.println("Connection established");
		/*
		 * Buffered Reader holt sich InputStream vom Socket
		 * liest den String 
		 * und gibt ihn mithilfe des Printwriters zurück
		 */
//		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		String string = in.readLine();
//		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
//		out.println("Server says: "+ string);
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		String val = input.readUTF();
		int number =  input.readInt();
		
		System.out.println("Server received: String:"+val +"und den int: "+number);
		
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
		output.writeUTF(val);
		output.writeInt(number);
		output.close();
		
	} catch (IOException e) {

		e.printStackTrace();
	}

	}

}
