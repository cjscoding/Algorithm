import java.util.Scanner;

public class Main_17135_캐슬디펜스 {

	static int N, M, D, ans;
	static int[] archer;
	static int[][] map, tmp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();// 행
		M = sc.nextInt();// 열
		D = sc.nextInt();// 궁수의 공격 거리 제한
		ans = 0;//최종 결과
		archer = new int[3];// N+1행에서 궁수들의 열 위치
		map = new int[N + 2][M + 1];
		tmp = new int[N + 2][M + 1];

		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				map[i][j] = sc.nextInt();

		comb(1, 0);// 열 1부터 조합만들기
		
		System.out.println(ans);
	}

	// N+1행에서 궁수 3명 위치 뽑기 : 조합
	static void comb(int start, int idx) {
		if (idx == 3) {// 궁수 위치 다 뽑았으면
			killEnemy();// 적들 죽이러 가기
			return;
		}

		for (int i = start; i <= M; i++) {
			archer[idx] = i;
			comb(i + 1, idx + 1);
		}
	}

	static void killEnemy() {
		// 클론배열 만들기
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				tmp[i][j] = map[i][j];

		int kill = 0;// 게임이 끝날 때까지 죽일 수 있는 적의 수

		for(int turn = 0; turn < N; turn++) {// 적이 아직 남아있다면
			// 가장 가까운 적 찾기
			int[][] enemy = new int[3][2];// 각 궁수가 공격할 적의 위치 저장(모든 궁수가 동시에 공격하여 같은 적을 공격할 수도 있기 때문에 저장해두고 동시에 죽여야한다.)
			for (int a = 0; a < 3; a++) {// 궁수 3명의 경우마다
				int aC = archer[a];// 궁수의 열 위치(행은 N+1)

				int dis = Integer.MAX_VALUE;// 궁수와 가장 가까운 적과의 거리
				int eR = 0;// 가장 가까운 적의 행 위치
				int eC = 0;// 가장 가까운 적의 열 위치
				for (int j = 1; j <= M; j++)
					for (int i = 1; i <= N; i++) {// 거리가 같다면 가장 왼쪽 적을 없애야 하기 때문에 열행 방식으로 탐색
						if (tmp[i][j] == 1) {// 적이 있는 위치라면
							int d = Math.abs(N + 1 - i) + Math.abs(aC - j);// 궁수와 적 사이의 거리구하기
							if (d <= D && d < dis) {// 활을 쏠 수 있는 범위 내이고 궁수와 가장 가까운 거리의 적이라면
								dis = d;
								eR = i;
								eC = j;
							}
						}
					}

				enemy[a][0] = eR;
				enemy[a][1] = eC;

			}

			// 적 없애기
			for (int i = 0; i < 3; i++) {
				int r = enemy[i][0];
				int c = enemy[i][1];
				if (tmp[r][c] == 1) {// 적이 있다면(아직 앞 궁수가 해당 적을 죽이지 않았다면)
					kill++;
					tmp[r][c] = 0;
				}
			}

			// 적들 한 칸씩 내려오기
			// N+1행(성)에 도착한 적들은 그냥 없애기
			for (int i = N; i >= 1; i--)// 마지막 행부터 채우기
				for (int j = 1; j <= M; j++)
					tmp[i][j] = tmp[i - 1][j];
		}
		
		//최대 죽일 수 있는 수로 갱신
		ans = Math.max(ans, kill);
	}
}
