import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_01707_이분그래프 {
	static int V, E, color[];
	static LinkedList<Integer>[] list;
	static boolean yes;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();// tc수
		for (int tc = 1; tc <= T; tc++) {
			V = sc.nextInt();// 정점 수
			E = sc.nextInt();// 간선 수
			color = new int[V + 1];// 인접 정점일 경우 색 다르게 하기 위한 배열
			yes = true;// 최종 결과 판별하기 위한 불리언 값

			list = new LinkedList[V + 1];// 간선의 수가 클 수 있어서 인접행렬이 아닌 인접 리스트로 파악
			for (int i = 1; i <= V; i++) {
				list[i] = new LinkedList<>();// 정점 수만큼 리스트 넣어주기
			}

			for (int i = 0; i < E; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				list[from].add(to);// 시작 정점 리스트에 끝 정점 추가
				list[to].add(from);// 끝 정점 리스트에 시작 정점 추가
			}

			BFS();

			System.out.println(yes ? "YES" : "NO");
		}
	}

	static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		out: for (int i = 1; i <= V; i++) {
			if (color[i] == 0) {// 아직 확인하지 않은 정점이라면
				q.offer(i);// 큐에 넣고
				color[i] = 1;// 1로 칠해주고

				while (!q.isEmpty()) {// 큐가 비지 않는다면
					int cur = q.poll();// 큐에서 탐색할 정점 꺼내고
					for (Integer j : list[cur]) {// 해당 정점의 인접리스트를 돌면서
						if (color[j] == 0) {// 방문한 적 없으면
							q.offer(j);// 큐에 넣어주고
							color[j] = color[cur] * -1;// 반대 색(-1)으로 칠해준다.
						} else if (color[j] == color[cur]) {// 인접 정점끼리 같은 색이라면 이분 그래프가 아닌 것
							yes = false;
							break out;
						}
					}
				}
			}
		}
	}
}
