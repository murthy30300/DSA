package com.ma;
import java.util.*;
public class Astrologer {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		ArrayList<Integer> al = new ArrayList<>();
		for(int i=0;i<m*n;i++) {
			al.add(sc.nextInt());
		}
		int[][] mat = new int[m][n];
		int index=0;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				mat[i][j] = al.get(index);
				index++;
			}
		}
		int[] rowSum = new int[m];
		int[] colSum = new int[n];
		for(int i=0;i<m;i++) {
			int s=0;
			for(int j=0;j<n;j++) {
				s+= mat[i][j];
			}
			rowSum[i] = s;
		}
		for(int i=0;i<m;i++) {
			int s=0;
			for(int j=0;j<n;j++) {
				s+= mat[j][i];
			}
			colSum[i] = s;
		}
		int rowMax = Integer.MIN_VALUE;
		int colMax = Integer.MIN_VALUE;
		for(int i=0;i<m;i++) {
			rowMax = Math.max(rowMax, rowSum[i]);
		}
		for(int j =0;j<n;j++) {
			colMax = Math.max(colMax, colSum[j]);
			
		}
		System.out.print(rowMax+colMax);
		
	}

}
