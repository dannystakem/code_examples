using System;

namespace AssignmentFive
{
    // base class that implements the Icomparable interface to sort Vehicle objects
    public class Vehicle : IComparable<Vehicle>
    {
        // declaring a private integer variable
        private int price;

        // default base constructor 
        public Vehicle() {}

        // implementing a Property to provide access to the private variable
        public int VehiclePrice
        {
            get { return price; }
            set { price = value; }
        }

        /*
         * implementing the CompareTo() method of the Icomparable interface which will compare two
         * vehicle objects using the Property for the price comparison, and the GetType() method 
         * to compare the type of the objects. If it is shown that the two objects have the same 
         * price after the first comparison, then the GetType() method is used to sort the objects
         */
        public int CompareTo(Vehicle other)
        {
            if (this.VehiclePrice == other.VehiclePrice)
            {
                return this.GetType().Name.CompareTo(other.GetType().Name);
            }
            return other.VehiclePrice.CompareTo(this.VehiclePrice);
        }

        /*
         * using the ToString() method that uses the GetType() method and the getter Property to 
         * print a string to the console
         */
        public override string ToString()
        {
            return this.GetType().Name + ": " + this.VehiclePrice.ToString() + " euros";
        }
    }
}

