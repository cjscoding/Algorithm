
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main_01260_DFS와BFS {
	static int N;
	static int[][] map;
	static boolean[] visited, check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		check = new boolean[N + 1];
		int M = sc.nextInt();
		int V = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = map[b][a] = 1;
		}

		visited[V] = true;
		System.out.print(V + " ");
		DFS(V);
		System.out.println();
		BFS(V);
	}

	static void DFS(int v) {
		for (int i = 1; i <= N; i++) {
			if (map[v][i] == 1 && !visited[i]) {
				visited[i] = true;
				System.out.print(i + " ");
				DFS(i);
			}
		}
	}

	static void BFS(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		check[v] = true;
		System.out.print(v + " ");
		while (!q.isEmpty()) {
			int from = q.peek();
			for (int i = 1; i <= N; i++) {
				if (map[from][i] == 1 && !check[i]) {
					System.out.print(i + " ");
					check[i] = true;
					q.offer(i);
				}
			}
			q.poll();
		}
	}
}