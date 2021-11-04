import java.util.Scanner;

public class Main_01987_알파벳 {
	static int R, C, map[][], max;
	static boolean[] sel;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new int[R][C];
		sel = new boolean[26];
		max = 1;

		for (int i = 0; i < R; i++) {
			String input = sc.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		backtrack(0, 0, 0);
		System.out.println(max);

	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void backtrack(int r, int c, int cnt) {
		if (sel[(int) map[r][c] - 'A']) {
			max = Math.max(max, cnt);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int ny = r + dy[i];
			int nx = c + dx[i];

			if (ny < 0 || nx < 0 || ny >= R || nx >= C)
				continue;
			sel[(int) map[r][c] - 'A'] = true;
			backtrack(ny, nx, cnt + 1);
			sel[(int) map[r][c] - 'A'] = false;
		}

	}
}
