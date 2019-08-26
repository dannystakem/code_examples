using System;

namespace AssignmentFour
{
    class Tester
    {
        static void Main()
        {
            // creating a MyHomePhone object with a MyHomePhone reference
            MyHomePhone phoneOne = new MyHomePhone();

            // using the setters to give the variables from the MyHomePhone class a value
            phoneOne.prefix = 191;
            phoneOne.phoneNumber = 123456;

            // printing a string while accessing the variables from the MyHomePhone class through the getters
            Console.WriteLine("Your home phone number is ({0}) {1}.\n", phoneOne.prefix, phoneOne.phoneNumber);

            // calling the MyHomePhone methods with the phoneOne reference
            phoneOne.MakeCall();
            phoneOne.HoldCall();

            Console.WriteLine("\n=========================================================\n");

            // creating a MySamsungGalaxy object with a MySamsungGalaxy reference
            MySamsungGalaxy galaxyOne = new MySamsungGalaxy();

            // using the setters to give the variables from the MySamsungGalaxy class a value
            galaxyOne.prefix = 919;
            galaxyOne.phoneNumber = 987654;

            // printing a string while accessing the variables from the MySamsungGalaxy class through the getters
            Console.WriteLine("Your Samsung Galaxy phone number is ({0}) {1}.\n", galaxyOne.prefix, galaxyOne.phoneNumber);

            // calling the MyHomePhone methods with the galaxyOne reference
            galaxyOne.MakeCall();
            galaxyOne.HoldCall();

            // calling the MySamsungGalaxy methods with the galaxyOne reference
            galaxyOne.SendSMS();
            galaxyOne.BrowseWeb();
            galaxyOne.CheckEmail();

            Console.ReadKey();
        }
    }
}
