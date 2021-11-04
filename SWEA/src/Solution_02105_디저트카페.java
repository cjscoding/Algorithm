import java.util.Scanner;

public class Solution_02105_디저트카페 {

	static int N, ans;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();// 한 변 길이
			map = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					map[i][j] = sc.nextInt();

			System.out.println("#" + tc + " " + ans);
		}
	}

}
