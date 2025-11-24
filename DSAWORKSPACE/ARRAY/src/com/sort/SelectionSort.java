package com.sort;

public class SelectionSort {
	public static void main(String[] args) {
		int arr[] = { 9, 8, 7, 6, 5, 4, 2, 3, 1 };
		int n = 9;
		int[] res = selectionSort(arr, n);
		for (int i = 0; i < res.length; i++)
			System.out.println(res[i]);
	}

	public static int[] selectionSort(int[] arr, int n) {
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i, min = arr[i];
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
		return arr;
	}

}
