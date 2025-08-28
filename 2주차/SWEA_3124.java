import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
	int v, w;
	
	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.w - o.w;
	}
}

class Solution {
	static ArrayList<Edge>[] list;
	static boolean[] visited;
	static long total;
	static int V, E;
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		StringBuilder builder = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			V = sc.nextInt();
			E = sc.nextInt();
			
			list = new ArrayList[V+1];
			for(int i=1; i<=V; i++) {
				list[i] = new ArrayList<>();
			}
			visited = new boolean[V+1];
			
			for(int i=0; i<E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int c = sc.nextInt();
				list[a].add(new Edge(b, c));
				list[b].add(new Edge(a, c));
			}
			
			total = 0;
			prim(1);
			builder.append("#" + test_case + " ").append(total).append("\n");
		}
		System.out.println(builder);
	}
	
	public static void prim(int node) {

		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.add(new Edge(node, 0));

		while(!queue.isEmpty()) {
			Edge now = queue.poll();
			if(visited[now.v]) {
				continue;
			}
			visited[now.v] = true;
			total += now.w;
			
			for(Edge next : list[now.v]) {
				if(!visited[next.v]) {
					queue.add(next);
				}
			}
		}
	}
}