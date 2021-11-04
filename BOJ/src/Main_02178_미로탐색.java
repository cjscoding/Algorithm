import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main_02178_미로탐색 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++)
				map[i][j] = str.charAt(j) - '0';
		}

		BFS(new Node(0, 0));

		System.out.println(map[N - 1][M - 1]);
	}

	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static void BFS(Node n) {
		Queue<Node> q = new LinkedList<>();
		q.offer(n);
		while (!q.isEmpty()) {
			Node from = q.poll();
			int R = from.r;
			int C = from.c;

			for (int i = 0; i < 4; i++) {
				int nr = R + dr[i];
				int nc = C + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (map[nr][nc] != 1)
					continue;

				map[nr][nc] = map[R][C] + 1;
				q.offer(new Node(nr, nc));
			}
		}
	}
}