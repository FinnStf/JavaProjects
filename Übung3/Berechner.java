package Übung3;

public class Berechner {
	public static void main(String[] args) {
		int [] zahlenfolge = {1,2,3,4,5};
		zahlenfolge = sort(zahlenfolge);
		for(int i=0 ;i<zahlenfolge.length ; i++) {
			System.out.println(zahlenfolge[i]);
		}
	
		
	}	
	public static int[] sort (int[] a){
			for ( int i = 1; i < a.length; i++ ){
			int j = i; while (j > 0){
			if ( a[j-1] > a[j] ){ int tmp = a[j]; a[j] = a[j-1]; a[j-1] = tmp;
			}
			j--; }
			}
			return a; }
}
