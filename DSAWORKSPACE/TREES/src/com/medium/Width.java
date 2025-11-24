package com.medium;

import java.util.*;

public class Width {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
			left = null;
			right = null;
		}
	}

	public static class Pair {
		TreeNode root;
		int num;

		Pair(TreeNode root, int num) {
			this.root = root;
			this.num = num;
		}
	}

	public static int widthOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;
		int ans = 0;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(root, 0));
		while (!q.isEmpty()) {
			int size = q.size();
			int min = q.peek().num;
			int first = 0, last = 0;
			for (int i = 0; i < size; i++) {
				int cur_index = q.peek().num - min;
				TreeNode node = q.peek().root;
				q.poll();
				if (i == 0)
					first = cur_index;
				if (i == size - 1)
					last = cur_index;
				if (node.left != null)
					q.offer(new Pair(node.left, cur_index * 2 + 1));
				if (node.right != null)
					q.offer(new Pair(node.right, cur_index * 2 + 2));

			}
			ans = Math.max(ans, last - first + 1);
		}

		return ans;
	}

	public static boolean sumOfChildren(TreeNode root) {
		// code here
		if (root == null)
			return true;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = q.poll();
				int left = (node.left != null) ? node.left.val : 0;
				int right = (node.right != null) ? node.right.val : 0;
				if (node.left != null)
					q.offer(node.left);
				if (node.right != null)
					q.offer(node.right);
				if (node.left == null && node.right == null)
					continue;
				if (node.val == left + right)
					continue;
				else {
					return false;
				}

			}
		}
		return true;

	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);

		int maxWidth = widthOfBinaryTree(root);
		boolean childrenSum = sumOfChildren(root);
		if(childrenSum)System.out.println("Satisfied");
		else System.out.println("Not satisfied");
		System.out.println("Maximum width of the binary tree is: " + maxWidth);
	}

}
