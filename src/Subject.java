package src;

import java.util.ArrayList;

abstract class Subject {
	private ArrayList<IObserver> observer;
	
	
	public Subject() {
		observer = new ArrayList<>();
}
	public void anmelden(IObserver o) {
		observer.add(o);
	}
	public void abmelden(IObserver o) {
		observer.remove(o);
	}
	public void benachrichtigen() {
		for(IObserver obs: observer) {
			obs.aktualisieren();
		}
	}
}