
public class MathPrimitives {

    public static double sqrt(double n, double delta) {
        double x = n;

        while(true) {
            System.out.println("Guessing: " + x);
            if(x*x > n-delta && x*x < n+delta) {
                return x;
            } else {
                x = (x + (n / x)) / 2;
            }
        }
    }

    public static void main(String[] args) {
        MathPrimitives.sqrt(100, 0.0000001);
    }
}