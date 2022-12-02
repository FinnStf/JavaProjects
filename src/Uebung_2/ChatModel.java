package src.Uebung_2;

public class ChatModel extends Subject{
	private String nachricht;

	
	public ChatModel(){
		//clearChatModel
	}
	
	public String getAktuellenWert() {
		if(nachricht!=null)
			return nachricht;
		else return null;
	}
	
	public void reset() {
		nachricht = null;
		benachrichtigen();
	}
	
	public void setzteWert(String m) {
		if(m!=null&&m!="") {
		nachricht = m+"\n";
		benachrichtigen();
		}
	}
}
