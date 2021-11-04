import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_10026_적록색약 {

	static int N, cnt, cnt2;
	static char[][] map;
	static boolean[][] visited, visited2;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Node {
		int i, j;

		Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 입력받기
		N = sc.nextInt();
		map = new char[N][N];
		visited = new boolean[N][N];
		visited2 = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < N; j++) {
				char a = s.charAt(j);
				map[i][j] = a;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					cnt++;
				}
				if (!visited2[i][j]) {
					dfs2(i, j);
					cnt2++;
				}
			}
		}

		System.out.println(cnt + " " + cnt2);
	}

	static void dfs(int r, int c) {
		visited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				continue;

			if (visited[nr][nc])
				continue;
			
			if(map[nr][nc] == map[r][c]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
	
	static void dfs2(int r, int c) {
		visited2[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				continue;
			
			if (visited2[nr][nc])
				continue;
			
			if(map[nr][nc] == 'B' && map[r][c] == 'B') {
				visited2[nr][nc] = true;
				dfs2(nr, nc);
			} else if(map[nr][nc] != 'B' && map[r][c] != 'B') {
				visited2[nr][nc] = true;
				dfs2(nr, nc);
			}
		}
	}
}
