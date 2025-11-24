package com.disjointset;
import java.util.*;
class DisjointGraph{
	List<Integer> rank = new ArrayList<Integer>();
	List<Integer> parent = new ArrayList<Integer>();
	List<Integer> size = new ArrayList<Integer>();
	public DisjointGraph(int n) {
		for(int i=0;i<=n;i++) {
			rank.add(0);
			parent.add(i);
			size.add(1);
		}
	}
	public int findUpar(int node){
		if(node == parent.get(node)) {
			return node;
		}
		int ulp = findUpar(parent.get(node));
		parent.set(node, ulp);
		return parent.get(node);
	}
	public void findUnionByRank(int u, int v) {
		int pu = findUpar(u);
		int pv = findUpar(v);
		if(pu == pv) return;
		if(rank.get(pu)<rank.get(pv)) {
			parent.set(pu, pv);
		}
		else if(rank.get(pu)>rank.get(pv)) {
			parent.set(pv, pu);
		}
		else {
			parent.set(pv, pu);
			int rankU = rank.get(pu);
			rank.set(pv, rankU+1);
		}
	}
	public void findUnionBySize(int u, int v) {
		int pu = findUpar(u);
		int pv = findUpar(v);
		if(pu == pv) return;
		if(size.get(pu)<size.get(pv)) {
			parent.set(pu, pv);
			size.set(pv, size.get(pu)+size.get(pv));
		}
		else {
			parent.set(pv, pu);
			size.set(pu, size.get(pu)+size.get(pv));
		}
	}
}
public class DisjointSet {
	public static void main(String[] args) {
		DisjointGraph dj = new DisjointGraph(7);
		dj.findUnionByRank(1,2);
		dj.findUnionByRank(2,3);
		dj.findUnionByRank(4,5);
		dj.findUnionByRank(6,7);
		dj.findUnionByRank(5,6);
		//dj.findUnionByRank(3,7);	
		if(dj.findUpar(3)==dj.findUpar(4)) {
			System.out.println("same");
		}
		else {
			System.out.println("Not same");
		}
		dj.findUnionByRank(3,7);
		if(dj.findUpar(3)==dj.findUpar(7)) {
			System.out.println("Same");
		}
		else {
			System.out.println("Both are Not Same");
		}
		
		DisjointGraph ds = new DisjointGraph(7);
		ds.findUnionBySize(1,2);
		ds.findUnionBySize(2,3);
		ds.findUnionBySize(4,5);
		ds.findUnionBySize(6,7);
		ds.findUnionBySize(5,6);
		//dj.findUnionByRank(3,7);	
		if(ds.findUpar(3)==ds.findUpar(4)) {
			System.out.println("same by size");
		}
		else {
			System.out.println("Size:Not same");
		}
		ds.findUnionBySize(3,7);
		if(ds.findUpar(3)==ds.findUpar(7)) {
			System.out.println("Size:Same");
		}
		else {
			System.out.println("Size: Both are Not Same");
		}
	}
	

}
