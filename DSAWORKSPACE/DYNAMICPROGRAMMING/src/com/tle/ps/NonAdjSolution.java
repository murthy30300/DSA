package com.tle.ps;
import java.util.*;
public class NonAdjSolution {
	public static void main(String[] args) {
		int[] arr = {0,4,1,-10,10,2};
		int n = arr.length;
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		int ans = findMaxSum(n-1, arr, dp);
		System.out.println(ans);
	}
	public static int findMaxSum(int index, int arr[], int[] dp) {
		if(index == 0)return arr[0];
		if(dp[index]!= -1)return dp[index];
		int pick = arr[index];
		if(index>=2)
			pick+= findMaxSum(index-2,arr,dp);
		int nonPick = findMaxSum(index-1,arr,dp);
		return dp[index] = Math.max(pick, nonPick);
	}

}
