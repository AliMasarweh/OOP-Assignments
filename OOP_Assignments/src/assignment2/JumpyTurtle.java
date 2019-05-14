package assignment2;

/**
 * The jumpy turtle is a smart turtle, and jumps while moving forward or drawing.
 * @author Ali Masarweh
 *
 */
public class JumpyTurtle extends SmartTurtle {
	
	/** Monitoring the state of the tail */
	private boolean hasRisenHisTail;
	private double _jump;
	/** Values less than 3 may have trouble rendering the spacing of the turtle on the GUI */
	public final static double Minimal_Jump = 3,
			Default_Jump = 5;
	
	/**
	 * 
	 * @param jump the given argument for the height of the jump
	 */
	public JumpyTurtle(double jump) {
		super();
		hasRisenHisTail = true;
		_jump = Math.max(Minimal_Jump, jump);
	}
	
	/**
	 * Constructs a jumpy turtle
	 */
	public JumpyTurtle() {
		super();
		hasRisenHisTail = true;
		_jump = Default_Jump;
	}
	
	@Override
	public void tailUp() {
		hasRisenHisTail = true;
		super.tailUp();
	}
	
	@Override
	public void tailDown() {
		hasRisenHisTail = false;
		super.tailUp();
	}

	@Override
	/**
	 * jumps while moving forward
	 */
	public void moveForward(double step) {
		if(hasRisenHisTail) {
			super.moveForward(step);
			return;
		}
		else {
			boolean hasToJump = true;
			while(step > 0) {
				if(hasToJump)
					super.tailUp();
				else
					super.tailDown();
				hasToJump = !hasToJump;
				super.moveForward(_jump);
				step -= _jump;
			}
			/* Resetting the state of the tail */
			tailDown();
		}
	}
}
