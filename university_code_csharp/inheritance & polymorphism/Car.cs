using System;

namespace AssignmentThree
{
    class Car : Vehicle
    {
        // declaring and initializing a boolean variable
        private bool automatic = true;

        /*
         * the Display() method unique to the Car class which uses the override keyword,
         * since the variables in the Vehicle class have the protected access modifier,
         * they can be accessed directly from the method
         */
        public override void Display()
        {
            Console.WriteLine("=======================================");
            Console.WriteLine("Car Attributes");
            Console.WriteLine("---------------------------------------\n");
            Console.WriteLine("Car doors: " + doors);
            Console.WriteLine("Car seats: " + seats);
            Console.WriteLine("Car wheels: " + wheels);
            Console.WriteLine("Is Automatic? " + automatic);
            Console.WriteLine("\n---------------------------------------\n");
        }

        // the GoFaster() method unique to the Car class which also implements the new keyword
        public new void GoFaster(int go)
        {
            go += 20;
            Console.WriteLine("You are driving {0}mph in your car. Please drive safely.\n", go);
        }
    }
}
