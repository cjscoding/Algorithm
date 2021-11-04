import java.util.Scanner;

public class Main_17406_배열돌리기4 {

	static int N, M, K, ans;
	static int[][] map, tmp;
	static Node[] nodeList;
	static int[] order;
	static boolean[] sel;

	static class Node {
		int r, c, s;

		Node(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		ans = Integer.MAX_VALUE;

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				map[i][j] = sc.nextInt();

		tmp = new int[N + 1][M + 1];
		order = new int[K];
		sel = new boolean[K];
		nodeList = new Node[K];
		for (int i = 0; i < K; i++)
			nodeList[i] = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt());

		perm(0);
		
		System.out.println(ans);
	}

	// 회전 연산 순서 정하기
	static void perm(int idx) {
		if (idx == K) {
			copyArray();
			rotateArray();
			ans = Math.min(ans, calMin());
		}

		for (int i = 0; i < nodeList.length; i++) {
			if (sel[i])
				continue;

			sel[i] = true;
			order[idx] = i;
			perm(idx + 1);
			sel[i] = false;

		}
	}

	// 임시 배열 복사하기
	static void copyArray() {
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				tmp[i][j] = map[i][j];
	}

	// 회전연산 실행하기
	// 시계방향 회전 == 반시계 방향으로 값 넣어주기
	static void rotateArray() {
		for (int o = 0; o < order.length; o++) {//회전연산 순서대로
			Node n = nodeList[order[o]];
			int r = n.r;
			int c = n.c;
			int s = n.s;

			for(int i = 1; i <= s; i++) {
				int curR = r - i;
				int curC = c + i;
				int tmpValue = tmp[curR][curC];//최초 시작 값 잠시 저장
			
				//right(왼쪽값을 현재값으로)
				while(true) {
					tmp[curR][curC] = tmp[curR][curC-1];
					curC--;
					if(curR == r - i && curC == c - i)
						break;
				}
				
				//up(아래값을 현재값으로)
				while(true) {
					tmp[curR][curC] = tmp[curR+1][curC];
					curR++;
					if(curR == r + i && curC == c - i)
						break;
				}
				
				//left(오른쪽값을 현재값으로)
				while(true) {
					tmp[curR][curC] = tmp[curR][curC+1];
					curC++;
					if(curR == r + i && curC == c + i)
						break;
				}
				
				//down(위쪽값을 현재값으로)
				while(true) {
					tmp[curR][curC] = tmp[curR-1][curC];
					curR--;
					if(curR == r - i+1 && curC == c + i) {
						tmp[curR][curC] = tmpValue;
						break;
					}
				}
			}
		}
	}

	// 최소값 구하기
	static int calMin() {
		int sum = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			int s = 0;
			for(int j = 1; j <= M; j++) {
				s += tmp[i][j];
			}
			sum = Math.min(sum, s);
		}
		return sum;
	}

}
