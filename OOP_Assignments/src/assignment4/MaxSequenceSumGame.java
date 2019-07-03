package assignment4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTextField;


/**
 * This a the algorithm implementation as a back-end for the game.
 * The goal of the game is to achieve higher sum of numbers than the other player,
 * each turn, the player whose turn is the current one has to pick either the number
 * at the right side number or the left side number from the sequence.
 * 
 * A simple strategy to guarantee the victory (or at least a draw) the pick the odd (even)
 * indexed numbers given that the odd (even) indexes has the higher sum value.
 * 
 * The best strategy is to use the above method each turn as an adaptive strategy
 * @author Ali Masarweh
 */
public class MaxSequenceSumGame {

	private List<Integer> gameSequence;
	private int sum_player1, sum_player2, len;
	private boolean isReset;
	private Agent agent;

	/**
	 * initializes the game with the given length of sequence,
	 * default agent is the sophisticated one. 
	 * @param game_len - length of the sequence
	 */
	public MaxSequenceSumGame(int game_len) {
		len = ((1 + game_len) / 2) * 2;
		generateRandomSequence(len);
		sum_player1 = 0;
		sum_player2 = 0;
		isReset = true;
		agent = AgentFactory.buildSophisticatedAgent();
	}

	/**
	 * sets the adversarial agent to sophisticated
	 */
	public void setAgentToSopisticated() {
		agent = AgentFactory.buildSophisticatedAgent();
	}

	/**
	 * sets the adversarial agent to random simple agent
	 */
	public void setAgentToRandom() {
		agent = AgentFactory.buildRandomAgent();
	}

	/**
	 * generates the random sequence in the range of 0-99
	 * @param game_len - length of the sequence
	 */
	private void generateRandomSequence(int game_len) {
		Random rnd = new Random();
		gameSequence = new ArrayList<>(game_len);
		for (int i = 0; i < game_len; i++) {
			gameSequence.add(rnd.nextInt(100));
		}
	}

	/**
	 * The back-end implementation for validation & testing
	 * @param reader - reads the user input
	 */
	public void start(Reader reader) {
		if (!isReset)
			this.reset();
		int turn = 1;
		String line = null;
		Scanner in = new Scanner(reader);
		while (!gameSequence.isEmpty()) {
			if (turn == 1) {
				line = agent.play(gameSequence) + "";
				if (line.equals("L"))
					sum_player2 += this.gameSequence.remove(0);
				else
					sum_player2 += gameSequence.remove(gameSequence.size() - 1);
			} else {
				line = in.nextLine();
				while (!line.equals("L") && !line.equals("R")) {
					System.out.println("Wrong Input, please input");
					line = in.nextLine();
				}
				if (line.equals("L"))
					sum_player2 += this.gameSequence.remove(0);
				else
					sum_player2 += gameSequence.remove(gameSequence.size() - 1);
			}
			turn = 3 - turn;
			System.out.println(gameSequence);
		}
		in.close();
		if (sum_player1 > sum_player2)
			System.out.println("Player 1 Won!");
		else if (sum_player2 > sum_player1)
			System.out.println("Player 2 Won!");
		else
			System.out.println("Draw!");
		isReset = false;

	}

	
	/**
	 * This Is An Implementation For The Older Approach That Didn't Work,
	 * Skip To See The Newer One
	 * @param pipeReader
	 * @param sequence
	 * @param output
	 */
	public void startGUI(PipedInputThread pipeReader,JTextField sequence, JTextField output) {
		if (!isReset)
			this.reset();
		int turn = 1;
		char input = PipedInputThread.NO_INPUT;
		while (!gameSequence.isEmpty()) {
			if (turn == 1) {
				input = agent.play(gameSequence);
				if (input == 'L')
					sum_player1 += this.gameSequence.remove(0);
				else
					sum_player1 += gameSequence.remove(gameSequence.size() - 1);
			} else {
				input = PipedInputThread.NO_INPUT;
				while ((input = pipeReader.read()) == PipedInputThread.NO_INPUT) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (input != 'L' && input != 'R') {
					while ((input = pipeReader.read()) != PipedInputThread.NO_INPUT)
						;
				}
				if (input == 'L') {
					sum_player2 += this.gameSequence.remove(0);
				}
				else {
					sum_player2 += gameSequence.remove(gameSequence.size() - 1);
				}
			}
			turn = 3 - turn;
			sequence.setText(gameSequence.toString());
			output.setText("Sum of p1: " + sum_player1 + "\tSum of p2: " + sum_player2);
		}
		String tmp = "Sum of p1: " + sum_player1 + "\tSum of p2: " + sum_player2;
		if (sum_player1 > sum_player2)
			output.setText(tmp + "\tPlayer 1 Won!");
		else if (sum_player2 > sum_player1)
			output.setText(tmp + "\tPlayer 2 Won!");
		else
			output.setText(tmp + "\tDraw!");
		isReset = false;

	}
	/**
	 * Starts/Restarts the game as soon as the Start button is clicked,
	 * uses the text fields as input & output to interact with the users.
	 * @param sequence displays the sequence of numbers
	 * @param output displays the game's output
	 */
	public void startGUI(JTextField sequence, JTextField output) {
		if (!isReset)
			this.reset();
		int turn = 1;
		char input = GUIAlpha.NO_INPUT;
		while (!gameSequence.isEmpty()) {
			if (turn == 1) {
				input = agent.play(gameSequence);
				if (input == 'L')
					sum_player1 += this.gameSequence.remove(0);
				else
					sum_player1 += gameSequence.remove(gameSequence.size() - 1);
			} else {
				GUIAlpha.input = GUIAlpha.NO_INPUT;
				input = GUIAlpha.NO_INPUT;
				while ((input = GUIAlpha.input) == GUIAlpha.NO_INPUT) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (input != 'L' && input != 'R') {
					while ((input = GUIAlpha.input) != GUIAlpha.NO_INPUT)
						;
				}
				if (!gameSequence.isEmpty() && input == 'L') {
					sum_player2 += this.gameSequence.remove(0);
				}
				else if (!gameSequence.isEmpty() && input == 'R'){
					sum_player2 += gameSequence.remove(gameSequence.size() - 1);
				}
			}
			turn = 3 - turn;
			sequence.setText(gameSequence.toString());
			output.setText("Sum of p1: " + sum_player1 + "\tSum of p2: " + sum_player2);
		}
		String tmp = "Sum of p1: " + sum_player1 + "\tSum of p2: " + sum_player2;
		if (sum_player1 > sum_player2)
			output.setText(tmp + "\tPlayer 1 Won!");
		else if (sum_player2 > sum_player1)
			output.setText(tmp + "\tPlayer 2 Won!");
		else
			output.setText(tmp + "\tDraw!");
		isReset = false;
		sequence.setText("Press start to restart the game!");
	}

	/**
	 * resets the game
	 */
	public void reset() {
		generateRandomSequence(len);
		sum_player1 = 0;
		sum_player2 = 0;
		isReset = true;
	}

	/**
	 * prints the sequence
	 */
	public void printSequence() {
		System.out.println(gameSequence);
	}

	public static void main(String[] args) {
		MaxSequenceSumGame game = new MaxSequenceSumGame(10);
		game.printSequence();
		game.start(new BufferedReader(new InputStreamReader(System.in)));
	}

	/**
	 * to display the sequence
	 * @return the sequence -> string
	 */
	public String sequence() {
		return gameSequence.toString();
	}

}
