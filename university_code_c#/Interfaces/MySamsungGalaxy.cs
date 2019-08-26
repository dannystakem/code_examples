using System;

namespace AssignmentFour
{
    // MySamsungGalaxy class implements IMobilePhone interface
    class MySamsungGalaxy : IMobilePhone
    {
        // declaring two private variables that can be accessed through the Properties
        private int samsungPrefix;
        private int samsungNumber;

        public MySamsungGalaxy()
        {
        Console.WriteLine("Calling MySamsungGalaxy default Constructor.\n");
        }

        // implementing the properties from the IPhone interface
        public int prefix
        {
            get { return samsungPrefix; }
            set { samsungPrefix = value; }
        }

        public int phoneNumber
        {
            get { return samsungNumber; }
            set { samsungNumber = value; }
        }

        // implementing the methods from the IPhone interface
        public void MakeCall()
        {
            Console.WriteLine("You have made a call from your Samsung Galaxy phone!\n");
        }

        public void HoldCall()
        {
            Console.WriteLine("Your Samsung Galaxy phone call is on hold!\n");
        }

        // implementing the methods from the IMobilePhone interface
        public void SendSMS()
        {
            Console.WriteLine("You have sent an SMS!\n");
        }

        public void BrowseWeb()
        {
            Console.WriteLine("You are browsing the web!\n");
        }

        public void CheckEmail()
        {
            Console.WriteLine("You have checked your email!\n");
        }
    }
}
