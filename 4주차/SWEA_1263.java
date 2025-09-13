import java.util.*;
import java.io.*;

class Solution {
	static ArrayList<Integer>[] list;
	static int[][] map;
	static int[] length;
	static boolean[] visited;
	static int[] result;
	static int N;
	
	public static void main(String args[]) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(reader.readLine());
		int T = Integer.parseInt(token.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			token = new StringTokenizer(reader.readLine());
			N = Integer.parseInt(token.nextToken());
			list = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			
			map = new int[N+1][N+1];
			length = new int[N+1];
			result = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					if(map[i][j] == 1) {
						list[i].add(j);
						list[j].add(i);
					}
				}
			}
			
			for(int i=1; i<=N; i++) {
				visited = new boolean[N+1];
				length = new int[N+1];
				BFS(i);
			}
			
			Arrays.sort(result);
			
			System.out.println("#" + test_case + " " + result[1]);
		}
	}
	
	public static void BFS(int n) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		visited[n] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : list[now]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
					length[next] = length[now]+1;
				}
			}
		}
		
		int sum = 0;
		for(int i=1; i<=N; i++) {
			sum += length[i];
		}
		result[n] = sum;
	}
}