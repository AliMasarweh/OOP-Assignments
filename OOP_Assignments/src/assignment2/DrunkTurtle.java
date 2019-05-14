package assignment2;

import java.util.Random;

import Turtle.SimpleTurtle;

/**
 * Drunk Turtle is a Simple Turtle which can't move forward very well
 * @author Ali Masarweh
 *
 */
public class DrunkTurtle extends SimpleTurtle{
	
	
	
	@Override
	/** 
	 * Moves forward in range of [0,2*step] with a random deviation angle of [-30,30]
	 */
	public void moveForward(double step) {
		Random rnd = new Random();
		double steps = rnd.nextDouble()*(2*step + 1);
		int angle = rnd.nextInt(60) - 30;
		if(angle < 0)
			turnLeft(angle*(-1));
		else
			turnRight(angle);
		
		super.moveForward(steps);
	}

}
