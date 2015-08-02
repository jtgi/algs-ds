import java.util.*;

public class Combinations {

  //[1, 2, 3]
  //[1, 1, 1]
  //[1, 2, 2] 3^16 / 3^16
  public static HashSet<ArrayList<Integer>> combiWithRepetition(int[] nums) {
    HashSet<ArrayList<Integer>> lsts = new HashSet<ArrayList<Integer>>();
    return combiWithRepsHelper(nums, 0, new ArrayList<Integer>(), lsts);
  }

  private static HashSet<ArrayList<Integer>> combiWithRepsHelper(int[] nums, 
                                                 int index, 
                                                 ArrayList<Integer> output,
                                                 HashSet<ArrayList<Integer>> lsts) {
    if(index >= nums.length) {
      lsts.add(output);
      return lsts;
    }

    for(int i = 0; i < nums.length; i++) {
      output.add(nums[i]);
      combiWithRepsHelper(nums, index + 1, new ArrayList<Integer>(output), lsts);
      output.remove(index);
    }

    return lsts;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    while(true) {
      int n = s.nextInt();
      int[] nums = new int[n];
      for(int i = 0; i < n; i++) {
        nums[i] = s.nextInt();
      }
      HashSet<ArrayList<Integer>> result = combiWithRepetition(nums);
      for(ArrayList<Integer> lst : result) {
        System.out.println(lst);
      }
    }

	}

}
