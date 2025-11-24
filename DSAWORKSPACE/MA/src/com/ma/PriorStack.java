package com.ma;
import java.util.*;
public class PriorStack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Integer> al = new ArrayList<>();
		for(int i=0;i<n;i++) {
			al.add(sc.nextInt());
		}
		Stack<Integer> st = new Stack<>();
		st.push(al.get(0));
		int ans =1;
		for(int i=1;i<n;i++) {
			if(al.get(i)>st.peek()) {
				ans++;
				st.push(al.get(i));
			}
		}
		System.out.print(st.size());
	}

}
