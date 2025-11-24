package com.medium;
import java.util.*;
public class Cycle {
	class Node{
		int curNode,parNode;
		Node(int curNode, int parNode){
			this.curNode = curNode;
			this.parNode = parNode;
		}
	}
	public boolean checkCycleBfs(ArrayList<ArrayList<Integer>> adj, int node, boolean[] vis) {
		vis[node] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(node,-1));
		while(!q.isEmpty()) {
			int cur = q.peek().curNode;
			int par = q.peek().parNode;
			q.poll();
			for(int it : adj.get(cur)) {
				if(vis[it]==false) {
					q.add(new Node(it,cur));
					vis[it] = true;
				}
				else if(par!=it)return true;
			}
			
		}
	return false;		
	}
	public boolean isCycleBfs(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] vis  = new boolean[V];
		Arrays.fill(vis,false);
		for(int i=0;i<V;i++) {
			if(vis[i]==false) {
				if(checkCycleBfs(adj, i , vis)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean isCycleDfs(int V, ArrayList<ArrayList<Integer>> adj) {
		int[] vis = new int[V];
		for(int i=0;i<V;i++) {
			if(vis[i] == 0) {
				if(dfs(i,-1,vis,adj)==true)return true;
			}
		}
		return false;
	}
	public boolean dfs(int node, int parent, int[] vis, ArrayList<ArrayList<Integer>> adj) {
		vis[node] = 1;
		for(int an:adj.get(node)) {
			if(vis[an] == 0) {
				if(dfs(an,node,vis,adj))return true;
			}
			else if(an!=parent) return true;
		}
		return false;
	}
	public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
                
        Cycle obj = new Cycle();
        boolean ans = obj.isCycleBfs(4, adj);
        if (ans)
            System.out.println("1");    
        else
            System.out.println("0");
        boolean res = obj.isCycleDfs(4,adj);
        if (res)
            System.out.println("1");    
        else
            System.out.println("0");

	}

}
