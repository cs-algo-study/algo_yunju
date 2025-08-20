import java.io.*;
import java.util.*;

class Solution {
	static int N;
	static int[][] cheese;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		StringBuilder builder = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			cheese = new int[N][N];
			
			int maxDay = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					cheese[i][j] = sc.nextInt();
					maxDay = Math.max(cheese[i][j], maxDay);
				}
			}
			
			int maxCnt = 0;
			for(int day=0; day<=maxDay; day++) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(cheese[i][j] == day) {
							cheese[i][j] = 0;
						}
					}
				}
				visited = new boolean[N][N];
			
				int count = 0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(!visited[i][j] && cheese[i][j] != 0) {
							findCnt(i, j);
							count++;
						}
					}
				}
			
				maxCnt = Math.max(maxCnt, count);
			}
			builder.append("#" + test_case + " " + maxCnt).append("\n");
		}
		System.out.println(builder);
	}
	
	public static void findCnt(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		visited[r][c] = true;
		queue.add(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(isRange(nx, ny) && !visited[nx][ny] && cheese[nx][ny] != 0) {
					visited[nx][ny] = true;
					queue.add(new int[] {nx,ny});
				}
			}
		}
	}
	
	public static boolean isRange(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}
		return false;
	}
}