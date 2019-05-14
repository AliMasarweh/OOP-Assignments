package assignment2;

import Turtle.SimpleTurtle;
/**
 * The Smart Turtle is a turtle, and can draw polygons.
 * @author Ali Masarweh
 *
 */
public class SmartTurtle extends SimpleTurtle{
	
	/**
	 * Draws a square, each edge is length of the given size argument.
	 * @param size, the given argument, of each side
	 */
	public void drawSquare (double size) {
		for(int i = 0; i < 4; i++) {
			moveForward(size);
			turnRight(90);
		}
		turnLeft(90);
	}
	
	/**
	 * Draws a polygon of the given argument sides,
	 * each side is the length of the given size argument
	 * @param sides the given argument number of the sides of the polygon
	 * @param size, the given argument, of each side
	 */
	public void drawPolygon(int sides, double size){
		int sumOfAngles = (sides-2)*180;
		int angle = 180 - (sumOfAngles / sides);
		for(int i = 0; i < sides; i++) {
			moveForward(size);
			turnRight(angle);
		}
		turnLeft(angle);
	}
}
