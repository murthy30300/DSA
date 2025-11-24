package com.concept;
import java.util.*;
import java.io.*;
import java.lang.*;
public class reverseStack {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			String str = br.readLine();
			int n = Integer.parseInt(str);
			Stack<Integer> stack = new Stack<>();
			String str1 = br.readLine();
			String[] starr1 = str1.split(" ");
			for (int j = 0; j < n; j++) {
				stack.push(Integer.parseInt(starr1[j]));
			}
			Solution1.reverse(stack);
			for (int j : stack) {
				out.print(j + " ");
			}
			out.println();
			out.println("~");
		}
		out.close();
	}
}

class Solution1 {
	static void reverse(Stack<Integer> s) {
		if (s.size() > 0) {
			int x = s.peek();
			s.pop();
			reverse(s);
			insert(x, s);
		}
	}

	static void insert(int x, Stack<Integer> s) {
		if (s.isEmpty()) {
			s.push(x);
			return;
		} else {
			int t = s.peek();
			s.pop();
			insert(x, s);
			s.push(t);
		}

	}
}
