
public class Edge {

  private final double DEFAULT_WEIGHT = 1.0;
  public Node src;
  public Node dst;
  public double weight;

  public Edge(Node src, Node dst, double weight) {
    this.src = src;
    this.dst = dst;
    this.weight = weight;
  }

  public Edge(Node src, Node dst) {
    this.src = src;
    this.dst = dst;
    this.weight = DEFAULT_WEIGHT;
  }

  public Edge() {
    this.src = null;
    this.dst = null;
    this.weight = DEFAULT_WEIGHT;
  }

  public String toString() {
    return String.format("%d -> %d (%f)", src.id, dst.id, weight);
  }

}
