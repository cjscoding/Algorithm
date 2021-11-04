import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_02383_점심식사시간 {

	static int N, ans;
	static int[][] map;
	static ArrayList<Node> list;
	static Node[] stairs;

	static class Node {

		int r, c, h;
		int idx, time;// 계단인덱스, 계단까지 가는데 걸리는 시간

		Node(int r, int c) {// 사람용
			this.r = r;
			this.c = c;
		}

		Node(int r, int c, int h) {// 계단용
			this.r = r;
			this.c = c;
			this.h = h;
		}

		private void arrive() {
			this.h = stairs[idx].h;
			this.time = Math.abs(r - stairs[idx].r) + Math.abs(c - stairs[idx].c);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			N = sc.nextInt();// 방 한 변의 길이
			ans = Integer.MAX_VALUE;
			list = new ArrayList<>();
			stairs = new Node[2];// 계단 위치 (항상 2개)
			map = new int[N][N];// 방

			int idx = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					int input = sc.nextInt();
					map[i][j] = input;
					if (input == 1) // 사람이면
						list.add(new Node(i, j));// 사람리스트에 추가
					else if (input > 1) // 계단이면
						stairs[idx++] = new Node(i, j, input);// 노드에 계단 높이 까지 넣어주기
				}

			whichStair(0);

			System.out.println("#" + tc + " " + ans);
		}
	}

	// 각 사람이 어떤 계단 사용할 지 모든 경우 구하기(각각 계단 1로 가는 경우, 계단 2로 가는 경우)
	static void whichStair(int p) {// 현재 보내려는 사람의 인덱스

		if (p == list.size()) {// 모든 사람 어떤 계단으로든 보냈으면
			calTime();//이동 완료 시간 구하기
			return;
		}
		

		// 계단 1로 가는 경우
		list.get(p).idx = 0;// p번째 사람이 가는 계단의 인덱스를 정해주고
		list.get(p).arrive();// 그 계단까지의 도착시간 계산하도록 함수 호출
		whichStair(p + 1);// 다음 사람 설정하러 가기

		// 계단 2로 가는 경우
		list.get(p).idx = 1;
		list.get(p).arrive();
		whichStair(p + 1);

	}

	static int[] stair1, stair2;
	//이동 완료 시간 구하기
	static void calTime() {
		stair1 = new int[list.size()];
		stair2 = new int[list.size()];
		int idx1 = 0;
		int idx2 = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).idx == 0) //계단 1 사용자라면
				stair1[idx1++] = list.get(i).time;//계단 1까지 도착시간 저장
			else if(list.get(i).idx == 1) //계단 2 사용자라면
				stair2[idx2++] = list.get(i).time;//계단 2까지 도착시간 저장
		}
		
		//계단 1에서 마지막 사람이 다 내려갈 때까지
		int time1 = 0;
		if(idx1 > 0) {
			Arrays.sort(stair1, 0, idx1);
			while(true) {
				int three = 0;
				for(int i = 0; i < idx1; i++) {
					if(stair1[i] > 0) {//아직 계단에 도착하지 못했다면
						stair1[i]--;
					}else if(stair1[i] == (stairs[0].h * -1)) {//계단을 모두 내려갔다면
						continue;
					}else if(stair1[i] < 0) {//계단을 내려가는 중이라면
						stair1[i]--;
						three++;
					}else if(stair1[i] == 0) {//계단에 도착한 상태면
						if(three < 3) {//아직 계단 이용자가 3명이 안되었다면
							stair1[i]--;
							three++;
						}
					}
				}
				time1++;
                if(stair1[idx1-1] == (stairs[0].h * -1)){//모두 도착했으면
                	time1++;
                    break;
                }
			}
		}
		
		//계단 2에서 마지막 사람이 다 내려갈 때까지
		int time2 = 0;
		if(idx2 > 0) {			
			Arrays.sort(stair2, 0, idx2);
			while(true) {
				int three = 0;
				for(int i = 0; i < idx2; i++) {
					if(stair2[i] > 0) {//아직 계단에 도착하지 못했다면
						stair2[i]--;
					}else if(stair2[i] == (stairs[1].h * -1)) {//계단을 모두 내려갔다면
						continue;
					}else if(stair2[i] < 0) {//계단을 내려가는 중이라면
						stair2[i]--;
						three++;
					}else if(stair2[i] == 0) {//계단에 도착한 상태면
						if(three < 3) {//아직 계단 이용자가 3명이 안되었다면
							stair2[i]--;
							three++;
						}
					}
				}
				time2++;
                if(stair2[idx2-1] == (stairs[1].h * -1)){//모두 도착했으면
                	time2++;
                    break;
                }
			}
		}
		
		int time = Math.max(time1, time2);//계단 1과 계단 2의 소요 시간 비교하여 큰 값만큼 걸린 것으로 설정
		ans = Math.min(time, ans);//최소시간 걸렸다면 갱신
	}
}