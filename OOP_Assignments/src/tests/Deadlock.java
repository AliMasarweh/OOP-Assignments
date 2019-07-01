package tests;

public class Deadlock {

	public static int que = 0, que2 = 0;
	public static Object critical = new Object();
	
	public static void main(String[] args) {
		Thread t1 = new Thread(()-> {
			while(true) {
				que = 1;
				while(que2 == 1) {
					que = 0;
					System.out.println("STUCK 1");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					que = 1;
				}
				System.out.println("RElEASED 1");
				critical = new Integer(que);
				que = 0;
				que2 = 1;
			}
		});
		
		Thread t2 = new Thread(()-> {
			while(true) {
				while(que == 1) {
					que2 = 0;
					System.out.println("STUCK 2");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					que2 = 1;
				}
				System.out.println("RElEASED 2");
				critical = new Integer(que2);
				que2 = 0;
				que = 1;
			}
		});
		
		t1.start();
		t2.start();
	}

}
