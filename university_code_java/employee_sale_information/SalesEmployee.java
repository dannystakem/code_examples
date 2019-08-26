
// importing a class from the java API
import java.text.DecimalFormat;

// Class is declared abstract so class methods will have to be implemented in concrete subclasses 
public abstract class SalesEmployee {

	// declaring variables of different types
	private String firstName;
	private String lastName;
	private static int bikeEmployeeNumber = 1000;
	private String ppsNumber;
	protected double sales;
	protected double commission;
	private int employeeNumber;

	DecimalFormat df = new DecimalFormat("0.00");

	/*
	 * creating a no arguments default constructor with a call to the Object
	 * superclass constructor
	 */
	public SalesEmployee() {
		super();
	}

	// creating a constructor that takes in three different string arguments
	public SalesEmployee(String firstName, String lastName, String ppsNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ppsNumber = ppsNumber;

		/*
		 * counting and incrementing the static variable and assigning it to
		 * employeeNumber so that every time an object is created in the
		 * constructor an ID value is assigned to that object
		 */
		bikeEmployeeNumber = bikeEmployeeNumber + 1;
		employeeNumber = bikeEmployeeNumber;
	}

	// creating accessor and mutator methods for all our different variables
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPpsNumber(String ppsNumber) {
		this.ppsNumber = ppsNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPpsNumber() {
		return ppsNumber;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	// overriding the to string method in the Object superclass
	@Override
	public String toString() {
		return "Employee first name: " + firstName + "\nEmployee last name: " + lastName + "\nEmployee PPS number: "
				+ ppsNumber + "\n";
	}

	/*
	 * creating an abstract method that will be implemented in our concrete
	 * subclasses
	 */
	public abstract void calculateCommission();

}
