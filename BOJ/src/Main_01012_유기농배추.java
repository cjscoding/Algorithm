import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_01012_유기농배추 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			M = sc.nextInt();
			N = sc.nextInt();
			map = new int[N][M];
			int K = sc.nextInt();

			for (int i = 0; i < K; i++) {
				int X = sc.nextInt();
				int Y = sc.nextInt();
				map[Y][X] = 1;
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						cnt++;
						map[i][j] = 0;
						BFS(i, j);
					}
				}
			}

			System.out.println(cnt);
		}
	}

	static void BFS(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c));
		while (!q.isEmpty()) {
			Node from = q.poll();
			int curR = from.r;
			int curC = from.c;
			for (int i = 0; i < 4; i++) {
				int nr = dr[i] + curR;
				int nc = dc[i] + curC;

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (map[nr][nc] == 0)
					continue;

				q.offer(new Node(nr, nc));
				map[nr][nc] = 0;
			}
		}
	}

	static class Node {
		int r = 0;
		int c = 0;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
}