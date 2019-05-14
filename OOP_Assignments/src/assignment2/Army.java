package assignment2;

import java.util.Scanner;

import Turtle.SimpleTurtle;
/**
 * An army of variuos types of turtles turtles.
 * @author Ali Masarweh
 *
 */
public class Army {
	/** The size of the turtles army */
	private static final int ARMY_SIZE = 5;
	/** The array of the army */
	private SimpleTurtle[] army;

	/**
	 * Creates the army according to the user input.
	 * @param types of the various turtles
	 */
	private Army(String[] types) {
		army = new SimpleTurtle[5];
		for (int i = 0; i < 5; i++) {
			if(types[i].equals("1") || types[i].equals("Simple"))
				army[i] = new SimpleTurtle();
			else if(types[i].equals("2") || types[i].equals("Smart"))
				army[i] = new SmartTurtle();
			else if(types[i].equals("3") || types[i].equals("Drunk"))
				army[i] = new DrunkTurtle();
			else
				army[i] = new JumpyTurtle();
		}
	}
	
	/**
	 * Commanding the army of turtles, the movement should harmonious 
	 * (i.e. any turtle must not advance to the second command,
	 *  unless every turtle has finished the previous command)
	 */
	private void moveTheArmy() {
		for (int i = 0; i < army.length; i++) {
			army[i].tailDown();
		}
		for (int i = 0; i < army.length; i++) {
			army[i].moveForward(100);
		}
		for (int i = 0; i < army.length; i++) {
			army[i].turnRight(90);
		}
		for (int i = 0; i < army.length; i++) {
			army[i].moveForward(60);
		}
		for (int i = 0; i < army.length; i++) {
			if(army[i] instanceof SmartTurtle)
				((SmartTurtle) army[i]).drawPolygon(6, 70);
		}
		for (int i = 0; i < army.length; i++) {
			army[i].hide();
		}
	}
	
	/**
	 * Lowers the tail of the army, moves it forward 100, turns it right 90 degrees
	 * moves it 60 forward, lastly, if capable of drawing, draws a 6-edged polygon, then hides them all.
	 * @param args of the main static method
	 */
	public static void main(String[] args) {
		System.out.println("Choose the type of a turtle: \r\n" + 
				"1. Simple  \n2. Smart  \n3. Drunk  \n4. Jumpy");
		Scanner scanner = new Scanner(System.in);
		String[] types = new String[ARMY_SIZE];
		for (int i = 0; i < ARMY_SIZE; i++) {
			types[i] = scanner.nextLine();
		}
		scanner.close();
		Army army = new Army(types);
		army.moveTheArmy();
	}
	
}
