package com.learning;

import java.util.*;

public class DFS {
	public static void findDfs(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> al = new ArrayList<>();
		int n = adj.size();
		int[] vis = new int[n + 1];
		dfs(0, vis, adj, al);
		for (int i : al) {
			System.out.println(i + " ");
		}
	}

	public static void dfs(int node, int vis[], ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> al) {
		vis[node] = 1;
		al.add(node);
		for (int i : adj.get(node)) {
			if (vis[i] == 0) {
				dfs(i, vis, adj, al);
			}
		}

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
		findDfs(adj);
	}

}
