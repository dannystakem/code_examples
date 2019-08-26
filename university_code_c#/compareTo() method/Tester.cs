// importing namespaces to make our code more efficient
using System;
using System.Collections.Generic;

namespace AssignmentFive
{
    class Tester
    {
        static void Main(string[] args)
        {     
            // creating an arraylist that holds Vehicle objects
            List<Vehicle> vehList = new List<Vehicle>();

            /*
             * using the Add() method to populate our arraylist, which uses the constructors of the 
             * derived classes to create new Car and Motorcycle objects. These objects are then given 
             * values through the use of the Property in the Vehicle base class
             */
            vehList.Add(new Motorcycle() { VehiclePrice = 1500 });
            vehList.Add(new Car() { VehiclePrice = 2500});
            vehList.Add(new Motorcycle() { VehiclePrice = 500 });
            vehList.Add(new Motorcycle() { VehiclePrice = 2500 });
            vehList.Add(new Car() { VehiclePrice = 25000 });
            vehList.Add(new Car() { VehiclePrice = 4500 });

            Console.WriteLine("\n===============================================================");
            Console.WriteLine("\nSample Input - an Unsorted Array\n");
            Console.WriteLine("===============================================================\n");

            // printing the unsorted arraylist using a foreach loop
            foreach (var element in vehList)
            {
                Console.WriteLine(element);
            }

            /* 
             * calling the Sort() method which will run the CompareTo() method of the Icomparable 
             * interface to sort the arraylist
             */
            vehList.Sort();

            Console.WriteLine("\n===============================================================");
            Console.WriteLine("\nSample Output - Sorted by Price (Descending) Type (Ascending)\n");
            Console.WriteLine("===============================================================\n");

            // printing the sorted arraylist using a foreach loop
            foreach (var element in vehList)
            {
                Console.WriteLine(element);
            }

            Console.ReadKey();
        }
    }
}   
 