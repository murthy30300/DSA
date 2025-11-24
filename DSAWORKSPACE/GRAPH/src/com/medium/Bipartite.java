package com.medium;
import java.util.*;
public class Bipartite {
	private static boolean isBipartite(int node, ArrayList<ArrayList<Integer>> adj) {
		int[] color = new int[node];
		for(int i=0;i<node;i++) {
			if(dfs(i,0,color,adj) == false) return false;
		}
		return true;
	}
	private static boolean dfs(int node, int col, int[] color,ArrayList<ArrayList<Integer>> adj ) {
		color[node] = col;
		for(int it:adj.get(node)) {
			if(color[it] == -1) {
				if(dfs(it,1-col,color,adj)==false) return false;
			}
			else if(color[it] == col) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList < > ();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList <>());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

       
        boolean ans = isBipartite(4, adj);
//        if(ans)
//            System.out.println(ans);
//        else 
        System.out.println(!ans);
	}

}
