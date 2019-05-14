package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class LineCounter extends Thread implements Runnable, Callable<IntULong>{
	
	private String fileName;
	private int counter;
	private long time;

	public LineCounter(String fileName) {
		this.fileName = fileName;
		counter = 0;
		time = 0;
	}

	public void run() {
		long startingTime = System.currentTimeMillis();
		File file = new File(fileName); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			while (br.readLine() != null) 
				counter++;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		time = (System.currentTimeMillis()-startingTime);
		//System.out.println("Lines: "+ counter +" ,Time: "+ time);
	}

	public int getCounter() {
		return counter;
	}
	
	public long getTime() {
		return time;
	}

	@Override
	public IntULong call() throws Exception {
		long startingTime = System.currentTimeMillis();
		File file = new File(fileName); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			while (br.readLine() != null) 
				counter++;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		time = (System.currentTimeMillis()-startingTime);
		return new IntULong(counter, time);
	}

}

class IntULong{
	private int counter;
	private long time;
	
	IntULong(int counter, long time){
		this.counter = counter;
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
}
