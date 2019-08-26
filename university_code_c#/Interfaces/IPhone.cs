
namespace AssignmentFour
{
    interface IPhone
    {
        /*
         * getter and setter properties to be implemeted by any class that inherits 
         * the interface, the two methods also need to be implemented
         */
        int prefix { get; set; }
        int phoneNumber { get; set; }

        void MakeCall();
        void HoldCall();

    }
}
