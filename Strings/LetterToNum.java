import java.util.*;

public class LetterToNum {

  //Letters are represented by numbers, e.g.
  //A = 1
  //B = 2
  //...
  //J = 10
  //K = 11
  //etc.
  //
  //Given a number (e.g. 113), how many different combinations of letters could this be converted to? 
  public static int solve(int num) {
    return solveRecur(Integer.toString(num), 0);
  }

  private static int solveRecur(String num, int index) {
    if(index > num.length()) return 0;
    if(index == num.length()) return 1;

    int useTwo = 0;
    if(index + 2 <= num.length()) {
      int takeTwo = Integer.parseInt(num.substring(index, index+2));
      if(takeTwo <= 26) {
        useTwo = solveRecur(num, index + 2);
      }
    }

    return solveRecur(num, index + 1) + useTwo;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    while(true) {
      int n = s.nextInt();
      System.out.println("Sol: " + solve(n));
    }



  }

}
