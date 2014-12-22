
public class Node {

  public int id;
  public Node(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object other) {
    if(this == other) return true;

    Node cmp = (Node) other;
    return cmp.id == this.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

}
