package hr.fer.ztel.tinf.hamming;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExponentOfTwoTest {

	@Test
	public void isExponentOfTwoOf2() {
		assertTrue(HammingCoder.exponentOfTwo(1));
		assertTrue(HammingCoder.exponentOfTwo(2));
		assertTrue(HammingCoder.exponentOfTwo(4));
		assertTrue(HammingCoder.exponentOfTwo(8));
		assertTrue(HammingCoder.exponentOfTwo((int) Math.pow(2, 16)));
		assertTrue(HammingCoder.exponentOfTwo((int) Math.pow(2, 30)));
	}

	@Test
	public void isNotPositiveExponentOf2() {
		assertFalse(HammingCoder.exponentOfTwo(-1));
		assertFalse(HammingCoder.exponentOfTwo(-2));
		assertFalse(HammingCoder.exponentOfTwo(-4));
		assertFalse(HammingCoder.exponentOfTwo(-8));
		assertFalse(HammingCoder.exponentOfTwo((int) Math.pow(-2, 15)));
		assertFalse(HammingCoder.exponentOfTwo((int) Math.pow(-2, 29)));
	}

	@Test
	public void isNotExponentOf2() {
		assertFalse(HammingCoder.exponentOfTwo(3));
		assertFalse(HammingCoder.exponentOfTwo(5));
		assertFalse(HammingCoder.exponentOfTwo(2345));
		assertFalse(HammingCoder.exponentOfTwo(28));
		assertFalse(HammingCoder.exponentOfTwo((int) Math.pow(2, 15) + 1));
		assertFalse(HammingCoder.exponentOfTwo((int) Math.pow(2, 29) + 1));
	}
}
