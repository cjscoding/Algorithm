import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_05658_보물상자비밀번호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int n = N / 4;

			Queue<Integer> q = new LinkedList<Integer>();// 숫자 로테이션 돌릴 큐
			String nums = sc.next();
			for (int i = nums.length() - 1; i >= 0; i--) {
				int tmp = nums.charAt(i);
				if (tmp < 65) {// 숫자면
					q.offer(tmp - '0');
				} else {// 알파벳이면
					q.offer(tmp - '7');
				}
			}

			ArrayList<Integer> list = new ArrayList<>();// 만들 수 있는 숫자들 저장할 배열
			for (int rotate = 0; rotate < n; rotate++) {
				for (int i = 0; i < 4; i++) {
					int num = 0;
					for (int j = 0; j < n; j++) {
						int tmp = q.poll();
						num += tmp * Math.pow(16, j);
						q.offer(tmp);
					}

					if (!list.contains(num))
						list.add(num);
				}

				int goToEnd = q.poll();
				q.offer(goToEnd);
			}

			Collections.sort(list, Collections.reverseOrder());
			System.out.println("#" + tc + " " + list.get(K - 1));
		}
	}

}
