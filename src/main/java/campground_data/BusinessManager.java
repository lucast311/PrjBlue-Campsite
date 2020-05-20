package campground_data;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.Date;

=======
>>>>>>> remotes/origin/master
public class BusinessManager {

    private Owner currUser;
    private BookingHelper bookingHelper;
    private PlotHelper plotHelper;
    private OwnerHelper ownerHelper;
    private GuestHelper guestHelper;
<<<<<<< HEAD
    private Booking searchbooking;
=======
>>>>>>> remotes/origin/master

    public void LogIn()
    {}

    public boolean validateId(String userID)
    {}

    public boolean validatePassword(String password)
    {}

    public Object search(Object obVal)
    {}

<<<<<<< HEAD
    public void managebooking()
    {

    }

    public Booking cancellbooking(String phoneNum)
    {
        searchbooking = BookingHelper.searchBookingID(phoneNum);
        return BookingHelper.searchBookingID(phoneNum);
    }

    public void cancelConfirm(boolean answer)
    {
        if (answer == true){
            //go to the next
        }else {

        }

    }

    public double refundconfirm(boolean answer)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        Date date2 = searchbooking.getEndDate();
        int  price;
        price = PlotHelper.searchPlot(searchbooking.getPlotID()).getPrice();



        int ratething = (int)(date1.getDay() - date2.getDay());
        ratething = ratething * price;

        if (answer == true){
            return (searchbooking.getTotal() - ratething);
        }else {
            return searchbooking.getTotal();
        }

=======
>>>>>>> remotes/origin/master
}
