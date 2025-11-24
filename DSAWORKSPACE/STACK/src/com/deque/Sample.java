package com.deque;
import java.util.*;
public class Sample {
	public static void main(String[] args) {
		Deque<String> dq = new ArrayDeque<>();
		dq.addFirst("first");
		dq.addLast("second");
		System.out.println(dq);
		System.out.println(dq.peek());
		System.out.println(dq.pollLast());
		System.out.println(dq);
	}

}
