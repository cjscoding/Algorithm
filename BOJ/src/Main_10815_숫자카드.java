import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10815_숫자카드 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);

		// 이분탐색
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int input = Integer.parseInt(st.nextToken());
			int left = 0;
			int right = N - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				int midValue = arr[mid];
				if (midValue == input) {
					sb.append(1 + " ");
					break;
				}

				if (midValue < input) {
					left = mid + 1;
				} else
					right = mid - 1;
				
				if (left > right) {
					sb.append(0 + " ");
					break;
				}
			}
		}
		System.out.println(sb);
	}

}
