package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q3_2_1 implements Callable<Integer>{
	public static void main(String[] args) {
		System.out.println(maxDividors(4,40));
	}
	
	private int min,max;
	public Q3_2_1(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	@SuppressWarnings("unused")
	public static int maxDividors(int numOfThreads, int N) {
		ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
		List<Future<Integer>> placeholders = new ArrayList<Future<Integer>>(numOfThreads);
		for (int i[] = {0}; i[0] < numOfThreads; i[0]++) {
			int min = i[0] * (N/numOfThreads), max = (i[0]+1) * (N/numOfThreads);
			Future<Integer> placeholder = executor.submit(//new Q3_2_1(min, max));
					new Callable<Integer>() {
						int min = i[0] * (N/numOfThreads), max = (i[0]+1) * (N/numOfThreads);
						@Override
						public Integer call() throws Exception {
							int maxDividors = 0;
							for (int j = min; j < max; j++) {
								maxDividors = numberOfDividorsFor(maxDividors) > numberOfDividorsFor(j)? maxDividors:j;
							}
							return maxDividors;
						}
					});
			placeholders.add(placeholder);
		}
		int maxDividors = 0;
		for (int i = 0; i < numOfThreads; i++) {
			try {
				Integer num = placeholders.get(i).get();
				System.out.println(num);
				maxDividors = numberOfDividorsFor(maxDividors) > numberOfDividorsFor(num)? maxDividors:num;
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		return maxDividors;
	}
	
	public static int numberOfDividorsFor(int num) {
		int c = 0;
		for (int k = 2; k < num; k++) {
			if(num % k == 0)
				c++;
		}
		return c;
	}

	@Override
	public Integer call() throws Exception {
		int maxDividors = 0;
		for (int j = min; j < max; j++) {
			maxDividors = numberOfDividorsFor(maxDividors) > numberOfDividorsFor(j)? maxDividors:j;
		}
		return maxDividors;
	}
}
