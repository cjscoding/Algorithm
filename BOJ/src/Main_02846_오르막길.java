import java.util.Scanner;

public class Main_02846_오르막길 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] nums = new int[N];
		for (int i = 0; i < N; i++)
			nums[i] = sc.nextInt();

		int start = 0;
		int end = 0;
		int max = 0;
		while (true) {
			if (end == N - 1)
				break;

			if (nums[end] < nums[end + 1]) {
				end++;
				max = Math.max(max, nums[end] - nums[start]);
			} else {
				max = Math.max(max, nums[end] - nums[start]);
				end++;
				start = end;
			}
		}

		System.out.println(max);
	}
}
