package com.concept;

public class countGoodNums {
	public static void main(String[] args) {
		System.out.println(countGoodNumbers(22));
	}
	    public static int countGoodNumbers(long n) {
	        int mod = 1000000007;
	        long odd = n/2;
	        long even = n/2 + n%2;
	        return (int)((counter(5,even)*counter(4,odd))%mod);
	    }
	    public static long counter(long x,long y){
	        int mod = 1000000007;
	        if(y == 0){
	            return 1;
	        }
	        long ans = counter(x,y/2);
	        ans = ans*ans;
	        ans%=mod;
	        if(y%2==1){
	            ans = ans*x;
	        }
	        ans%=mod;
	        return ans;
	    }
	
}
