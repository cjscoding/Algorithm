import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_01249_보급로_Dijkstra {
	static int N, map[][], time[][], INF;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			INF = Integer.MAX_VALUE;
			time = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					if (i == 0 && j == 0)
						continue;
					time[i][j] = INF;
				}

			BFS(new Node(0, 0));

			System.out.println("#" + tc + " " + time[N - 1][N - 1]);
		}
	}

	static void BFS(Node node) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(node);

		while (!q.isEmpty()) {
			Node n = q.poll();
			int r = n.r;
			int c = n.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (time[nr][nc] > time[r][c] + map[nr][nc]) {
					time[nr][nc] = time[r][c] + map[nr][nc];
					q.offer(new Node(nr, nc));
				}
			}
		}
	}
}
