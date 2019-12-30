package hr.fer.ztel.tinf.hamming;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.*;

public class HammingCoderTest {

	@Test
	public void successfulInitialization() {
		new HammingCoder(17, 12);
		new HammingCoder(9, 5);
		new HammingCoder(15, 11);
		new HammingCoder(7, 4);
	}

	@Test
	public void unsuccessfulInitialization() {
		assertThrows(HammingException.class, () -> new HammingCoder(17, 14));
		assertThrows(HammingException.class, () -> new HammingCoder(9, 7));
		assertThrows(HammingException.class, () -> new HammingCoder(15, 13));
		assertThrows(HammingException.class, () -> new HammingCoder(7, 5));
	}

	@Test
	public void RuntimeChecksFails() {
		HammingCoder hammingCoder = new HammingCoder(7, 4);
		assertThrows(HammingException.class, () -> hammingCoder.run(new int[]{0, 1, 0, 1, 0, 1, 0}));
		assertThrows(HammingException.class, () -> hammingCoder.run(new int[]{0, 1, 0, 1, 0}));
		assertThrows(HammingException.class, () -> hammingCoder.run(new int[]{0, 2, 2, 1, 0, 0, 1, 0}));
	}

	@Test
	public void RuntimeChecksSucceeds() {
		HammingCoder hammingCoder;

		hammingCoder = new HammingCoder(7, 4);
		hammingCoder.run(new int[]{0, 1, 0, 1, 0, 1, 0, 0});
		hammingCoder.run(new int[]{0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0});

		hammingCoder = new HammingCoder(17, 12);
		hammingCoder.run(new int[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1});
		hammingCoder.run(new int[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1});
	}

	@Test
	public void correctSingleWordCoding() {
		HammingCoder hammingCoder;
		hammingCoder = new HammingCoder(7, 4);
		assertEqualListsOfArrays(List.of(new int[]{0, 0, 1, 1, 0, 0, 1}), hammingCoder.run(new int[]{1, 0, 0, 1}));

		hammingCoder = new HammingCoder(7, 4);
		assertEqualListsOfArrays(List.of(new int[]{1, 0, 1, 1, 0, 1, 0}), hammingCoder.run(new int[]{1, 0, 1, 0}));

		hammingCoder = new HammingCoder(17, 12);
		assertEqualListsOfArrays(List.of(new int[]{1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1}), hammingCoder.run(new int[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1}));

		hammingCoder = new HammingCoder(9, 5);
		assertEqualListsOfArrays(List.of(new int[]{0, 0, 1, 1, 0, 1, 0, 1, 1}), hammingCoder.run(new int[]{1, 0, 1, 0, 1}));

		hammingCoder = new HammingCoder(15, 11);

		assertEqualListsOfArrays(List.of(new int[]{1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1}), hammingCoder.run(new int[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}));
	}

	@Test
	public void correctMultiWordCoding() {
		HammingCoder hammingCoder;
		hammingCoder = new HammingCoder(7, 4);
		assertEqualListsOfArrays(List.of(new int[]{1, 0, 1, 1, 0, 1, 0}, new int[]{1, 0, 1, 1, 0, 1, 0}), hammingCoder.run(new int[]{1, 0, 1, 0, 1, 0, 1, 0}));

		hammingCoder = new HammingCoder(9, 5);
		assertEqualListsOfArrays(List.of(new int[]{0, 0, 1, 1, 0, 1, 0, 1, 1}, new int[]{0, 0, 1, 1, 0, 1, 0, 1, 1}), hammingCoder.run(new int[]{1, 0, 1, 0, 1, 1, 0, 1, 0, 1}));
	}

	@Test
	public void correctRKCalculation() {
		HammingCoder hammingCoder;
		hammingCoder = new HammingCoder(17, 12);
		assertEquals((double) 12 / 17, hammingCoder.getRK(), pow(10, -6));

		hammingCoder = new HammingCoder(9, 5);
		assertEquals((double) 5 / 9, hammingCoder.getRK(), pow(10, -6));

		hammingCoder = new HammingCoder(15, 11);
		assertEquals((double) 11 / 15, hammingCoder.getRK(), pow(10, -6));

		hammingCoder = new HammingCoder(7, 4);
		assertEquals((double) 4 / 7, hammingCoder.getRK(), pow(10, -6));
	}

	@Test
	public void testLogs() {
		HammingCoder hammingCoder = new HammingCoder(7, 4);
		assertFalse(hammingCoder.getLogs().isBlank());
		hammingCoder.clearLogs();
		assertTrue(hammingCoder.getLogs().isBlank());
	}

	private void assertEqualListsOfArrays(List<int[]> expected, List<int[]> actual) {
		for (int i = 0; i < expected.size(); i++)
			for (int j = 0; j < expected.get(i).length; j++)
				assertEquals(expected.get(i)[j], actual.get(i)[j]);
	}
}
