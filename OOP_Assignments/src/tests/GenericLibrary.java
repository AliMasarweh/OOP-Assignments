package tests;

public class GenericLibrary{

	public static <T extends Comparable<T>> T theBigger(T a, T b) {
		return ((Comparable<T>) a).compareTo(b) > 0? a:b;
	}
	
	public static <T extends Comparable<T>> T theSmaller(T a, T b) {
		return ((Comparable<T>) a).compareTo(b) < 0? a:b;
	}
	
	
	public static <T extends Comparable<T>> boolean isEqual(T a, T b) {
		return theBigger(a,b) == a && theBigger(b,a) == b;
	}
	
	public static <T extends Comparable<T>> boolean isBigger(T a, T b) {
		return ((Comparable<T>) a).compareTo(b) > 0;
	}
	
	
	public static void main(String[] args) {
		Integer x = 10, y = 20, z = 30, f = 20;
		System.out.println(GenericLibrary.theBigger(x,y));
		System.out.println(GenericLibrary.theBigger(z,y));
		System.out.println(GenericLibrary.theSmaller(z,y));
		System.out.println(GenericLibrary.theSmaller(x,y));
		System.out.println(GenericLibrary.isEqual(x,y));
		System.out.println(GenericLibrary.isEqual(f,y));
	}
}
