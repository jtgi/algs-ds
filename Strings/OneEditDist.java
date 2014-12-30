public class OneEditDist {
	

	public static boolean solve(String n, String m) {
		String shorter = n.length() < m.length() ? n : m;
		String longer = n.length() < m.length() ? m : n;
		boolean isDiffLen = (shorter.length() - longer.length()) != 0;

		for(int i = 0; i < shorter.length(); i++) {
			if(shorter.charAt(i) != longer.charAt(i)) {
				if(isDiffLen) {
					return shorter.substring(i).equals(longer.substring(i+1));
				} else {
					return shorter.substring(i+1).equals(longer.substring(i+1));
				}
			}
		}

		return Math.abs(shorter.length() - longer.length()) < 2;
	}

	public static void main(String[] args) {
		System.out.println(String.format("%s, %s: %b", "abc", "ab", solve("abc", "ab")));
		System.out.println(String.format("%s, %s: %b", "abc", "bc", solve("abc", "bc")));
		System.out.println(String.format("%s, %s: %b", "abc", "a", solve("abc", "a")));
		System.out.println(String.format("%s, %s: %b", "abc", "abc", solve("abc", "abc")));
		System.out.println(String.format("%s, %s: %b", "c", "c", solve("c", "c")));
		System.out.println(String.format("%s, %s: %b", "abc", "abx", solve("abc", "abx")));
		System.out.println(String.format("%s, %s: %b", "abc", "abcde", solve("abc", "abcde")));
		System.out.println(String.format("%s, %s: %b", "abc", "xyc", solve("abc", "xyc")));
		System.out.println(String.format("%s, %s: %b", "", "", solve("", "")));
	}
}
