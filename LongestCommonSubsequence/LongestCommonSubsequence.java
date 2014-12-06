package me.jtgi;

/**
 * Created by John on 12/6/2014.
 */
public class LongestCommonSubsequence {

    public static String lcs(String m, String n) {
        int[][] matrix = new int[m.length()+1][n.length()+1];
        fillMatrix(matrix, 0, m.length(), n.length());
        findCommonChars(matrix, m, n);
        return findPath(matrix, " " + m, " " + n, m.length(), n.length());
    }

    private static void fillMatrix(int[][] matrix, int value, int maxX, int maxY) {
        for(int i = 0; i < maxX; i++) {
            for(int j = 0; j < maxY; j++) {
                matrix[i][j] = value;
            }
        }
    }

    public static void findCommonChars(int[][] matrix, String m, String n) {
        for(int i = 1; i < m.length(); i++) {
            for(int j = 1; j < n.length(); j++) {
                if(m.charAt(i) == n.charAt(j)) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }
    }

    /*
     * Work backwards exploring graph through maximums until
     * back to position 0 again.
     */
    public static String findPath(int[][] matrix, String m, String n, int x, int y) {
        if(x == 0 || y == 0) {
            return "";
        } else if(m.charAt(x) == n.charAt(y)) {
            return findPath(matrix, m, n, x-1, y-1) + m.charAt(x);
        } else {
            if(matrix[x][y-1] > matrix[x-1][y]) {
                return findPath(matrix, m, n, x, y-1);
            } else {
                return findPath(matrix, m, n, x-1, y);
            }
        }
    }

    public static void lcsTest() {
        lcsRunner("abc", "abc", "abc");
        lcsRunner("A", "", "empty");
        lcsRunner("", "C", "empty");
        lcsRunner("ABCABCDEF", "ABCD", "ABCD");
        lcsRunner("ABDADADAXY", "KJDFABDXY", "ABDXY");
        lcsRunner("AHBDCRD", "LOBCDPN", "BC");
    }

    private static void lcsRunner(String n, String m, String expected) {
        System.out.println(String.format("[%s, %s], Expect: ABC, Given: %s", n, m, lcs(n, m)));
    }

}
