import java.util.*;

public class Trie {

  /*
   * What's a trie?
   * A trie is a tree like structure for storing
   * sequences. It seems to be most beneficial when
   * the items being stored or searched are 
   * likely to have repeating sequences among them.
   *
   * A node in a trie is a trie
   * A node in a trie contains the value of its
   * parents.
   * 
   * As an example: a trie after having inserted
   * fubar, further, fail and fan
   *
   *                   f
   *              a         u
   *           n    il  bar   rther
   *                 
   *
   * Here the trie is rooted at f.
   * All of f's descendants begin with f.
   * You can imagine how this could be quick
   * to find all known words beginning with
   * 'f', you'd simply format the subtrees
   * as desired.
   *
   * Or if a user wanted all words rooted at
   * 'fa' we have to options fan|fail this
   * can also be done quickly.
   *
   * We may also encode frequency information
   * in the trie and use it for counting.
   *
   * Operations:
   * insert = O(n), n = len insertion term.
   * contains = O(n), n = len search term
   * get O(n), n = len search term
   *
   * We can concat strings at the leaves to give
   * quick lookup times.
   *
   * The following is a simple implementation
   * of a trie with no compression at the leaves.
   */

  private String value;
  private ArrayList<Trie> children;

  public Trie(String value) {
    this.value = value;
    this.children = new ArrayList<Trie>();
  }

  public void insert(String key) {
    if(key.equals("")) return;

    for(Trie c : children) {
      if(c.value.startsWith(Character.toString(key.charAt(0)))) {
        c.insert(key.substring(1));
        return;
      }
    }

    Trie t = new Trie(key.substring(0, 1));
    t.insert(key.substring(1));
    children.add(t);
  }

  public Trie search(String key) {
    if(key.equals("")) 
      return this;

    for(Trie c : children) { 
      if(c.value.startsWith(Character.toString(key.charAt(0)))) {
        return c.search(key.substring(1));
      }
    }

    return null;
  }

  public String toString() {
    StringBuffer buff = new StringBuffer();
    LinkedList<Trie> q1 = new LinkedList<Trie>();
    LinkedList<Trie> q2 = new LinkedList<Trie>();
    int level = 0;
    q1.add(this);
    
    while(!q1.isEmpty()) {

      Trie head = q1.pop();
      buff.append(String.format("[%d] %s \t", level, head.value));

      for(Trie c : head.children) {
        q2.add(c);
      }

      if(q1.isEmpty()) {
        LinkedList<Trie> tmp = q1;
        q1 = q2;
        q2 = tmp;
        level++;
        buff.append("\n");
      }

    }

    return buff.toString();
  }

  public boolean contains(String key) {
    return search(key) != null;
  }

  public static void main(String[] args) {
    Trie t = new Trie("");
    t.insert("hello");
    t.insert("heman");
    t.insert("actually");
    t.insert("actor");
    t.insert("cat");
    t.insert("category");
    t.insert("categorical");
    System.out.println(t.toString());

    Trie result = t.search("categor");
    System.out.println(result.toString());
  }
}
