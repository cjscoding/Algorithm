import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136_색종이붙이기 {

	static int N;
	static int ans = Integer.MAX_VALUE;
	static int[] confetti = {0, 5, 5, 5, 5, 5};
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = 10;
		map = new int[10][10];
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		search(0, 0, 0);
		
		if(ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	static void search(int r, int c, int cnt) {
		if(ans <= cnt)
			return;
		
		if(r >= 9 && c > 9) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(c > 9) {
			search(r+1, 0, cnt);
			return;
		}
		
		if(map[r][c] == 1) {
			for(int i = 5; i > 0; i--) {
				if(confetti[i] == 0)
					continue;
				
				if(r+i > 10 || c+i > 10) {
					
				}
			}
		}
	}
}
