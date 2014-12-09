
public class MathPrimitives {

	/*
	 * The definition of a square root of n is any
	 * such number x, such that x*x = n. Put simply:
	 *
	 * if x*x = n then x = sqrt(n)
	 * solving for x: x/n = x
	 *
	 * Finding the square root of a number
	 * is an optimization problem, we simply approach
	 * the true square root of a number until satisfied
	 * with our precision. Therefore we must define some
	 * function that gradually converges towards the
	 * the true square root of n. Since a fixed amount
	 * of precision is often good enough in many occasions
	 * we provide a delta `delta` to allow our guestimate
	 * to be +- a delta of the true sqrt(n).
	 *
	 * Heron's Method works in the following manner, we
	 * make a guess x, and take the average of x and n/x.
	 * In this way, we are guaranteed to get a number closer
	 * to that of x. Our guesses will approach the true
	 * square root quadratically until reaching within
	 * our delta.
	 */
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
