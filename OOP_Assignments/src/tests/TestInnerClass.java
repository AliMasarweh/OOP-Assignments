package tests;

class Foo {     
	class Bar{       
		public Bar() {       
			System.out.println("Inner class Bar");      
		}     
	} 
} 

public class TestInnerClass {     
	@SuppressWarnings("unused")
	public static void main (String [] args) {         
		Foo f = new Foo();
		//Foo.Bar b = new Foo.Bar(); 
		Foo.Bar b2 = f.new Bar();
		//Bar b3 = new f.Bar();
		//Bar b4 = f.new Bar();
		String s1="I am Java Expert"; 
		String s2="I am C Expert"; 
		String s3="I am Java Expert";
		int c = 0;
		if(s1 instanceof Object)
			c++;
		if(s2 instanceof Object)
			c++;
		if(s2 instanceof Object)
			c++;
		
		System.out.println(c);
	} 
}   