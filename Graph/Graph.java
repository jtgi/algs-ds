
public abstract class Graph {

  private int numVertices;
  private boolean isDirected;

  public abstract boolean hasEdge(Node src, Node dst);
  public abstract int numVertices();
  public abstract boolean isDirected();
  public abstract void insert(Edge e);
  public abstract Edge getEdge(Node src, Node dst);
}
