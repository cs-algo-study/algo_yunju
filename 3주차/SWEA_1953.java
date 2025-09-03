import java.util.*;
import java.io.*;

class Position {
	int r, c, n;
	
	public Position(int r, int c, int n) {
		this.r = r;
		this.c = c;
		this.n = n;
	}
}

class Solution {
	static int[][] map;
	static boolean[][] visited;
	static int result;
	static int N, M, R, C, L;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[][] haveDir = {
			{},  // 0
			{0, 1, 2, 3},  // 1
			{0, 2}, // 2
			{1, 3}, // 3
			{0, 3}, //4
			{2, 3}, // 5
			{1, 2}, // 6
			{0, 1}  // 7
	};
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			
			map = new int[N][M];
			visited = new boolean[N][M];
			result = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			BFS(R, C);
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j]) {
						result++;
					}
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static void BFS(int r, int c) {
		visited[r][c] = true;
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(r, c, map[r][c]));
		int time = 1;
		
		if(time == L) {
			return;
		}
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0; s<size; s++) {
				Position now = queue.poll();

				for(int i=0; i<haveDir[now.n].length; i++) {
					int curDir = haveDir[now.n][i];
					int nextR = now.r + dr[curDir];
					int nextC = now.c + dc[curDir];
					
					if(!isRange(nextR, nextC)) continue;
					if(map[nextR][nextC] == 0) continue;
					if(!isConnected(nextR, nextC, (curDir+2)%4)) continue;
					if(visited[nextR][nextC]) continue;
					
					visited[nextR][nextC] = true;
					queue.add(new Position(nextR, nextC, map[nextR][nextC]));
					
				}
			}
			time++;
			if(time == L) {
				break;
			}
		}
	}
	
	public static boolean isConnected(int r, int c, int dir) {
		int nextTunnel = map[r][c];
		for(int d : haveDir[nextTunnel]) {
			if(d == dir) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isRange(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<M) {
			return true;
		}
		return false;
	}
}