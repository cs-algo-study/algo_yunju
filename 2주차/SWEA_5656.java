import java.util.*;
import java.io.*;

class Block {
	int r, c, v;
	
	public Block(int r, int c, int v) {
		this.r = r;
		this.c = c;
		this.v = v;
	}
}

class Solution {
	static int W, H, N;
	static int result;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{		
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			map = new int[H][W];
			int cnt = 0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] > 0) {
						cnt++;
					}
				}
			}
			
			result = cnt+1;
			int[][] temp = new int[H][W];
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			for(int i=0; i<W; i++) {
				for(int j=0; j<H; j++) {
					if(map[j][i] != 0) {
						crushBlock(0, j, i, cnt);
						for(int r=0; r<H; r++) {
							for(int c=0; c<W; c++) {
								map[r][c] = temp[r][c];
							}
						}
						break;
					}
					
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static void crushBlock(int crushCnt, int r, int c, int cnt) {
		Queue<Block> queue = new LinkedList<>();

		if(crushCnt == N) {
			if(result > cnt) {
				result = cnt;
			}
			return;
		}
		
		queue.add(new Block(r, c, map[r][c]));
		map[r][c] = 0;
		cnt--;
		
		while(!queue.isEmpty()) {
			Block now = queue.poll();
			for(int i=1; i<now.v; i++) {
				for(int d=0; d<4; d++) {
					int nextR = now.r + i*dr[d];
					int nextC = now.c + i*dc[d];
					
					if(!isRange(nextR, nextC)) {
						continue;
					}
					
					if(map[nextR][nextC] == 0) {
						continue;
					}
					
					queue.add(new Block(nextR, nextC, map[nextR][nextC]));
					map[nextR][nextC] = 0;
					cnt--;
				}
			}
		}
		
		if(cnt <= 0) {
			result = 0;
			return;
		}
		
		//블록 내리기
		for(int j=0; j<W; j++) {
			int down = H-1;
			while(down > 0) {
				if(map[down][j] == 0) {
					int next = down-1;
					while(next>0 && map[next][j] ==0) {
						next--;
					}
					map[down][j] = map[next][j];
					map[next][j] = 0;
				}
				down--;
			}
		}
		

		int[][] temp = new int[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		//다음 블록 부수기
		for(int i=0; i<W; i++) {
			for(int j=0; j<H; j++) {
				if(map[j][i] != 0) {
					crushBlock(crushCnt+1, j, i, cnt);
					for(int x=0; x<H; x++) {
						for(int y=0; y<W; y++) {
							map[x][y] = temp[x][y];
						}
					}
					break;
				}
			}
		}
		
	}
	
	public static boolean isRange(int r, int c) {
		if(r>=0 && r<H && c>=0 && c<W) {
			return true;
		}
		return false;
	}
}