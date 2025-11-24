package com.concept;
public class pow {
	public static void main(String[] args) {
		System.out.println(myPow(2.0000, 10));
	}
	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1.0;
		}
		if (n < 0) {
			double t = powi(x, -1 * n);
			return 1 / t;
		}
		return powi(x, n);
	}
	public static double powi(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n % 2 == 0) {
			double p = powi(x, n / 2);
			return p * p;
		} else {
			double l = powi(x, n / 2);
			return x * l * l;
		}
	}
}
