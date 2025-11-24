package com.klu.tod;

import java.util.*;

public class NinjaTraining {
	public static void main(String[] args) {
		int[][] points = { { 10, 40, 70 }, 
						   { 20, 50, 80 }, 
				           { 30, 60, 90 } 
						 };

		int n = points.length; // Get the number of days
		System.out.println(ninjaTraining(n, points));
	}
	static int ninjaTraining(int n, int points[][]) {
		int[][] dp = new int[n][4];
		for(int[] row:dp) {
			Arrays.fill(row, -1);
		}
		return fun(n-1,3,dp,points);
	}
	static int fun(int day, int last, int dp[][], int points[][]) {
		if(dp[day][last]!=-1) return dp[day][last];
		if(day ==0 ) {
			int maxi=0;
			for(int i=0;i<=2;i++) {
				if(i!=last) {
					maxi = Math.max(maxi, points[0][i]);
				}
			}
			return dp[day][last] = maxi;
		}
		int maxi = 0;
		for(int i=0;i<=2;i++) {
			if(i!=last) {
				int activity = points[day][i] + fun(day-1,i,dp,points);
				maxi = Math.max(maxi, activity);
			}
		}
		return dp[day][last]= maxi;
		
	}

}
