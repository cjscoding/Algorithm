import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_01194_달이차오른다가자 {
	static int N, M, startR, startC;
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][64];// 비트마스크로 6가지 열쇠 유무 체크 하면서 방문 확인하기
		// 6개 열쇠가 모두 있을 경우 총합 63
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					startR = i;
					startC = j;
				}
			}
		}

		BFS(startR, startC, 0, 0);// r, c, distance, cntKeys
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class node {
		int r, c, d, k;

		node(int r, int c, int d, int k) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.k = k;
		}
	}

	static void BFS(int r, int c, int d, int k) {
		Queue<node> q = new LinkedList<>();
		q.offer(new node(r, c, d, k));
		visited[r][c][k] = true;
		while (!q.isEmpty()) {
			node n = q.poll();
			int R = n.r;
			int C = n.c;
			int D = n.d;
			int K = n.k;

			for (int i = 0; i < 4; i++) {
				int nr = R + dr[i];
				int nc = C + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)// 탐색 범위 넘어갈 시 패스
					continue;

				if (map[nr][nc] == '#' || visited[nr][nc][K])// 벽이거나 현재 키꾸러미 들고 방문한 적이 있다면
					continue;

				if (map[nr][nc] == '1') {// 출구면 끝내
					System.out.println(D + 1);
					return;
				}

				// 빈칸이거나 출발점 지날 때
				if (map[nr][nc] == '.' || map[nr][nc] == '0') {
					visited[nr][nc][K] = true;// 방문처리 해주고
					q.offer(new node(nr, nc, D + 1, K));// 큐에 넣기

					// key칸일때
				} else if (map[nr][nc] - 'a' >= 0 && map[nr][nc] - 'a' < 6) {
					int keys = K | (1 << (map[nr][nc] - 'a'));// 이전까지 가지고 있던 키들과 새로운 키를 or해서 새로운 키 꾸러미 만들기
					if (!visited[nr][nc][keys]) {// 새로 만들어진 꾸러미를 가지고 방문한 적이 없다면
						visited[nr][nc][keys] = true;// 방문처리해주고
						q.offer(new node(nr, nc, D + 1, keys));// 큐에 넣기
					}

					// door칸일때
				} else if (map[nr][nc] - 'A' >= 0 && map[nr][nc] - 'A' < 6) {
					int isExist = K & 1 << (map[nr][nc] - 'A');
					if (isExist > 0) {// 대응하는 키가 있으면 0이상이 나오는데 그렇다면
						visited[nr][nc][K] = true;// 방문처리해주고
						q.offer(new node(nr, nc, D + 1, K));// 거리하나 늘리고 큐에 넣기
					}
				}
			}
		}
		// 1(출구)를 만나지 못하고 나온 경우
		System.out.println(-1);
		return;
	}
}