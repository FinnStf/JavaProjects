package Uebung10;

import java.util.Arrays;

public class Calculator {
	
	public static int euklid(int a, int b) {
		if(b == 0) return a;
		else return euklid(b, a%b);
	}
	
	public static int getMultInver(int x, int n) {
		int counter = 0;
		int erg = 0;
		boolean flag = true;
		if(euklid(x,n)!= 1||x<=2) {
			return -1;
		}
		while(flag) {
			erg = (x*counter)%n;
			counter++;
			if(erg==1) {
				flag = false;
			}
		}return --counter;
	}
	
	public static int[] erwEuklid(int a, int b) {
		if(b == 0) return new int[]{a, 1, 0};
		else {
			int[] erg = new int[3];
			erg = erwEuklid(b,a%b);
			return new int[] {erg[0],erg[2],erg[1]-(erg[2]*(a/b))};
		}
	}
	
	public static void main(String[]args) {
		int x = 16;
		int n = 29;
		int erg = getMultInver(x, n);
		System.out.println("Das multiplikativ Inverse von ["+x+"]"+n+" = "+ erg);
		int[] erg2 = new int[3];
		erg2 = erwEuklid(x, n);
		System.out.println("Erweiterter Euklidischer Alg. von ["+x+"]"+n+" = "+ Arrays.toString(erg2));
		System.out.println("ggT("+x+","+n+") = "+erg2[0]+" = "+ erg2[1]+" * "+x+" + "+erg2[2]+" * "+n);
	}
}
