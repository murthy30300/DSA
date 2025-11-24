package com.medium;
import java.util.*;
public class Matrix {
	class Pair{
		int row,col,step;
		Pair(int row, int col, int step){
			this.row = row;
			this.col = col;
			this.step = step;
		}
	}
	int[][] nearest(int[][] grid){
		int n = grid.length;
		int m = grid[0].length;
		int[][] vis = new int[n][m];
		int[][] dis = new int[n][m];
		Queue<Pair> q = new LinkedList<>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(grid[i][j]==0) {
					q.add(new Pair(i,j,0));
					vis[i][j] = 1;
				}
				else {
					vis[i][j] = 0;
				}
			}
		}
		int[] delX = {0,1,0,-1};
		int[] delY = {-1,0,1,0};
		while(!q.isEmpty()) {
			int row = q.peek().row;
			int col = q.peek().col;
			int steps = q.peek().step;
			q.poll();
			dis[row][col] = steps;
			for(int i=0;i<4;i++) {
				int nrow = row+delX[i];
				int ncol = col+delY[i];
				if(nrow>=0&&nrow<n&&ncol>=0&&ncol<m&&vis[nrow][ncol]==0) {
					vis[nrow][ncol] =1;
					q.add(new Pair(nrow,ncol,steps+1));
				}
			}
		}
		return dis;
	}
	public static void main(String[] args) {
		int[][] grid =  {{0,0,0},{0,1,0},{1,1,1}};

		Matrix obj = new Matrix();
		int[][] ans = obj.nearest(grid);
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[i].length; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}
}
