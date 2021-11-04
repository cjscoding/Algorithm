import java.util.Scanner;

public class Main_01520_내리막길 {

	static int N, M, cnt;
	static int[][] map, visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				visited[i][j] = -1;
			}
		}
		
		System.out.println(dfs(N-1, M-1));
	}

	static int dfs(int r, int c) {

		if (r == 0 && c == 0)
			return 1;

		if (visited[r][c] == -1) {
			visited[r][c] = 0;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				
				if (map[r][c] < map[nr][nc])
					visited[r][c] += dfs(nr, nc);
			}
		}
		
		return visited[r][c];
	}
}
