package Übung5;

public class MyListEl {

	private Object item;
	private MyListEl next;

	public MyListEl(Object item) {
		this.item = item;
		this.next = null;
	}

	protected void setNext(MyListEl next) {
		this.next = next;
	}

	protected MyListEl getNext() {
		return next;
	}

	protected Object getItem() {
		return item;
	}
}
