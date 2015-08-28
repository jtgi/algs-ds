import java.util.*;

public class MaxSum {

  /**
   * Problem: return the maximum sum without using neighbouring
   * numbers.
   *
   * Let sol(n, nums) be the maximum sum array w/o using neighbours for numbers
   * 0 through n in nums.
   *
   * Then, sol(n, nums) is just the maximum of either using the nth number
   * and not using the next, or not using the nth number and using the next.
   *
   * More formally:
   *
   * S(0, nums) = nums[0]
   * S(1, nums) = max(nums[0], nums[1])
   * S(n, nums) = max(S(n-2, nums) + nums[n], S(n-1))
   */

  // Recursive without memoization, basically 1:1 with recurrence
  public static int MaxSum(int[] nums) {
    if(nums.length == 0) return 0;
    if(nums.length == 1) return nums[0];
    if(nums.length == 2) return Math.max(nums[0], nums[1]);

    int take = MaxSum(Arrays.copyOfRange(nums, 2, nums.length)) + nums[0];
    int dontTake = MaxSum(Arrays.copyOfRange(nums, 1, nums.length));
    return Math.max(take, dontTake);
  }

  // Iterative w/memoization, sol[n] = maxSum 0 thru n
  public static int MaxSumDp(int[] nums) {
    if(nums.length == 0) return -1;
    if(nums.length == 1) return nums[0];

    int[] sol = new int[nums.length];
    Arrays.fill(sol, Integer.MIN_VALUE);

    // Base cases
    sol[0] = nums[0];
    sol[1] = Math.max(nums[0], nums[1]);

    for(int i = 2; i < nums.length; i++) {
      sol[i] = Math.max(sol[i-2] + nums[i], sol[i-1]);
    }

    return sol[nums.length - 1];
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int[] nums = new int[n];
    for(int i = 0; i < n; i++) {
      nums[i] = s.nextInt();
    }

    System.out.println(MaxSumDp(nums));
  }

}
