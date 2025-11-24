package com.medium;

public class FloodFill {
	public void dfs(int row, int col, int[][] ans,int[][] image, int iniColor, int newColor, int[] delRow, int[] delCol) {
		ans[row][col] = newColor;
		int m = ans.length;
		int n = ans[0].length;
		for(int i=0;i<4;i++) {
			int nrow = row+delRow[i];
			int ncol = col+delCol[i];
			if(nrow>=0&&nrow<m&&ncol>=0&&ncol<n&&image[nrow][ncol]==iniColor&&ans[nrow][ncol]!=newColor) {
				dfs(nrow,ncol,ans,image, iniColor,newColor,delRow, delCol);
			}
		}
		
	}
	public int[][] floodFill(int[][] image, int sr, int sc, int nc){
		int ic = image[sr][sc];
		int[][] ans = image;
		int[] delRow = {0,1,0,-1};
		int[] delCol = {1,0,-1,0};
		dfs(sr,sc,ans,image,ic,nc,delRow,delCol);
		return ans;
	}
	public static void main(String[] args) {
		  int[][] image =  {
			        {1,1,1},
			        {1,1,0},
			        {1,0,1}
		  };
		  FloodFill obj = new FloodFill();
		  int[][] ans = obj.floodFill(image, 1, 1, 2);
		  for (int i = 0; i < ans.length; i++) {
				for (int j = 0; j < ans[i].length; j++)
					System.out.print(ans[i][j] + " ");
				System.out.println();
		  }
	}

}
