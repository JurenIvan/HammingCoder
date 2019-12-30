package hr.fer.ztel.tinf.hamming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HammingCoder {

	private int k;
	private int n;
	private StringBuilder logBuilder;

	public HammingCoder(int n, int k) {
		this.k = k;
		this.n = n;
		checkIfNumberOfPartyBitsEnough(n, k);
		logBuilder = new StringBuilder();
		logBuilder.append("\n\t__Hamming coder logs__\nCreated hammingCoder with n = ").append(n).append(", k = ").append(k).append(" .\n");
	}

	public double getRK() {
		double rk = (double) k / n;
		logBuilder.append("Requested calculation of R(K), R(K) = ").append(rk);
		return rk;
	}

	public String getLogs() {
		return logBuilder.toString();
	}

	public void clearLogs() {
		this.logBuilder = new StringBuilder();
	}

	public List<int[]> run(int[] d) {
		logBuilder.append("Requested coding of ").append(Arrays.toString(d)).append(".\n");
		runTimeChecks(d);

		List<int[]> words = new ArrayList<>();
		for (int i = 0; i < d.length / k; i++)
			words.add(createOutputArray(Arrays.copyOfRange(d, k * i, k * (i + 1))));

		logBuilder.append("Final result is: ");
		for (var word : words)
			logBuilder.append(Arrays.toString(word)).append("\n");

		return words;
	}

	private void runTimeChecks(int[] d) {
		logBuilder.append("Runtime checks:\n1) Is inputted length in line bits that require coding.\n");
		if (d.length % k != 0) {
			logBuilder.append(" FAILED.\n");
			throw new HammingException("It's not possible to calculate hamming code with this combination of params and input.");
		}
		logBuilder.append("2) Is inputted array consisting of 0's and 1's only.\n");
		if (Arrays.stream(d).anyMatch(e -> e > 1 || e < 0)) {
			logBuilder.append(" FAILED.\n");
			throw new HammingException("Illegal input, only bits represented with 0's and 1's allowed");
		}
		logBuilder.append("Checks Passed.\n");
	}

	private int[] createOutputArray(int[] d) {
		int pointer = 0;
		int[] code = new int[n + 1];

		logBuilder.append("Copying inputted bits into new array.\n");
		logBuilder.append("Original Array :\n ").append(Arrays.toString(d)).append("\n");

		for (int i = 1; i <= n; i++)
			if (!exponentOfTwo(i))
				code[i] = d[pointer++];
			else {
				code[i] = 0;
				logBuilder.append(i).append(" is exponent of 2 and this place is reserved for parity bit.\n");
			}
		logBuilder.append("Coding array (1-indexed):\n ").append(Arrays.toString(Arrays.copyOfRange(code, 1, code.length))).append("\n");

		logBuilder.append("Calculating parity bits.\n");
		for (int i = 1; i <= n; i++) {
			if (exponentOfTwo(i)) calculateParityFor(i, code);
		}

		return Arrays.copyOfRange(code, 1, code.length);
	}

	private void calculateParityFor(int pos, int[] code) {
		logBuilder.append("Calculating parity for position ").append(pos).append("\n").append("Taking into considuration data at \n pos    value \n");
		int calculated = 0;
		for (int i = pos; i <= n; ) {
			for (int j = i; j < i + pos && j <= n; j++) {
				calculated += code[j];
				logBuilder.append(String.format("%3d\t\t %d \n", j, code[j]));
			}
			i = i + 2 * pos;
		}
		code[pos] = calculated % 2;
		logBuilder.append("Parity bit for position ").append(pos).append(" is ").append(code[pos]).append(".\n");
	}

	public static boolean exponentOfTwo(int number) {
		return number > 0 && ((number & (number - 1)) == 0);
	}

	public static void checkIfNumberOfPartyBitsEnough(int totalBits, int dataBits) {
		int parityBits = totalBits - dataBits;

		if (totalBits > Math.pow(2, parityBits) - 1 || dataBits > Math.pow(2, parityBits) - parityBits - 1)
			throw new HammingException("No enough parity bits to cover data bits.");
	}
}
