import java.util.*;
import java.awt.Point;

class IntervalUnion {

	// [1, 3], [2, 4], [3, 4], [0, 5], [8, 10]
	// -> [0, 5], [8, 10]


	public static List<Point> solve(List<Point> intervals) {
		if(intervals == null || intervals.size() == 0) {
			return null;
		}

		Collections.sort(intervals, new Comparator<Point>() {
			public int compare(Point self, Point other) {
				return Integer.valueOf(self.x).compareTo(Integer.valueOf(other.x));
			}
		});

		List<Point> out = new ArrayList<>();
		Queue<Point> intervalQueue = new LinkedList<>(intervals);

		while(!intervalQueue.isEmpty()) {
			Point head = intervalQueue.poll();
			out.add(head);

			Point next = intervalQueue.peek();
			while(next != null && canMerge(head, next)) {
				next = intervalQueue.poll();
				merge(head, next);
				next = intervalQueue.peek();
			}
		}

		return out;
	}

	public static boolean canMerge(Point p1, Point p2) {
		if(p1 == null || p2 == null) 
			return false;

		return (p1.x <= p2.x && p1.y >= p2.x) || 
			   (p2.x <= p1.x && p2.y >= p1.x) ||
			   (p1.x <= p2.x && p1.y >= p2.y) ||
			   (p2.x <= p1.x && p2.y >= p1.y); 
	}

	public static void merge(Point dst, Point src) {
		if(canMerge(dst, src)) {
			System.out.print("mergin " + dst + " " + src);
			dst.x = Math.min(dst.x, src.x);
			dst.y = Math.max(dst.y, src.y);
			System.out.println(":: result " + dst);
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		while(s.hasNext()) {
			int n = s.nextInt();
			List<Point> intervals = new ArrayList<>();

			for(int i = 0; i < n; i++) {
				intervals.add(new Point(s.nextInt(), s.nextInt()));
			}

			System.out.println("Solution :: " + solve(intervals));
		}

	}
}