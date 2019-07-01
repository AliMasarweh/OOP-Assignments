package tests;

class A {  
	int a;  
	public A(int a){   
		this.a = a;   
		System.out.println("constructor A 1");  
	}  
	public A(){   
		System.out.println("constructor A 2");  
	} 
} 

class B extends A{
	int b;     
	public B(int b){   
		System.out.println("constructor B");      
		this.b = b;     
	} 
} 

public class TYPE{  
	public static void main(String[] args) {   
		System.out.println(new B(1).a);  
	}  
} 