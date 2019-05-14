package assignment1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class TestBet {
	
	private static Map<String,Integer> words_To_Counter = new HashMap<>((int)(52844/.75) +1);
	private static int max_Freq = 0;
	private static String most_Frequent_Word = "";
	private static int longest_Length = 0;
	private static String longest_Word = "";
	private static int nonDistinct_words_Counter = 0;
	
	/**
	 * Reads the file and processes the vocabulary in the text, 
	 * by mapping each word to it's frequency , saves the most frequent word and its frequency,
	 * count the number of non/distinct words and saves the longest word.
	 * @param fileName
	 */
	public static void readFile (String fileName){ 
		try { // try read from the file 
			FileReader fr = new FileReader(fileName);  
			BufferedReader br = new BufferedReader(fr); 
			StringTokenizer str_Tokenizer = null;
			String str = new String(); 
			for(int i=0; str!=null; i=i+1) { 
				str = br.readLine(); 
				if (str != null){ 
					str_Tokenizer = new StringTokenizer(str," ,:;.()-/");
					  nonDistinct_words_Counter += str_Tokenizer.countTokens();
					  while(str_Tokenizer.hasMoreTokens()) {
						  String token = str_Tokenizer.nextToken();
						  token_Processor(token);
					  }
					} 
				} 
			br.close();         
			}    catch(IOException ex) {   
				System.out.print("Error reading file\n" + ex); 
				System.exit(2);    
				} 
		}
	/**
	 * Process the token's frequency and length of the current text.
	 * @param token
	 */
	private static void token_Processor(String token) {
		//If the word isn't read before, map to 1
		if(!words_To_Counter.containsKey(token)) {
			words_To_Counter.put(token,1);
			if(token.length() > longest_Length) {
				  longest_Length = token.length();
				  longest_Word = token;
			  }
		  }
		//If the word is read before => counter++
		  else {
			  int counter = words_To_Counter.remove(token)+1;
			  words_To_Counter.put(token, counter);
			  if(max_Freq < counter) {
				  max_Freq = counter;
				  most_Frequent_Word = token;
			  }
		  }
	}
	/**
	 * Same as token_Processor but using lambda.
	 * @param token
	 */
	private static void token_Processor_Lambda(String token) {
		words_To_Counter.computeIfPresent(token, (k,v) -> v+1);
		words_To_Counter.computeIfAbsent(token, k -> 1);
		if(token.length() > longest_Length) {
			  longest_Length = token.length();
			  longest_Word = token;
		  }
		if(max_Freq < words_To_Counter.get(token)) {
			  max_Freq = words_To_Counter.get(token);
			  most_Frequent_Word = token;
		  }
	}
	
	public void reset_Class_Members() {
		words_To_Counter = new HashMap<>((int)(52844/.75) +1);
		max_Freq = 0;
		most_Frequent_Word = "";
		longest_Length = 0;
		longest_Word = "";
		nonDistinct_words_Counter = 0;
	}
	public static void main(String[] args) {
		readFile("src\\text3.txt");
		System.out.println("The Number of Differnt Words: " + words_To_Counter.size() +
				"\nThe Number of Words: " + nonDistinct_words_Counter +
				"\nThe Most Frequent Word: (" + most_Frequent_Word +
				"), frequenncy: " + max_Freq +
				"\nThe Longest Word: ("+ longest_Word+")"); 
		}
	}
