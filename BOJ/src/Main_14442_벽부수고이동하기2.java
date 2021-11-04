import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_14442_벽부수고이동하기2 {
	static int N, M, K;
	static int[][] map;
	static int[][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		visited = new int[N][M][K + 1];
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++)
				map[i][j] = str.charAt(j) - '0';
		}

		if (N == 1 && M == 1)
			System.out.println(1);
		else
			BFS(0, 0, 1, 0);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class node {
		int r, c, d, k;

		node(int r, int c, int d, int k) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.k = k;
		}
	}

	static void BFS(int r, int c, int d, int k) {
		Queue<node> q = new LinkedList<>();
		q.offer(new node(r, c, d, k));
		visited[r][c][k] = 1;
		while (!q.isEmpty()) {
			node n = q.poll();
			int R = n.r;
			int C = n.c;
			int D = n.d;
			int curK = n.k;

			for (int i = 0; i < 4; i++) {
				int nr = R + dr[i];
				int nc = C + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (curK == K && map[nr][nc] == 1)
					continue;

				if (nr == N - 1 && nc == M - 1) {
					System.out.println(D + 1);
					return;
				}

				if (map[nr][nc] == 0) {
					if (visited[nr][nc][curK] == 0) {
						visited[nr][nc][curK] = 1;
						q.offer(new node(nr, nc, D + 1, curK));
					}
				} else {// 벽일때
					if (visited[nr][nc][curK + 1] == 0) {
						visited[nr][nc][curK + 1] = 1;
						q.offer(new node(nr, nc, D + 1, curK + 1));
					}
				}
			}
		}
		System.out.println(-1);
		return;
	}
}