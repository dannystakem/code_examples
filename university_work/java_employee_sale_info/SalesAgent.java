/* 
 * using inheritance so that our subclass can access the variables 
 * and methods of the parent class by using the 'extends' keyword
 */
public class SalesAgent extends SalesEmployee {

	/*
	 * creating a no arguments default constructor with a call to the
	 * SalesEmployee parent class default constructor
	 */
	public SalesAgent() {
		super();
	}

	/*
	 * creating a constructor that takes in three string objects, that also
	 * calls the SalesEmployee parent class constructor
	 */
	public SalesAgent(String firstName, String lastName, String ppsNumber) {
		super(firstName, lastName, ppsNumber);
	}

	/*
	 * overriding the abstract method from our superclass and providing our own
	 * implementation for the method
	 */
	@Override
	public void calculateCommission() {

		/*
		 * calculating the commission on the total sale at 10% and rounding it
		 * off using the decimal format method
		 */
		commission = sales * 0.10;
		System.out.println("Commission for this sale is: " + df.format(commission));

	}
}
