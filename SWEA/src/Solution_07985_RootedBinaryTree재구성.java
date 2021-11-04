import java.util.Scanner;
//중위 순회 순서
//	    4
//  2       6
//1   3   5   7

public class Solution_07985_RootedBinaryTree재구성 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int K = sc.nextInt();
			int max = (int) Math.pow(2, K);
			int[] tree = new int[max];
			for (int i = 1; i < max; i++)
				tree[i] = sc.nextInt();

			System.out.print("#" + tc + " ");
			int start = max / 2;
			for (int i = K - 1; i >= 0; i--) {
				int plus = (int) Math.pow(2, i + 1);
				for (int j = start; j < max; j += plus) {
					System.out.print(tree[j] + " ");
				}
				System.out.println();
				start /= 2;
			}

		}
	}
}
