import java.util.*;
import java.io.*;

class Solution {
	static boolean[] visited;
	static ArrayList<Integer>[] list;
	
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			list = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			visited = new boolean[N+1];
			
			for(int i=0; i<M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				list[a].add(b);
				list[b].add(a);
			}
			
			int result = 0;
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					BFS(i);
					result++;
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static void BFS(int n) {
		Queue<Integer> queue = new LinkedList<>();
		visited[n] = true;
		queue.add(n);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : list[now]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
	}
}