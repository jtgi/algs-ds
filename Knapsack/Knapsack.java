import java.util.*;

  /*
   *
   * Thurs, Dec 4, 2014 ===
   *
   * 0 1 Knapsack Problem
   * Given n items with corresponding benefit and weight, and given 
   * a maximum weight w, we attempt to find the set of items S
   * such that the benefit is maximimized. We may use an item zero
   * or one time only.
   *
   * Some thoughts: In problems like these at each stage we can
   * ask ourselves one simple question to move closer to a solution
   *
   * Question: 
   *  Should we include the item? Or not? 
   * Which is naturally followed by: 
   *  If the item is included will it be better than not including it?
   *
   * To answer this apply the problem domain:
   *
   * If we include an item then we have to account for the benefit
   * we gain and the weight that it adds.
   *
   * If we don't include it then it means we are better off with
   * whatever we had before.
   *
   * Since we want to generalize this problem for any weight and
   * any number of items, we need some syntax.
   *
   * Let solution[n, w] = the maximum benefit possible by choosing
   * n items such that the sum of the weights of the items < w
   *
   * Then with number of items `n` at a max weight of `w`
   * we can describe our approach in plain english like this:
   *
   * If number of items is 0 or the weight is 0, the solution is 0.
   * If the item weighs more than the max weight use our previous best weight
   * Otherwise take the max of using the item or just using the previous result.
   *
   * Which we can state more eloquently with a recurrence relation:
   *
   * solution[n, w] = {
   *   max(solution[n-1, w], benefit(n) + solution[n-1, w-weight[n]])   if weights[n] <= w
   *   solution[n-1, w]                                                 if weights[n] > w
   *   0                                                                if n == 0 || w == 0
   * }
   *
   * Since the recurrence shows for any n we need to compute n-1 until ~n-n
   * we can see that as modeled here solving this problem gives requires us
   * to repeatedly solve the same sub-problems (overlapping subproblems)
   * Further we can see that optimal solution for items `n`, 
   * max weight `w` is possibly the optimal
   * solution of the subproblems (optimal subsequence).
   * Thus we can use a dynamic programming approach to cache intermediate
   * results reducing an otherwise O(2^n) solution to O(n*w).
   *
   * Damn Java, this took a lot of code.
   */

public class Knapsack {

  public Knapsack() { /* empty */}

  /*
   * Iteratively we solve this by building up a weights X benefits
   * matrix in typical dynamic programming style. We use this
   * to cache our previous results and make note of each item
   * we include. A final pass over the matrix
   * is done to build up and return the result.
   */
  public ArrayList<KnapsackItem> solveIter(int[] weights, int[] benefits, int maxWeight) {
    int numItems = weights.length;

    ArrayList<KnapsackItem> results = new ArrayList<KnapsackItem>();
    int[][] solution = new int[numItems][maxWeight+1];
    boolean[][] take = new boolean[numItems][maxWeight+1];

    for(int n = 1; n < numItems; n++) {
      for(int w = 1; w <= maxWeight; w++) {

        int dontTakeIt = solution[n-1][w];
        int takeIt = Integer.MIN_VALUE;

        if(weights[n] <= w) {
          takeIt = benefits[n] + solution[n-1][w-weights[n]];
        }

        solution[n][w] = Math.max(dontTakeIt, takeIt);
        take[n][w] = (takeIt > dontTakeIt);
      }
    }

    return buildResult(weights, benefits, take, maxWeight);
  }

  private ArrayList<KnapsackItem> buildResult(int[] weights, int[] benefits, int[] take, int maxWeight) {

    for(int i = weights.length-1, j = maxWeight; i > 0; i--) {
      if(take[i][j]) {
        results.add(new KnapsackItem(i, weights[i], benefits[i], true));
        j = j-weights[i];
      } else {
        results.add(new KnapsackItem(i, weights[i], benefits[i], false));
      }
    }

    return results;
  }

  public class KnapsackItem {
    public int itemNo;
    public int weight;
    public int benefit;
    public boolean included;

    public KnapsackItem(int itemNo, int weight, int benefit, boolean included) {
      this.itemNo = itemNo;
      this.weight = weight;
      this.benefit = benefit;
      this.included = included;
    }

    public String toString() {
      return String.format(
          "item: %d, weight: %d, benefit: %d, included: %b", 
          itemNo, weight, benefit, included
          );
    }
  }

  public static void main(String[] args) {

    if(args.length < 2) { 
      System.out.println("Knapsack numItems maxWeight"); 
      System.exit(1);
    }
    
    Knapsack k = new Knapsack();

    int numItems = Integer.parseInt(args[0]);
    int maxWeight = Integer.parseInt(args[1]);

    int[] weights = new int[numItems+1];
    int[] benefits = new int[numItems+1];

    for(int x = 1; x <= numItems; x++) {
      weights[x] = (int) (Math.random() * maxWeight);
      benefits[x] = (int) (Math.random() * (maxWeight * maxWeight));
    }

    ArrayList<KnapsackItem> results = k.solveIter(weights, benefits, maxWeight);
    for(KnapsackItem item : results) {
      System.out.println(item);
    }
  }

}
