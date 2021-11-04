import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_02112_보호필름 {

	static int D, W, K, ans;
	static int[][] spec;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());// R
			W = Integer.parseInt(st.nextToken());// C
			K = Integer.parseInt(st.nextToken());// 합격조건

			spec = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++)
					spec[i][j] = Integer.parseInt(st.nextToken());// 0이면 A, 1이면 B
			}

			ans = Integer.MAX_VALUE;

			DFS(0, 0);

			System.out.println("#" + tc + " " + ans);
		}
	}

	static void DFS(int i, int cnt) {// 약물 주입하려는 행 번호, 약물 주입 횟수
		if (success()) {
			ans = Math.min(ans, cnt);
			return;
		}

		if(i >= D)//범위 넘어간 경우
			return;
		
		if(cnt >= ans)//이미 구해진 최소 약물 주입 횟수보다 많으면 
			return;
		
		// 원래 값들 저장해두기
		int[] tmp = new int[W];
		for (int j = 0; j < W; j++)
			tmp[j] = spec[i][j];

		// 현재 행에 약물 주입하지 않고 다음 행으로
		DFS(i + 1, cnt);

		// 약물 A 주입하고 다음 행으로
		Arrays.fill(spec[i], 0);
		DFS(i + 1, cnt + 1);

		// 약물 B 주입하고 다음 행으로
		Arrays.fill(spec[i], 1);
		DFS(i + 1, cnt + 1);
		
		//다시 원래대로
		for (int j = 0; j < W; j++)
			spec[i][j] = tmp[j];
		

	}

	static boolean success() {
		int[] same = new int[W];
		for (int i = 1; i < D; i++) {
			for (int j = 0; j < W; j++) {
				if (same[j] == K - 1)// 최초 비교한 i == 0일 때의 값은 카운트 안했으니까
					continue;
				if (spec[i - 1][j] == spec[i][j])
					same[j]++;
				else
					same[j] = 0;
			}
		}

		for (int s : same) {
			if (s != K - 1)
				return false;
		}

		return true;
	}
}
