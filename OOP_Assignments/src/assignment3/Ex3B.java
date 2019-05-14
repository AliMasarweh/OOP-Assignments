package assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex3B {
	public static String[] createFiles(int n) {
		/* **Preparing Name Format & Random Series ** */
		String fileName = "File_%s.txt";
		String[] ans = new String[n];
		Random rnd = new Random(123);

		for (int i = 1; i <= n; i++) {
			/* **Preparing Name & Text ** */
			ans[i - 1] = String.format(fileName, i);
			String toWrite = "";
			int lines = rnd.nextInt(1000);
			for (int j = 0; j < lines; j++) {

				toWrite += "Hello World\n";
			}
			File file = new File(ans[i - 1]);
			/* **File Creation ** */
			try {
				file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
				writer.write(toWrite);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return ans;
	}

	public static void deleteFiles(String[] fileNames) {
		for (String fileName : fileNames)
			new File(fileName).delete();
	}

	public static void countLinesThreads(int numFiles) {
		String[] fileNames = createFiles(numFiles);

		LineCounter[] threadsCounter = new LineCounter[numFiles];
		for (int i = 0; i < threadsCounter.length; i++) {
			threadsCounter[i] = new LineCounter(fileNames[i]);
		}
		for (int i = 0; i < threadsCounter.length; i++) {
			threadsCounter[i].start();
		}
		long time = 0;
		for (int i = 0; i < threadsCounter.length; i++) {
			while (threadsCounter[i].isAlive())
				;
			time += threadsCounter[i].getTime();
		}
		System.out.println(time);
		deleteFiles(fileNames);
	}

	private static void countLinesOneProcess(int numFiles) {
		String[] fileNames = createFiles(numFiles);
		long time = 0;
		for (int i = 0; i < fileNames.length; i++) {
			LineCounter oneThread = new LineCounter(fileNames[i]);
			oneThread.run();
			time += oneThread.getTime();
		}
		System.out.println(time);
		deleteFiles(fileNames);
	}

	private static void countLinesThreadPool(int numFiles) {
		String[] fileNames = createFiles(numFiles);
		ExecutorService executor = Executors.newFixedThreadPool(numFiles);
		int[] counter = {0};
		long[] time = new long[1];
		long[] startingTime = {System.currentTimeMillis()};
		// Runnable, return void, nothing, submit and run the task async
		Runnable[] exe = new Runnable[numFiles];
		for (int[] indx = {0}; indx[0] < fileNames.length; indx[0]++) {
			exe[indx[0]] = new Runnable() {

				@Override
				public void run() {

					File file = new File(fileNames[indx[0]]); 
					try {
						BufferedReader br = new BufferedReader(new FileReader(file)); 
						while (br.readLine() != null) 
							counter[0]++;
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			};

		}
		for (int i = 0; i < exe.length; i++) {
			executor.execute(exe[i]);
		}
		time[0] = (System.currentTimeMillis()-startingTime[0]);
		System.out.println(time);
		executor.shutdown();
		deleteFiles(fileNames);
	}

	public static void main(String[] args) {
		int num = 1000;
		//countLinesThreads(num);
		countLinesOneProcess(num);
		//countLinesThreadPool(num);
	}

}
