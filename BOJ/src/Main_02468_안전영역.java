import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main_02468_안전영역 {

	static int N, map[][], tmpMap[][], cnt;
	static int max;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		}

		int idx = -1;
		max = 1;
		while (++idx <= 100) {
			// 비오는 높이마다 조작할 맵 새로 만들기
			tmpMap = new int[N][N];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] <= idx)
						tmpMap[i][j] = 0;
					else
						tmpMap[i][j] = map[i][j];
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tmpMap[i][j] > idx) {
						bfs(i, j);
						cnt++;
					}
				}
			}

			max = Math.max(cnt, max);
		}
		System.out.println(max);
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void bfs(int r, int c) {

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));
		tmpMap[r][c] = 0;

		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {

				int ny = n.r + dy[i];
				int nx = n.c + dx[i];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || tmpMap[ny][nx] == 0)
					continue;

				q.add(new Node(ny, nx));
				tmpMap[ny][nx] = 0;

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
