package word_game;

import java.util.Scanner;

public class WordGame {

	private static char[][] charMatrix;
	private static String word;
	private static int count = 0;

	public static int find(char[][] chMatr, String w) {
		charMatrix = chMatr;
		word = w;
		countWord();
		countWitRevWord();
		return count;
	}
	
	private static void countWitRevWord() {
		String revStr = new StringBuilder(word).reverse().toString();
		if (!word.equals(revStr)) {
			word = revStr;
			countWord();
		}
	}

	private static void checkRow(int i, int j) {
		if (j + word.length() <= charMatrix[i].length) { // check matrix bounder
			for (int k = 1; k < word.length(); k++) {
				if (word.charAt(k) != charMatrix[i][j + k]) {
					return;
				}
			}
			count++;
		}
	}

	private static void checkColumn(int i, int j) {
		if (i + word.length() <= charMatrix.length) {
			for (int k = 1; k < word.length(); k++) {
				if (word.charAt(k) != charMatrix[i + k][j]) {
					return;
				}
			}
			count++;
		}
	}

	private static void checkRightDiagonals(int i, int j) {
		if ((j + word.length() <= charMatrix[i].length)
				&& (i + word.length() <= charMatrix.length)) {
			for (int k = 1; k < word.length(); k++) {
				if (word.charAt(k) != charMatrix[i + k][j + k]) {
					return;
				}
			}
			count++;
		}
	}

	private static void checkLeftDiagonals(int i, int j) {
		if ((j - word.length() >= 0)
				&& (i + word.length() <= charMatrix.length)) {
			for (int k = 1; k < word.length(); k++) {
				if (word.charAt(k) != charMatrix[i + k][j - k]) {
					return;
				}
			}
			count++;
		}
	}

	private static void countWord() {
		for (int i = 0; i < charMatrix.length; i++) { // rows
			for (int j = 0; j < charMatrix[i].length; j++) { // columns
				if (word.charAt(0) != charMatrix[i][j])
					continue;
				checkRow(i, j);
				checkColumn(i, j);
				checkRightDiagonals(i, j);
				checkLeftDiagonals(i, j);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String word = scanner.next();
		char[][] charMatrix;
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		charMatrix = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				charMatrix[i][j] = scanner.next().charAt(0);
			}
		}
		System.out.println(WordGame.find(charMatrix, word));
	}

}
