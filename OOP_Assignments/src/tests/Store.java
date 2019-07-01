package tests;

public class Store {
	int current, max;
	public final Object lock = new Object();
	public static boolean isFull = false, finishedAdding = false;
	Store(){
		current = 0;
		max = 4;
	}
	
	public void add(int id) {
		while(current == 4) {
			isFull = true;
			try {
				System.out.println("The storehouse is full");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized (lock) {
			System.out.println("Manufacturer added "+ 1 +"\nproduct Goods in storehouse: "+ ++current);
		}
	}
	
	public void remove(int id) {
		while(!isFull && !finishedAdding) { 
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(current == 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized (lock) {
			System.out.println("The buyer bought "+ 1 +"\nitem Goods in storehouse: "+ --current);
			isFull = false;
		}
	}
	
	public static void main(String[] args) {  
		Store store=new Store();  
		Producer  producer = new Producer(store);  
		Consumer consumer = new Consumer(store);  
		new Thread(producer).start();  
		new Thread(consumer).start(); }
}

class Producer implements Runnable{
	
	Store store;
	public final static int len = 10;

	public Producer(Store store) {
		this.store = store;
	}

	@Override
	public void run() {
		for (int i = 0; i < len; i++) {
			store.add(i+1);
		}
		Store.finishedAdding = true;
	}
	
}

class Consumer implements Runnable{
	
	Store store;

	public Consumer(Store store) {
		this.store = store;
	}

	@Override
	public void run() {
		for (int i = 0; i < Producer.len; i++) {
			store.remove(i);
		}
	}
	
}
