package question3;

import java.util.Scanner;

public class EuclidAlgorithm {

	// recursive implementation of Euclid's algorithm
	public static int gcd(int a, int b) {
		
		if (b == 0) // this is the base case
			return a;
		else
			return gcd(b, a % b); // method calls itself
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		// let the user pick their own values to be tested on the console
		System.out.println("Demonstrating a recursive implementation of Euclid's algorithm.\n");
		System.out.print("Please enter number 1: ");
		int a = input.nextInt();
		System.out.print("\nPlease enter number 2: ");
		int b = input.nextInt();
		
		int c = gcd(a, b); // call Euclid's algorithm method 
		
		System.out.println("\nThe greatest common divisor of " + a + " and " + b + " is " + c);

		input.close();
	}
}
