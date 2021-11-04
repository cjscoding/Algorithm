import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_01600_말이되고픈원숭이 {
	static int N, map[][], cnt;
	static boolean sel[][];

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer stn = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stn.nextToken());
			}
		}
		sel = new boolean[N + 1][N + 1];

		sel[1][2] = true;
		pipe(1, 1, 1, 2);
		System.out.println(cnt);
	}

	static void pipe(int r1, int c1, int r2, int c2) {
		if (r2 == N && c2 == N) {
			cnt++;
			return;
		}

		// 파이프가 가로 상태일 때
		if (r2 - r1 == 0 && c2 - c1 == 1) {
			// {가로로, 대각선으로}
			int[] dy1 = { 0, 0 };// 시작점
			int[] dx1 = { 1, 1 };
			int[] dy2 = { 0, 1 };// 끝점
			int[] dx2 = { 1, 1 };

			for (int i = 0; i < 2; i++) {
				int ny1 = r1 + dy1[i];
				int nx1 = c1 + dx1[i];
				int ny2 = r2 + dy2[i];
				int nx2 = c2 + dx2[i];

				if (ny2 > N || nx2 > N)
					continue; // 범위 벗어나면 패스
				if (i == 1) {// 대각선으로 갈 때 세방향이 모두 비어있어야 가능(우, 우하, 하)
					if (map[ny1][nx1 + 1] == 1 || map[ny1 + 1][nx1] == 1)
						continue;
				}
				if (sel[ny2][nx2])
					continue;// 이미 지난 길이어도 패스
				if (map[ny2][nx2] == 1)
					continue; // 벽이어도 패스

				sel[ny2][nx2] = true;
				pipe(ny1, nx1, ny2, nx2);
				sel[ny2][nx2] = false;
			}

			// 파이프가 대각선 상태일 때
		} else if (r2 - r1 == 1 && c2 - c1 == 1) {
			// {가로로, 세로로, 대각선으로}
			int[] dy1 = { 1, 1, 1 };// 시작점
			int[] dx1 = { 1, 1, 1 };
			int[] dy2 = { 0, 1, 1 };// 끝점
			int[] dx2 = { 1, 0, 1 };

			for (int i = 0; i < 3; i++) {
				int ny1 = r1 + dy1[i];
				int nx1 = c1 + dx1[i];
				int ny2 = r2 + dy2[i];
				int nx2 = c2 + dx2[i];

				if (ny2 > N || nx2 > N)
					continue; // 범위 벗어나면 패스
				if (i == 2)// 대각선으로 갈 때 세방향이 모두 비어있어야 가능(우, 우하, 하)
					if (map[ny1][nx1 + 1] == 1 || map[ny1 + 1][nx1] == 1)
						continue;
				if (sel[ny2][nx2])
					continue;// 이미 지난 길이어도 패스
				if (map[ny2][nx2] == 1)
					continue; // 벽이어도 패스

				sel[ny2][nx2] = true;
				pipe(ny1, nx1, ny2, nx2);
				sel[ny2][nx2] = false;
			}

			// 파이브가 세로 상태일 때
		} else if (r2 - r1 == 1 && c2 - c1 == 0) {
			// {세로로, 대각선으로}
			int[] dy1 = { 1, 1 };// 시작점
			int[] dx1 = { 0, 0 };
			int[] dy2 = { 1, 1 };// 끝점
			int[] dx2 = { 0, 1 };

			for (int i = 0; i < 2; i++) {
				int ny1 = r1 + dy1[i];
				int nx1 = c1 + dx1[i];
				int ny2 = r2 + dy2[i];
				int nx2 = c2 + dx2[i];

				if (ny2 > N || nx2 > N)
					continue; // 범위 벗어나면 패스
				if (i == 1)// 대각선으로 갈 때 세방향이 모두 비어있어야 가능(우, 우하, 하)
					if (map[ny1][nx1 + 1] == 1 || map[ny1 + 1][nx1] == 1)
						continue;
				if (sel[ny2][nx2])
					continue;// 이미 지난 길이어도 패스
				if (map[ny2][nx2] == 1)
					continue; // 벽이어도 패스

				sel[ny2][nx2] = true;
				pipe(ny1, nx1, ny2, nx2);
				sel[ny2][nx2] = false;
			}
		}

	}
}