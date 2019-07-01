package tests;

public class GameTester {
	
	public static void main(String[] args) throws InterruptedException {
		Player p = new Player();
		Game g = new Game(p);
		int range = 30;
		for (int i = 0; i < range; i++) {
			g.stop(i);
		}
		Thread.sleep(1000);
		for (int i = 0; i < range; i++)
			g.play(i);
		Thread.sleep(1000);
		for (int i = 0; i < range; i++)
			g.start(i);
	}

}
