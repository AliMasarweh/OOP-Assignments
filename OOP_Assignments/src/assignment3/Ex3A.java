package assignment3;

public class Ex3A implements Runnable{
	
	boolean ans = false;
	boolean finished = false;
	long num = -1;

	public boolean isPrime(long n, double d) {
		this.num = n;
		Thread thread = new Thread(this);
		long startingTime = System.currentTimeMillis();
		thread.start();
		while(!finished) {
			if(System.currentTimeMillis() - d*1000 > startingTime) {
				thread.stop();
				return false;
			}
		}
		return ans;
	}

	@Override
	public void run() {
		ans = Ex3_tester.isPrime(num);
		finished = true;
	}

}
