using System;

namespace AssignmentThree
{
    class Tester
    {
        static void Main()
        {
            /*
             * declaring v1 of type Vehicle with its value of type Vehicle, 
             * and then calling the Display() method with the v1 reference
             */
            Vehicle v1 = new Vehicle();
            v1.Display();

            // declaring c1 of type Vehicle with its value of type Car
            Vehicle c1 = new Car();
            c1.Display();

            // declaring m1 of type Vehicle with its value of type Motorcycle
            Vehicle m1 = new Motorcycle();
            m1.Display();

            // calling the GoFaster() method with our different object references
            v1.GoFaster(40);
            c1.GoFaster(40);
            m1.GoFaster(40);

            /* 
             * declaring c2 of type Car with its value of type Car, 
             * then calling the GoFaster() method and passing in an int argument
             */
            Car c2 = new Car();
            c2.GoFaster(40);

            // declaring m2 of type Motorcycle with its value of type Motorcycle
            Motorcycle m2 = new Motorcycle();
            m2.GoFaster(40);

            Console.ReadKey();
        }
    }
}
