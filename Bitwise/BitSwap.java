import java.util.*;

public class BitSwap {

	/**
	 * Given a 64 bit integer and two indices, swap
	 * the bits at the given indice and return the resulting
	 * integer.
	 */
	public static long bitSwap(long num, int x, int y) {
		if(((num >> (x - 1)) & 1) != ((num >> (y - 1)) & 1)) {
			num ^= (1 << (x - 1));
			num ^= (1 << (y - 1));
		}

		return num;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(s.hasNext()) {
			long num = s.nextLong();
			int i = s.nextInt();
			int j = s.nextInt();
			System.out.println(bitSwap(num, i, j));
		}
	}
}