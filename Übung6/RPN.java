package Übung6;

import java.util.Arrays;

public class RPN {
	    
	   int rpn(String ausdruck[]) {
	      int[] stack = new int[ausdruck.length / 2 + 1];
	      int   top   = -1;
	      
	      int readPos  = 0;
	      int resultat;
	      while(readPos < ausdruck.length) {
	          String operant = ausdruck[readPos];
	          if("+".equals(operant)) {
	            resultat = stack[top - 1] + stack[top];
	            top = top - 1;
	            stack[top] = resultat;
	          } else
	          if("*".equals(operant)) {
	            resultat = stack[top - 1] * stack[top];
	            top = top - 1;
	            stack[top] = resultat;
	          } else
	          if("-".equals(operant)) {
	            resultat = stack[top - 1] - stack[top];
	            top = top - 1;
	            stack[top] = resultat;
	          } else
	          if("/".equals(operant)) {
	            resultat = stack[top - 1] / stack[top];
	            top = top - 1;
	            stack[top] = resultat;
	          }
	          else { // Kein Opreator
	            top = top + 1;
	            stack[top] = Integer.parseInt(operant);
	          }
	          readPos = readPos + 1;
	      }
	      
	      return stack[0];
	   }
	   
	   public static void main(String[] args) {
	    new RPN().tests();
	  }
	   
	  void tests() {
	     test(13, new String[] {"11", "2", "+"}); 
	     test(22, new String[] {"11", "2", "*"});
	     test(77, new String[] {"3", "4", "+", "11", "*"});  
	     test( 2, new String[] {"22", "13", "2", "-", "/"});
	  }
	  
	  void test(int erwartet, String[] ausdruck) {
	     int ist = rpn(ausdruck);
	     System.out.println(Arrays.toString(ausdruck) + " liefert " + ist + " ...");
	     if(ist == erwartet) {
	         System.out.println(" ... ok.");
	     } else {
	         System.out.println(" ... FEHLER!");
	         
	     }
	  }
	}
