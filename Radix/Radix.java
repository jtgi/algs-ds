import java.util.*;

public class Radix {

  private static String input = "Gathered racecar racerac carerac hi ih hello hlloe hleol ehllo elhlo ellho elloh";

  /*
   * Problem definition
   * Given a list of words group by anagram
   *
   * Algorithm:
   * Hash the sorted words while adding unsorted words as values
   * return values of hash
   * We may be able to see a speedup here using a non-comparison
   * based sort but likely to be irrelevant given size of each word
   * 
   * Running Time
   * O(n*mlogm), where n is number of words, m is longest word
   *
   *  FB
   *  Ex. given {trees, bike, cars, steer, arcs} 
   *  return { {cars, arcs}, {bike}, {trees, steer} } 
   *
   *  m = # of words 
   *  n = length of longest word
   */

  public static Collection<ArrayList<String>> solve(String[] words) {
    HashMap<String, ArrayList<String>> output = new HashMap<String, ArrayList<String>>();

    for(int i = 0; i < words.length; i++) {
      String unsorted = words[i];
      String sorted = sortString(unsorted);

      if(output.containsKey(sorted)) {
        output.get(sorted).add(unsorted);
      } else {
        ArrayList<String> lst = new ArrayList<String>();
        lst.add(unsorted);
        output.put(sorted, lst);
      }

    }

    return output.values();
  }

  public static String sortString(String w) {
    char[] cs = w.toCharArray();
    Arrays.sort(cs);
    return new String(cs);
  }

  public static void main(String[] args) {
    Collection<ArrayList<String>> result = solve(input.split(" "));
    for(ArrayList<String> ar : result) {
      System.out.println(Arrays.toString(ar.toArray()));
    }


  }

}
