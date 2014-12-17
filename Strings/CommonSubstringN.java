
public class CommonSubstringN {

  /*
   * Problem:
   * The problem is to find if string m
   * and n have a common substring of len `len`
   *
   * For example:
   * m: ABCD
   * n: ABDBCG
   * len: 2
   *
   *
   * Naive Way (best way?)
   * For each contiguous string of len 2 in s1 (AB, BC, CD)
   * Compare with each len 2 string in s2 with KMP-like-alg
   * if ever equal
   *   return true
   * else
   *   continue
   *
   * O(|S1|*|S2|)
   *
   * w/Hashing
   *
   * 
   *
   * BC, AB are valid answers.
   * We can do this by in O(min(s1, s2)) time by
   * use a sliding window of len `len'
   */
  public static boolean solve(String s1, String s2, int n) {
    String shorter;
    String longer;

    if(s1.length() < s2.length()) {
      shorter = s1;
      longer = s2;
    } else {
      shorter = s2;
      longer = s1;
    }

    //[abcd], n = 2 -> ab bc cd
    //[cd]
    for(int i = 0; i < shorter.length(); i++) {
      if(i + n > shorter.length()) {
        return false;
      } else if(containsSequence(longer, shorter.substring(i, n))) {
        return true;
      }
    }

    return false;
  }

  /*
   * Recursive Version, cleaner :D
   */
  public static boolean solve2(String s1, String s2, int n) {
    String shorter = s1.length() < s2.length() ? s1 : s2;
    String longer = s1.length() < s2.length() ? s2 : s1;

    if(shorter.length() < n) return false;
    return containsSequence(longer, shorter.substring(0, n)) || solve2(s1, s2.substring(n), n);
  }

  /*
   * ABC
   * ABABABCAB
   * 010101201
   * 012345678
   *
   * if our counter ever gets to len of pattern return
   * index - counter.
   *
   * if a character does not match resume counter from
   * last unmatched location with beginning of pattern
   *
   * Checks if n is contained within m. If so,
   * returns the index of the starting position
   * of the substring. Otherwise returns -1
   *
   * O(|text|)
   *
   */
  public static int indexOf(String pattern, String text) {
    int i = 0;
    int j = 0;

    while(i < text.length()) {
      if(pattern.charAt(j) == text.charAt(i)) {
        j++;

        if(j == pattern.length()) {
          int result = (i+1) - j;
          return result;
        }
      } else if(j != 0){
        j = 0;
        continue;
      }

      i++;
    }

    return -1;
  }

  public static boolean containsSequence(String s, String pattern) {
    return indexOf(pattern, s) != -1;
  }

  public static void main(String[] args) {
    System.out.println(String.format("Check substr: t %b", solve2("ABCD", "CD", 2)));
    System.out.println(String.format("Check substr: t %b", solve2("ABCD", "BC", 2)));
    System.out.println(String.format("Check substr: t %b", solve2("ABCD", "AB", 2)));
    System.out.println(String.format("Check substr: f %b", solve2("ABCD", "DE", 2)));
    System.out.println(String.format("Check substr: f %b", solve2("ABCD", "DE", 2)));
  }

}
