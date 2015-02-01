import java.util.*;

public class ConsecutiveChar {


	// Given a string return the characters that repeat most not
	// including spaces

	// example i/o
	// 'this is a test sentence' => [t, h, i, s, i, s, a, t, e, s, t, s, e, n, t, e, n, c, e]
	// 'thiis iss a teest seentennce' => [i, s, e, e, n]
	// 'thiiis iss aa teeest seentennnce' => [i, e, n]
	// 'thiiiis iss a teeest seeentennncccce' => [i, c]
	// Approach: ezpz no explain

	public static ArrayList<Character> solve(String in) {
		ArrayList<Character> lst = new ArrayList<Character>();

		if(in == null) 
			return lst;

		String s = in.replace(" ", "");

		if(s.isEmpty())
			return lst;

		lst.add(s.charAt(0));
		int maxSeq = 1;
		int seq = 1;

		for(int i = 1; i < s.length(); i++) {

			if(s.charAt(i) == s.charAt(i-1))  {
				seq++;
			} else {
				seq = 1;
			}

			if(seq > maxSeq) {
				maxSeq = seq;
				lst.clear();
				lst.add(s.charAt(i));
			} else if(seq == maxSeq) {
				lst.add(s.charAt(i));
			}

		}

		return lst;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.println(solve(s.nextLine()));
		}
	}
}