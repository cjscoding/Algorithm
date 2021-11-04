import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class Main_02667_단지번호붙이기 {
	static int N, map[][], complex[], idx;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		complex = new int[N * N / 2 + 1];
		for (int i = 0; i < N; i++) {
			String input = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					bfs(i, j);
					idx++;
				}
			}
		}
		Arrays.sort(complex, 0, idx);
		System.out.println(idx);
		for (int i = 0; i < idx; i++)
			System.out.println(complex[i]);
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void bfs(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));
		map[r][c] = 0;
		complex[idx]++;
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = n.r + dy[i];
				int nx = n.c + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;
				if (map[ny][nx] == 0)
					continue;
				q.add(new Node(ny, nx));
				map[ny][nx] = 0;
				complex[idx]++;
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