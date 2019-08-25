package question2;

import java.util.Scanner;

class QuickSort {

	/*
	 * creating the QuickSortRecursive method that has an array, as well as two
	 * integer values as parameters
	 */
	public static void QuickSortRecursive(int[] arrayOne, int left, int right) {

		// calling the partition method to choose the pivot and sort the array
		if (left < right) {
			int pivot = Partition(arrayOne, left, right);

			/*
			 * method calling itself recursively to sort the half of the array
			 * that's left of the pivot
			 */
			if (pivot > 1)
				QuickSortRecursive(arrayOne, left, pivot - 1);

			/*
			 * method calling itself recursively to sort the half of the array
			 * that's right of the pivot
			 */
			if (pivot + 1 < right)
				QuickSortRecursive(arrayOne, pivot + 1, right);
		}
	}

	public static int Partition(int[] arrayTwo, int left, int right) {

		// choosing the first index on the left hand side as the pivot
		int pivot = arrayTwo[left];

		while (true) {

			// incrementing the left hand index if it's less than the pivot
			while (arrayTwo[left] < pivot)
				left++;

			// decrementing the right hand index if it's greater than the pivot
			while (arrayTwo[right] > pivot)
				right--;

			/*
			 * if the left index is greater than the pivot and the right index
			 * is less than the pivot we switch the two, making use of the temp
			 * integer to temporarily hold the the value of the right variable
			 */
			if (left < right) {
				int temp = arrayTwo[right];
				arrayTwo[right] = arrayTwo[left];
				arrayTwo[left] = temp;
			} else {
				return right;
			}
		}
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int size = 8;
		int[] array = new int[size];

		System.out.println("Please enter eight random numbers for Quick Sort recursive algorithm:\n");

		// letting the user choose 8 values to populate the array with
		for (int i = 0; i < size; i++) {
			System.out.print("Number " + (i + 1) + ": ");
			int choice = input.nextInt();
			array[i] = choice;
		}

		System.out.print("\nArray before Quick Sort: ");

		// printing the unsorted array to the console
		for (int a : array) {
			System.out.print(a + " ");
		}

		System.out.print("\n\nArray after Quick Sort: ");

		// calling the QuickSortRecursive method
		QuickSortRecursive(array, 0, size - 1);

		// printing the sorted array to the console
		for (int i = 0; i < size; i++)
			System.out.print(array[i] + " ");

		input.close();
	}
}
