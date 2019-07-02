package tests;

import java.util.Date;

public class MyCode  implements car_code  {
	
	car c;
	
	public MyCode(){
		c = new DummyCar();
	}
	
	@Override
	public car getCar() {
		return c;
	}

	@Override
	public boolean open(String key) {
		getCar();
		if(canBeOpen() == false)
			return false;
		Thread codeChekcer = new Thread(()->{
			while(!isOpen() && !getCar().open(key)) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread counter = new Thread(()-> {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		codeChekcer.start();
		counter.start();
		while(counter.isAlive() && codeChekcer.isAlive());
		boolean ans = isOpen();
		codeChekcer.stop();
		return ans;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return getCar().isRunning();
	}

	@Override
	public boolean canBeOpen() {
		// TODO Auto-generated method stub
		return getCar().lastRun() == null || 
				new Date().getTime() - getCar().lastRun().getTime() > 15 * 60 * 1000;
	} 
	
	class DummyCar implements car{
		
		String code;
		boolean isRunning;
		Date date;
		
		public DummyCar(){
			code = "1231#";
			isRunning = false;
			date = null;
		}

		@Override
		public boolean isRunning() {
			return isRunning;
		}

		@Override
		public boolean open(String key) {
			if(key.equals(code)) {
				isRunning = true;
				date = new Date();
				return true;
			}
			return false;
		}

		@Override
		public Date lastRun() {
			return date;
		}
		
	}
	
	public static void main(String[] args) {
		MyCode c = new MyCode();
		System.out.println(c.open("1231"));
	}
}
