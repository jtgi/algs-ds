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

  public static void main(String[] args) {
    Bst balanced = new Bst(10,
        new Bst(5, null, null), new Bst(15, null, null));

    Bst unbalanced = new Bst(10,
        new Bst(5, null, null), new Bst(15, null, 
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

  }

}
