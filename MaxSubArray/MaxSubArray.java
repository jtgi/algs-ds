import java.util.Arrays;

/*
 * Fri, Dec 5, 2014
 * Maximum Contiguous Sub Array
 *
 * Here we are asked to find the maximum contigous
 * set of numbers in an 1d array.
 *
 * This problem is only interesting when it has
 * negative numbers or we'd trivially take the sum
 * of the array.
 *
 * Example: [-1, 2, 3] = 2 + 3 = 5
 * Example: [-1, 2, -1, 2] = 2 - 1 + 2 = 3
 *
 * We do this by keeping track of our current best
 * sum as well as a working or rolling sum.
 * At every step we compute whether the current
 * element is greater than the sum thus far
 * then we compare whether the that is greater
 * than the best sum we have.
 *
 * The implementation assumes given an array
 * of negative numbers we must choose some
 * minimum from the set. IE we cannot just
 * return 0.
 */

public class MaxSubArray {

  public static int solveIter(int[] arr) {
    if(arr.length == 0) return Integer.MIN_VALUE;
    int currMax = arr[0];
    int bestMax = arr[0];
    
    for(int i = 1; i < arr.length; i++) {
      currMax = Math.max(currMax, currMax + arr[i]);
      bestMax = Math.max(currMax, bestMax);
    }

    return bestMax;
  }

  public static int solveRecur(int[] arr) {
    if(arr.length < 1) return Integer.MIN_VALUE;
    else return solveRecurHelper(arr, arr[0], arr[0], 1);
  }

  private static int solveRecurHelper(int[] arr, int currMax, int bestMax, int i) {
    if(i >= arr.length) return bestMax;
    int newCurrMax = Math.max(currMax, currMax + arr[i]);
    return solveRecurHelper(arr, newCurrMax, Math.max(bestMax, newCurrMax), i+1);
  }

  public static void main(String[] args) {
    int[] t1 = new int[] { -1 };    //-1
    int[] t2 = new int[] { -1, 2 }; //2
    int[] t3 = new int[] { -1, 2, -1 }; //2
    int[] t4 = new int[] { -1, 2, -1, 2 }; //3
    int[] t5 = new int[] { -1, 2, -1, 100 }; //100

    System.out.println("Maximum Sub Array");
    System.out.println(String.format("MaxSubArray of %s: Expect: -1, Given: %d, RecurIter: %b", Arrays.toString(t1), solveIter(t1), solveIter(t1) == solveRecur(t1)));
    System.out.println(String.format("MaxSubArray of %s: Expect: 2, Given: %d, RecurIter: %b", Arrays.toString(t2), solveIter(t2), solveIter(t2) == solveRecur(t2)));
    System.out.println(String.format("MaxSubArray of %s: Expect: 2, Given: %d, RecurIter: %b", Arrays.toString(t3), solveIter(t3), solveIter(t3) == solveRecur(t3)));
    System.out.println(String.format("MaxSubArray of %s: Expect: 3, Given: %d, RecurIter: %b", Arrays.toString(t4), solveIter(t4), solveIter(t4) == solveRecur(t4)));
    System.out.println(String.format("MaxSubArray of %s: Expect: 101, Given: %d, RecurIter: %b", Arrays.toString(t5), solveIter(t5), solveIter(t5) == solveRecur(t5)));
  }

}
