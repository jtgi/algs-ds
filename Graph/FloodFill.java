import java.util.*;
import Math.*;
import java.awt.*;

public class FloodFill {
	
	/*
	the problem is as such:
	you are implementing the paint can tool, where you
	click a pixel and it fills up until it hits a boundary
	with that color.

	fill matrix x < 0 || len(matrix) || 
	fill matrix x y
	*/
	public static void solve(int[][] matrix, int x, int y, int target, int replace) {
		if(x < 0 || x > matrix.length-1) return;
		if(y < 0 || y > matrix[0].length-1) return;
		if(matrix[x][y] != target || matrix[x][y] == replace) return;
		matrix[x][y] = replace;

		solve(matrix, x + 1, y, target, replace);
		solve(matrix, x - 1, y, target, replace);
		solve(matrix, x, y + 1, target, replace);
		solve(matrix, x, y - 1, target, replace);
	}

	public static void solveWithStack(double[][] matrix, int x, int y, int target, int replace) {
		Stack<Point> stack = new Stack<Point>();
		stack.push(new Point(x, y));
		while(!stack.empty()) {
			Point pt = stack.pop();

			if(matrix[pt.getX()][pt.getY()] != target || matrix[pt.getX()][pt.getY()] == replace) continue;
			if(pt.getX() < 0 || pt.getX() > matrix.length-1) continue;
			if(pt.getY() < 0 || pt.getY() > matrix[0].length-1) continue;

			matrix[x][y] = replace;


			stack.push(new Point(x + 1, y));
			stack.push(new Point(x - 1, y));
			stack.push(new Point(x, y + 1));
			stack.push(new Point(x, y - 1));
		}

	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
			{ 0, 0, 0, 0 },
			{ 1, 1, 0, 2 },
			{ 1, 0, 0, 2 },
			{ 2, 0, 2, 1 }
		};

		solveWithStack(matrix, 0, 0, 0, 3);

		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

}