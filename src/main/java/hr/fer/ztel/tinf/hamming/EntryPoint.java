package hr.fer.ztel.tinf.hamming;

import java.util.Arrays;
import java.util.List;

public class EntryPoint {

	public static void main(String[] args) {
		Inputter inputter = Inputter.fromStringArrayOrInputStream(args, System.in);

		int n = inputter.readInt();         // total number of bits
		int k = inputter.readInt();         // number of bits carrying information

		try {
			HammingCoder hammingCoder = new HammingCoder(n, k);
			List<int[]> outputWords = hammingCoder.run(inputter.readIntArray());

			System.out.println(formatData(outputWords));
			System.out.println("R(K)=" + hammingCoder.getRK());
			System.out.println(hammingCoder.getLogs());
			hammingCoder.clearLogs();

		} catch (HammingException e) {
			System.out.println(e.getMessage());
		} finally {
			inputter.closeScanner();
		}
	}

	private static String formatData(List<int[]> outputWords) {
		StringBuilder sb = new StringBuilder();
		for (var word : outputWords)
			sb.append(Arrays.toString(word));
		return sb.toString();
	}
}
