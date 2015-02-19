import java.util.*;

/*
  Problem Description:
  http://community.topcoder.com/stat?c=problem_statement&pm=11502

  The problem is to find all the ways a set of ranges can be arranged
  such that the no vertical lines intersect any of the ranges

  The solution is simply the number of ways a platform can be arranged
  without intersecting a vertical line multiplied by the rest of the ways
  the other platforms can be arranged without intersecting with a vertical
  line.
 */
class YetAnotherIncredibleMachine {

	public static int countWaysHelper(int[] platformMount, int[] platformLength, HashSet<Integer> balls) {
		if(platformMount.length == 0)
			return 1;

		int ways = 0;

		for(int j = 0; j <= platformLength[0]; j++) {
			int start = platformMount[0] - j;
			int end = start + platformLength[0];
			boolean valid = true;

			for(int i = start; i <= end; i++)
				valid = valid || set.contains(i);

			ways += valid ? 1 : 0;
		}

		//ffs java..
		return ways * countWays(Arrays.copyOfRange(platformMount, 1, platformMount.length), Arrays.copyOfRange(platformLength, 1, platformLength.length), balls);
	}

	public static int countWays(int[] platformMount, int[] platformLength, int[] balls) {
		HashSet<Integer> set = new HashSet<Integer>();

		for(int k = 0; k < balls.length; k++)
			set.add(balls[k]);

		return countWaysHelper(platformMount, platformLength, set);
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		for(int i = 0; i < n; i++) {

			int platforms = s.nextInt();
			int numBalls = s.nextInt();
			int[] platformMount = new int[platforms];
			int[] platformLength = new int[platforms];
			int[] balls = new int[numBalls];

			for(int j = 0; j < platforms; j++) {
				platformMount[j] = s.nextInt();
			}

			for(int j = 0; j < platforms; j++) {
				platformLength[j] = s.nextInt();
			}

			for(int j = 0; j < numBalls; j++) {
				balls[j] = s.nextInt();
			}

			System.out.println(Arrays.toString(platformMount));
			System.out.println(Arrays.toString(platformLength));
			System.out.println(Arrays.toString(balls));
			System.out.println(countWays(platformMount, platformLength, balls) % 1000000009);

		}
	}
}