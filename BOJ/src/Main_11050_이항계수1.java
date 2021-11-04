import java.util.Scanner;

public class Main_11050_이항계수1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		int ans = recur(N, K);
		System.out.println(ans);
	}

	static int recur(int n, int r) {

		if (r == 0 || r == n) {// 아무것도 안뽑거나 모두 뽑는 경우는 1
			return 1;
		}

		return recur(n - 1, r - 1) + recur(n - 1, r);
	}

}
