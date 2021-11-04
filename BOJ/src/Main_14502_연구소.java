import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main_14502_연구소 {
	static int N, M, ans;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();

		dfs(0);

		System.out.println(ans);
	}

	static void dfs(int wall) {
		if (wall == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(wall + 1);
					map[i][j] = 0;
				}
	}

	static class virus {
		int i, j;

		virus(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void bfs() {
		int[][] vMap = new int[N][M];
		Queue<virus> q = new LinkedList<>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				vMap[i][j] = map[i][j];
				if (map[i][j] == 2)
					q.add(new virus(i, j));
			}

		while (!q.isEmpty()) {
			virus v = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = v.i + dy[d];
				int nx = v.j + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;

				if (vMap[ny][nx] == 0) {
					vMap[ny][nx] = 2;
					q.offer(new virus(ny, nx));
				}
			}
		}

		cntSafeArea(vMap);
	}

	static void cntSafeArea(int[][] inputMap) {
		int cnt = 0;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (inputMap[i][j] == 0)
					cnt++;

		ans = Math.max(cnt, ans);
	}
}