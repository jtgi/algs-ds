import java.util.*;

/*
 * Write a function called eval, which takes 
 * a string and returns a boolean. This string 
 * is allowed 6 different characters: 0, 1, &, 
 * |, (, and ). Eval should evaluate the 
 * string as a boolean expression, where 
 * 0 is false, 1 is true, & is an and, and | 
 * is an or. An example string might look 
 * like "(0 | (1 | 0)) & (1 & ((1 | 0) & 0))"
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
