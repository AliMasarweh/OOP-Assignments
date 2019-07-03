package assignment4;

import java.util.List;

public interface Agent {
	/**
	 * Makes decision of what the agent's next play is given the game state.
	 * @param gameSequence - to observe the game state
	 * @return the next play, R is right, L is left
	 */
	public char play(List<Integer> gameSequence);
}
