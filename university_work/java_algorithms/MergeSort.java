package question1;

import java.util.Scanner;

class MergeSort {

	/*
	 * creating the MergeSortRecursive method that has an array, as well as two
	 * integer values as parameters
	 */
	static public void MergeSortRecursive(int[] numbers, int left, int right) {

		int mid;

		if (right > left) { // this is the base case

			// finding the middle of the array
			mid = (right + left) / 2;

			// method calling itself recursively to divide up the left array
			MergeSortRecursive(numbers, left, mid);

			// method calling itself recursively to divide up the right array
			MergeSortRecursive(numbers, (mid + 1), right);

			// calling the DoMerge method
			DoMerge(numbers, left, (mid + 1), right);
		}
	}

	/*
	 * creating the DoMerge method which takes in an array and four integers, 
	 * and merges the arrays back together to create one sorted array
	 */
	static public void DoMerge(int[] numbers, int left, int mid, int right) {

		// creating a temporary array thats the same length as the numbers array
		int[] tempArray = new int[numbers.length];
		int i, leftEnd, numElements, tempPosition;

		/*
		 * setting leftEnd to the last index of the left array, tempPosition to
		 * the first index of the left array and putting the length of the array
		 * into the numElements variable
		 */
		leftEnd = (mid - 1);
		tempPosition = left;
		numElements = (right - left + 1);

		/*
		 * incrementing different indices of the arrays if they satisfy the
		 * different tests of the while loops, in the second and third while
		 * loops the temp array is populated and sorted.  The arrays are 
		 * also incremented after each iteration
		 */
		while ((left <= leftEnd) && (mid <= right)) {

			if (numbers[left] <= numbers[mid])
				tempArray[tempPosition++] = numbers[left++];
			else
				tempArray[tempPosition++] = numbers[mid++];
		}

		while (left <= leftEnd)
			tempArray[tempPosition++] = numbers[left++];

		while (mid <= right)
			tempArray[tempPosition++] = numbers[mid++];

		// transfer the values in tempArray to the numbers array
		for (i = 0; i < numElements; i++) {
			numbers[right] = tempArray[right];
			right--;
		}
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int size = 8;
		int[] array = new int[size];

		System.out.println("Please enter eight random numbers for Merge Sort recursive algorithm:\n");

		// letting the user choose 8 values to populate the array with
		for (int i = 0; i < size; i++) {
			System.out.print("Number " + (i + 1) + ": ");
			int choice = input.nextInt();
			array[i] = choice;
		}

		System.out.print("\nArray before Merge Sort: ");

		// printing the unsorted array to the console
		for (int a : array) {
			System.out.print(a + " ");
		}

		System.out.print("\n\nArray after Merge Sort: ");

		// calling the MergeSortRecursive method
		MergeSortRecursive(array, 0, size - 1);

		// printing the sorted array to the console
		for (int i = 0; i < size; i++)
			System.out.print(array[i] + " ");

		input.close();
	}
}