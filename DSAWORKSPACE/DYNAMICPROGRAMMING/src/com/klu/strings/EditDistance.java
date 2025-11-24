package com.klu.strings;

public class EditDistance {
	 public static int[][] computeMatrix(String str1, String str2) {
	        int n = str1.length();
	        int m = str2.length();

	        int[][] dp = new int[n + 1][m + 1];

	        // Initialize base cases
	        for (int i = 0; i <= n; i++) {
	            dp[i][0] = i;
	        }
	        for (int j = 0; j <= m; j++) {
	            dp[0][j] = j;
	        }

	        // Fill DP table
	        for (int i = 1; i <= n; i++) {
	            for (int j = 1; j <= m; j++) {
	                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
	                    dp[i][j] = dp[i - 1][j - 1];  // No operation needed
	                } else {
	                    dp[i][j] = 1 + Math.min(
	                        dp[i - 1][j - 1], // Replace
	                        Math.min(dp[i - 1][j],    // Delete
	                                 dp[i][j - 1])   // Insert
	                    );
	                }
	            }
	        }

	        return dp;
	    }
	 public static void printMatrix(String str1, String str2, int[][] dp) {
	        System.out.println("Edit Distance Matrix:");
	        System.out.print("    ");
	        for (int j = 0; j < str2.length(); j++) {
	            System.out.print("  " + str2.charAt(j));
	        }
	        System.out.println();

	        for (int i = 0; i <= str1.length(); i++) {
	            if (i == 0) {
	                System.out.print("  ");
	            } else {
	                System.out.print(str1.charAt(i - 1) + " ");
	            }

	            for (int j = 0; j <= str2.length(); j++) {
	                System.out.printf("%2d ", dp[i][j]);
	            }
	            System.out.println();
	        }
	        System.out.println("Levenshtein Distance = " + dp[str1.length()][str2.length()]);
	        System.out.println("--------------------------------------------------");
	    }
	public static void main(String[] args) {
        String[][] pairs = {
                {"Levenshtein", "Lavenstaein"},
                {"TryHackMe", "TriHackingMe"},
                {"Optimization", "Progressive"},
                {"This is easy", "This is easy"}
            };

            for (String[] pair : pairs) {
                System.out.println("Comparing: \"" + pair[0] + "\" vs \"" + pair[1] + "\"");
                int[][] matrix = computeMatrix(pair[0], pair[1]);
                printMatrix(pair[0], pair[1], matrix);
            }

	}
}
