import java.util.Scanner;

public class Solution_02117_홈방법서비스 {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			for(int i = 0; i < N; i++)
				for(int j = 0; j <N; j++)
					map[i][j] = sc.nextInt();
			
			
		}

	}

}
