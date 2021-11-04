import java.util.Scanner;

public class Solution_05643_키순서_플로이드워샬 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();// 학생 수
			int M = sc.nextInt();// 비교횟수
			int[][] map = new int[N + 1][N + 1];

			int INF = Integer.MAX_VALUE;// 초깃값 무한대
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = INF;
				}
			}

			// 연결돼있으면 체크
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				map[a][b] = 1;
			}

			// 플로이드 워셜
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (map[i][k] == 1 && map[k][j] == 1)
							map[i][j] = 1;
					}
				}
			}

			// 자기 순서 알려면 다른 애들과 모두 연결되어 있어야 함
			// 내가 걔한테 가거나 걔가 오거나 둘 중 하나만 되어있어도 연결된 것
			int know = 0;
			for (int i = 1; i <= N; i++) {
				int cnt = 0;
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1 || map[j][i] == 1)
						cnt++;
				}
				if (cnt == N - 1)
					know++;
			}

			System.out.println("#" + t + " " + know);
		}
	}
}