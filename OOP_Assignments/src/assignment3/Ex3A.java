package assignment3;

public class Ex3A implements Runnable{
	
	boolean ans = false;
	boolean finished = false;
	long num = -1;

	/**
	 * Wrapper of the function Ex3_tester.isPrimes(long n) to deal with
	 * it looping in some situations.
	 * @param n the given number argument to be checked
	 * @param d the given time in seconds argument to break 
	 * out of internal function, in this case we multiply this time argument 
	 * by 5 to account for slower systems
	 * @return
	 * @throws RuntimeException in case of internal function failure
	 */
	public boolean isPrime(long n, double d) throws RuntimeException{
		this.num = n;
		Thread thread = new Thread(this);
		long startingTime = System.currentTimeMillis();
		thread.start();
		while(!finished) {
			if(System.currentTimeMillis() - d*5000 > startingTime) {
				thread.stop();
				throw new RuntimeException("Internal function is stuck!");
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
