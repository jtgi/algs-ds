import java.util.*;

public class ArcadeManao {

/*
  Problem Description: 
  http://community.topcoder.com/stat?c=problem_statement&pm=12504 

  Approach:
  We note our player can move only up/down right/left
  further, the map is limited to 50 by 50.
  explore the following paths
  - 1 immediately right and left of
  - all above provided they are less than bound
  - all below provided they are less than bound

  By modeling this problem as a graph, it becomes easier.
  We can simply do a dfs for each possible ladder height.
  since the ladder height is bound at 50, we can get away
  with a brute force.

  Because we visit each node once n*n, for each possible
  later height (n) => O(n*n*n) or something
*/

  	public static void dfs(int x, int y, boolean[][] visited, String[] level, int maxHeight) {
  		if(x < 0 || x > level[0].length()-1) return;
  		if(y < 0 || y > level.length-1) return;
  		if(level[y].charAt(x) != 'X') return;
  		if(visited[x][y]) return;

  		visited[x][y] = true;

  		dfs(x-1, y, visited, level, maxHeight);
  		dfs(x+1, y, visited, level, maxHeight);

  		for(int i = 1; i <= maxHeight; i++) {
			dfs(x, y+i, visited, level, maxHeight);
			dfs(x, y-i, visited, level, maxHeight);
  		}

  	}

	public static int shortestLadder(String[] level, int coinRow, int coinColumn) {
		for(int i = 0; i < level.length; i++) {
			boolean[][] visited = new boolean[level[0].length()][level.length];
			dfs(level[0].length()-1, level.length-1, visited, level, i);
			if(visited[coinColumn-1][coinRow-1]) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		for(int j = 0; j < n; j++) {
			int numLines = s.nextInt(); s.nextLine();
			String[] level = new String[numLines];
			for(int i = 0; i < numLines; i++) {
				level[i] = s.nextLine();
			}

			System.out.println(shortestLadder(level, s.nextInt(), s.nextInt()));
			s.nextLine();
		}
	}

}