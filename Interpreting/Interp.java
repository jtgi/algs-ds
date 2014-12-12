import java.util.*;

/*
 * The Challenge
 * ------------
 * Evaluate: (0 | (1 | 0)) & (1 & ((1 | 0) & 0))
 * where 0 = false
 *       1 = true
 *       | = OR
 *       & = AND
 *
 * We can eval this by writing an extremely basic
 * recursive descent parser with the following grammar:
 *
 * <expr>  ::= <number> | <expr> <binop> <expr>
 * <binop> ::= '&' | '|'
 *
 * The interesting parts of the implementation implement
 * Expr's eval();
 *
 * interface Expr
 * BoolExpr(boolean val) implements Expr
 * BinopExpr(BinopExpr left, char op, BinopExpr right) implements Expr
 *
 * Our parser builds up an AST returning one
 * expression which is evaluated recursively yielding a result.
 *
 */
public class Interp {

  private static int cursor = 0; 

  public static Expr parse(String in) {
    char c = in.charAt(cursor);

    if(Character.isDigit(c)) {
      int digit = Character.getNumericValue(c);
      boolean bool = digit == 1 ? true : false;
      return new BoolExpr(bool);
    } else if(c == '(') {

      cursor++; 
      Expr left = parse(in);

      cursor++;
      char op = in.charAt(cursor);

      cursor++;
      Expr right = parse(in);

      cursor++;
      if(in.charAt(cursor) != ')') {
        throw new IllegalArgumentException("Expected `)` here. but found: " + c);
      }

      return new BinopExpr(left, op, right);
    }

    throw new IllegalArgumentException("Invalid expression");

  }

  public static String preprocess(String in) {
    String s = in.replaceAll("\\s","");
    System.out.println("Processed Input: " + s);
    return s;
  }

  public static boolean interp(Expr expr) {
    cursor = 0; //yuck
    return expr.eval();
  }

  public static boolean run(String input) {
    return interp(parse(preprocess(input)));
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    while(true) {
      System.out.print("Input: ");
      String in = s.nextLine();

      if(in.equals(":q")) {
        System.out.println("Goodbye.");
        System.exit(0);
      }

      boolean result = run(in);
      System.out.println("Output: " + result);
    }
  }

}
