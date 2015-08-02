import java.util.*;

public class Bst {

  public Bst left;
  public Bst right;
  public int value;

  public Bst(int value, Bst left, Bst right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  /*
   * Given a list of numbers create a balanced bst
   * First sort the list, then choose the median
   * and set its left and right child as the
   * median of the left and right half recursively.
   *
   * Running time: O(nlgn) from comparison-sort
   */
  public static Bst createBalancedBst(int[] arr) {
    Arrays.sort(arr);
    return createBalancedBstHelper(arr, 0, arr.length - 1);
  }

  private static Bst createBalancedBstHelper(int[] arr, int lo, int hi) {
    if(hi < lo) return null;
    int mid = (lo + hi) / 2;
    Bst left = createBalancedBstHelper(arr, lo, mid-1);
    Bst right = createBalancedBstHelper(arr, mid+1, hi);
    return new Bst(arr[mid], left, right);
  }

  /*              5
   *            2   10
   *          1    7  15
   *
   *          5
   *          2 -> 10
   *          1 -> 7 -> 15
   */
  public static ArrayList<LinkedList<Bst>> createLevelOrderLinkedList(Bst root) {
    ArrayList<LinkedList<Bst>> lsts = new ArrayList<LinkedList<Bst>>();
    Queue<Bst> q1 = new LinkedList<Bst>();
    Queue<Bst> q2 = new LinkedList<Bst>();

    LinkedList<Bst> rootList = new LinkedList<Bst>();
    rootList.add(root);
    lsts.add(rootList);

    q1.add(root);

    while(!q1.isEmpty()) {
      Bst top = q1.poll();

      if(top.left != null) {
        q2.add(top.left);
      }

      if(top.right != null) {
        q2.add(top.right);
      }

      if(q1.isEmpty()) {
        if(q2.size() > 0) {
          lsts.add(new LinkedList<Bst>(q2));
        }

        Queue<Bst> tmp = q1;
        q1 = q2;
        q2 = q1;
      }
    }

    return lsts;
  }

  public boolean isBalanced() {
    int leftHeight = (left == null) ? 0 : left.height();
    int rightHeight = (right == null) ? 0 : right.height();
    return Math.abs(left.height() - right.height()) <= 1;
  }

  public int height() {
    int leftHeight = (left == null) ? 0 : 1 + left.height();
    int rightHeight = (right == null) ? 0 : 1 + right.height();
    return Math.max(leftHeight, rightHeight);
  }

  public static boolean isBalanced(Bst root) {
    return Math.abs(height(root.left) - height(root.right)) <= 1;
  }

  public static int height(Bst root) {
    if(root == null) return 0;
    int leftHeight = 1 + height(root.left);
    int rightHeight = 1 + height(root.right);
    return Math.max(leftHeight, rightHeight);
  }

  public String toString() {
    StringBuffer buff = new StringBuffer();

    if(left != null) {
      buff.append(left);
    }

    buff.append(value);

    if(right != null) {
      buff.append(right);
    }

    return buff.toString();
  }

  public static void printLevelOrderList(ArrayList<LinkedList<Bst>> lsts) {
    for(LinkedList<Bst> lst : lsts) {
      for(Bst b : lst) {
        System.out.print(b.value + ", ");
      }
      System.out.println();
    }
  }

    /*
     * lca is the node that contains 
     *
     *         10
     *       5    15
     *     2  7  11 20
     *
     *     case 1:   node's left contains b1 and node's right contains b2, return b1
     *     case 1.1: node is b1 and contains b2 in right or left subtree
     *                 return b1.parent
     *     case 1.2: node is b2 and contains b1 in right or left subtree
     *                 return b2.parent
     *     case 2:   node's left contains both b1 and b2
     *               explore left
     *     case 3:   node's right contains both b1 and b2
     *               explore right
     *
     *     OR
     *
     *     we can model this problem as a directed acyclic graph
     *     we run a search on each node keeping track of its parent
     *     at each decision point.
     *     we are then given a link list containing the path to 
     *     each one if its trees
     *     then compare both lists starting from the root the first
     *     node where they differ or the last node is the lca.
     *   
     */

  public static Bst lca(Bst root, Bst b1, Bst b2) {
    ArrayList<Bst> pathb1 = findPath(root, b1, new ArrayList<Bst>());
    ArrayList<Bst> pathb2 = findPath(root, b2, new ArrayList<Bst>());

    if(pathb1 == null || pathb2 == null) return null;

    int i = 0;
    while(i < pathb1.size() && i < pathb2.size()) {
      if(pathb1.get(i) != pathb2.get(i)) {
        return pathb1.get(i-1);
      }
      i++;
    }

    return null;
  }

  /*
   *        5
   *      3   10
   *    0  4 9  20
   */

  public static ArrayList<Bst> findPath(Bst root, Bst target, ArrayList<Bst> path) {
    path.add(root);

    if(root.value == target.value)  {
      return path;
    }

    if(root.left != null) {
      ArrayList<Bst> leftPath = findPath(root.left, target, new ArrayList<Bst>());
      if(leftPath.contains(target)) {
        path.addAll(leftPath);
      }
    }

    if(root.right != null) {
      ArrayList<Bst> rightPath = findPath(root.right, target, new ArrayList<Bst>());
      if(rightPath.contains(target)) {
        path.addAll(rightPath);
      }
    }

    return path;
  }

  public boolean equals(Object other) {
    if(this == other) return true;
    Bst otherBst = (Bst) other;
    return this.value == otherBst.value;
  }

  /*
   * convert tree to list of lists
   * # lists = height
   * n nodes
   *
   *                5
   *     
   *           /         \
   *           1          3
   *         /   \      /   \
   *        4     5    6    7   8
   *       / \  / \  / \  / \ / \
   *      1   2 3  4 5   6 7
   *
   * [5], [1, 3], [4, 5, 6 ,7]
   * width_between_chars = 1space
   * 
   *
   *
   */

  public static void prettyPrint(Bst root) {
    ArrayList<LinkedList<Bst>> lsts = createLevelOrderLinkedList(root);
    int level = height(root);
    int width = Math.pow(2, level);
  }
  public static void main(String[] args) {
    Bst balanced = new Bst(10,
        new Bst(5, null, null), new Bst(15, null, null));

    Bst unbalanced = new Bst(10,
        new Bst(5, new Bst(3, null, null), null), new Bst(15, null, 
                                          new Bst(20, null, 
                                              new Bst(25, null, null))));

    System.out.println(String.format("balanced bst: %b", isBalanced(balanced)));
    System.out.println(String.format("unbalanced bst: %b", isBalanced(unbalanced)));
    System.out.println(String.format("balanced OOP bst: %b", balanced.isBalanced()));
    System.out.println(String.format("unbalanced OOP bst: %b", unbalanced.isBalanced()));

    Bst balancedBst = Bst.createBalancedBst(new int[] { 5, 4, 2, 3, 8 });
    System.out.println(balancedBst.toString());
    System.out.println(String.format("Balanced bst is balanced? %b", balancedBst.isBalanced()));

    ArrayList<LinkedList<Bst>> lsts = createLevelOrderLinkedList(unbalanced);
    printLevelOrderList(lsts);

    Bst result = lca(unbalanced, new Bst(5, null, null), new Bst(15, null, null));
    System.out.println("Value " + result.value);
  }

}
