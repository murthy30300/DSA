package com.gs;
import java.util.*;
public class InToRoman {
	public static void main(String[] args) {
		int n= 4400;
		StringBuilder sb = new StringBuilder();
		String s = Integer.toString(n);
		
		System.out.println(s);
		while(n>0) {
			if(n>=1000) {
				n=n%1000;
				sb.append("M");
			}
			else if(n>=900) {
				n = n%900;
				sb.append("CM");
			}
			else if(n>=500) {
				n = n%500;
				sb.append("D");
			}
			else if(n>=400) {
				n = n%400;
				sb.append("CD");
			}
			else if(n>=100) {
				n = n%100;
				sb.append("C");
			}
			else if(n>=90) {
				n= n%90;
				sb.append("XC");
			}
			else if(n>=50) {
				n=n%50;
				sb.append("L");
			}
			else if(n>=40) {
				n= n%40;
				sb.append("XL");
			}
			else if(n>=10) {
				n = n%10;
				sb.append("X");
				
			}
			else if(n>=5) {
				n = n%5;
				sb.append("V");
			}
			else if(n>=4) {
				sb.append("IV");
			}
			else {
				sb.append("I");
			}
		}
		System.out.println(sb.toString());
		
	}
}
