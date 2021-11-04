import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

//섬에 번호 붙이기=>BFS
//사방탐색으로 한 방향으로 다리를 만들어 다른 섬과 연결될 경우 큐에 넣어주기
//최소간선 합 (N-1개의 간선) => 크루스칼
public class Main_17472_다리만들기2 {
	static int N, M, map[][], island, represent[], ans, cnt;
	static boolean visited[][];
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();// 크루스칼 위해 간선 가중치 기준으로 오름차순으로 정렬해야 함

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];

		// 육지인지 바다인지 입력받기
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();

		// 섬에 번호 붙이기
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == 1 && !visited[i][j]) {
					island++;
					BFS(new Node(i, j));
				}

		// 다리 만들기
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] > 0)
					bridge(new Node(i, j), map[i][j]);// 현 좌표, 현재 위치한 섬의 번호

		// 크루스칼로 최소 신장 트리 구하기
		represent = new int[island + 1];

		make();

		int size = pq.size();
		for (int i = 0; i < size; i++) {
			Edge edge = pq.poll();

			int a = find(edge.from);
			int b = find(edge.to);

			if (a == b)
				continue;

			union(edge.from, edge.to);
			ans += edge.weight;
			cnt++;
		}

		if (ans == 0 || cnt != island - 1)// 총 가중치의 합이 0이거나 간선의 수가 섬 개수-1이 아니면
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	// 같은 방향으로 다리 깔기
	static void bridge(Node n, int num) {
		int R = n.r;
		int C = n.c;
		int len = 0;
		for (int i = 0; i < 4; i++) {
			while (true) {
				R += dr[i];
				C += dc[i];
				// 범위를 벗어나거나 같은 섬 구역 만나면
				if (R < 0 || C < 0 || R >= N || C >= M || map[R][C] == num) {
					// 원래대로 돌려놓고
					len = 0;
					R = n.r;
					C = n.c;
					break;// 끝내기
				} else if (map[R][C] == 0) {// 바다면 다리 놓기
					len++;
				} else {// 다른 섬이면
					if (len > 1)// 연결된 다리 길이가 1이상이면
						pq.add(new Edge(num, map[R][C], len));// from, to, weight

					// 다른 다리 놓기 위해 reset
					R = n.r;
					C = n.c;
					len = 0;
					break;
				}
			}
		}
	}

	// make(모든 원소를 각 집합의 대표자로 만들기)
	static void make() {
		for (int i = 0; i < represent.length; i++)
			represent[i] = i;
	}

	// find(대표자 찾기)
	static int find(int a) {
		if (a == represent[a])// 본인이 대표자면
			return a;// 본인리턴
		represent[a] = find(represent[a]);// 아니면 대표자 재귀로 찾아서 리턴
		return represent[a];
	}

	// union(대표자 일치시키기)
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) // 대표자 같으면
			return;

		represent[aRoot] = b;
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return o.weight >= this.weight ? -1 : 1;
		}
	}

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 섬에 번호 붙이기
	static void BFS(Node n) {
		Queue<Node> q = new LinkedList<Node>();
		map[n.r][n.c] = island;
		visited[n.r][n.c] = true;
		q.offer(n);

		while (!q.isEmpty()) {
			Node node = q.poll();
			int R = node.r;
			int C = node.c;

			for (int i = 0; i < 4; i++) {
				int nr = R + dr[i];
				int nc = C + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (!visited[nr][nc] && map[nr][nc] == 1) {
					map[nr][nc] = island;
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
	}
}