
public class SmallerThanN {


  /* 
   * Given a number n, find a smaller
   * number by rearranging the numbers
   * in n.
   *
   * we can only consider numbers less than the highest order number
   * if a number is in sorted increasing order, it is already smallest.
   * if a number is in sorted decreasing order, we have the most options
   * if a number is in mixed order, we have at least 1 option.
   *
   * unless an item is sorted increasingly or all equal we can always
   * compute a lower number.
   *
   * this can't be done faster than linear to the number of digits in the number.
   * sol1: sort the number, if less return else throw exception O(n) [counting sort]
   *       where n = number of digits in the number.
   * sol2: find the first smaller number than any number previous and swap.
   *       simple, runs in O(n) time, O(n) space.
   * sol3: probably some bit shifting maniac can solve it faster without
   *       stringifying this stuff
   *
   */
  public static int solve(int n) {
    String s = Integer.toString(n);

    if(s.length() <= 1) 
      return n;

    char[] cs = s.toCharArray();
    boolean isNegative = n < 0 ? true : false;
    int start = isNegative ? 2 : 1;

    for(int i = start; i < cs.length; i++) {
      if(isNegative) {
        if(cs[i] > cs[i-1]) {
          swap(cs, i, i-1);
          break;
        }
      } else if(cs[i] < cs[i-1]) {
        swap(cs, i, i-1);
        break;
      }
    }

    return Integer.parseInt(new String(cs));
}

public static void swap(char[] cs, int i, int j) {
  char tmp = cs[i];
  cs[i] = cs[j];
  cs[j] = tmp;
}


  public static void main(String[] args) {
    System.out.println(String.format("In: %d, expect: %d, given: %d", 1, 1, solve(1)));
    System.out.println(String.format("In: %d, expect: %d, given: %d", 54, 54, solve(54)));
    System.out.println(String.format("In: %d, expect: %d, given: %d", 444, 444, solve(444)));
    System.out.println(String.format("In: %d, expect: %d, given: %d", 4454, 4445, solve(4454)));
    System.out.println(String.format("In: %d, expect: %d, given: %d", -4454, -4544, solve(-4454)));
  }

}
