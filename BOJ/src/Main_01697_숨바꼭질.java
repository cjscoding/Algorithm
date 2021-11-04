import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_01697_숨바꼭질 {
	static int N, K;
	static int[] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		check = new int[100001];

		if (N == K)
			System.out.println(0);
		else
			BFS(N);
	}

	static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		check[start] = 1;
		while (!q.isEmpty()) {
			int f = q.poll();
			int[] dir = { f - 1, f + 1, 2 * f };
			for (int i = 0; i < 3; i++) {
				if (dir[i] < 0 || dir[i] >= check.length || check[dir[i]] != 0)
					continue;

				if (dir[i] == K) {
					System.out.println(check[f]);// check[f]+1에서 시작점 체크해준 1빼기
					return;
				}

				q.offer(dir[i]);
				check[dir[i]] = check[f] + 1;
			}
		}
	}
}
