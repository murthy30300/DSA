package com.klu.knapsack;

import java.util.Arrays;

public class AmmusementPark {

	public static void main(String[] args) {
		int[] cost = {10,20,30};
		int[] fun = {60,100,120};
		int M = 50;
		int[][] dp = new int[4][M+1];
		for(int[] al: dp) {
			Arrays.fill(al, -2);
		}
		int res = function(0,M,cost, fun,dp);
		int[] resTab = tabulation(M,cost,fun,dp);
		System.out.println(resTab[0]);
		System.out.println(resTab[1]);
		System.out.println(res);
	}
	public static int[] tabulation(int M, int[] cost, int[] fun, int[][] dp) {
		int n = cost.length;
		 for (int j = 0; j <= M; j++) dp[0][j] = 0;
		for(int index=1;index<=n;index++) {
			for(int totalCost = 0;totalCost<=M;totalCost++) {
				int not_pick = dp[index-1][M];
				int pick = 0;
				if(totalCost>=cost[index-1])
				pick = fun[index-1]+dp[index-1][totalCost-cost[index-1]];
				dp[index][totalCost] = Math.max(pick, not_pick);
			}
		}
		int maxFun = dp[n][M];
		int minCost =-1;
		for(int j = 0;j<=M;j++) {
			if(dp[n][j] == maxFun) {
				minCost = j;
				break;
			}
		}
		return new int[] {maxFun,minCost};
	}
	public static int  function(int index, int M, int[] cost, int[] fun,int[][] dp) {
		if(index>=cost.length) {
			return 0;
		}
		if(dp[index][M]!=-2) return dp[index][M];
		int pick = Integer.MIN_VALUE;
		if(M-cost[index]>=0)
		pick = fun[index]+function(index+1,M-cost[index],cost,fun,dp);
		int not_pick = function(index+1,M,cost,fun,dp);
		return dp[index][M] = Math.max(pick, not_pick);
	}

}
