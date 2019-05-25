package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class LineCounter extends Thread implements Runnable, Callable<Integer>{
	
	private String fileName;
	private int counter;

	/**
	 * Assigns a file for the LineCounter object
	 * to count the lines utilizing the threads parallelism
	 * @param fileName
	 */
	public LineCounter(String fileName) {
		this.fileName = fileName;
		counter = 0;
	}

	/**
	 * Sums the number of lines in each file that is generated randomly
	 * Stores the summation of lines
	 */
	public void run() {
		File file = new File(fileName); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			while (br.readLine() != null) 
				counter++;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCounter() {
		return counter;
	}

	/**
	 * Sums the number of lines in each file that is generated randomly
	 * @Returns the summation of lines
	 */
	@Override
	public Integer call() throws Exception {
		File file = new File(fileName); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			while (br.readLine() != null) 
				counter++;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Integer(counter);
	}

}