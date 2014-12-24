import java.util.*;

public class Tree {

  private static int visitedCounter = 1;

  private int visited;
  public int value;
  public ArrayList<Tree> children;

  public Tree(int value, ArrayList<Tree> children) {
    this.value = value;
    this.children = children;
    this.visited = 0;
  }

  public static boolean pathExists(Tree src, Tree dst) {
    return bfs(src, dst) != null;
  }

  public static Tree bfs(Tree root, Tree target) {
    Queue<Tree> q = new LinkedList<Tree>();
    q.add(root);
    visitedCounter++;

    while(!q.isEmpty()) {
      Tree t = q.poll();
      t.visited = visitedCounter;

      if(t.equals(target)) {
        return t;
      }

      for(Tree child : t.children) {
        if(child.visited != visitedCounter) {
          q.add(child);
        }
      }
    }

    return null;
  }

  public static void main(String[] args) {
    Tree t1 = new Tree(1, new ArrayList<Tree>());
    Tree t2 = new Tree(2, new ArrayList<Tree>());
    Tree t3 = new Tree(3, new ArrayList<Tree>());
    Tree t4 = new Tree(4, new ArrayList<Tree>());
    Tree t5 = new Tree(5, new ArrayList<Tree>());
    Tree t6 = new Tree(6, new ArrayList<Tree>());
    Tree t7 = new Tree(7, new ArrayList<Tree>()); 

    t1.children.add(t2);
    t2.children.add(t3);
    t3.children.add(t4);
    t4.children.add(t5);

    t5.children.add(t6);
    t5.children.add(t1);
    t5.children.add(t2);

    t6.children.add(t7);

    System.out.println(String.format("pathExists t1 -> t7: %b", pathExists(t1, t7)));
    System.out.println(String.format("pathExists t5 -> t4: %b", pathExists(t5, t4)));

  }

}
