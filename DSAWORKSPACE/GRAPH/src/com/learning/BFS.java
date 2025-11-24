package com.learning;
import java.util.*;
public class BFS {
	public static void bfs(ArrayList<ArrayList<Integer>> adj) {
		int n = adj.size();
		int[] vis = new int[n];
		Queue<Integer> q = new LinkedList<>();
		vis[0] = 1;
		q.add(0);
		ArrayList<Integer> al= new ArrayList<>();
		while(!q.isEmpty()) {
			int node = q.poll();
			al.add(node);
			for(int it:adj.get(node)) {
				if(vis[it] == 0) {
					vis[it] =1;
					q.add(it);
				}
			}
			
		}
		System.out.println(al);
	}
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 5; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(0).add(2);
		adj.get(2).add(0);
		adj.get(0).add(1);
		adj.get(1).add(0);
		adj.get(0).add(3);
		adj.get(3).add(0);
		adj.get(2).add(4);
		adj.get(4).add(2);
		bfs(adj);

	}

}
