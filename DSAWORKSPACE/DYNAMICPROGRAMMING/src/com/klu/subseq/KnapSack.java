package com.klu.subseq;

import java.util.Arrays;

public class KnapSack {
	public static void main(String[] args) {
		int n = 3;
		int[] wt = { 3, 2, 5 };
		int[] val = { 30, 50, 60 };
		int Wt = 6;
		System.out.println("RECRUSION");
		System.out.println(knapsack(2, Wt, wt, val));
		int[][] dp = new int[n][Wt + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println("Memoization");
		System.out.println(knapsack(n - 1, Wt, wt, val, dp));
		int[][] tdp = new int[n][Wt+1];
		for (int[] row : tdp) {
			Arrays.fill(row, 0);
		}
		System.out.println("Tabulation");
		System.out.println(knapsackTabulation(3, 6, wt, val, tdp));

	}

	// recursion
	public static int knapsack(int index, int Wt, int w[], int val[]) {
		if (index == 0) {
			if (Wt >= w[0])
				return val[0];
			else
				return 0;
		}
		int not_pick = 0 + knapsack(index - 1, Wt, w, val);
		int pick = Integer.MIN_VALUE;
		if (Wt >= w[index])
			pick = val[index] + knapsack(index - 1, Wt - w[index], w, val);
		return Math.max(not_pick, pick);
	}

	/*
	 * Time complexity : O(2^n) Space Complexity: O(n) - recursion stack space
	 */
	// memoiation
	public static int knapsack(int index, int Wt, int w[], int val[], int[][] dp) {
		if (index == 0) {
			if (Wt >= w[0])
				return val[0];
			return 0;
		}
		if (dp[index][Wt] != -1)
			return dp[index][Wt];
		int not_pick = 0 + knapsack(index - 1, Wt, w, val, dp);
		int pick = Integer.MIN_VALUE;
		if (Wt >= w[index])
			pick = val[index] + knapsack(index - 1, Wt - w[index], w, val, dp);
		return dp[index][Wt] = Math.max(not_pick, pick);
	}

	/*
	 * Time complexity : O(n*w) Space Complexity: O(n*w)+O(n) - recursion stack
	 * space
	 */
	// Tabulation
	public static int knapsackTabulation(int n, int Wt, int w[], int val[], int[][] dp) {

		for (int i = w[0]; i <=Wt; i++) {
			dp[0][i] = val[0];
		}
		for (int ind = 1; ind < n; ind++) {
			for (int wj = 0; wj <= Wt; wj++) {
				int not_pick = 0 + dp[ind - 1][wj];
				int pick = Integer.MIN_VALUE;
				if (wj >= w[ind])
					pick = val[ind] +  dp[ind - 1][wj-w[ind]];
				dp[ind][wj] =Math.max(not_pick, pick);
			}
		}
		return dp[n-1][Wt];
	}
}
