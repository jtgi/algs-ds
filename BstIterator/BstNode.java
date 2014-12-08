import java.util.*;


  /*
   * I've chosen to do this emulating a recursive method
   * with a stack. We could also do this by searching each
   * time for the successor of the root, but that would be
   * O(logn).
   *
   * The approach is simply to think like a machine and
   * walk through the steps of the canonical recursive
   * implementation where each stack frame corresponds
   * to a push of the node instead.
   *
   * In order to halt in iterative fashion we push the
   * right node as the last step and return the root.
   * That part is admittedly a little tricky.
   *
   */
public class BstNode implements Iterable<BstNode> {

  public BstNode left;
  public BstNode right;
  public int value;

  public BstNode(BstNode left, BstNode right, int value) {
    this.left = left;
    this.right = right;
    this.value = value;
  }

  public Iterator<BstNode> iterator() {
    return new BstIterator(this);
  }

  private static class BstIterator implements Iterator<BstNode> {
    private Stack<BstNode> stack;
    private BstNode head;

    public BstIterator(BstNode root) {
      this.stack = new Stack<BstNode>();
      this.head = root;
    }

    public boolean hasNext() {
      return !stack.isEmpty() || head != null;
    }

    public BstNode next() {
      if(!hasNext()) {
        throw new NoSuchElementException("Empty tree");
      }


      while(head != null) {
        stack.push(head);
        head = head.left;
      }

      BstNode tmp = stack.pop();
      head = tmp.right;

      return tmp;
    }
  }

  public static void main(String[] args) {
    BstNode l4 = new BstNode(null, null, 7);
    BstNode l3 = new BstNode(null, null, 2);
    BstNode l2 = new BstNode(null, null, 15);
    BstNode l1 = new BstNode(l3, l4, 5);
    BstNode root = new BstNode(l1, l2, 10);

    Iterator<BstNode> it = root.iterator();
    while(it.hasNext()) {
      System.out.println(String.format("%d", it.next().value));
    }

  }
}


