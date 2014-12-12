
/*
 * Binary Operation
 * Binop ::= <expr> <op> <expr>
 */
public class BinopExpr implements Expr {

  Expr left;
  Expr right;
  char op;


  public BinopExpr(Expr left, char op, Expr right) {
    this.left = left;
    this.op = op;
    this.right = right;
  }

  public boolean eval() {
    switch(op) {
      case('&'): 
        return left.eval() && right.eval();
      case('|'):
        return left.eval() || right.eval();
      default:
        throw new IllegalArgumentException("Unrecognized argument");
    }
  }
}
