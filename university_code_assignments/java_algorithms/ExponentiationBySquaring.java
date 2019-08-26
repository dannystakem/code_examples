package question4;

import java.util.Scanner;

public class ExponentiationBySquaring {

	/*
	 * creating the expBySquaring method that takes in the user's two integers
	 * as parameters. The method has different base cases to stop the recursion
	 * if satisfied, and if n is equal to one or two. 
	 */
	public int expBySquaring(int x, int n) {
		if (n < 0)
			return expBySquaring(1 / x, -n);
		else if (n == 0)
			return 1;
		else if (n == 1)
			return x;
		else if (n % 2 == 0)
			return expBySquaring(x * x, n / 2); // method calls itself
		else
			return x * expBySquaring(x * x, (n - 1) / 2); // method calls itself
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		// Making an object of ExponentiationBySquaring class 
		ExponentiationBySquaring ebs = new ExponentiationBySquaring();

		System.out.println("Using a recursive implementation of the exponentiation by squaring algorithm.\n");

		// letting the user input their own numbers into the console
		System.out.print("Please enter the first number: ");
		int n = input.nextInt();

		System.out.print("\nPlease enter the second number: ");
		int k = input.nextInt();

		// calling the expBySquaring method and assigning the answer into a long
		long result = ebs.expBySquaring(n, k); 

		System.out.println("\nComputing the exponential result : " + result);
		
		input.close();
	}

}