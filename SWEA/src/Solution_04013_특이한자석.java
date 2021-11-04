import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_04013_특이한자석 {

	static int n, ans;
	static int[][] mag;
	static int[] whichD;
	static boolean[] visited;

	static class Node {
		int idx, dir;

		Node(int idx, int dir) {
			this.idx = idx;
			this.dir = dir;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int K = sc.nextInt();// 회전 횟수
			mag = new int[4][8];// 자석 4개 각각 극 8개
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 8; j++)
					mag[i][j] = sc.nextInt();

			for (int i = 0; i < K; i++) {
				n = sc.nextInt() - 1;// 이번에 회전할 바퀴 번호
				int turn = sc.nextInt();

				whichD = new int[4];
				visited = new boolean[4];
				turnOrNot(new Node(n, turn));

				for (int w = 0; w < 4; w++) {
					if (whichD[w] == 1) {
						turnRight(w);
					} else if (whichD[w] == -1) {
						turnLeft(w);
					}
				}
			}

			ans = 0;
			for (int i = 0; i < 4; i++) {
				if (mag[i][0] == 1) {
					ans += Math.pow(2, i);
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	// 도는지 안도는지 돌면 어느 방향으로 도는지 정해주기
	static void turnOrNot(Node node) {// 현재 바퀴, 도는 방향
		Queue<Node> q = new LinkedList<>();
		q.offer(node);
		while (!q.isEmpty()) {
			Node n = q.poll();
			int i = n.idx;
			int d = n.dir;
			whichD[i] = d;
			visited[i] = true;

			if (i - 1 >= 0) {// 왼쪽 바퀴가 있고
				if (!visited[i - 1] && mag[i][6] != mag[i - 1][2]) {// 방문한 적 없고 접한 극이 서로 다른 극이라면
					q.offer(new Node(i - 1, d * -1));
				}
			}
			if (i + 1 < 4) {// 오른 바퀴 있고
				if (!visited[i + 1] && mag[i][2] != mag[i + 1][6]) {// 방문한 적 없고 접한 극이 서로 다른 극이라면
					q.offer(new Node(i + 1, d * -1));
				}
			}
		}
	}

	static void turnRight(int row) {
		int tmp = mag[row][7];
		for (int i = 7; i >= 1; i--)
			mag[row][i] = mag[row][i - 1];

		mag[row][0] = tmp;
	}

	static void turnLeft(int row) {
		int tmp = mag[row][0];
		for (int i = 0; i < 7; i++)
			mag[row][i] = mag[row][i + 1];

		mag[row][7] = tmp;
	}
}
