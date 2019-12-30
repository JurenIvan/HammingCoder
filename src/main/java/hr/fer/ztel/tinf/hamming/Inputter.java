package hr.fer.ztel.tinf.hamming;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Inputter {

	public static Inputter fromInputStream(InputStream inputStream) {
		Inputter inputter = new Inputter();
		inputter.scanner = new Scanner(inputStream);
		return inputter;
	}

	public static Inputter fromString(String input) {
		return fromStringArray(List.of(input).toArray(String[]::new));
	}

	public static Inputter fromStringArray(String[] args) {
		String input = String.join(" ", args);
		Inputter inputter = new Inputter();
		inputter.scanner = new Scanner(input);
		return inputter;
	}

	public static Inputter fromStringArrayOrInputStream(String[] args, InputStream is) {
		String input = String.join(" ", args);
		if (input.isBlank())
			return fromInputStream(is);
		return fromStringArray(args);
	}

	private Scanner scanner;

	private Inputter() {
	}

	public int readInt() {
		return scanner.nextInt();
	}

	public int[] readIntArray() {
		return scanner.next().chars().map(e -> e - '0').toArray();
	}

	public void closeScanner() {
		this.scanner.close();
	}
}
