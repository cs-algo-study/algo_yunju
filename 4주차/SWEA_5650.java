import java.util.*;
import java.io.*;

class Solution {
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] blocks = {
			{},  // 0번 블록
			{2, 0, 3, 1},  // 1번 블록
			{3, 2, 0, 1},  // 2번 블록
			{1, 3, 0, 2},  // 3번 블록
			{2, 3, 1, 0},  // 4번 블록
			{2, 3, 0, 1}  // 5번 블록
	};
	static Map<Integer, List<int[]>> wormholes;
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			map = new int[N][N];
			wormholes = new HashMap<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j]>=6 && map[i][j]<=10) {
						wormholes.putIfAbsent(map[i][j], new ArrayList<>());
						wormholes.get(map[i][j]).add(new int[] {i, j});
					}
				}
			}
			int result = 0;
			int sum = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 0) {
						for(int d=0; d<4; d++) {
							sum = simulate(i, j, d);
							result = Integer.max(sum, result);
						}
					}
				}
			}
			
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static int simulate(int sr, int sc, int dir) {
		int r = sr;
		int c = sc;
		int d = dir;
		int score = 0;
		
		while(true) {
			r += dx[d];
			c += dy[d];
			
			// 벽에 부딪히는 경우 반대방향으로
			if(!isRange(r, c)) {
				d = (d+2) % 4;
				score++;
				continue;
			}
			
			// 출발 위치 도착 or 블랙홀 도착
			if((r==sr && c==sc) || map[r][c]==-1) {
				return score;
			}
			
			// 블록을 만나는 경우
			if(map[r][c] >= 1 && map[r][c] <= 5) {
				score++;
				d = blocks[map[r][c]][d];
			}
			
			// 웜홀을 만나는 경우
			if(map[r][c] >= 6 && map[r][c] <= 10) {
				List<int[]> pos = wormholes.get(map[r][c]);
				if(pos.get(0)[0] == r && pos.get(0)[1] == c) {
					r = pos.get(1)[0];
					c = pos.get(1)[1];
				} else {
					r = pos.get(0)[0];
					c = pos.get(0)[1];
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