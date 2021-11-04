//답 틀렸음 다시보기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//갔던 길은 기억해둠으로써 중복호출을 막는 방법
//메모이제이션
public class Solution_05643_키순서_MEMOIZATION {
	static int N, M, adj[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());// 학생 수
			M = Integer.parseInt(br.readLine());// 간선정보 수
			adj = new int[N + 1][N + 1];

			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from][to] = 1;// from보다 to가 키가 크다.
			}

			for (int i = 1; i <= N; i++) {
				adj[i][0] = -1;
			}

			for (int i = 1; i <= N; i++) {
				if (adj[i][0] == -1)
					dfs(i);// 자신보다 큰 학생 탐색(아직 탐색이 안된 학생만)
			}
			
			//위에서 탐색된 결과를 토대로 자신보다 작은 학생 수 카운팅
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					adj[0][j] += adj[i][j];
				}
			}
			
			int ans = 0;
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(adj[i][0] + adj[0][i] == N-1)
						ans++;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

	// 자신보다 큰 학생따라 탐색
	private static void dfs(int cur) {

		for (int i = 1; i <= N; i++) {
			if (adj[cur][i] == 1) {// 자신보다 큰 학생이면
				if (adj[i][0] == -1) {
					dfs(i);
				}
				// 자신보다 큰 학생 탐색을 완료한 상태(메모가 되어있으면 탐색안하고 바로 내려옴)
				if (adj[i][0] > 0) {// i보다 큰 학생이 존재
					// i의 인접행렬의 상태를 cur에 반영
					for (int j = 1; j <= N; j++) {
						if (adj[i][j] == 1)
							adj[cur][j] = 1;
					}
				}
			}
		}

		int cnt = 0;
		for (int j = 1; j <= N; j++) {
			cnt += adj[cur][j];
		}
		adj[cur][0] = cnt;
	}
}
