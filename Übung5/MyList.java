package Übung5;

public class MyList {
	private MyListEl anf;
	private MyListEl ende;

	public MyList() {
		anf = null;
		ende = null;
	}

	public void add(Object item) {
		MyListEl neu = new MyListEl(item);
		if (isEmpty()) {
			anf = neu;
			ende = neu;
		} else {
			ende.setNext(neu);
			ende = neu;
		}
	}

	public boolean isEmpty() {
	if(anf==null && ende == null)
		return true;
	else return false;
	}
	
}