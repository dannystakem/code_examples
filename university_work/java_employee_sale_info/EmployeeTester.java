
// importing different classes from the java API
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeTester {

	public static void main(String[] args) {

		// creating a scanner object
		Scanner input = new Scanner(System.in);

		// creating a decimal format object
		DecimalFormat df = new DecimalFormat("0.00");

		// creating an array list of type SalesEmployee using the 'new' keyword
		List<SalesEmployee> staff = new ArrayList<SalesEmployee>();

		/*
		 * creating salesAgent and salesPerson objects and adding them to our
		 * staff array list
		 */
		SalesEmployee a = new SalesAgent("John", "Smith", "1234567");
		SalesEmployee b = new SalesPerson("Kerry", "Joyce", "9876543");
		SalesEmployee c = new SalesAgent("Mick", "Nally", "54658752");
		staff.add(a);
		staff.add(b);
		staff.add(c);

		// using a for loop to traverse the staff array list
		for (int i = 0; i < staff.size(); i++) {

			// letting the user choose their own name when completing a sale
			System.out.println("Which sales employee are you?");
			System.out.print("Please enter 1 for J. Smith, 2 for K. Joyce, or 3 for M. Nally: ");
			int choice = input.nextInt();
			System.out.print("Please enter the total value of the sale: ");

			// depending on the user choice, a total sale value is input using a scanner
			if (choice == 1) {

				a.sales = input.nextDouble();
				System.out.print("\nThe total value of the sale is: " + df.format(a.sales) + "\n");
			}

			else if (choice == 2) {

				b.sales = input.nextDouble();
				System.out.print("\nThe total value of the sale is: " + df.format(b.sales) + "\n");
			}

			else if (choice == 3) {

				c.sales = input.nextDouble();
				System.out.print("\nThe total value of the sale is: " + df.format(c.sales) + "\n");
			}

			// to make sure a valid choice is entered
			else {
				System.out.println("Please enter a valid number!\n");
			}

			/*
			 * the employee's details are printed to the console using the to
			 * string method, then commission is charged using the calculate
			 * commission method. This value is rounded off using the decimal
			 * format method
			 */
			staff.get(i).calculateCommission();
			System.out.print(staff.get(i).toString());
			System.out.print("Employee ID No: " + staff.get(i).getEmployeeNumber() + "\n");
			System.out.println();

		}

		// closing the scanner
		input.close();
	}
}