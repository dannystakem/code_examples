using System;

namespace AssignmentThree
{
    class Motorcycle : Vehicle
    {
        // constructor which sets new values for the protected variables in the Vehicle class
        public Motorcycle()
        {
            this.doors = 0;
            this.seats = 1;
            this.wheels = 2;
        }

        // the Display() method unique to the Motorcycle class which uses the override keyword
        public override void Display()
        {
            Console.WriteLine("=======================================");
            Console.WriteLine("Motorcycle Attributes");
            Console.WriteLine("---------------------------------------\n");
            Console.WriteLine("Motorcycle doors: " + doors);
            Console.WriteLine("Motorcycle seats: " + seats);
            Console.WriteLine("Motorcycle wheels: " + wheels);
            Console.WriteLine("\n---------------------------------------\n");
        }

        // the GoFaster() method unique to the Motorcycle class which also implements the new keyword
        public new void GoFaster(int go)
        {
            go += 70;
            Console.WriteLine("You are going {0}mph on your motorbike. Slow down you maniac.\n", go);
        }
    }
}
