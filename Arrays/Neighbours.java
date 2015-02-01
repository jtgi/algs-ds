import java.util.*;
import java.awt.*;

public class Neighbours {

  /*
   * Problem:
   * Given an array of ints, group consecutive integers while preserving
   * order
   *
   * Ex:
   *
   * in: [1, 3, 2, 5, 8, 7]
   * out: [1, 3, 2], [5], [8, 7]
   */
  public static ArrayList<ArrayList<Integer>> solve(int[] nums) {
    int[] sorted = nums.clone();
    Arrays.sort(sorted);

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    ArrayList<Point> ranges = partition(nums);

    for(int k = 0; k < ranges.size(); k++) {
      Point range = ranges.get(k);
      for(int i = range.x; i <= range.y; i++) {
        map.put(i, k);
      }
    }

    ArrayList<ArrayList<Integer>> lsts = new ArrayList<ArrayList<Integer>>();

    for(int j = 0; j < ranges.size(); j++)
      lsts.add(new ArrayList<Integer>());

    for(int z = 0; z < nums.length; z++) {
      int pos = map.get(nums[z]);
      lsts.get(pos).add(nums[z]);
    }

    return lsts;
  }

  public static ArrayList<Point> partition(int[] nums) {
    ArrayList<Point> lst = new ArrayList<Point>();
    int start = 0;
    int finish = 1;

    while(finish <= nums.length) {
      if(finish == nums.length || nums[finish] != nums[finish-1] + 1) {
        Point p = new Point(nums[start], nums[finish-1]);
        lst.add(p);
        start = finish;
      }

      finish++;
    }

    return lst;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    while(true) {
      int n = s.nextInt();
      int[] arr = new int[n];

      for(int i = 0; i < n; i++)
        arr[i] = s.nextInt();

      ArrayList<ArrayList<Integer>> result = solve(arr);
      for(ArrayList<Integer> lst : result)
        System.out.print(lst.toString() + " ");

    }
  }

}
