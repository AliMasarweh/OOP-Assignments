package tests;

public interface GameInterface {
	interface startTheGame{
		public void start(Player p);
	}
	
	interface playTheGame{
		public void play(Player p);
	}
	
	interface stopTheGame{
		public void stop(Player p);
	}
}