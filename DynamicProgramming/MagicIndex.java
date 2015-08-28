import java.util.*;

public class MagicIndex {

  /**
   *
   * A magic index in an array A[0...n-1] is defined to
   * be an index such that A[i] = i. Given a sorted array
   * of distinct integers, write a method to find a magi index
   * if one exists, in Array A
   *
   * Sorted: should probably take advantage of this
   *  0  1  2  3
   * [1, 2, 3, 4]
   *  n  n  n  n
   *
   *  It's trivial to do this in linear time but this would
   *  take no advantage of the sorting aspect of this.
   *
   *  This seems to be a natural divide and conquer.
   *  Since the list is sorted, we know the following:
   *  for i, 0..n:
   *    if A[i] < i then 0..i cannot be a magic index
   *    elif A[i] > i and len(A)-1 - i < A[i] - i then i..n cannot be a magic index
   *    elif A[i] == i found magic index.
   *  end
   *
   *  If compare the midpoint of the array we can be guaranteed to eliminate
   *  50% of the array at each step giving us log time.
   */
  public static int findMagicIndex(int[] nums) {
    if(nums.length == 0) return -1;
    return findMagicIndexHelper(nums, 0, nums.length);
  }

  /**
   * This works for unique integers
   */
  private static int findMagicIndexHelper(int[] nums, int lo, int hi) {
    if(lo > hi) return -1;
    int mid = (hi - lo) / 2;

    if(nums[mid] == mid) {
      return mid;
    } else if(nums[mid] < mid) {
      return findMagicIndexHelper(nums, mid + 1, hi);
    } else {
      return findMagicIndexHelper(nums, lo, mid - 1);
    }
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    while(true) {
      int n = s.nextInt();
      int[] nums = new int[n];
      for(int i = 0; i < n; i++) {
        nums[i] = s.nextInt();
      }

      System.out.println(findMagicIndex(nums));
    }
  }

}
