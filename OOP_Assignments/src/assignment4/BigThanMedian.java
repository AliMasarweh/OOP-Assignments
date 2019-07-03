package assignment4;



import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import java.util.Random;

import java.util.concurrent.ExecutionException;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.Future;

import java.util.concurrent.ThreadFactory;



public class BigThanMedian implements Runnable {

	private static long time;

	private static int arrR[] = null, arrL[] = null, ans[] = null, len = 0;

	private int indx;



	public static void setLength(int len) {

		BigThanMedian.len = len;

		arrR = new int[len];

		arrL = new int[len];

		ans = new int[len];

	}



	public BigThanMedian(int i) {

		indx = i;

	}



	public static List<int[]> generateTwoSortedArraysWithRandomBias(double rnd) {

		if (rnd > 1 || rnd < 0)

			rnd = 0.5;

		List<Integer> lstA = new ArrayList<Integer>(len);

		List<Integer> lstB = new ArrayList<Integer>(len);

		Random random = new Random();

		int j = 0;

		for (; j < len * 2; j++) {

			if (random.nextDouble() <= rnd)

				lstA.add(j);

			else

				lstB.add(j);

			if (lstA.size() == len || lstB.size() == len)

				break;

		}

		while (lstA.size() < len)

			lstA.add(j++);

		while (lstB.size() < len)

			lstB.add(j++);

		int[] a = lstA.stream().mapToInt(Integer::intValue).toArray();

		int[] b = lstB.stream().mapToInt(i -> i).toArray();

		@SuppressWarnings("unused")

		//Allocating memory for ans with the required length

		int[] ans = new int[len];

		List<int[]> lst = new ArrayList<>();

		lst.add(a);

		lst.add(b);

		return lst;

	}



	public static List<int[]> generateTwoSortedArrays() {

		List<Integer> lstA = new ArrayList<Integer>(len);

		List<Integer> lstB = new ArrayList<Integer>(len);

		Random random = new Random();

		int j = 0;

		for (; j < len * 2; j++) {

			if (random.nextDouble() <= 0.5)

				lstA.add(j);

			else

				lstB.add(j);

			if (lstA.size() == len || lstB.size() == len)

				break;

		}

		while (lstA.size() < len)

			lstA.add(j++);

		while (lstB.size() < len)

			lstB.add(j++);

		int[] a = lstA.stream().mapToInt(Integer::intValue).toArray();

		int[] b = lstB.stream().mapToInt(i -> i).toArray();

		List<int[]> lst = new ArrayList<>();

		lst.add(a);

		lst.add(b);

		return lst;

	}



	/*

	 * private static void recursiveGeneration(List<Integer> lstA, List<Integer>

	 * lstB, int len, Random random, int Val) { if(lstA.size() == len) fill(lstB,

	 * len, Val); else if(lstB.size() == len) fill(lstA, len, Val); else {

	 * if(random.nextDouble() <= 0.5) lstA.add(Val++); else lstB.add(Val++);

	 * recursiveGeneration(lstA, lstB, len, random, Val); } }

	 * 

	 * 

	 * 

	 * private static void fill(List<Integer> lstA, int len, int val) { while

	 * (lstA.size() != len) { lstA.add(val++); } }

	 * 

	 */



	public static int[] bigThanMedianAlgo(int[] a, int[] b) {

		int n = a.length;

		if(ans == null || ans.length != n) setLength(n);

		arrR = a;

		arrL = b;

		if (n != b.length)

			return null;

		Thread[] threads = new Thread[n];

		for (int i = 0; i < n; i++) {

			Runnable r = new BigThanMedian(i);

			threads[i] = new Thread(r);

		}



		time = System.currentTimeMillis();



		for (int i = 0; i < n; i++) {



			threads[i].start();



		}



		for (int i = 0; i < n; i++) {

			while (threads[i].isAlive())

				;

		}

		time = System.currentTimeMillis() - time;



		return ans;

	}



	public static int[] bigThanMedianAlgo2(int[] a, int[] b) {

		int n = a.length;

		if(ans == null || ans.length != n) setLength(n);

		arrR = a;

		arrL = b;

		if (n != b.length)

			return null;

		/*

		 * final int[] indx = {0}; ExecutorService threads =

		 * Executors.newFixedThreadPool(n,new ThreadFactory ( ){

		 * 

		 * public Thread newThread(Runnable r) { return new Thread(r){ {ans[indx[0]] =

		 * Math.max(a[indx[0]], b[n - indx[0] -1]); indx[0]++;} // this is an

		 * initialization statement, added to all constructors. }; }});

		 */

		ExecutorService executor = Executors

				.newFixedThreadPool(Math.max(1, Runtime.getRuntime().availableProcessors()));

		List<Future<?>> placeholders = new ArrayList<Future<?>>(n);



		for (int i = 0; i < n; i++) {

			Future<?> placeholder = executor.submit(new BigThanMedian(i));

			placeholders.add(placeholder);

		}



		time = System.currentTimeMillis();



		for (Future<?> future : placeholders)

			try {

				future.get();

			} catch (InterruptedException | ExecutionException e) {

				e.printStackTrace();

			}



		boolean allDone = true;

		for (Future<?> future : placeholders) {

			allDone &= future.isDone(); // check if future is done

		}



		if (!allDone) {

			System.out.println("ERROR!");

		}

		executor.shutdown();



		time = System.currentTimeMillis() - time;

		

		return ans;

	}



	public static int[] bigThanMedianMerge(int[] a, int[] b) {

		int n = a.length;

		if(ans == null || ans.length != n) setLength(n);

		arrR = a;

		arrL = b;

		time = System.currentTimeMillis();

		int[] c = new int[n * 2];

		int indxA = 0, indxB = 0;

		int i = 0;

		for (; i < n; i++) {

			if (indxA == n || indxB == n)

				break;

			else if (a[indxA] < b[indxB])

				c[i] = a[indxA++];

			else

				c[i] = b[indxB++];

		}

		while (indxA < n)

			c[i++] = a[indxA++];

		while (indxB < n)

			c[i++] = b[indxB++];

		for (i = 0; i < n; i++) {

			ans[i] = c[i + n];

		}

		time = System.currentTimeMillis() - time;

		return ans;

	}



	public static void main(String[] args) {

		len = 100000;

		int[] a = new int[len];

		int[] b = new int[len];

		ans = new int[len];

		List<int[]> lst = generateTwoSortedArrays();

		a = lst.remove(0);

		b = lst.remove(0);

		Arrays.toString(bigThanMedianAlgo(a, b));

		System.out.println(time);

		Arrays.toString(bigThanMedianMerge(a, b));

		System.out.println(time);

		bigThanMedianAlgo2(a, b);

		System.out.println(time);

	}



	@Override

	public void run() {

		ans[indx] = Math.max(arrR[indx], arrL[len - indx - 1]);

	}

}