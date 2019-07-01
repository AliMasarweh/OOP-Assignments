package tests;


public class Game {
	
	public static final Object run_lock = new Object(),
			play_lock = new Object(), stop_lock = new Object();

	final static private String START_STATE = "start", PLAY_STATE = "play",
			STOP_STATE = "stop";
	private String state;
	private Player p;
	Game(Player p){
		state = STOP_STATE;
		this.p = p;
	}
	
	public void start() {
		new game_starter().start();
	}
	
	public void start(int i) {
		new game_starter(i).start();
	}
	
	public void play() {
		new game_playing().start();
	}
	
	public void play(int i) {
		new game_playing(i).start();
	}
	
	public void stop() {
		new game_stopper().start();
	}
	
	public void stop(int i) {
		new game_stopper(i).start();
	}
	
	public boolean isReady() {
		return state.equals(STOP_STATE);
	}
	
	public boolean hasStarted() {
		return state.equals(START_STATE);
	}
	public boolean isPlaying() {
		return state.equals(PLAY_STATE);
	}
	
	public boolean hasFinished() {
		return state.equals(STOP_STATE);
	}
	
	class game_starter extends Thread implements GameInterface.startTheGame{
		
		int indx;
		
		game_starter(){
			indx = -1;
		}
		
		game_starter(int i){
			indx = i;
		}
		@Override
		public void run() {
			synchronized(run_lock) {
				while(!isReady()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				start(p);
			}
			
		}
		@Override
		public void start(Player p) {
			p.start(indx);
			state = START_STATE;
		}
		
	}
	
	class game_playing extends Thread  implements GameInterface.playTheGame{
		
		int indx;
		
		game_playing(){
			indx = -1;
		}
		
		game_playing(int i){
			indx = i;
		}

		@Override
		public void run() {
			synchronized(play_lock) {
				while(!hasStarted()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				play(p);
			}
			
		}
		@Override
		public void play(Player p) {
			p.play(indx);
			state = PLAY_STATE;
		}
		
	}
	
	class game_stopper extends Thread  implements GameInterface.stopTheGame{
		
		int indx;
		
		game_stopper(){
			indx = -1;
		}
		
		game_stopper(int i){
			indx = i;
		}

		@Override
		public void run() {
			synchronized(stop_lock) {
				while(!isPlaying()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				stop(p);
			}
			
		}
		@Override
		public void stop(Player p) {
			p.stop(indx);
			state = STOP_STATE;
		}
		
	}
	
}

class Player{
	public void start(int indx) {
		if(indx == -1)
			System.out.println("Player is in start state");
		else
			System.out.println("Player is in start state " + indx);
	}
	public void play(int indx) {
		if(indx == -1)
			System.out.println("Player is in playing state");
		else
			System.out.println("Player is in playing state " + indx);
	}
	public void stop(int indx) {
		if(indx == -1)
			System.out.println("Player is in stopped state");
		else
			System.out.println("Player is in stopped state " + indx);
	}
}


