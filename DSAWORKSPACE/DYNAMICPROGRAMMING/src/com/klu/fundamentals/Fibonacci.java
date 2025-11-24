package com.klu.fundamentals;

import java.util.Arrays;

public class Fibonacci {
	public static void main(String[] args) {
		int n = 6;
		int[] dp = new int[n+1];
		Arrays.fill(dp,-1);
		System.out.println(fibonacci(n,dp));
		
	}
	static int fibonacci(int n,int[] dp) {
		if(n<=1) {
			return n;
		}
		else {
			if(dp[n]!=-1) {
				return dp[n];
			}
			dp[n] = fibonacci(n-1,dp) + fibonacci(n-2, dp);
			System.out.printf("dp[%d] is %d\n",n,dp[n]);
		}
		return -3;
	}
}
