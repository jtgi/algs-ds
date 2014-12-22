
public class LinkedList<T> {

  public T val;
  public LinkedList<T> next;

  public LinkedList(T val, LinkedList<T> next) {
    this.val = val;
    this.next = next;
  }

  public static <T> LinkedList<T> reverse(LinkedList<T> head) {
    return reverseHelper(head, null);
  }

  public static <T> LinkedList<T> reverseHelper(LinkedList<T> curr, LinkedList<T> prev) {
    if(curr == null) return prev;
    LinkedList<T> next = curr.next;
    curr.next = prev;
    return reverseHelper(next, curr);
  }

  public String printReverse() {
    if(next == null) {
      return val.toString();
    }
    return String.format("%s -> %s", next.printReverse(), val.toString());
  }

  public String toString() {
      return String.format("%s -> %s", val.toString(), next);
  }

  public static void main(String[] args) {
    LinkedList<Integer> four = new LinkedList<Integer>(4, null);
    LinkedList<Integer> three = new LinkedList<Integer>(3, four);
    LinkedList<Integer> two = new LinkedList<Integer>(2, three);
    LinkedList<Integer> one = new LinkedList<Integer>(1, two);
    System.out.println(one.toString());
    System.out.println(one.printReverse());

    LinkedList<Integer> newHead = reverse(one);
    System.out.println(newHead.toString());
  }


}
