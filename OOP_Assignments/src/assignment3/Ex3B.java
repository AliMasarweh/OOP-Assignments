package assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
		long time = System.currentTimeMillis();

		for (int i = 0; i < threadsCounter.length; i++) {
			threadsCounter[i].start();
		}
		int sum = 0;
		for (int i = 0; i < threadsCounter.length; i++) {
			while (threadsCounter[i].isAlive());
			sum += threadsCounter[i].getCounter();
		}

		time = System.currentTimeMillis();
		System.out.println("Lines: " + sum+"\t Threads Time: "+time/1000 + " seconds");
		deleteFiles(fileNames);
	}

	private static void countLinesOneProcess(int numFiles) {
		String[] fileNames = createFiles(numFiles);
		int sum = 0;
		long time = System.currentTimeMillis();

		for (int i = 0; i < fileNames.length; i++) {
			LineCounter oneThread = new LineCounter(fileNames[i]);
			/* Notice the use of run (Non-parallel computing) */
			oneThread.run();
			sum += oneThread.getCounter();
		}
	
		time = System.currentTimeMillis() - time;
		System.out.println("Lines: " + sum+"\t One Process Time: "+time+ " milliseconds");
		deleteFiles(fileNames);
	}

	private static void countLinesThreadPool(int numFiles) {
		String[] fileNames = createFiles(numFiles);
		ExecutorService executor = Executors.newFixedThreadPool(numFiles);
		List<Future<Integer>> placeholders = new ArrayList<Future<Integer>>(numFiles);
		long time = System.currentTimeMillis();

		for (int i = 0; i < fileNames.length; i++) {
			Future<Integer> placeholder = executor.submit(
					(Callable<Integer>) new LineCounter(fileNames[i]));
			placeholders.add(placeholder);
		}
		
		int sum = 0;
		
		for (int i = 0; i < fileNames.length; i++) {
			try {
				Integer Lines = placeholders.get(i).get();
				sum += Lines;
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	
		time = System.currentTimeMillis() - time;
		System.out.println("Lines: " + sum+"\t Thread Pool Time: "+time+ " milliseconds");
		executor.shutdown();
		deleteFiles(fileNames);
	}

	public static void main(String[] args) {
		int num = 1000;
		countLinesThreads(num);
		countLinesOneProcess(num);
		countLinesThreadPool(num);
		
		int numOfProcessor = Runtime.getRuntime().availableProcessors();
		countLinesMulitpleCores(num,numOfProcessor);
	}

	private static void countLinesMulitpleCores(int numFiles, int numOfProcessor) {
		String[] fileNames = createFiles(numFiles);
		ExecutorService executor = Executors.newFixedThreadPool(numOfProcessor);
		List<Future<Integer>> placeholders = new ArrayList<Future<Integer>>(numFiles);
		long time = System.currentTimeMillis();

		for (int i = 0; i < fileNames.length; i++) {
			Future<Integer> placeholder = executor.submit(
					(Callable<Integer>) new LineCounter(fileNames[i]));
			placeholders.add(placeholder);
		}
		
		int sum = 0;
		
		for (int i = 0; i < fileNames.length; i++) {
			try {
				Integer Lines = placeholders.get(i).get();
				sum += Lines;
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		time = System.currentTimeMillis() - time;
		System.out.println("Lines: " + sum+"\t Multiple Corse Time: "+time+ " milliseconds");
		executor.shutdown();
		deleteFiles(fileNames);
	}

}
