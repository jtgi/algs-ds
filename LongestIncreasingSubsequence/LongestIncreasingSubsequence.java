import java.util.*;

public class LongestIncreasingSubsequence {

  /*
   * Length of the longest increasing subsequence.
   * Given a list simply return the length of longest
   * increasing sequence.
   *
   * Ex: [1 3 4 2] = 3, [1 3 4]
   *
   * Our approach to solving this will be as such,
   * for the ith number, find the maximum increasing
   * subsequence before it that the ith number can
   * be added to (it can be added if it is greater than
   * the last number in the sequence).
   *
   * if there is no sequence that it can be added to then
   * i itself is the longest common sequence.
   * 
   * More formally:
   *
   * lis(i) = 1 + max(lis(j)),    for all 0 < j < i, where val(j) < val(i)
   *          1                   otherwise
   *
   * Diving into the recurrence we see it unfolds in the following manner:
   *
   * lis(i) = 1 + max(lis(0), lis(1), lis(2), ...,  lis(i-1))
   * lis(0) = 1
   * lis(1) = 1 + max(lis(0));
   * lis(2) = 1 + max(lis(0), lis(1));
   * lis(3) = 1 + max(lis(0), lis(1), lis(2));
   * lis(4) = 1 + max(lis(0), lis(1), lis(2), lis(i-1));
   *
   * We can see that we recompute the same sub problems as we go forward. Thus
   * we can simply cache the results dynamic programing style to avoid non-polynomial
   * running time.
   *
   * On completion of the algorithm, lis(i) will then contain the 
   * longest increasing subsequence that ends with i. 
   * This may not be the longest increasing subsequence of the entire
   * list because the longest increasing subsequence may not in fact contain the ith
   * element.
   *
   * For example, when the last number is less than the rest of the list.
   * [1, 2, 3, 0] lis(3) = 1, lis(2) = 3, lis(1) = 2, lis(0) = 1
   *
   * Therefore, we must traverse our solutions and return the max.
   *
   * The following runs in O(N^2) time. 
   * Note: A O(nlgn) solutions does exist.
   */

  public static int lis(int[] arr) {
    int[] sol = new int[arr.length];
    lisHelper(arr, arr.length-1, sol);
    return max(sol);
  }

  private static int lisHelper(int[] arr, int index, int[] sol) {
    if(index == 0) return 1;

    int max = 1;

    for(int i = 0; i < index; i++) {

      if(sol[i] == 0) {
        sol[i] = lisHelper(arr, i, sol);
      }

      if(arr[i] < arr[index] && sol[i] + 1 > max) {
        max = sol[i] + 1;
      }

    }

    sol[index] = max;
    return sol[index];
  }

  private static int max(int[] arr) {
    int max = Integer.MIN_VALUE;
    for(int i : arr) {
      if(i > max) {
        max = i;
      }
    }
    return max;
  }


  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    while(true) {
      String line = s.nextLine();
      String[] nums = line.split(" ");
      int[] n = new int[nums.length];

      for(int i = 0; i < nums.length; i++) {
        n[i] = Integer.parseInt(nums[i]);
      }

      System.out.println(Integer.toString(lis(n)));
    }
  }

}
