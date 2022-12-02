package src.Uebung_3;

public class ChatModel extends Subject {
	private String nachricht;

	private String benutzerName;
	private String serverAdresse;

	public ChatModel() {
		// clearChatModel
	}

	public String getAktuellenWert() {
		if (nachricht != null)
			return nachricht;
		else
			return null;
	}

	public void reset() {
		nachricht = null;
		benachrichtigen();
	}

	public void setzteNachricht(String m) {
		if (m != null && m != "") {
			nachricht = m + "\n";
			benachrichtigen();
		}
	}

	public void setzteBenutzerName(String name) {
		if (name != null && name != "") {
			benutzerName = name;
			benachrichtigen();
		}
	}

	public void setzteServerAdresse(String adresse) {
		if (adresse != null && adresse != "") {
			serverAdresse = adresse;
			benachrichtigen();
		}
	}
}
