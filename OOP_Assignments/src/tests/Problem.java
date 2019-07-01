package tests;

public class Problem {  
	// Do either 1 or 2
	// 1. Add static to solve the problem
	/*static*/ String s;
	// 2. Or remove static
	/*static*/ class Inner {   
		void testMethod() {      
			s = "I love Java";   
		}  
	} 
} 
