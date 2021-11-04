import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_17471_게리맨더링 {
	static int N, min;
	static int[] p;
	static int[][] map;
	static boolean check[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		min = Integer.MAX_VALUE;
		check = new boolean[N];

		p = new int[N];
		for (int i = 0; i < N; i++)
			p[i] = sc.nextInt();

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			int K = sc.nextInt();
			for (int j = 0; j < K; j++) {
				int C = sc.nextInt() - 1;
				map[i][C] = map[C][i] = 1;
			}
		}

		DFS(0);

		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	// 지역 선택 미선택으로 부분 집합 만들기
	static void DFS(int idx) {
		if (idx == N) {
			int sumTrue = 0;
			int sumFalse = 0;
			if (isConnect(true) && isConnect(false)) {// 각각 두 집합이 연결된 상태라면
				for (int i = 0; i < N; i++) {
					if (check[i])
						sumTrue += p[i];
					else
						sumFalse += p[i];
				}
				if (sumTrue == 0 || sumFalse == 0)
					return;
				min = Math.min(min, Math.abs(sumTrue - sumFalse));
			}

			return;
		}

		// 지역을 선택 할 경우
		check[idx] = true;
		DFS(idx + 1);

		// 선택하지 않을 경우
		check[idx] = false;
		DFS(idx + 1);
	}

	static boolean isConnect(boolean bool) {
		boolean visited[] = new boolean[N];
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {// 최초 탐색 시작점 큐에 넣기
			if (check[i] == bool) {
				q.add(i);
				visited[i] = true;
				break;
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < N; i++) {
				if (visited[i] || check[i] != bool || map[cur][i] == 0)
					continue;

				q.add(i);
				visited[i] = true;
			}
		}

		for (int i = 0; i < N; i++) {
			if (check[i] != bool)
				continue;

			if (!visited[i])
				return false;
		}
		return true;
	}
}