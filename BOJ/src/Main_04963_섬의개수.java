import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main_04963_섬의개수 {
	static int R, C, map[][], cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			C = sc.nextInt();
			R = sc.nextInt();
			if (C == 0 && R == 0)
				break;

			map = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 1) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
			cnt = 0;
		}
	}

	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static void bfs(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));
		map[r][c] = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 8; i++) {
				int ny = n.r + dy[i];
				int nx = n.c + dx[i];
				if (ny < 0 || nx < 0 || ny >= R || nx >= C)
					continue;
				if (map[ny][nx] == 0)
					continue;
				q.add(new Node(ny, nx));
				map[ny][nx] = 0;
			}
		}

	}

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}