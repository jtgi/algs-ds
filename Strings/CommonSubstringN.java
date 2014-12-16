
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

  public static boolean containsSequence(String s, String pattern) {
    return s.indexOf(pattern) != -1;
  }

  public static void main(String[] args) {
    System.out.println(String.format("Check substr: t %b", solve("ABCD", "CD", 2)));
    System.out.println(String.format("Check substr: t %b", solve("ABCD", "BC", 2)));
    System.out.println(String.format("Check substr: t %b", solve("ABCD", "AB", 2)));
    System.out.println(String.format("Check substr: f %b", solve("ABCD", "DE", 2)));
    System.out.println(String.format("Check substr: f %b", solve("ABCD", "DE", 2)));
  }

}
