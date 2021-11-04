import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_04485_녹색옷입은애가젤다지 {
	static int T, N, INF = Integer.MAX_VALUE;
	static int[][] map, dis;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			T++;// tc
			N = sc.nextInt();// 동굴의 크기 = 정점 개수
			if (N == 0)
				break;

			map = new int[N][N];// 인접행렬
			dis = new int[N][N];// 각 좌표까지의 최소 거리

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();// 입력받기
					dis[i][j] = INF;// 최대값으로 모두 채워두기
				}
			}

			BFS(new Node(0, 0));

			System.out.println("Problem " + T + ": " + dis[N - 1][N - 1]);
		}
	}

	static void BFS(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.offer(node);
		dis[0][0] = map[0][0];// 시작점

		while (!q.isEmpty()) {
			Node n = q.poll();
			int r = n.r;
			int c = n.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (dis[nr][nc] > dis[r][c] + map[nr][nc]) {
					dis[nr][nc] = dis[r][c] + map[nr][nc];
					q.offer(new Node(nr, nc));
				}
			}
		}
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}