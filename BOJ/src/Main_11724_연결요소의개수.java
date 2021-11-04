import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main_11724_연결요소의개수 {
	static int N, M, map[][];
	static boolean visited[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();// 정점개수
		M = sc.nextInt();// 간선개수
		map = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			int U = sc.nextInt();
			int V = sc.nextInt();
			map[U][V] = map[V][U] = 1;
		}

		int cnt = 0;
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				cnt++;
				BFS(i);
			}
		}
		System.out.println(cnt);
	}

	static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		q.offer(start);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i <= N; i++) {
				if (visited[i] || cur == i || map[cur][i] == 0)
					continue;

				visited[i] = true;
				q.offer(i);
			}
		}
	}
}