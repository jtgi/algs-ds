
public class Combinations {
	/*	
		Problem 
		Given a mapping of alphabets to integers as follows: 

		1 = A 
		2 = B 
		3 = C 
		... 
		26 = Z 

		Given any combination of the mapping numbers as string, return the number of ways in which the input string can be split into sub-strings and represented as character strings. For e.g. given 
		"111" -> "AAA", "AK", "KA" -> 3 
		Valid combinations are ({1,1,1}, {1,11},{11,1}) = 3 
		"11" -> "AA", "K" -> 2 
		Valid combinations are ({1,1},{11}) = 2 
		"123" -> "ABC", "LC", "AW" -> 3 
		Valid combinations are ({1,2,3},{1,23},{12,3}) = 3 

		You don't have to return all the mappings, only the number of valid mappings.

		Facts:
		All #'s greater than 26 and less than 0 are invalid.
			- Any combination of numbers greater than 2 is invalid.
			- Any 2 digit number where first num > 2 is invalid.
		Assume number cannot be reordered by example 1, 2, and 3.

		Approach:
		At each step we reduce the string by the first character
		or the first 2 characters so long as they represent a valid
		alphanumeric.
	*/

	/*
	* This is how I would do it in a functional language
	* where strings are cons-lists. I could preprocess this
	* to take a linked list but honestly java just gets in the
	* ways for this kind of programming. It is more java-y to
	* pass and index into the string and then fuss with .
	* Note: This will fail for empty list, have to do a helper
	* function.
	*/
	public static int solve(String indices) {
		if(indices == null) return 0;
		if(indices.isEmpty() || indices.length() == 1) return 1;
		
		int firstPair = Integer.parseInt(indices.substring(0, 2));
		
		if(firstPair < 0 || firstPair > 26) {
			return solve(indices.substring(1));
		} 
		
		return solve(indices.substring(1)) + solve(indices.substring(2));
	}

	/* Cause string are immutable and aren't listy and because
	 * java uses arrays for strings as oppose to lists
	 */
	public static int solveJavaStyle(String indices) {
		if(indices == null || indices.isEmpty())
			return 0;

		return solveJavaStyleHelper(indices, 0);
	}

	private static int solveJavaStyleHelper(String indices, int i) {
		if(i > indices.length()) return 0;
		if(i == indices.length() || i == indices.length() - 1)
			return 1;

		int firstPair = Integer.parseInt("" + indices.charAt(i) + indices.charAt(i+1));
		
		if(firstPair < 0 || firstPair > 26) {
			return solveJavaStyleHelper(indices, i + 1);
		} 
		
		return solveJavaStyleHelper(indices, i + 1) + solveJavaStyleHelper(indices, i + 2);
	}

	public static void main(String[] args) {
		System.out.println(String.format("in: %s, out: %s", "123", solve("123")));
		System.out.println(String.format("in: %s, out: %s", "111", solve("111")));
		System.out.println(String.format("in: %s, out: %s", "11", solve("11")));

		System.out.println(String.format("in: %s, out: %s", "123", solveJavaStyle("123")));
		System.out.println(String.format("in: %s, out: %s", "111", solveJavaStyle("111")));
		System.out.println(String.format("in: %s, out: %s", "11", solveJavaStyle("11")));

		System.out.println(String.format("in: %s, out: %s", "1231", solveJavaStyle("1231")));
		System.out.println(String.format("in: %s, out: %s", "1231", solveJavaStyle("1111")));
	}

}