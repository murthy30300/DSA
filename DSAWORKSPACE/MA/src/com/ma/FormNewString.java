package com.ma;
import java.util.*;
public class FormNewString {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		String res = "";
		for(int i=0;i<a.length();i++) {
			int index = b.indexOf(a.charAt(i));
			//System.out.println(index);
			if(index==-1) {
				System.out.print(a.charAt(i));
			}		
		}
	}

}
