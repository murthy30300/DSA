package com.concept;
import java.util.*;
public class sortStack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			Stack<Integer> s = new Stack<>();
			int n = sc.nextInt();
			while (n-- > 0)
				s.push(sc.nextInt());
			GfG g = new GfG();
			Stack<Integer> a = g.sort(s);
			while (!a.empty()) {
				System.out.print(a.peek() + " ");
				a.pop();
			}
			System.out.println();

			System.out.println("~");
		}
	}
}

class GfG {
	public Stack<Integer> sort(Stack<Integer> s) {
		sortings(s);
		return s;
	}

	public void sortings(Stack<Integer> s) {
		if (!s.isEmpty()) {
			int x = s.pop();
			sort(s);
			sortInserted(s, x);
		}

	}

	public void sortInserted(Stack<Integer> s, int x) {
		if (s.isEmpty() || s.peek() < x) {
			s.push(x);
			return;
		}
		int temp = s.pop();
		sortInserted(s, x);
		s.push(temp);
	}
}
