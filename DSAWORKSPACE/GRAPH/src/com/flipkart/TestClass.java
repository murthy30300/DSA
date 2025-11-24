package com.flipkart;

import java.util.*;

class FindShortestPath {
    private int V;
    private int E;
    private int[][] edges;
    private int[][] pairs;
    private int colorNumber;
    private List<List<int[]>> adj;

    FindShortestPath(int V, int E, int[][] edges, int[][] pairs, int colorNumber) {
        this.V = V;
        this.E = E;
        this.edges = edges;
        this.pairs = pairs;
        this.colorNumber = colorNumber;
        buildGraph();
    }

    private void buildGraph() {
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }
    }

    private int[] dijkstra(int start, int end) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        dist[start] = 0;
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (visited[u]) continue;
            visited[u] = true;

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0], w = neighbor[1];
                if (!visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int curr = end;
        while (curr != -1) {
            path.add(curr);
            curr = parent[curr];
        }
        Collections.reverse(path);

        return path.stream().mapToInt(i -> i).toArray();
    }

    int[] processData() {
        if (colorNumber > pairs.length || pairs[colorNumber - 1][1] == 0 && V % 2 != 0)
            return new int[]{pairs[colorNumber - 1][0]}; // Handle leftover node in case of odd V

        int u = pairs[colorNumber - 1][0];
        int v = pairs[colorNumber - 1][1];
        return dijkstra(u, v);
    }

    public void printResult() {
        int[] result = processData();
        for (int i = 0; i < result.length; ++i) {
            System.out.print(result[i]);
            if (i != result.length - 1)
                System.out.print(" ");
        }
    }
}

public class TestClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        List<int[]>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w}); // undirected
        }

        int pairCount = V / 2 + (V % 2);
        int[][] pairs = new int[pairCount][2];

        for (int i = 0; i < pairCount - 1; i++) {
            pairs[i][0] = sc.nextInt();
            pairs[i][1] = sc.nextInt();
        }

        if (V % 2 == 1) {
            pairs[pairCount - 1][0] = sc.nextInt();
        } else {
            pairs[pairCount - 1][0] = sc.nextInt();
            pairs[pairCount - 1][1] = sc.nextInt();
        }

        int colorQuery = sc.nextInt();

        if (pairs[colorQuery - 1][1] == 0 && V % 2 == 1) {
            return; // No valid pair for this color
        }

        int src = pairs[colorQuery - 1][0];
        int dest = pairs[colorQuery - 1][1];

        int[] parent = new int[V];
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int node = dest;
        while (node != -1) {
            path.add(node);
            node = parent[node];
        }

        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i != path.size() - 1) System.out.print(" ");
        }
    }
}