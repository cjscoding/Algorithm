import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_01949_등산로조성 {

	static int N, K, top, ans;
	static int[][] map;
	static ArrayList<Node> list;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Node {
		int r, c, l;

		Node(int r, int c, int l) {
			this.r = r;
			this.c = c;
			this.l = l;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();// 지도 한 변의 길이
			K = sc.nextInt();// 최대 공사 가능 깊이
			map = new int[N][N];
			top = 0;
			ans = 0;
			list = new ArrayList<>();
			
			//입력받고 최고 높이 봉우리 찾기
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					top = Math.max(top, map[i][j]);
				}
			
			//최고 높이 봉우리들만 리스트에 넣어주기
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					if(map[i][j] == top)
						list.add(new Node(i, j, 1));
				}
			
			//모든 k에 대해 모든 행렬 한번씩 빼보고 최고 봉우리들 완탐 보내기
			for(int k = 0; k <= K; k++) {				
				for (int i = 0; i < N; i++)
					for (int j = 0; j < N; j++) {
						if(map[i][j] - k < 0)//땅높이 -면 넘어가기
							continue;
						else//+면 빼주고
							map[i][j] -= k;
						
						for(int t = 0; t < list.size(); t++) {//그 경우에 모든 최고 높이 봉우리에서 완탐시작
							bfs(new Node(list.get(t).r, list.get(t).c, list.get(t).l));// 지도 상의 위치, 등산로 길이
						}
						map[i][j] += k;//탐색 끝나면 다시 구멍메워주기
					}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	static void bfs(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.offer(node);

		int len = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			int r = n.r;
			int c = n.c;
			int l = n.l;
			len = Math.max(len, l);

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (map[nr][nc] < map[r][c]) //내리막길일 때만
					q.offer(new Node(nr, nc, l+1));
			}
		}
		ans = Math.max(ans, len);
	}
}
