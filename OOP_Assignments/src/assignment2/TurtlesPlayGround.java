package assignment2;

import java.util.Random;

import Turtle.SimpleTurtle;;

/**
 * A playground for the turtle, give it a try to see the various functionality of each turtle
 * @author Ali Masarweh
 *
 */
public class TurtlesPlayGround {

	public static void main(String[] args) {
		drawPolygons(6, 50);
		differnceOfSmartAndJumpy(8, 50);
		randomMoves();
	}
	
	/**
	 * Visual demonstration of the difference behavior that exhibits the drawing of the jumpy turtle in contrast to the smart turtle 
	 * @param sides - number of the sides of the polygon
	 * @param size - size of each side
	 */
	public static void differnceOfSmartAndJumpy(int sides, double size) {
		SmartTurtle sos = new SmartTurtle();
		JumpyTurtle coc = new JumpyTurtle();
		
		coc.tailDown();
		coc.drawPolygon(sides, size);
		sos.tailDown();
		sos.drawPolygon(sides, size);
	}
	
	/**
	 * Smart turtle drawing polygons
	 * @param sides - number of the sides of the polygon
	 * @param size - size of each side
	 */
	public static void drawPolygons(int sides, double size) {
		SimpleTurtle smart = new JumpyTurtle();
		smart.tailDown();
		((SmartTurtle) smart).drawPolygon(sides, size);
	}
	
	/**
	 * Random moves for all the 4 kinds of turtles
	 */
	public static void randomMoves() {
		SimpleTurtle simple = new SimpleTurtle();
		SimpleTurtle smart = new SmartTurtle();
		SimpleTurtle drunk = new DrunkTurtle();
		SimpleTurtle jumpy = new JumpyTurtle();
		/* ****************** */
		simple.tailDown();
		smart.tailDown();
		drunk.tailDown();
		jumpy.tailDown();
		/* ****************** */
		Random rnd = new Random();
		for (int i = 0; i < 100; i++) {
			double x = rnd.nextDouble();
			int step = rnd.nextInt(100);
			int degree = rnd.nextInt(90);
			if(x < 0.25) {
				simple.moveForward(step);
				smart.turnRight(degree);
				smart.moveForward(step);
				jumpy.moveBackward(step);
				drunk.turnLeft(degree);
				drunk.moveForward(step);
			}
			else if (x < 0.5) {
				simple.turnRight(degree);
				simple.moveForward(step);
				smart.moveBackward(step);
				jumpy.turnLeft(degree);
				jumpy.moveForward(step);
				drunk.moveForward(step);
				
			}
			else if(x < 0.75) {
				simple.turnLeft(degree);
				simple.moveForward(step);
				smart.moveForward(step);
				jumpy.turnRight(degree);
				jumpy.moveForward(step);
				drunk.moveBackward(step);
			}
			else {
				simple.moveBackward(step);
				smart.turnLeft(degree);
				smart.moveForward(step);
				jumpy.moveForward(step);
				drunk.turnRight(degree);
				drunk.moveForward(step);
			}
		}
	}

}
