package src.Uebung4_3;

public class Programm {
	public static void main(String[] args) {

		//Server initialisieren
		ChatGUI view = new ChatGUI();
		UdpEchoServer server = new UdpEchoServer(view.getChatVerlauf());
		view.setVisible(true);
		server.laufen();
	}
}
