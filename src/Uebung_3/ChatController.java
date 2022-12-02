package src.Uebung_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatController {
	private ChatModel model;
	private ChatView view;

	public ChatController(ChatModel model, ChatView view) {
		this.model = model;
		this.view = view;

		view.addSendListener(new SendListener());
		view.addEnterListener(new EnterListener());
	}

	class SendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setzteNachricht(view.getUserInput());
			view.clearEingabe();
		}
	}

	class EnterListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==(KeyEvent.VK_ENTER)) {
				model.setzteNachricht(view.getUserInput());
				view.clearEingabe();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
