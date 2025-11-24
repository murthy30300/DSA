package com.flipkart;
import java.util.*;

public class TestClass2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int V = sc.nextInt();
        int E = sc.nextInt();

        // Adjacency list for the graph
        List<int[]>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        // Read all edges
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w}); // Since the graph is undirected
        }

        // Determine number of color pairs
        int pairCount = V / 2 + (V % 2);
        int[][] pairs = new int[pairCount][2];

        // Read all pairs
        for (int i = 0; i < pairCount - 1; i++) {
            pairs[i][0] = sc.nextInt();
            pairs[i][1] = sc.nextInt();
        }

        // Handle last color group (could be a single node if V is odd)
        if (V % 2 == 1) {
            pairs[pairCount - 1][0] = sc.nextInt();
            pairs[pairCount - 1][1] = -1; // mark as single node
        } else {
            pairs[pairCount - 1][0] = sc.nextInt();
            pairs[pairCount - 1][1] = sc.nextInt();
        }

        // Read color query
        int colorQuery = sc.nextInt();
        int src = pairs[colorQuery - 1][0];
        int dest = pairs[colorQuery - 1][1];

        // If no valid pair (e.g., leftover node in an odd-sized graph), skip
        if (dest == -1) {
            return;
        }

        // Dijkstra's initialization
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        // Dijkstra's algorithm
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0], weight = neighbor[1];

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int node = dest;
        while (node != -1) {
            path.add(node);
            node = parent[node];
        }
        Collections.reverse(path);

        // Print path
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i != path.size() - 1) System.out.print(" ");
        }
    }
}
