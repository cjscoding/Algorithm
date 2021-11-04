import java.util.Scanner;

public class Main_02581_소수 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[] nums = new int[10001];
		for (int i = 2; i <= N; i++) {
			if(nums[i] == 0) {
				for(int j = i+i; j <= N; j += i) {
					nums[j] = 1;
				}
			}
		}

		int sum = 0;
		int min = 0;
		for (int i = M; i <= N; i++) {
			if(nums[i] == 1 || i == 1)
				continue;
			
			if(min == 0)
				min = i;

			sum += i;
		}
		
		System.out.println(min == 0 ? -1 : (sum + "\n" + min));
	}

}
