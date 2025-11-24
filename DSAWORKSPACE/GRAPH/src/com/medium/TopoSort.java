package com.medium;

import java.util.*;

public class TopoSort {
	public static void main(String[] args) {
		int V = 6;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(2).add(3);
		adj.get(3).add(1);
		adj.get(4).add(0);
		adj.get(4).add(1);
		adj.get(5).add(0);
		adj.get(5).add(2);
		ArrayList<Integer> ans = topoSortDfs(V, adj);
		for (int node : ans) {
			System.out.print(node + " ");
		}
		System.out.println("");
		ArrayList<Integer> res = topoSortKhan(V, adj);
		System.out.println(res);
	}

	public static ArrayList<Integer> topoSortDfs(int V, ArrayList<ArrayList<Integer>> adj) {
		int[] vis = new int[V];
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < V; i++) {
			if (vis[i] == 0) {
				dfs(i, vis, st, adj);
			}
		}
		ArrayList<Integer> al = new ArrayList<>();
		while (!st.isEmpty()) {
			al.add(st.pop());
		}
		return al;
	}

	public static void dfs(int node, int[] vis, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj) {
		vis[node] = 1;
		for (int it : adj.get(node)) {
			if (vis[it] != 1) {
				dfs(it, vis, st, adj);
			}
		}
		st.push(node);
	}

	public static ArrayList<Integer> topoSortKhan(int V, ArrayList<ArrayList<Integer>> adj) {
		int[] indegree = new int[V];
		for (int i = 0; i < V; i++) {
			for (int it : adj.get(i)) {
				indegree[it]++;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}
		int[] topo = new int[V];
		int p = 0;
		while (!q.isEmpty()) {
			int node = q.peek();
			q.poll();
			topo[p++] = node;
			for (int it : adj.get(node)) {
				indegree[it]--;
				if (indegree[it] == 0) {
					q.add(it);
				}
			}
		}
		ArrayList<Integer> t = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			t.add(topo[i]);
		}
		return t;
	}

}
