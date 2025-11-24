package com.ma;
import java.util.*;
public class Wheel {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		int w = sc.nextInt();
		findWheel(v,w);
		sc.close();
	}
	static void findWheel(int v, int w) {
		int tw = (4*v-w)/2;
		int fw = v - tw;
		System.out.printf("TW = %d FW = %d",tw,fw);
	}

}
