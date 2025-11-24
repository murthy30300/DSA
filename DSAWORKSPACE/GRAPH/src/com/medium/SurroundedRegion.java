package com.medium;

import java.util.*;

public class SurroundedRegion {
	void dfsr(int row,int col, int delX[], int delY[],int vis[][],char mat[][]) {
		vis[row][col] = 1;
		int n = mat.length;
		int m = mat[0].length;
		for(int i=0;i<4;i++) {
			int nrow = row+delX[i];
			int ncol = col+delY[i];
			if(nrow>=0&&nrow<n&&ncol>=0&&ncol<m&&vis[nrow][ncol]==0&&mat[nrow][ncol]=='O') {
				dfsr(nrow,ncol,delX,delY,vis,mat);
			}
		}
	}
	char[][] fill(int n,int m,char mat[][]){
		int[] delX = {0,1,0,-1};
		int[] delY = {1,0,-1,0};
		int[][] vis = new int[n][m];
		for(int i=0;i<m;i++) {
			if(vis[0][i]==0&&mat[0][i]=='O') {
				dfsr(0,i,delX,delY,vis,mat);
			}
			if(vis[n-1][i]==0&&mat[n-1][i]=='O') {
				dfsr(n-1,i,delX,delY,vis,mat);
			}
		}
		for(int j=0;j<n;j++) {
			if(vis[j][0] == 0 && mat[j][0]=='O') {
				dfsr(j,0,delX,delY,vis,mat);
			}
			if(vis[j][m-1]==0 && mat[j][m-1]=='O') {
				dfsr(j,m-1,delX,delY,vis,mat);
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(mat[i][j]=='O' && vis[i][j]==0)mat[i][j] = 'X';
			}
		}
		return mat;
	}
	public static void main(String[] args) {
		char mat[][] = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'O', 'X', 'X' },
				{ 'X', 'X', 'O', 'O' } };

		// n = 5, m = 4
		SurroundedRegion ob = new SurroundedRegion();
		char[][] ans = ob.fill(5, 4, mat);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}
}
