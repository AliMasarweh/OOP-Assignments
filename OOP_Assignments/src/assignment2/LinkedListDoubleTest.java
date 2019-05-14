package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Testing unit of the LinkedListDouble
 * @author Ali Masarweh
 *
 */
class LinkedListDoubleTest {
	
	private static final String NOT_THROWING_FAIL = "Method didn't throw in an "
			+ "expected situation to throw!",
			WORNG_THROWING_FAIL = "Method threw the wrong expection!",
			THROWNING_FAIL = "Method threw in an expected situtaion to not throw!";

	@Test
	/**
	 * Test of the toString, which uses direct iterator
	 */
	void DirectIteratorTest() {
		LinkedListDouble<String> list = new LinkedListDouble<>();
		char tmp = 'a';
		for (int i = 0; i < 5; i++) {
			list.add(tmp++ +"");
		}
		assertEquals("a, b, c, d, e, ", list.toString());
	}
	
	@Test
	/**
	 * Test of the toStringInReverse, which uses reverse iterator
	 */
	void ReverseIteratorTest() {
		LinkedListDouble<String> list = new LinkedListDouble<>();
		char tmp = 'a';
		for (int i = 0; i < 5; i++) {
			list.add(tmp++ +"");
		}
		assertEquals("e, d, c, b, a, ", list.toStringInReverse());
	}
	
	@Test
	/**
	 * Test of the toString & toStringInReverse methods, on a symmetric list.
	 */
	void LinkedSymmetricListTest() {
		LinkedListDouble<Integer> list = new LinkedListDouble<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		ListIterator<Integer> iterator = list.listIterator();
		LinkedListDouble<Integer> symmetric = new LinkedListDouble<>();
		for (int i = 0; i < list.getSize()*2 - 1; i++) {
			try {
				if(i < list.getSize())
					symmetric.add(iterator.next());
				else
					symmetric.add(iterator.previous());
			} catch (Exception e) {
				fail(THROWNING_FAIL);
			}
		}
		assertEquals(symmetric.toString(),symmetric.toStringInReverse());
		assertEquals(symmetric.toString(), 
				"0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, ");
	}
	
	@Test
	/**
	 * The list is bounded and handles out of bounds errors correctly.
	 */
	void LinkedListBoundnessTest() {
		LinkedListDouble<Integer> list = new LinkedListDouble<>();
		list.add(1);
		list.add(2);
		ListIterator<Integer> iterator = list.listIterator();
		try {
			iterator.previous();
			fail(NOT_THROWING_FAIL);
		} catch (Exception e) {
			if(!(e instanceof NoSuchElementException))
				fail(WORNG_THROWING_FAIL);
		}
	}
	
	@Test
	/**
	 * The list is bounded and handles out of bounds errors correctly. (Reverse iterating)
	 */
	void LinkedListReverseBoundnessTest() {
		LinkedListDouble<Integer> list = new LinkedListDouble<>();
		list.add(1);
		list.add(2);
		ListIterator<Integer> iterator = list.reverseListIterator();
		try {
			iterator.next();
			fail(NOT_THROWING_FAIL);
		} catch (Exception e) {
			if(!(e instanceof NoSuchElementException))
				fail(WORNG_THROWING_FAIL);
		}
	}
	
	@Test
	/**
	 * A combination of the above two tests
	 */
	void LinkedListReverse_RegularBoundnessTest() {
		LinkedListDouble<Integer> list = new LinkedListDouble<>();
		list.add(1);
		list.add(2);
		ListIterator<Integer> iterator = list.listIterator();
		try {
			iterator.previous();
			fail(NOT_THROWING_FAIL);
		} catch (Exception e) {
			if(!(e instanceof NoSuchElementException))
				fail(WORNG_THROWING_FAIL);
		}
		try {
			assertEquals(new Integer(1), iterator.next());
			assertEquals(new Integer(2), iterator.next());
		} catch (Exception e) {
			fail(THROWNING_FAIL);
		}
		try {
			iterator.next();
			fail(NOT_THROWING_FAIL);
		} catch (Exception e) {
			if(!(e instanceof NoSuchElementException))
				fail(WORNG_THROWING_FAIL);
		}
		try {
			assertEquals(new Integer(1), iterator.previous());
		} catch (Exception e) {
			fail(THROWNING_FAIL);
		}
	}

}
