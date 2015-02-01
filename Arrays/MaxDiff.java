import java.util.*;

public class MaxDiff {
  /*
   * Problem
   * Maximum difference between two elements such 
   * that larger element appears after the smaller number
   *
   * The approach
   * keep track of max diff and smallest val
   * while iterating over the array.
   * At each step test if new diff > max diff
   * test if val smaller than smallest found.
   *
   * This is suprisingly tricky. the key insight
   * is that the minimum value may change repeatedly
   * without the maxdiff changing. By always comparing
   * each value to the minimum value found thus far we
   * can guarantee that we will be find the max diff
   *
   * the recurrence is something like
   * maxdiff(i, j) = max(maxdiff(i-1), i-j) where j is min val so far.
   */
  public static int solve(int[] arr) {
    if(arr.length < 2) return -1;
    int maxDiff = 0;
    int min = arr[0];

    for(int i = 1; i < arr.length; i++) {
      maxDiff = Math.max(maxDiff, arr[i] - min);
      min = Math.min(min, arr[i]);
    }

    return maxDiff;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    while(true) {
      int n = s.nextInt();
      int[] arr = new int[n];

      for(int i = 0; i < n; i++) {
        arr[i] = s.nextInt();
      }

    System.out.println(String.format("%s", solve(arr)));
    }
  }
}
