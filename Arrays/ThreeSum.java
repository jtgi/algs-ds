import java.util.*;

public class ThreeSum {

  /*
   * Problem:
   * Given an array of ints determine if any 3
   * of them add to 0. You may re-use the same
   * #.
   *
   * Approach:
   * simply looking at it this way makes it obvious
   * a + b + c = 0 <=> a + b = -c
   *
   * so, simply: give each item a turn being a
   * add every other element to it and check to
   * see if there is a negative of that sum.
   *
   * Use a hashmap to make lookups O(1)
   *
   */
  public static boolean threeSum(int[] nums) {
    if(nums.length == 0) return false;
    HashSet<Integer> set = new HashSet<Integer>();

    for(int i = 0; i < nums.length; i++)
      set.add(nums[i]);

    for(int j = 0; j < nums.length; j++) {
      for(int k = j; k < nums.length; k++) {
        int sum = nums[j] + nums[k];
        if(nums[j] == 0 || set.contains(-sum)) {
          return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    while(true) {
      int n = s.nextInt();
      int[] nums = new int[n];
      for(int i = 0; i < n; i++) {
        nums[i] = s.nextInt();
      }

      System.out.println(threeSum(nums));
    }
  }

}
