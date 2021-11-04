import java.util.Scanner;

public class Solution_05656_벽돌깨기 {

	static int N, W, H;
	static int[][] map, clone;
	static boolean[] mb;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <=T; tc++) {
			int N = sc.nextInt();//구슬의 개수
			int W = sc.nextInt();//C
			int H = sc.nextInt();//R
			
			map = new int[H][W];
			for(int i = 0; i < H; i++)
				for(int j = 0; j < W; j++)
					map[i][j] = sc.nextInt();
			
			mb = new boolean[W];
			comb(0, 0);
			
		}
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == N) {
			return;
		}
		
		for(int i = 0; i < W; i++) {
			mb[idx] = true;
			
		}
	}

}
