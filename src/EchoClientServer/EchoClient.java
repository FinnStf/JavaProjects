package src.EchoClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) {
		
		String s = "Das ist ein String";
		int n = 12345;

		System.out.println("client started");

		try {
			Socket sock = new Socket("localhost",7411);
			/*
			 * Buffered Reader nimmt dem InputStream vom System(Rechner-->Client)
			 */
//			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//			System.out.println("Enter a String: ");
//			String str = userInput.readLine();
			
			ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
			output.writeUTF(s);
			output.writeInt(n);
			output.flush();
			
			ObjectInputStream input = new ObjectInputStream(sock.getInputStream());
			String val = input.readUTF();
			int number =  input.readInt();
			System.out.println("Client receiverd: "+val + ", "+number );
			/*
			 * OutputStream(über Printwriter) wird an den Socket gegeben und Autoflush aktiviert
			 */
//			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
//			out.println(str);
			/*
			 * Neuer BufferedReader liest Antwort vom Server
			 */
//			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//			System.out.println(in.readLine());
		
			sock.close();
		
		} catch (UnknownHostException e) {
			System.out.println("Unkown Host");
			e.getMessage();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	} 

}
