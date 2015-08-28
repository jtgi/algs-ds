import java.util.*;

public class Parity {

	private Map<Integer, Integer> parityCache;

	public Parity() {
		parityCache = new HashMap<>();
	}

	/**
	* Parity of a sequence is 1 if number of 1's
	* in the bit sequence for a number is odd, otherwise
	* 0.
	*/
	public int parity(int num) {
		return getParity(num & 0xFFFF) ^ getParity(num >> 16);
	}


	private int computeParity(int num) {
		int result = 0;

		while(num > 0) {
			result ^= (num & 1);
			num = num >> 1;
		}

		return result;
	}

	private int getParity(int num) {
		if(!parityCache.containsKey(num)) {
			parityCache.put(num, computeParity(num));
		}

		return parityCache.get(num);
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Parity p = new Parity();

		while(s.hasNext()) {
			System.out.println(p.parity(s.nextInt()));
		}
	}
}