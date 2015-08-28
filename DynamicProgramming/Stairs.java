import java.util.*;

public class Stairs {

  /**
   * A child is running up a staircase with n steps, and can hop either
   * 1 step, 2 steps, or 3 steps at a time. Implement a method to count
   * how many possible ways the child can run up the stairs.
   *
   * ways(0) = 0
   * ways(1) = 1
   * ways(2) = 2
   * ways(3) = 4
   * ways(n) = ways(n-3) + ways(n-2) + ways(n-1)
   *
   */

  /**
   * DP iterative style
   * Incorrect for n < 4
   */
  public static int NumWaysIter(int n) {
    int[] ways = new int[n + 1];

    ways[0] = 0;
    ways[1] = 1;
    ways[2] = 2;
    ways[3] = 4;

    for(int i = 4; i <= n; i++) {
      ways[i] = ways[i-3] + ways[i-2] + ways[i-1];
    }

    return ways[n];
  }

  /**
   * DP Mutual Recursion Style.
   */
  public static int NumWaysRecursive(int n) {
    int[] cache = new int[n + 1];
    Arrays.fill(cache, -1);

    return NumWaysRecursive(n, cache);
  }

  private static int NumWaysRecursive(int n, int[] cache) {
    if(cache[n] == -1) {
      cache[n] = NumWaysRecursiveHelper(n, cache);
    }

    return cache[n];
  }

  private static int NumWaysRecursiveHelper(int n, int[] cache) {
    if(n == 0) return 0;
    if(n == 1) return 1;
    if(n == 2) return 2;
    if(n == 3) return 4;

    return NumWaysRecursive(n - 1, cache) +
           NumWaysRecursive(n - 2, cache) +
           NumWaysRecursive(n - 3, cache);
  }

  /**
   * DP standard recursion
   */
  public static int NumWaysRecursiveDeux(int n) {
    int[] cache = new int[n + 1];
    return NumWaysRecursiveDeuxHelper(n, cache);
  }

  private static int NumWaysRecursiveDeuxHelper(int n, int[] cache) {
    if(cache[n] != 0) return cache[n];
    if(n == 0) return 0;
    if(n == 1) return 1;
    if(n == 2) return 2;
    if(n == 3) return 4;

    cache[n] = NumWaysRecursiveDeuxHelper(n - 1, cache) +
               NumWaysRecursiveDeuxHelper(n - 2, cache) +
               NumWaysRecursiveDeuxHelper(n - 3, cache);

    return cache[n];
  }

  public static void main(String[] args) {
    while(true) {
      Scanner s = new Scanner(System.in);
      int n = s.nextInt();

      System.out.println("Recursive " + NumWaysRecursive(n));
      System.out.println("Recursive Deux " + NumWaysRecursiveDeux(n));
      System.out.println("Iterative " + NumWaysIter(n));
    }
  }
}
