import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution_05644_무선충전 {

	static int time, num;
	static int[] A, B;
	static ArrayList[][] map;

	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	static class Node implements Comparable<Node> {
		int ap;
		int p;

		Node(int ap, int p) {
			this.ap = ap;
			this.p = p;
		}

		@Override
		public int compareTo(Node o) {
			return o.p - this.p;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			time = sc.nextInt();
			A = new int[time];// A의 이동방향
			B = new int[time];// B의 이동방향

			num = sc.nextInt();// ap개수

			for (int i = 0; i < time; i++)
				A[i] = sc.nextInt();
			for (int i = 0; i < time; i++)
				B[i] = sc.nextInt();

			map = new ArrayList[11][11];
			for (int i = 1; i < 11; i++)
				for (int j = 1; j < 11; j++)
					map[i][j] = new ArrayList<Node>();

			for (int n = 1; n <= num; n++) {// apNumber(ap개수)
				int Y = sc.nextInt();// R
				int X = sc.nextInt();// C
				int C = sc.nextInt();// 충전범위
				int P = sc.nextInt();// 충전량

				for (int i = 1; i < 11; i++)
					for (int j = 1; j < 11; j++) {
						if (Math.abs(i - X) + Math.abs(j - Y) <= C) {// 충전범위 내에 있다면
							map[i][j].add(new Node(n, P));// ap번호와 충전량을 노드로 해당 위치 ArrayList에 추가
							if (map[i][j].size() > 1) {// 1이상이다 == 해당 위치가 여러개의 ap범위에 해당한다
								Collections.sort(map[i][j]);// p(충전량) 기준으로 내림차순 정렬
							}
						}
					}
			}

			int xA = 1;
			int yA = 1;
			int xB = 10;
			int yB = 10;

			int ch = 0;// 결과 충전량 => 같이 합쳐도 상관없음(결과를 A+B충전량으로 내야하니까)

			for (int t = 0; t <= time; t++) {
				int apA = 0;
				int apB = 0;
				if (map[xA][yA].size() > 0) {// 사용자 A가 어떤 영역에든 포함되어 있다면
					Node n = (Node) map[xA][yA].get(0);
					apA = n.ap;
				}
				if (map[xB][yB].size() > 0) {// 사용자 B가 어떤 영역에든 포함되어 있다면
					Node n = (Node) map[xB][yB].get(0);
					apB = n.ap;
				}

				if (apA == 0 && apB != 0) {// B만 영역에 포함된 경우
					Node n = (Node) map[xB][yB].get(0);
					ch += n.p;
				} else if (apA != 0 && apB == 0) {// A만 영역에 포함된 경우
					Node n = (Node) map[xA][yA].get(0);
					ch += n.p;
				} else if (apA != 0 && apB != 0) {// 둘 다 영역에 포함된 경우
					if (apA != apB) {// 가장 높은 충전량에 해당하는 ap번호가 각각 다르다면
						Node n1 = (Node) map[xA][yA].get(0);
						Node n2 = (Node) map[xB][yB].get(0);
						ch += n1.p + n2.p;
					} else if (apA == apB) {// 가장 높은 충전량에 해당하는 ap번호가 같다면
						if (map[xA][yA].size() == 1 && map[xB][yB].size() == 1) {// 둘 다 한 영역에만 포함이면
							Node n = (Node) map[xA][yA].get(0);
							ch += n.p;
						} else if (map[xA][yA].size() == 1) {// A만 한 영역에 포함이면
							Node n1 = (Node) map[xA][yA].get(0);
							Node n2 = (Node) map[xB][yB].get(1);
							ch += n1.p + n2.p;
						} else if (map[xB][yB].size() == 1) {
							Node n1 = (Node) map[xA][yA].get(1);
							Node n2 = (Node) map[xB][yB].get(0);
							ch += n1.p + n2.p;
						} else {
							Node n1 = (Node) map[xA][yA].get(1);
							Node n2 = (Node) map[xB][yB].get(1);
							if (n1.p >= n2.p) {
								n2 = (Node) map[xB][yB].get(0);
								ch += n1.p + n2.p;
							} else {
								n1 = (Node) map[xA][yA].get(0);
								ch += n1.p + n2.p;
							}
						}
					}
				}

				if (t == time)
					break;

				int dA = A[t];// A의 다음방향
				int dB = B[t];// B의 다음방향

				xA += dr[dA];
				yA += dc[dA];
				xB += dr[dB];
				yB += dc[dB];
			}

			System.out.println("#" + tc + " " + ch);

		}
	}
}