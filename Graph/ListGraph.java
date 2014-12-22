import java.util.*;

public class ListGraph extends Graph {
  private HashMap<Node, ArrayList<Edge>> graph;
  private boolean isDirected;

  public ListGraph() {
    this.graph = new HashMap<Node, ArrayList<Edge>>();
    this.isDirected = false;
  }

  public ListGraph(int numNodes, boolean isDirected) {
    this.isDirected = isDirected;
    this.graph = new HashMap<Node, ArrayList<Edge>>();

    for(int i = 0; i < numNodes; i++) {
      graph.put(new Node(i), new ArrayList<Edge>());
    }
  }

  public boolean hasEdge(Node src, Node dst) {
    if(graph.containsKey(src)) {
      System.out.println("contains key!");
      ArrayList<Edge> edges = graph.get(src);
      for(Edge e : edges) {
        System.out.println(e);
        if(e.dst.equals(dst)) {
          return true;
        }
      }
      System.out.println("did not find edge");
    }

    return false;
  }

  public int numVertices() {
    return graph.size();
  }
  public boolean isDirected() {
    return isDirected;
  }

  public void insert(Edge e) {
    if(graph.containsKey(e.src) && graph.containsKey(e.dst)) {
      ArrayList<Edge> edges = graph.get(e.src);
      if(!edges.contains(e)) {
        edges.add(e);
      }
    }

    if(!isDirected()) {
      insert(new Edge(e.dst, e.src, e.weight));
    }
  }

  public Edge getEdge(Node src, Node dst) {
    if(graph.containsKey(src) && graph.containsKey(dst)) {
      ArrayList<Edge> edges = graph.get(src);
      for(Edge e : edges) {
        if(e.src == src && e.dst == dst) {
          return e;
        }
      }
    }

    return null;
  }

  public static void main(String[] args) {
    ListGraph g = new ListGraph(5, true);

    //Hmm, is this the best way to do this?
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);

    g.insert(new Edge(n1, n2, 20.0));
    g.insert(new Edge(n2, n3, 5.0));
    g.insert(new Edge(n4, n5, 10.0));
    g.insert(new Edge(n5, n1, 15.0));

    System.out.println(String.format("Edge from n1 to n2? %b", g.hasEdge(n1, n2)));
    System.out.println(String.format("Edge from n2 to n3? %b", g.hasEdge(n2, n3)));
    System.out.println(String.format("Edge from n3 to n4? %b", g.hasEdge(n3, n4)));
  }



}
