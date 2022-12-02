package Übung3;

public class Rekursion {
	public static void main(String[] args) {
	try {
		System.out.println(rek(20));
	} catch (Exception e) {
		e.printStackTrace();
	}

	}

	public static long rek( int n ) throws Exception {
		if(n<1) {
			throw new IllegalArgumentException("Falsche Eingabe : muss größer 0 sein");
		}
		if(n==1)
		return 1;
		else if(n==2)
			return 2;
		else{
			return n*rek(n-1)-rek(n-2);}
		}
	
}