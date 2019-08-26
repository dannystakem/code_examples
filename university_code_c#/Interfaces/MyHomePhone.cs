using System;

namespace AssignmentFour
{
    // MyHomePhone class implements IPhone interface
    class MyHomePhone : IPhone
    {
        // declaring two private variables that can be accessed through the Properties
        private int homePrefix;
        private int homeNumber;

        public MyHomePhone()
        {
        Console.WriteLine("Calling MyHomePhone default Constructor.\n");
        }

        // implementing the properties from the IPhone interface
        public int prefix
        {
            get { return homePrefix; }
            set { homePrefix = value; }
        }

        public int phoneNumber
        {
            get { return homeNumber; }
            set { homeNumber = value; }
        }

        // implementing the methods from the IPhone interface
        public void MakeCall()
        {
            Console.WriteLine("You have made a call from your home phone!\n");
        }

        public void HoldCall()
        {
            Console.WriteLine("Your home phone call is on hold!\n");
        }
    }
}
