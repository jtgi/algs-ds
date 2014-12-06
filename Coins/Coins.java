import java.util.*;

public class Coins {
  /*
   * Coins
   *
   * Given a set of coins and total
   * find all possible combinations of the coins
   * that sum up to the total
   *
   * Returning the set of all coins was considerably
   * uglier than just the amount of coins.
   *
   */

  /*
   * Return # of combinations possible (but not the 
   * combinations themselves)
   */
  public static int solve(int[] coins, int value) {
    if(value == 0 || coins.length == 0) return 0;
    return solveHelper(coins, value, 0);
  }

  private static int solveHelper(int[] coins, int value, int index) {
    if(index >= coins.length || value < 0) 
      return 0;
    if(value == 0) 
      return 1;
    else 
      return solveHelper(coins, value - coins[index], index) + solveHelper(coins, value, index + 1);
  }

  /*
   * Return all the combinations possible
   */
  public static Set<List<Integer>> solveWithItems(int[] coins, int value) {
    if(coins.length == 0 || value <= 0) return null;
    Set<List<Integer>> output = new HashSet<List<Integer>>();
    return solveWithItemsHelper(coins, value, new int[coins.length], output, 0);
  }

  private static Set<List<Integer>> solveWithItemsHelper(int[] coins, 
                                           int value, 
                                           int[] count, 
                                           Set<List<Integer>> output, 
                                           int index) {

    if(index >= coins.length || value < 0)  
      return output;
    if(value == 0) {
      List<Integer> result = buildResult(coins, count);
      output.add(result);
      return output;
    } else {
      int[] useCoin = count.clone();
      useCoin[index]++;
      solveWithItemsHelper(coins, value-coins[index], useCoin, output, index);
      return solveWithItemsHelper(coins, value, count, output, index+1);
    }
  }

  private static List<Integer> buildResult(int[] coins, int[] count) {
    ArrayList<Integer> coinSet = new ArrayList<Integer>();
    for(int i = 0; i < count.length; i++) {
      for(int j = 1; j <= count[i]; j++) {
        coinSet.add(coins[i]);
      }
    }

    return coinSet;
  }

  public static void main(String[] args) {
    int[] coins = new int[] { 1, 10, 25, 100 };
    int result = solve(coins, 10);
    System.out.println(result);

    Set<List<Integer>> resultSet = solveWithItems(coins, 10);
    for(List<Integer> s : resultSet) {
      System.out.print("Set: ");
      for(Integer i : s) {
        System.out.print(i);
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}
