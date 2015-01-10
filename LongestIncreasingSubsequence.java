
public class LongestIncreasingSubsequence {

  /*
   * Problem:
   * Return the length of the longest sequence of 
   * increasing numbers in an unsorted array
   *
   * Given [1, 4, 2, 0, 3, 9]
   * out: [1, 2, 3, 9] => 4
   *
   * It's easy enough to tell that we cannot
   * make a locally optimal choice in this
   * as given [4, 1, 2] there is no way
   * to know if we should include 4 before
   * examining the remaining numbers.
   *
   * The problem asks for the length of
   * the longest increasing sub array.
   *
   * A naive algorithm could compute
   * this for each number in the sequence.
   *
   * [1, 4, 2, 0, 3, 9]
   *  1  1  0  0  0  1 (take)
   *  1  0  1  0  1  1 (take)
   *  1  0  0  1  
   *
   *  We see here that for each number
   *  we compute n sequences and that to
   *  compute each sequence requires n
   *  operations.
   *
   *  Thus for considering whether to include
   *  a single number in the increasing set
   *  in the naive case, we are looking at
   *  n^2 * (n-1)^2 * (n-2)^2 which is O(n^3).
   *  which is useless.
   *
   *  Given this is a take or dont take problem
   *  and that there is no local optimal choice
   *  it is worth considering a dynamic prog.
   *  approach.
   *
   *  We also notice that we recompute the same
   *  subsequences for each number in the naive
   *  case even. We ask ourselves this, is the
   *  longest increasing subseq. of a part of
   *  the array in the answer for the superset?
   *
   *  if we have some set of ints S whose LISS
   *  is x, if we then add a number to this set
   *  we either have the choice to include it in
   *  a series or not. If we include it we must
   *  remove all those less than that before it
   *  if we can do not include it we keep whatever
   *  answer we have up until now.
   *
   *  Therefore we can define a recurrence in the
   *  following way:
   *
   *  Let LISS(i) be the longest increasing sub
   *  sequence for an i length list. Then:
   *
   *  LISS(i)
   *     | val(i) > val(i-1) = LISS(i-1) + 1
   *     | val(i) < val(i-1) = max(LISS(i-1), LISS(i-2) + 1)
   *     | otherwise = i
   *
   *  [1, 3, 2] 
   *  LISS(3) = max(LISS(2), 
   *
   */
  public static void solve(int[] nums) {
    
  }

  public static void Main(String[] args) {
  }

}
