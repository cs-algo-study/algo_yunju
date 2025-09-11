import java.util.*;
import java.io.*;

class Solution {
	static int[] list, length;
	static int N;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			list = new int[N];
			length = new int[N];
			for(int i=0; i<N; i++) {
				list[i] = sc.nextInt();
			}
			
			for(int k=0; k<N; k++) {
				length[k] = 1;
				for(int i=0; i<k; i++) {
					if(list[i] < list[k]) {
						length[k] = Math.max(length[k], length[i]+1);
					}
				}
			}
			
			Arrays.sort(length);
			System.out.println("#" + test_case + " " + length[N-1]);
		}
	}
}