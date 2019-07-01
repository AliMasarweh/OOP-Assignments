package tests;

import java.util.Arrays;

public class Q5_2_2 {
	public static void main(String[] args) {
		char[] a1 = {'a','b','c','d'}, a2 = {'x', 'y'};         
		MyString str1 = new MyString(a1) ;         
		MyString str2 = new MyString(a2) ;         
		System.out.println("Before concat str1: " + str1);         
		str1.concat(str2);// change (i.e. mutate) the element         
		System.out.println("After concat str1: " +str1);         //////////         
		MyString str3 = str1.concat(str2);         
		System.out.println("str3: " +str3);         //////         
		str3 = str1.remove(1);         
		System.out.println("str3: " +str3);         
		System.out.println("str1: " +str1);     
	}
}

class MyString{
	private char[] str;
	public MyString(char[] a1) {
		str = new char[a1.length];
		for (int i = 0; i < a1.length; i++) {
			str[i] = a1[i];
		}
	}

	public MyString remove(int i) {
		char[] tmp = new char[str.length-1];
		for (int j = 0; j < tmp.length; j++) {
			if(j<i)
				tmp[j] = str[j];
			else
				tmp[j] = str[j+1];
		}
		return new MyString(tmp);
	}

	public MyString concat(MyString str2) {
		char[] tmp = new char[str.length + str2.str.length];
		for (int j = 0; j < tmp.length; j++) {
			if(j < str.length)
				tmp[j] = str[j];
			else
				tmp[j] = str2.str[j-str.length];
		}
		return new MyString(tmp);
		
	}
	
	public String toString() {
		return Arrays.toString(str);
	}
	
}
