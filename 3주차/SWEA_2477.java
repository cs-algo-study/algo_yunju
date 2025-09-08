import java.util.*;
import java.io.*;

class Customer {
	int num, time, receptNo, repairNo, rt;

	public Customer(int num, int time, int receptNo, int repairNo, int rt) {
		this.num = num;
		this.time = time;
		this.receptNo = receptNo;
		this.repairNo = repairNo;
		this.rt = rt;
	}
}

class Desk {
	int no, needTime, proc;
	
	public Desk(int no, int needTime, int proc) {
		this.no = no;
		this.needTime = needTime;
		this.proc = proc;
	}
}

class Solution {
	static int N;
	static int M;
	static int K;
	static int A;
	static int B;
	static int[] tk;
	
	static Desk[] receptDesk;
	static Desk[] repairDesk;
	static Customer[] customers;
	static boolean[] done;
	
	static int ans;
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			A = sc.nextInt();
			B = sc.nextInt();
			
			receptDesk = new Desk[N+1];
			repairDesk = new Desk[M+1];
			
			tk = new int[K+1];
			customers = new Customer[K+1];
			done = new boolean[K+1];
			
			ans = 0;
			for(int i=1; i<=N; i++) {
				int n = sc.nextInt();
				receptDesk[i] = new Desk(0, n, 0);
			}
			
			for(int j=1; j<=M; j++) {
				int n = sc.nextInt();
				repairDesk[j] = new Desk(0, n, 0);
			}
			
			for(int k=1; k<=K; k++) {
				tk[k] = sc.nextInt();
				customers[k] = new Customer(k, tk[k], 0, 0, 0);
			}
			
			simulate();
			
			for(int i=1; i<=K; i++) {
				if(customers[i].receptNo==A && customers[i].repairNo==B) {
					ans+=customers[i].num;
				}
			}
			if(ans == 0) {
				ans = -1;
			}
			System.out.println("#"+test_case + " " + ans);
		}
	}
	
	public static void simulate() {
		PriorityQueue<Customer> receptQ = new PriorityQueue<>(new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				if(o1.num < o2.num) {
					return -1;
				}
				return 1;
			}
		});
		
		PriorityQueue<Customer> repairQ = new PriorityQueue<>(new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				if(o1.rt < o2.rt) return -1;
				else if(o1.rt > o2.rt) return 1;
				else {
					if(o1.receptNo < o2.receptNo) return -1;
					else if(o1.receptNo > o2.receptNo) return 1;
				}
				return -1;
			}
		});
		
		int t = 0;
		
		while(!done[K]) {
			
			for(int i=1; i<=K; i++) {
				if(customers[i].time == t) {
					receptQ.add(customers[i]);
				}
			}
			
			for(int i=1; i<=N; i++) {
				if(receptDesk[i].no != 0) {
					receptDesk[i].proc++;
				}
			}
			for(int i=1; i<=M; i++) {
				if(repairDesk[i].no != 0) {
					repairDesk[i].proc++;
				}
			}
			
			for(int i=1; i<=N; i++) {
				if(receptDesk[i].proc == receptDesk[i].needTime) {
					customers[receptDesk[i].no].rt = t;
					repairQ.add(customers[receptDesk[i].no]);
					receptDesk[i].proc = 0;
					receptDesk[i].no = 0;
				}
			}
			
			for(int i=1; i<=M; i++) {
				if(repairDesk[i].proc == repairDesk[i].needTime) {
					done[repairDesk[i].no] = true;
					repairDesk[i].proc = 0;
					repairDesk[i].no = 0;
				}
			}
			
			// 접수 창구에 고객 입장
			for(int i=1; i<=N; i++) {
				if(!receptQ.isEmpty()) {
					if(receptDesk[i].no == 0) {
						Customer customer = receptQ.poll();
						customer.receptNo = i;
						receptDesk[i].no = customer.num;
					}
				}
			}
			
			for(int i=1; i<=M; i++) {
				if(!repairQ.isEmpty()) {
					if(repairDesk[i].no == 0) {
						Customer customer = repairQ.poll();
						customer.repairNo = i;
						repairDesk[i].no = customer.num;
					}
				}
			}
			t++;
		}
	}
}