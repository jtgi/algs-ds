import java.util.*;

public class BigInt {

  /*
   * Problem:
   * Design a class to support an infinitely
   * large number.
   *
   * Creation should be possible via string.
   * And addition of two non-bigints.
   *
   * Lists can be any size.
   * Store as a list of numbers.
   *
   * Let's say our max num is 10,000
   * then: given 20,000 we store the following
   *
   * Approach #1:
   * Store an offset indicating multiple of overflow.
   * Given: 20,500, store: { multiple: 1, residual: 500 }
   * Given: 40,000, store: { multiple: 2, residual: 0 }
   * Adding  { multiple: 1, residual: 5, 500 } + { multiple: 2, residual: 5, 000 }   
   * Add multiples
   * diff <- maxint - residual1 ( 10,000 - 5,500 = 4,500)
   * if res2 < diff (5, 000 < 4,500) ? NO
   *   complete sum
   * else
   *   increase multiple by 1 (mult++, 
   *   set residual = res2 - diff //500
   *
   * toString:
   *   given: multiple 4, rem: 500
   *   output: 40,000 + 500 = 40,500
   *   123,123
   *   m: 6, r: 3, 123
   *   1, 323, 1232
   *
   * Deficiency: Though simple, this causes us to 
   * needs floats to represent partial multiples of MAX_INT.
   *
   * Next Idea: Base 10 array
   * Assume MAX_INT = 1,000
   *
   * Given: 12,356, 142,539
   * Store as 
   *    [1, 2, 3, 5, 6]
   * [1, 4, 2, 5, 3, 9]
   *
   * Addition 
   *
   *    [1, 2, 3, 5, 6]
   * [1, 4, 2, 5, 3, 9]
   * ------------------
   *  1  5  4  8  9  5
   *
   *  toString:
   *    concat each num
   *
   * This approach works and is much simpler.
   * We could do this in binary as well to save a few bytes
   * potentially and possibly speed up add ops.
   *
   * Follow up:
   * This worked out great. One complication however was
   * combining the two internal lists into one. Since we
   * work from the lower order digits and need to add
   * them last to our new bigint throughout our computation,
   * it is much more efficient to work store the internal
   * representation of the list in reverse. This avoids
   * shifting with an ArrayList giving us O(1) appends while
   * maintaining O(1) reads from other lasts as well as
   * simplifying indexing.
   */

  private ArrayList<Integer> nums;

  public BigInt(String n) {
    this.nums = new ArrayList<Integer>();

    char[] cs = n.toCharArray();
    for(int i = cs.length - 1; i >= 0; i--) {
      nums.add(Character.getNumericValue(cs[i]));
    }
  }

  private BigInt(ArrayList<Integer> base10Array) {
    this.nums = base10Array;
  }

  public BigInt add(BigInt y) {
    ArrayList<Integer> yNums = y.asBase10Array();
    ArrayList<Integer> sum = new ArrayList<Integer>();

    int ySize = yNums.size();
    int xSize = nums.size();
    int maxSize = ySize > xSize ? ySize : xSize;

    int i = 0;
    int j = 0;
    int carry = 0;

    while(i < maxSize || j < maxSize) {
      int columnSum = 0;

      if(i < ySize) {
        columnSum += yNums.get(i);
      }

      if(j < xSize) {
        columnSum += nums.get(j);
      }

      columnSum += carry;
      carry = (columnSum >= 10) ? 1 : 0;
      columnSum = (columnSum >= 10) ? columnSum - 10 : columnSum;

      sum.add(columnSum);
      i++; j++;
    }

    if(carry != 0) {
      sum.add(carry);
    }

    return new BigInt(sum);
  }

  public static BigInt add(BigInt x, BigInt y) {
    return x.add(y);
  }

  public ArrayList<Integer> asBase10Array() {
    return this.nums;
  }

  public String toString() {
    StringBuffer buff = new StringBuffer();
    for(int i = nums.size() - 1; i >= 0; i--) {
      buff.append(Integer.toString(nums.get(i)));
    }
    return buff.toString();
  }

  public static void main(String[] args) {
    String n1 = "112";
    String n2 = "999";
    BigInt bigN1 = new BigInt(n1);
    BigInt bigN2 = new BigInt(n2);

    System.out.println(String.format("Test Storing n1. Expect: %s, Actual: %s", "111", bigN1));

    BigInt bigN3 = BigInt.add(bigN1, bigN1);
    System.out.println(String.format("Test Add n1. Expect: %s, Actual: %s", "222", bigN3));
    BigInt bigN4 = BigInt.add(bigN1, bigN1);
    System.out.println(String.format("Test Add n1. Expect: %s, Actual: %s", "222", bigN4));

    BigInt bigN5 = BigInt.add(new BigInt(Long.toString(Long.MAX_VALUE)), new BigInt(Long.toString(Long.MAX_VALUE)));
    System.out.println(String.format("Test Add n1. Expect: (%s * 2) = 18446744073709551614, Actual: %s", Long.MAX_VALUE, bigN5));
  }


}




