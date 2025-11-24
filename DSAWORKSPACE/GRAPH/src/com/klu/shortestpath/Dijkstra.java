package com.klu.shortestpath;

import java.util.*;

public class Dijkstra {
	static class Pair {
		int dist, node;

		Pair(int dist, int node) {
			this.dist = dist;
			this.node = node;
		}
	}

	public static List<Integer> shortestPath(int n, int m, int edges[][]) {
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int d = edges[i][2];
			int u = edges[i][0];
			int v = edges[i][1];
			adj.get(u).add(new Pair(d, v));
			adj.get(v).add(new Pair(d, u));
		}
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
		int[] dist = new int[n + 1];
		int[] parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = (int) 1e9;
			parent[i] = i;
		}
		dist[1] = 0;
		pq.offer(new Pair(0, 1));
		while (!pq.isEmpty()) {
			Pair cur = pq.peek();
			int d = cur.dist;
			int curNode = cur.node;
			pq.poll();
			for (Pair it : adj.get(curNode)) {
				int edgWt = it.dist;
				int adjNode = it.node;
				if (edgWt + d < dist[adjNode]) {
					dist[adjNode] = edgWt + d;
					pq.offer(new Pair(edgWt + d, adjNode));
					parent[adjNode] = curNode;

				}
			}

		}
		List<Integer> path = new ArrayList<>();
		if (dist[n] == (int) 1e9) {
			path.add(-1);
			return path;
		}
		int nd = n;
		while (parent[nd] != nd) {
			path.add(nd);
			nd = parent[nd];
		}
		path.add(1);
		Collections.reverse(path);
		return path;

	}

	public static void main(String[] args) {
		int n = 5, m = 6;
		int[][] edges = { { 1, 2, 2 }, { 1, 3, 4 }, { 2, 3, 1 }, { 2, 4, 7 }, { 3, 5, 3 }, { 4, 5, 1 } };

		System.out.println(shortestPath(n, m, edges));
	}

}
