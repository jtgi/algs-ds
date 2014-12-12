public class BoolExpr implements Expr {
  boolean val;

  public BoolExpr(boolean val) {
    this.val = val;
  }

  @Override
  public boolean eval() {
    return val;
  }
}
