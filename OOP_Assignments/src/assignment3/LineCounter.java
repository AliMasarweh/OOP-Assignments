package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter extends Thread {
	
	private String fileName;
	private int counter;
	private long time;

	public LineCounter(String fileName) {
		this.fileName = fileName;
		counter = 0;
		time = 0;
	}

	public void run() {
		long startingTime = System.nanoTime();
		File file = new File(fileName); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			while (br.readLine() != null) 
				counter++;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		time = (System.nanoTime()-startingTime);
		//System.out.println("Lines: "+ counter +" ,Time: "+ time);
	}

	public int getCounter() {
		return counter;
	}
	
	public long getTime() {
		return time;
	}

}
