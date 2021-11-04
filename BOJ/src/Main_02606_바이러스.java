import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_02606_바이러스 {
	static int N;
	static int[][] map;
	static boolean[] virus;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		int M = sc.nextInt();
		virus = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = map[b][a] = 1;
		}

		BFS(1);
		int cnt = 0;
		for (boolean v : virus) {
			if (v)
				cnt++;
		}
		System.out.println(cnt - 1);
	}

	static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		virus[start] = true;
		while (!q.isEmpty()) {
			int from = q.poll();
			for (int i = 1; i <= N; i++) {
				if (map[from][i] == 1 && !virus[i]) {
					virus[i] = true;
					q.offer(i);
				}
			}
		}
	}
}