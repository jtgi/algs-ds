
public class Bst {

  public Bst left;
  public Bst right;
  public int value;

  public Bst(int value, Bst left, Bst right) {
    this.value = value;
    this.left = left;
    this.right = right;
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

  }

}
