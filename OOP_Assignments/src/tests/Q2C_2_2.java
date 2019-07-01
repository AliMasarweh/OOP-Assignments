package tests;

public class Q2C_2_2 {
	
	public static int x = 7;   
	public int y = 3;         
	@SuppressWarnings("static-access")
	public static void main(String[] args) {   
		Q2C_2_2 a = new Q2C_2_2();   
		Q2C_2_2 b = new Q2C_2_2();   
		a.y = 5;   
		b.y = 6;   
		a.x = 1;   
		b.x = 2;   
		System.out.println("a.y = " + a.y);   
		System.out.println("b.y = " + b.y);   
		System.out.println("a.x = " + a.x);   
		System.out.println("b.x = " + b.x);   
		System.out.println("Q2.x = " + Q2C_2_2.x);  
		}
	
	/*public static void main(String[] args) {   
		ArrayList<Integer> p = new ArrayList<>();   
		p.add(7);   
		p.add(1);   
		p.add(5);   
		p.add(1);   
		p.remove(1);   
		System.out.println(p);  //[7, 5, 1]
		}*/
}