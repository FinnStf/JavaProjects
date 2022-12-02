package src.Uebung_3;

public class Programm {
	public static void main(String[] args) {

		ChatModel model = new ChatModel();
		ChatView view = new ChatView(model);
		view.setVisible(true);
	}
}
