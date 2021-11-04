import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_02206_벽부수고이동하기 {
	static int N, M, ans;
	static int[][] map;
	static boolean[][][] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];// 갈수있는지 여부
		check = new boolean[N][M][2];// 현재까지 최단 거리
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++)
				map[i][j] = str.charAt(j) - '0';
		}

		if (N == 1 && M == 1)
			ans = 1;
		else
			BFS(0, 0, 1, 0);

		System.out.println(ans == 0 ? -1 : ans);
	}

	static void BFS(int r, int c, int d, int b) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c, d, b));
		while (!q.isEmpty()) {
			Node f = q.poll();
			int R = f.r;
			int C = f.c;
			int D = f.d;// 지금까지 거리
			int B = f.b;// 깼냐 안깼냐
			for (int i = 0; i < 4; i++) {
				int nr = R + dr[i];
				int nc = C + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (nr == N - 1 && nc == M - 1) {
					ans = D + 1;
					return;
				}

				if (map[nr][nc] == 0) {// 길이면
					if (!check[nr][nc][B]) {// 깼든 안깼든 그 경우를 고려한 적 없으면
						check[nr][nc][B] = true;
						q.offer(new Node(nr, nc, D + 1, B));
					}
				} else {// 벽이면
					if (B == 1 || check[nr][nc][1]) // 이전까지 깬 적 있거나, 깬 적은 없지만 깨고 현재까지 온 경우 고려한 적 있으면
						continue;

					check[nr][nc][1] = true;
					q.offer(new Node(nr, nc, D + 1, 1));
				}
			}
		}
	}

	static class Node {
		int r, c, d, b;

		Node(int r, int c, int d, int b) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.b = b;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
}