import java.util.*;

class EditDistance {

	public static int solve(String s1, String s2) {
		if(s1 == null || s2 == null) return -1;
		if(s1.isEmpty()) return s2.length();
		if(s2.isEmpty()) return s1.length();

		int[][] sol = new int[s1.length() + 1][s2.length() + 1];

		for(int i = 0; i < sol.length; i++) {
			sol[i][0] = i;
		}

		for(int i = 0; i < sol[0].length; i++) {
			sol[0][i] = i;
		}

		for(int i = 1; i < sol.length; i++) {
			for(int j = 1; j < sol[0].length; j++) {
				int leave = s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1;
				leave += sol[i-1][j-1];
				int blankS1 = sol[i-1][j] + 1;
				int blankS2 = sol[i][j-1] + 1;
				sol[i][j] = Math.min(leave, Math.min(blankS1, blankS2));
			}
		}

		return sol[s1.length()][s2.length()];
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(s.hasNext()) {
			String s1 = s.next();
			String s2 = s.next();
			System.out.println("Solution " + solve(s1, s2));
		}
	}
}