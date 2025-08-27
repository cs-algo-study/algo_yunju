import java.util.*;
import java.io.*;

class Point {
	int x;
	int y;
	int d;
	
	public Point(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
	
	public void getDistance(Point o) {
		int dis = Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
		this.d = dis + o.d;
	}
}

class Solution {
	static boolean[] visited;
	static Point[] customers;
	static int min, N;
	static Point company, home;
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		StringBuilder builder = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			visited = new boolean[N];
			customers = new Point[N];
			company = new Point(sc.nextInt(), sc.nextInt(), 0);
			home = new Point(sc.nextInt(), sc.nextInt(), 0);
			
			for(int i=0; i<N; i++) {
				customers[i] = new Point(sc.nextInt(), sc.nextInt(), 0);
			}
			
			min = Integer.MAX_VALUE;
			bruteforce(company, 0);
			builder.append("#" + test_case + " ").append(min).append("\n");
		}
		System.out.println(builder);
	}
	
	public static void bruteforce(Point p, int depth) {
		if(depth == N) {
			home.getDistance(p);
			min = Math.min(min, home.d);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				customers[i].getDistance(p);
				bruteforce(customers[i], depth+1);
				visited[i] = false;
			}
		}
	}
}