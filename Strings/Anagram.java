import java.util.*;

public class Anagram {

  /*
   * Design a method that detects whether
   * 2 strings are anagrams.
   *
   * Two words are anagram of each other if
   * there letters can be arranged to form each
   * other.
   *
   * O(nlogn) comparison sort on both: sort both strings, if
   * equal, they are anagrams of each other.
   *
   * O(n) counting sort on alphabet a-z
   *
   * O(n) hash each char in n, verify all of m
   * are in hash and len(n) == len(m)
   */
  public static boolean isAnagramHash(String n, String m) {
    if(n == null || m == null) return false;
    if(n.length() != m.length()) return false;

    HashMap<Character, Integer> nmap = new HashMap<Character, Integer>();
    char[] nchars = n.toCharArray();
    char[] mchars = m.toCharArray();

    for(char c : nchars) {
      if(!nmap.containsKey(c)) {
        nmap.put(c, 0);
      }
      int currentValue = nmap.get(c);
      nmap.put(c, currentValue + 1);
    }

    for(char c : mchars) {
      if(!nmap.containsKey(c) || nmap.get(c) == 0) {
        return false;
      }

      int currentValue = nmap.get(c);
      nmap.put(c, currentValue - 1);
    }

    return true;
  }

  /*
   * Believe java uses quicksort + insertion sort for O(nlogn)
   * Could do this with non-comparison sort in N time with finite
   * alphabet.
   */
  public static boolean isAnagramSort(String n, String m) {
    if(n == null || m == null) return false;
    char[] nChars = n.toCharArray();
    char[] mChars = m.toCharArray();
    Arrays.sort(nChars);
    Arrays.sort(mChars);
    return Arrays.equals(nChars, mChars);
  }

  public static void main(String[] args) {
    System.out.println("Basic Hashing Approach...");
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "ABC", "CBA", true, isAnagramHash("ABC", "CBA")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "ABC", "CCC", false, isAnagramHash("ABC", "CCC")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "A", "B", false, isAnagramHash("A", "B")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "A", "A", true, isAnagramHash("A", "A")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "[empty]", "[empty]", true, isAnagramHash("", "")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "[null]", "[null]", false, isAnagramHash(null, null)));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "[empty]", "A", false, isAnagramHash("", "A")));
    
    System.out.println("\nSorting...");
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "ABC", "CBA", true, isAnagramSort("ABC", "CBA")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "ABC", "CCC", false, isAnagramSort("ABC", "CCC")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "A", "B", false, isAnagramSort("A", "B")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "A", "A", true, isAnagramSort("A", "A")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "[empty]", "[empty]", true, isAnagramSort("", "")));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "[null]", "[null]", false, isAnagramSort(null, null)));
    System.out.println(String.format("Test: %s, %s, expect: %b, given: %b", "[empty]", "A", false, isAnagramSort("", "A")));
  }

}
