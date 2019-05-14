package assignment1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TestBet1 {
	
	private static List<String> all_Words = new ArrayList<>();
	private static List<Integer> counter_Of_Words = new ArrayList<>();
	private static int max_Freq = 0;
	private static String most_Frequent_Word = "";
	private static int longest_Length = 0;
	private static String longest_Word = "";
	private static int nonDistinct_words_Counter = 0;
	
	
	public static void readFile (String fileName){ 
		try { // try read from the file 
		  FileReader fr = new FileReader(fileName);  
		  BufferedReader br = new BufferedReader(fr); 
		  String str = br.readLine(); 
		  StringTokenizer str_Tokenizer = new StringTokenizer(str," ,:;.()-/");
		  nonDistinct_words_Counter += str_Tokenizer.countTokens();
		  while(str_Tokenizer.hasMoreTokens()) {
			  String token = str_Tokenizer.nextToken();
			  token_Processor(token);
		  }
		  for(int i=1; str!=null; i=i+1) { 
			  str = br.readLine(); 
			  if (str != null){ 
				  //System.out.println(i+") "+str); 
				  str_Tokenizer = new StringTokenizer(str," ,:;.()-/");
				  nonDistinct_words_Counter += str_Tokenizer.countTokens();
				  while(str_Tokenizer.hasMoreTokens()) {
					  String token = str_Tokenizer.nextToken();
					  token_Processor(token);
				  }
			  } 
		  	} 
		  	br.close();         
		  	}    
			catch(IOException ex) {   
				System.out.print("Error reading file\n" + ex); 
				System.exit(2);    
				} 
		}
	
	private static void token_Processor(String token){
		if(!all_Words.contains(token)) {
			  all_Words.add(token);
			  counter_Of_Words.add(1);
			  if(token.length() > longest_Length) {
				  longest_Length = token.length();
				  longest_Word = token;
			  }
		  }
		  else {
			  int indx = all_Words.indexOf(token);
			  int counter;
			  counter_Of_Words.set(indx, counter = counter_Of_Words.get(indx)+1);
			  if(max_Freq < counter) {
				  max_Freq = counter;
				  most_Frequent_Word = token;
			  }
		  }
	}
	
	public void reset_Class_Members() {
		all_Words = new ArrayList<>();
		counter_Of_Words = new ArrayList<>();
		max_Freq = 0;
		most_Frequent_Word = "";
		longest_Length = 0;
		longest_Word = "";
		nonDistinct_words_Counter = 0;
	}
	
	
	public static void main(String[] args) {
		readFile("src\\text3.txt");
		System.out.println("The Number of Differnt Words: " + all_Words.size() +
				"\nThe Number of Words: " + nonDistinct_words_Counter +
				"\nThe Most Frequent Word: (" + most_Frequent_Word +
				"), frequenncy: " + max_Freq +
				"\nThe Longest Word: ("+ longest_Word+")"); 
	}
}
