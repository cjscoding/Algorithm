import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main_18870_좌표압축 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		//입력받기
		int N = sc.nextInt();
		int[] origin = new int[N];
		for(int i = 0; i < N; i++) 
			origin[i] = sc.nextInt();
		
		//배열 클론 뜨기(원본 저장해 두기)
		int[] temp = origin.clone();
		
		//크기에 따라 idx부여하기 위해 정렬
		Arrays.sort(origin);
		
		//순서대로 idx(밸류로)값 할당
		int cnt = 0;
		HashMap<Integer, Integer> hMap = new HashMap<>();
		for(int i = 0; i < origin.length; i++) {
			if(!hMap.containsKey(origin[i]))
				hMap.put(origin[i], cnt++);
		}
		
		//원본 값 차례대로 돌면서 해당 밸류값 가져옴
		for(int i = 0; i < temp.length; i++) {
			sb.append(hMap.get(temp[i])+" ");
		}
		
		System.out.println(sb);
	}

}
