package com.concept;
import java.util.*;
/*
Given an integer, K. Generate all binary strings of size k without consecutive 1’s.
Examples: 
Input : K = 3  
Output : 000 , 001 , 010 , 100 , 101 
Input : K  = 4 
Output :0000 0001 0010 0100 0101 1000 1001 1010
*/
public class GenerateBinary 
{
	public static void main(String[] args) 
	{
		int n = 10;
		int[] a = new int[n];
		print(a,n);
		Stack<Integer> s= new Stack<>();
	}
	public static void print(int[] arr,int num) {
		arr[0] = 0;
		generateBinary(arr,num,1);
		arr[0] = 1;
		generateBinary(arr,num,1);
	}
	public static void generateBinary(int[] arr,int num,int r) {
		if(num == r) {
//			if(r%10 == 0) {
//				System.out.println();
//			}
			for(int i:arr) {
				System.out.print(arr[i]);
			}
			System.out.print(" ");
		}
		else if(arr[r-1] == 1) {
			arr[r] = 0;
			generateBinary(arr, num, r+1);
		}
		else {
			arr[r]=0;
			generateBinary(arr, num, r+1);
			arr[r]=1;
			generateBinary(arr, num, r+1);
		}
	}
}
