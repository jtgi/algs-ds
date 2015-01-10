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

  private static int solveWithBarriers(int x, int y, boolean[][] walls) {
    return solveWithBarriersHelper(0, 0, x, y, walls);
  }

  private static int solveWithBarriersHelper(int posX, int posY, int x, int y, boolean[][] walls) {
    if(posX == x && posY == y) return 1;
    if(posX > x || posY > y) return 1;
    if(walls[posX][posY]) return 0;

    return solveWithBarriersHelper(posX + 1, posY, x, y, walls) + solveWithBarriersHelper(posX, posY + 1, x, y, walls);
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    while(true) {
      String in = s.nextLine();

      if(in.equals(":q")) break;
      String[] split = in.split(" ");

      int x = Integer.parseInt(split[0]);
      int y = Integer.parseInt(split[1]);
      boolean[][] walls = new boolean[x+1][y+1];

      for(String wall : Arrays.copyOfRange(split, 2, split.length)) {
        int col = Character.getNumericValue(wall.charAt(1));
        int row = Character.getNumericValue(wall.charAt(3));
        walls[col][row] = true;
      }

      System.out.println(String.format("Max paths in [%d, %d]: %d", x, y, solveWithBarriers(x, y, walls)));
    }
  }

}
