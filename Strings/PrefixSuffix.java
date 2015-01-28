import java.util.*;

public class PrefixSuffix {


	/*
	 * The problem asks for the longest common substring
	 * that is a prefix of s1 and a suffix of s2.
	 * 
	 * Basic Implementation:
	 * Simply align lengths of strings and compare each
	 * letter until there are no letters. Return the first
	 * result as it will be guaranteed to be the longest.

	 * Running time O(n^2) thanks to substring being O(n)
	 * as of java 7.

	 * Can improve with a KMP string pattern matching style
	 * indices, left as an exercise to the reader.
	 */
	public static String solve(String s1, String s2) {
		String shorter = s1.length() < s2.length() ? s1 : s2;
		String longer = s1.length() < s2.length() ? s2 : s1;

		int diff = s1.length() - s2.length();
		int start = diff >= 0 ? 0 : -diff;
		int minLen = Math.min(s1.length(), s2.length());

		int offset = 0;
		String answer = "";

		while(start < longer.length()) {
			if(s1.substring(0, minLen-offset).equals(s2.substring(start))) {
				return s2.substring(start);
			}

			start++; offset++;
		}

	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		while(true) {
			String s1 = s.next();
			String s2 = s.next();
			System.out.println(String.format("s1: %s, s2: %s, ans: %s", s1, s2, solve(s1, s2)));
		}
	}
}