import java.util.*;
import java.io.*;

class Solution {
	static int[] rate;
	static int[] month;
	static int result;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			rate = new int[4];
			for(int i=0; i<4; i++) {
				rate[i] = sc.nextInt();
			}
			month = new int[13];
			for(int i=1; i<=12; i++) {
				month[i] = sc.nextInt();
			}
			result = Integer.MAX_VALUE;
			DFS(1, 0);
			result = Math.min(result, rate[3]);
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static void DFS(int now, int amount) {
		if(now > 12) {
			result = Math.min(result, amount);
			return;
		}
		
		// 1일 요금제
		DFS(now+1, amount+(month[now]*rate[0]));
		
		// 1달 요금제
		DFS(now+1, amount+rate[1]);
		
		// 3달 요금제
		DFS(now+3, amount+rate[2]);
		
	}
}