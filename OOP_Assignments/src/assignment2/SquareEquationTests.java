package assignment2;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import assignment2.SquareEquation.SquareEquationException;

/**
 * Testing unit of the square equations
 * @author Ali Masarweh
 *
 */
class SquareEquationTests {
	
	private static final String NOT_THROWING_FAIL = "Method didn't throw in an "
			+ "expected situation to throw!",
			WORNG_THROWING_FAIL = "Method threw the wrong expection!";

	@Test
	/**
	 * Trivial solution error & exception handling
	 */
	void TrivialSolutionTest() {
		SquareEquation SE = new SquareEquation(0, 0, 0);
		try {
			SE.solveForX();
			fail(NOT_THROWING_FAIL);
		} catch (SquareEquationException e) {
			if(!e.getMessage().equals(SquareEquationException.EXP_TRIVIAL_SOLUTIONS_MSG))
				fail(WORNG_THROWING_FAIL);
		}
	}
	
	@Test	
	/**
	 * Imaginary root solution error & exception handling
	 */
	void ImaginaryRootTest() {
			SquareEquation SE = new SquareEquation(1, 0, 1);
			try {
				SE.solveForX();
				fail(NOT_THROWING_FAIL);
			} catch (SquareEquationException e) {
				if(!e.getMessage().equals(SquareEquationException.EXP_IMGINARY_ROOT_MSG))
					fail(WORNG_THROWING_FAIL);
			}
		}
	
	@Test
	/**
	 * Unsolvable solution error & exception handling
	 */
	void UnsolvableTest() {
			SquareEquation SE = new SquareEquation(0, 0, 1);
			try {
				SE.solveForX();
				fail(NOT_THROWING_FAIL);
			} catch (SquareEquationException e) {
				if(!e.getMessage().equals(SquareEquationException.EXP_UNSOLVABLE_MSG))
					fail(WORNG_THROWING_FAIL);
			}
		}
	
	@Test
	/**
	 * Example of correct numbers test
	 */
	void solveForXTest1() {
		SquareEquation SE = new SquareEquation(-2.3, 5.1, 12.98);
		try {
			SE.solveForX();
		} catch (SquareEquationException e) {
			e.printStackTrace();
		}
		assertEquals(SE.get_x1(), -1.5128848463076623 );
		assertEquals(SE.get_x2(), 3.730276150655489 );
	}
	
	@Test
	/**
	 * Example of correct numbers test
	 */
	void solveForXTest2() {
		SquareEquation SE = new SquareEquation(1, -5, 6);
		try {
			SE.solveForX();
		} catch (SquareEquationException e) {
			e.printStackTrace();
		}
		assertEquals(SE.get_x1(), 3.0 );
		assertEquals(SE.get_x2(), 2.0 );
	}
	
	@Test
	/**
	 * Example of correct numbers test
	 */
	void solveForXTest3() {
		SquareEquation SE = new SquareEquation(1, -2, 1);
		try {
			SE.solveForX();
		} catch (SquareEquationException e) {
			e.printStackTrace();
		}
		assertEquals(SE.get_x1(), 1.0 );
		assertEquals(SE.get_x2(), 1.0 );
	}
	
	@Test
	/**
	 * Example of correct numbers test
	 */
	void solveForXTest4() {
		SquareEquation SE = new SquareEquation(0, 2, 5);
		try {
			SE.solveForX();
		} catch (SquareEquationException e) {
			e.printStackTrace();
		}
		assertEquals(SE.get_x1(), -2.5 );
		assertEquals(SE.get_x2(), -2.5 );
	}
	
	@Test
	/**
	 * Example of correct numbers test
	 */
	void solveForXTest5() {
		SquareEquation SE = new SquareEquation(1, 0, 0);
		try {
			SE.solveForX();
		} catch (SquareEquationException e) {
			e.printStackTrace();
		}
		assertEquals(SE.get_x1(), 0.0 );
		assertEquals(SE.get_x2(), 0.0 );
	}
}
