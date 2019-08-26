
namespace AssignmentFour
{
    // an interface that extends the IPhone interface
    interface IMobilePhone : IPhone
    {
        // three methods to be implemented by any class that inherits this interface
        void SendSMS();
        void BrowseWeb();
        void CheckEmail();
    }
}
