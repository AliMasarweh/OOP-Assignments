package assignment4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTextField;

public class MaxSequenceSumGame {

	private List<Integer> gameSequence;
	private int sum_player1, sum_player2, len;
	private boolean isReset;
	private Agent agent;

	public MaxSequenceSumGame(int game_len) {
		len = ((1 + game_len) / 2) * 2;
		generateRandomSequence(len);
		sum_player1 = 0;
		sum_player2 = 0;
		isReset = true;
		agent = AgentFactory.buildSophisticatedAgent();
	}

	public void setAgentToSopisticated() {
		agent = AgentFactory.buildSophisticatedAgent();
	}

	public void setAgentToRandom() {
		agent = AgentFactory.buildRandomAgent();
	}

	private void generateRandomSequence(int game_len) {
		Random rnd = new Random();
		gameSequence = new ArrayList<>(game_len);
		for (int i = 0; i < game_len; i++) {
			gameSequence.add(rnd.nextInt(100));
		}
	}

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
	
	public void startGUI(JTextField sequence, JTextField output) {
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
				GUIAlpha.input = PipedInputThread.NO_INPUT;
				input = PipedInputThread.NO_INPUT;
				while ((input = GUIAlpha.input) == PipedInputThread.NO_INPUT) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (input != 'L' && input != 'R') {
					while ((input = GUIAlpha.input) != PipedInputThread.NO_INPUT)
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

	public void reset() {
		generateRandomSequence(len);
		sum_player1 = 0;
		sum_player2 = 0;
		isReset = true;
	}

	public void printSequence() {
		System.out.println(gameSequence);
	}

	public static void main(String[] args) {
		MaxSequenceSumGame game = new MaxSequenceSumGame(10);
		game.printSequence();
		game.start(new BufferedReader(new InputStreamReader(System.in)));
	}

	public String sequence() {
		return gameSequence.toString();
	}

}
