import java.util.Arrays;

public class SeqAlign {

  /*
   * The Global Sequence Alignment Problem
   *
   * We're given 2 sequences of somethings and
   * asked to find the best global alignment score
   * and to return the best alignment.
   *
   * EX:
   *
   * Given strings ABCD BCD
   *
   * We have the following operations at our disposal
   * Insert a gap into string 1 or 2 shown by '1'
   * Leave the character as is.
   *
   * If insert a gap we pay a penalty `gap_penalty`
   * If we levae the characters as, if they are the
   * same, we pay a mismatch cost, otherwise we gain
   * a match benefit.
   *
   * The best global sequence alignment is one where
   * the score of the total alignment is maximized
   *
   * This problem is typically discussed
   * in context of bioinformatics for matching
   * between 2 DNA sequences or computing
   * the 'distance' 2 sequences differ.
   *
   * We could propose a score of 1 for each
   * aligned character and a -1 for each 
   * unaligned character.
   *
   * The rules are as such:
   *
   * Given a pair of sequences (X, Y),
   * a valid alignment (X', Y') is one such that
   *
   * Let F[i, j] = the maximum global alignment score
   * for strings i, j.
   *
   * Then,
   * F[i, j] =  0                 if i = j = 0
   *            j*gap_penaltly    if i = 0
   *            i*gap_penalty     if j = 0
   *            otherwise 
   *             max {  F[i-1, j-1] + (match(i, j) ? score : mismatch_penalty),
   *                  F[i-1, j] + gap_penalty,
   *                  F[i, j-1] + gap_penatly
   *                }
   *
   */
  static int gapPenalty = -1;
  static int mismatchPenalty = -1;
  static int matchBenefit = 1;

  /*
   * Iterative Implementation
   * Typical DP style. Watch those indexes though;
   * they be tricky.
   */ 
  public static void solve(String n, String m) {
    int nlen = n.length();
    int mlen = m.length();

    int[][] maxAlign = new int[nlen + 1][mlen + 1];
    score(n, m, maxAlign);

    int maxLen = Math.max(nlen, mlen);
    char[] out1 = new char[maxLen];
    char[] out2 = new char[maxLen];

    backtrack(n, m, maxAlign, nlen, mlen, out1, out2);

    System.out.println(String.format("%s \n %s", new String(out1), new String(out2)));
  }

  public static int score(String n, String m, int[][] maxAlign) {
    int nlen = n.length();
    int mlen = m.length();

    for(int q = 0; q <= nlen; q++) 
      maxAlign[q][0] = q * gapPenalty;

    for(int r = 0; r <= mlen; r++) 
      maxAlign[0][r] = r * gapPenalty;

    for(int i = 1; i <= nlen; i++) {
      for(int j = 1; j <= mlen; j++) {
          int matchScore = (n.charAt(i-1) == m.charAt(j-1)) ? matchBenefit : mismatchPenalty;

          int leaveIt = maxAlign[i-1][j-1] + matchScore;
          int addGapN = maxAlign[i-1][j] + gapPenalty;
          int addGapM = maxAlign[i][j-1] + gapPenalty;

          maxAlign[i][j] = Math.max(leaveIt, Math.max(addGapN, addGapM));
      }
    }

    return maxAlign[nlen][mlen];
  }

  /*
   * We return void here and use some state 
   * because no tuples and dont want to write again
   */
  public static void backtrack(String n, String m, int[][] scores, int i, int j, char[] out1, char[] out2) {
    while(i > 0 && j > 0) {

      if(i > 0 && scores[i-1][j] + gapPenalty == scores[i][j]) {
        out1[i] = n.charAt(i-1);
        out2[j] = '-';
        i--;
      } else if(j > 0 && scores[i][j-1] + gapPenalty == scores[i][j]) {
        out1[i] = '-';
        out2[j] = n.charAt(j-1);
        j--;
      } else {
        out1[i] = n.charAt(i-1);
        out2[j] = m.charAt(j-1);
        i--; j--;
      }
    }
  }

  /*
   * Recursive implementation with caching.
   * Indexing gets ugly
   * as I use length of strings in 0-based arrays and as
   * bounds. Plus alloc'ing stack frames in quadratic alg
   * is no good with a VM that doesn't support tail calls
   * and a impl that also doesn't tail recurse.
   *
   * Running Time: O(n*m)
   */
  public static int solveWithCache(String n, String m) {
    String paddedN = " " + n;
    String paddedM = " " + m;

    int[][] cache = new int[paddedN.length()][paddedM.length()];

    for(int[] row : cache)
      Arrays.fill(row, Integer.MIN_VALUE);

    return solveWithCacheCompute(paddedN, paddedM, paddedN.length()-1, paddedM.length()-1, cache);
  }

  /* 
   * Note this function recursively calls solveWithCacheHelper(..) 
   * and not itself.
   */
  private static int solveWithCacheCompute(String n, String m, int i, int j, int[][] cache) {
    if(cache[i][j] != Integer.MIN_VALUE) return cache[i][j];
    if(i == 0 && j == 0) return 0;
    if(i == 0) return (j) * gapPenalty;
    if(j == 0) return (i) * gapPenalty;

    int matchScore = (n.charAt(i) == m.charAt(j)) ? matchBenefit : mismatchPenalty;

    int leaveIt = solveWithCacheCompute(n, m, i-1, j-1, cache) + matchScore;
    int addGapN = solveWithCacheCompute(n, m, i-1, j, cache) + gapPenalty;
    int addGapM = solveWithCacheCompute(n, m, i, j-1, cache) + gapPenalty;

    cache[i][j] = Math.max(leaveIt, Math.max(addGapN, addGapM));
    return cache[i][j];
  }

  public static void main(String[] args) {
    String n = "hellosadfalkjdafdlk;ajsdfas";
    String m = "elloasdfsaddfasdfasdksdfas";

    solve(n, m);
    solveWithCache(n, m);
  }
}
