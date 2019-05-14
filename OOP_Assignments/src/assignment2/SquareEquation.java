package assignment2;
/**
 * Square Equation of a*x^2 + b*x + c = 0, can solve for x if any solutions exist.
 * @author Ali Masarweh
 *
 */
public class SquareEquation {
	/** The parameters of the square equation a*x^2 + b*x + c = 0 */
	private double _a, _b ,_c;
	/** The two possible solution for the equation, in cases there might be less solutions */
	private double _x1, _x2;
	
	/**
	 * Constructs a Square Equation
	 * @param a - the parameter for x^2
	 * @param b - the parameter for x
	 * @param c - free parameter
	 */
	public SquareEquation(double a, double b, double c) {
		_a = a;
		_b = b;
		_c = c;
	}
	
	/**
	 * 
	 * @return the first solution
	 */
	public double get_x1() {
		return _x1;
	}

	/**
	 * 
	 * @return the second solution, in case of one solution, returns the same as get_x1()
	 */
	public double get_x2() {
		return _x2;
	}
	
	/**
	 * 
	 * @param pointerOfSquaredVal a pointer of the yielded value of 
	 * {(b*b -4*a*c) | a,b,c are the parameters in the equation as a*x^2 + b*x + c = 0}
	 * @return true, if the squared  is positive (Real), otherwise, false (Imaginary)
	 */
	private boolean hasRealRoots(double[] pointerOfSquaredVal) {
		return (pointerOfSquaredVal[0] = (_b*_b - 4*_a*_c)) >= 0;
	}
	
	/**
	 * 
	 * @return true, if the equation is representing a trivial equation, otherwise, false
	 */
	private boolean isTrivial() {
		return (_a == 0) && (_b == 0) && (_c == 0);
	}
	
	/**
	 * 
	 * @return true, if there's no solution of the equation, otherwise, false
	 */
	private boolean hasNoSolution() {
		return (_a == 0) && (_b == 0) && (_c != 0);
	}
	
	/**
	 * solves for x, in the quadratic equation.
	 * @throws SquareEquationException, in case of trivial solutions, unavailable solution,
	 * or imaginary solution
	 */
	public void solveForX() throws SquareEquationException {
		if(isTrivial()) 
			throw new SquareEquationException(" x1 can be any number - trivial! ");
		
		if(hasNoSolution())
			throw new SquareEquationException(" Error, no answer!! ");
		
		double[] pointerOfSquaredVal = new double[1];
		if(hasRealRoots(pointerOfSquaredVal)) {
			if(_a!=0) {
				_x1 = (-_b + Math.sqrt(pointerOfSquaredVal[0])) / (2*_a);
				_x2 = (-_b - Math.sqrt(pointerOfSquaredVal[0])) / (2*_a);
				if(_x2 == -0.0)
					// More convenient and intuitive 
					_x2 = 0.0;
			}
			else { // Linear (0*X^2)
				_x1 = (-_c/_b);
				_x2 = _x1;
			}
		}
		else 
			throw new SquareEquationException(" Error: NO real roots! ");
	}
	
	@SuppressWarnings("serial")
	class SquareEquationException extends Exception{
		
		public final static String  EXP_TRIVIAL_SOLUTIONS_MSG = " x1 can be any number - trivial! ",
				EXP_IMGINARY_ROOT_MSG = " Error: NO real roots! ",
				EXP_UNSOLVABLE_MSG = " Error, no answer!! ";
		
		public SquareEquationException(String message) {
			super(message);
		}
	}
	
}



