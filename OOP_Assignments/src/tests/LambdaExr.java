package tests;

interface InterfaceCalcultion {  
	public double calculate(double x, double y);
} 


public class LambdaExr {
	@SuppressWarnings("unused")
	public static void main(String[] args) { //  Anonymous    
		InterfaceCalcultion ip = new InterfaceCalcultion() { 
			public double calculate(double x, double y) {     
				return Math.sqrt(x*x + y*y);    
				}   
		};
		InterfaceCalcultion ip2 = (double x, double y) -> Math.sqrt(x*x + y*y);
		double a = ip2.calculate(3, 4);
		System.out.println("a = " + a); 
		
		System.out.println("=== RunnableTest ===");   // Anonymous Runnable   
		Runnable r1 = new Runnable() {    
			@Override    
			public void run() {     
				System.out.println("Hello World!");    
			}   
		};   
		Runnable r2 = () -> System.out.println("Hello World!");
		new Thread(r2).start(); 
	} 
}
