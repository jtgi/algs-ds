import java.util.*;

public class Robots {

  public static int solve(int x, int y) {
    return solveHelper(0, 0, x, y);
  }

  private static int solveHelper(int x, int y, int maxX, int maxY) {
    if(x == maxX && y == maxY) 
      return 1;
    if(x > maxX || y > maxY) 
      return 0;

    return solveHelper(x + 1, y, maxX, maxY) + solveHelper(x, y + 1, maxX, maxY);
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    while(true) {
      String in = s.nextLine();
      if(in.equals(":q")) break;
      String[] split = in.split(" ");
      int x = Integer.parseInt(split[0]);
      int y = Integer.parseInt(split[1]);
      System.out.println(String.format("Max paths in [%d, %d]: %d", x, y, solve(x, y)));
    }
  }

}
