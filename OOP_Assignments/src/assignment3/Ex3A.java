package assignment3;

/**
 * Ex3A is a class to cover for Ex3_tester class's faulty implementation
 * Utilizing the threads properties of semi-parallel computing
 * to run an internal function while keeping track of it's running 
 * time to determine whether it took an unreasonable without returning an 
 * answer or the flag finished has been triggered in due time of the prediction 
 * by the function isPrime which used to determine if the number is prime.
 * @author Ali Masarweh
 * 
 */
public class Ex3A implements Runnable{
	
	boolean ans = false;
	boolean finished = false;
	long num = -1;
	final static int minimal_time = 5000;
	int time;
	
	public Ex3A(){
		time = minimal_time;	
	}
	
	public Ex3A(int time){
		this.time = Math.max(time, minimal_time);	
	}

	/**
	 * Wrapper of the function Ex3_tester.isPrimes(long n) to deal with
	 * it looping in some situations.
	 * @param n the given number argument to be checked
	 * @param d the given time in seconds argument to break 
	 * out of internal function, in this case we multiply this time argument 
	 * by time member to account for slower systems
	 * @return true if the given number is prime, otherwise, false
	 * @throws RuntimeException in case of internal function failure
	 */
	@SuppressWarnings("deprecation")
	public boolean isPrime(long n, double d) throws RuntimeException{
		this.num = n;
		Thread thread = new Thread(this);
		long startingTime = System.currentTimeMillis();
		thread.start();
		while(!finished) {
			if(System.currentTimeMillis() - d*time > startingTime) {
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
