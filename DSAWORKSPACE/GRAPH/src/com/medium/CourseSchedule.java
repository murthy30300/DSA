package com.medium;
import java.util.*;
public class CourseSchedule {
	public static ArrayList<Integer> findOrder(int N, int M, int[][] edges) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for(int i=0;i<N;i++) {
			adj.add(new ArrayList<>());
		}
		for(int[] e:edges) {
			int u = e[1];
			int v = e[0];
			adj.get(u).add(v);
		}
		int[] indegree = new int[N];
		for(int i=0;i<N;i++) {
			for(int it:adj.get(i)) {
				indegree[it]++;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for(int i=0;i<N;i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		ArrayList<Integer> topo = new ArrayList<>();
		while(!q.isEmpty()) {
			int node = q.poll();
			topo.add(node);
			for(int it:adj.get(node)) {
				indegree[it]--;
				if(indegree[it]==0)q.offer(it);
			}
		}
		return topo;
		
	}
	public static void main(String[] args) {
		int N = 4;
        int M = 3;
        int[][] edges = {{1,0},{2,1},{3,2}};

        ArrayList<Integer> ans = findOrder(N, M, edges);

        for (int task : ans) {
            System.out.print(task + " ");
        }
        System.out.println("");
	}

}
