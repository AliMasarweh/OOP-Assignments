package assignment4;

import java.util.List;
import java.util.Random;


public class AgentFactory{
	public static Agent buildSophisticatedAgent() {
		return new SophisticatedAgent();
	}
	
	public static Agent buildRandomAgent() {
		return new RandomAgent();
	}
}

class SophisticatedAgent implements Agent{
	
	public char play(List<Integer> gameSequence) {
		int odd = 0, even = 0, turn = 1;
		for (int i = 0; i < gameSequence.size(); i++) {
			if(turn == 1)
				even += gameSequence.get(i);
			else
				odd += gameSequence.get(i);
			turn = 3 - turn;
		}
		if(odd > even)
			// Last Number
			return 'R';
		// First Number
		return 'L';
	}

}

class RandomAgent implements Agent{
	
	Random rnd;
	
	public RandomAgent(){
		rnd = new Random();
	}
	
	public char play(List<Integer> gameSequence) {
		if(rnd.nextDouble() <= 0.5)
			// Last Number
			return 'R';
		// First Number
		return 'L';
	}

}
