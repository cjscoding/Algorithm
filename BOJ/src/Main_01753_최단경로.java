import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main_01753_최단경로 {
	static int V, E, start;
	static int[] d;
	static ArrayList<Edge>[] adj;// 메모리초과나니까 인접행렬말고 리스트로
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());// 정점개수
		E = Integer.parseInt(st.nextToken());// 간선개수
		start = Integer.parseInt(br.readLine());// 시작점

		List<Edge>[] adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++)
			adj[i] = new ArrayList<Edge>();// 각 정점 별로 연결되어 있는 타정점의 정보를 저장할 리스트 생성
		for (int i = 0; i < E; i++) {// 간선 정보 인접 행렬에 입력
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());// 정점개수
			int to = Integer.parseInt(st.nextToken());// 간선개수
			int weight = Integer.parseInt(st.nextToken());// 시작점
			adj[from].add(new Edge(to, weight));
		}

		visited = new boolean[V + 1]; // 최소신장트리 포함여부
		d = new int[V + 1]; // 시작점에서 각 정점별 최소경로거리
		Arrays.fill(d, Integer.MAX_VALUE); // 모든 최소거리 맥스로 설정
		d[start] = 0; // 출발정점 최소 비용 0으로 설정

		for (int i = 1; i <= V; i++) {

			int min = Integer.MAX_VALUE;
			int minV = -1;
			for (int j = 1; j <= V; j++) {
				// 확보할 정점 선택
				// 아직 신장트리에 포함되지 않았고
				// 비용이 가장 작은 정점이면 선택
				if (!visited[j] && min > d[j]) {
					min = d[j];// 해당 정점의 가중치
					minV = j;// 해당 정점
				}
			}

			if (minV == -1) // 새로 선택된 정점이 없음 == 더 이상 경로가 없음
				break;// 그럼 끝
			visited[minV] = true; // 확보한 정점을 신장트리에 포함

			// 현재 정점에서의 거리와 기존 거리를 비교해 최단으로 설정
			for (Edge edge : adj[minV]) {// 현 정점의 리스트에 있는 엣지들 다 돌아(minV == from)
				// to 정점이 아직 신장트리에 포함되어 있지 않고
				// to정점까지의 기존 거리가 (from까지의 최단 거리 + from에서 to까지의 거리) 보다 길다면 짧은 걸로 바꿈
				if (!visited[edge.to] && d[edge.to] > d[minV] + edge.weight)
					d[edge.to] = d[minV] + edge.weight;
			}
		}

		for (int i = 1; i <= V; i++) {
			if (d[i] == Integer.MAX_VALUE)
				sb.append("INF").append("\n");
			else
				sb.append(d[i]).append("\n");
		}
		System.out.println(sb);
	}

	static class Edge {

		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
}