import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_07576_토마토 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					q.offer(new Node(i, j));
			}

		BFS();
		int day = 0;
		boolean clear = true;
		out: for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					clear = false;
					break out;
				}
				day = Math.max(day, map[i][j]);
			}
		day--;
		System.out.println(clear ? day : -1);
	}

	static Queue<Node> q = new LinkedList<>();

	static void BFS() {
		while (!q.isEmpty()) {
			Node from = q.poll();
			int curR = from.r;
			int curC = from.c;
			for (int i = 0; i < 4; i++) {
				int nr = dr[i] + curR;
				int nc = dc[i] + curC;

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (map[nr][nc] != 0)
					continue;

				map[nr][nc] = map[curR][curC] + 1;
				q.offer(new Node(nr, nc));
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

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
}