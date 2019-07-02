package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Pass {
	public static boolean testPass(String s) {
		return s.equals("98765432");
	}
	
	public static String findPass() { 
		final int max = 100000000;
		final int threadNumbers = 3;
		ExecutorService ex = Executors.newFixedThreadPool(threadNumbers);
		List<Future<String>> phs = new ArrayList<>();
		for (int i = 0; i < threadNumbers; i++) {
			final int slice = i;
			Future<String> ph = ex.submit(new Callable<String>(){
				@Override
				public String call() throws Exception {
					for (int j = 0; j < (slice + 1) * max/threadNumbers; j++) {
						if(testPass(addZeros(j+""))) {
							return addZeros(j+"");
						}
					}
					return null;
				}
			});
			phs.add(ph);
		}
		for (int i = 0; i < threadNumbers; i++) {
			try {
				String pass = phs.get(i).get();
				if(pass != null) {
					ex.shutdown();
					return pass;
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private static String addZeros(String pass) {
		for (int i = pass.length(); i < 8; i++) {
			pass = "0"+ pass;
		}
		return pass;
	}
	
	public static void main(String[] args) {
		System.out.println(findPass());
	}

	
}
