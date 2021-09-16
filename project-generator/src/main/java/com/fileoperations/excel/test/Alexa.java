package com.fileoperations.excel.test;

public class Alexa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getTwoMin(1);

	}

	private static void getTwoMin(int n) {
		int num1 = 536;
		int num2 = 0;
		for (int i = 536; i>= 0; i--) {
			for (int j = i - 1; j> 0; j--) {
				if ((i^j) == n 
//				&& (i < num1 || j < num2)
				&& ((i-j) < (num1 - num2))
				) {
					num1 = i;
					num2 = j;
				}
			}
		}
//		System.out.println("Num1: " + ());
		System.out.println("Num1: " + num1);
		System.out.println("Num2: " + num2);
	}

}
