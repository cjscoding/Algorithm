import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_05643_키순서_DFS {
	static int N, M, adj[][];
	static int gtCnt, ltCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());// 학생 수
			M = Integer.parseInt(br.readLine());// 간선정보 수
			adj = new int[N + 1][N + 1];

			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from][to] = 1;// from보다 to가 키가 크다.
			}

			int ans = 0;
			for (int i = 1; i <= N; i++) {
				gtCnt = ltCnt = 0;
				gtDFS(i, new boolean[N + 1]);
				ltDFS(i, new boolean[N + 1]);
				if (gtCnt + ltCnt == N - 1)
					ans++;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	// 자신보다 큰 학생따라 탐색
	private static void gtDFS(int cur, boolean[] visited) {

		visited[cur] = true;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adj[cur][i] == 1) {
				gtCnt++;
				gtDFS(i, visited);
			}
		}
	}

	// 자신보다 작은 학생따라 탐색
	private static void ltDFS(int cur, boolean[] visited) {

		visited[cur] = true;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adj[i][cur] == 1) {
				ltCnt++;
				ltDFS(i, visited);
			}
		}
	}

}
