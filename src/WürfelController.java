package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WürfelController {
	private WürfelModell modell;
	private WürfelView view;

	public WürfelController(WürfelModell m, WürfelView v) {
		this.modell = m;
		this.view = v;

		view.addWürfListener(new WürfelListener());
		view.addBeendenListener(new BeendenListener());
	}

	class WürfelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			modell.würfel();
		}
	}

	class BeendenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			WürfelController.this.view.dispose();

		}

	}
}
