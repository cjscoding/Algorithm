import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_01953_탈주범검거 {
	static int N, M, R, C, L, cnt;
	static int[][] map;
	static boolean visited[][];
	static int[][] way = { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 1, 0, 0, 1 },
			{ 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 1, 0 } };

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Node {
		int r, c, l;

		Node(int r, int c, int l) {
			this.r = r;
			this.c = c;
			this.l = l;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			cnt = 0;
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			visited = new boolean[N][M];

			BFS(new Node(R, C, 1));

			System.out.println("#" + tc + " " + cnt);
		}
	}

	static void BFS(Node node) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(node);
		visited[R][C] = true;

		while (!q.isEmpty()) {
			Node n = q.poll();
			int r = n.r;
			int c = n.c;
			int l = n.l;
			if (l == L + 1)
				break;
			cnt++;

			for (int i = 0; i < 4; i++) {

				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				int cur = map[r][c];
				int next = map[nr][nc];

				if (visited[nr][nc] || next == 0 || way[cur][i] != 1)
					continue;

				if (i == 0) {
					if (way[next][1] != 1)
						continue;
				} else if (i == 1) {
					if (way[next][0] != 1)
						continue;
				} else if (i == 2) {
					if (way[next][3] != 1)
						continue;
				} else if (i == 3) {
					if (way[next][2] != 1)
						continue;
				}

				visited[nr][nc] = true;
				q.offer(new Node(nr, nc, l + 1));
			}
		}
	}
}
