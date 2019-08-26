using System;

namespace AssignmentThree
{
    class Vehicle
    {
        // declaring and initializing three int variables with protected access modifiers 
        protected int doors = 4;
        protected int seats = 4;
        protected int wheels = 4;

        // the Display() method unique to the Vehicle class which uses the virtual keyword
        public virtual void Display()
        {
            Console.WriteLine("=======================================");
            Console.WriteLine("Vehicle Attributes");
            Console.WriteLine("---------------------------------------\n");
            Console.WriteLine("Vehicle doors: " + doors);
            Console.WriteLine("Vehicle seats: " + seats);
            Console.WriteLine("Vehicle wheels: " + wheels);
            Console.WriteLine("\n---------------------------------------\n");
        }

        // the GoFaster() method unique to the Vehicle class which also implements the virtual keyword
        public virtual void GoFaster(int go)
        {
            go += go;
            Console.WriteLine("You are going {0}mph in your vehicle. Nice driving.\n", go);
        }
    }
}
