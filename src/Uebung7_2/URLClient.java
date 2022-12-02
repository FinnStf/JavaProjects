package src.Uebung7_2;

import java.io.*;
import java.net.*;

public class URLClient {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.tombleek.de/");
			URLConnection urlcon = url.openConnection();
			BufferedReader input  = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
