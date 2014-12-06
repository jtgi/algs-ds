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
  public static int solve(String n, String m) {
    int nlen = n.length();
    int mlen = m.length();
    int[][] maxAlign = new int[nlen + 1][mlen + 1];

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
   * Recursive Implementation.
   * Good for understanding how to convert a recurrence
   * to recursion. Especially as indexes are often glossed
   * over in recurrences. 
   *
   * However, this function is otherwise effectively useless.
   *
   * Running time: O(2^n) 
   */
  public static int solveNaive(String n, String m) {
    return solveRecur(n, m, n.length()-1, m.length()-1);
  }

  public static int solveRecur(String n, String m, int i, int j) {
    if(i < 0 && j < 0) return 0;
    if(i < 0) return (j+1) * gapPenalty;
    if(j < 0) return (i+1) * gapPenalty;

    int matchScore = (n.charAt(i) == m.charAt(j)) ? matchBenefit : mismatchPenalty;

    int leaveIt = solveRecur(n, m, i-1, j-1) + matchScore;
    int addGapN = solveRecur(n, m, i-1, j) + gapPenalty;
    int addGapM = solveRecur(n, m, i, j-1) + gapPenalty;

    return Math.max(leaveIt, Math.max(addGapN, addGapM));
  }

  /*
   * Recursive implementation with caching.
   * I prefer the iterative version. Indexing gets ugly
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

    return solveWithCacheHelper(paddedN, paddedM, paddedN.length()-1, paddedM.length()-1, cache);
  }

  /*
   * Init array to some default val, before computing value
   * check to see if we already have, if so just return the
   * cached result.
   */
  private static int solveWithCacheHelper(String n, String m, int i, int j, int[][] cache) {
    if(cache[i][j] == Integer.MIN_VALUE) {
      cache[i][j] = solveWithCacheCompute(n, m, i, j, cache);
    }

    return cache[i][j];
  }

  /* 
   * Note this function recursively calls solveWithCacheHelper(..) 
   * and not itself.
   */
  private static int solveWithCacheCompute(String n, String m, int i, int j, int[][] cache) {
    if(i == 0 && j == 0) return 0;
    if(i == 0) return (j) * gapPenalty;
    if(j == 0) return (i) * gapPenalty;

    int matchScore = (n.charAt(i) == m.charAt(j)) ? matchBenefit : mismatchPenalty;

    int leaveIt = solveWithCacheHelper(n, m, i-1, j-1, cache) + matchScore;
    int addGapN = solveWithCacheHelper(n, m, i-1, j, cache) + gapPenalty;
    int addGapM = solveWithCacheHelper(n, m, i, j-1, cache) + gapPenalty;

    return Math.max(leaveIt, Math.max(addGapN, addGapM));
  }

  public static void main(String[] args) {
    String n = "hello";
    String m = "ello";

    int scoreDp = solve(n, m);
    int score = solveNaive(n, m);
    int scoreWithCache = solveWithCache(n, m);

    System.out.println(String.format("Score for '%s', '%s': %d", n, m, score));
    System.out.println(String.format("Score for '%s', '%s': %d", n, m, scoreDp));
    System.out.println(String.format("Score for '%s', '%s': %d", n, m, scoreWithCache));
  }
}
