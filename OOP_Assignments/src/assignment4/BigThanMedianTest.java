package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class BigThanMedianTest {

	@Test
	final void testGenerateTwoSortedArraysWithRandomBiasForth() {
		BigThanMedian.setLength(10);
		List<int[]> lst = BigThanMedian.generateTwoSortedArraysWithRandomBias(0.25);
		int[] array = lst.remove(0), array2 = lst.remove(0);
		if(IntStream.range(0, array.length - 1).anyMatch(i -> array[i] >= array[i + 1]))
			fail("Doesn't generate a sorted array, in 1");
		if(IntStream.range(0, array2.length - 1).anyMatch(i -> array2[i] >= array2[i + 1]))
			fail("Doesn't generate a sorted array, in 2");
	}

	@Test
	final void testGenerateTwoSortedArrays() {
		BigThanMedian.setLength(10);
		List<int[]> lst = BigThanMedian.generateTwoSortedArrays();
		int[] array = lst.remove(0), array2 = lst.remove(0);
		if(IntStream.range(0, array.length - 1).anyMatch(i -> {return array[i] > array[i + 1];}))
			fail("Doesn't generate a sorted array, in 1");
		if(IntStream.range(0, array2.length - 1).anyMatch(i -> array2[i] > array2[i + 1]))
			fail("Doesn't generate a sorted array, in 2");
	}

	@Test
	final void testBigThanMedianAlgo() {
		BigThanMedian.setLength(100);
		List<int[]> lst = BigThanMedian.generateTwoSortedArrays();
		int[] array = lst.remove(0), array2 = lst.remove(0);
		int[] ans = BigThanMedian.bigThanMedianAlgo(array, array2);
		if(IntStream.range(0, ans.length).anyMatch(i -> ans[i] < 100))
			fail("A number smaller than median exists!");
	}

	@Test
	final void testBigThanMedianAlgo2() {
		BigThanMedian.setLength(100);
		List<int[]> lst = BigThanMedian.generateTwoSortedArrays();
		int[] array = lst.remove(0), array2 = lst.remove(0);
		int[] ans = BigThanMedian.bigThanMedianAlgo2(array, array2);
		if(IntStream.range(0, ans.length).anyMatch(i -> ans[i] < 100))
			fail("A number smaller than median exists!");
	}

	@Test
	final void testBigThanMedianMerge() {
		BigThanMedian.setLength(100);
		List<int[]> lst = BigThanMedian.generateTwoSortedArrays();
		int[] array = lst.remove(0), array2 = lst.remove(0);
		int[] ans = BigThanMedian.bigThanMedianMerge(array, array2);
		if(IntStream.range(0, ans.length).anyMatch(i -> ans[i] < 100))
			fail("A number smaller than median exists!");
	}

}
