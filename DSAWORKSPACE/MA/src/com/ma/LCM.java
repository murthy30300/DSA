package com.ma;

public class LCM {
	public static void main(String [] args) {
		long lcm = 1;
		int divisor =2;
		int[] arr =  {1,3,4,5,6};
		while(true) {
			int count=0;
			boolean divisible = false;
			for(int i =0;i<arr.length;i++) {
				if(arr[i]==0) {
					//return 0;
					System.out.println("0");
				}
				else if(arr[i]<0) {
					arr[i] +=1; 
				}
			}
		}
	}
}
