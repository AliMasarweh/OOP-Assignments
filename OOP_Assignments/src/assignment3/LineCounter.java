package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class LineCounter extends Thread implements Runnable, Callable<Integer>{
	
	private String fileName;
	private int counter;

	public LineCounter(String fileName) {
		this.fileName = fileName;
		counter = 0;
	}

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