package campground_data;


import java.text.SimpleDateFormat;
import java.util.Date;

public class BusinessManager {

    private Owner currUser;
    private BookingHelper bookingHelper;
    private PlotHelper plotHelper;
    private OwnerHelper ownerHelper;
    private GuestHelper guestHelper;

    private Booking searchbooking;


    public void LogIn() {
    }

    public boolean validateId(String userID) {

    }

    public boolean validatePassword(String password) {

    }

    public Object search(Object obVal) {

    }


    public void managebooking() {

    }

    public Booking cancellbooking(String phoneNum) {
        searchbooking = BookingHelper.search(phoneNum);
        return BookingHelper.search(phoneNum);
    }

    public void cancelConfirm(boolean answer) {
        if (answer == true) {
            //go to the next
        } else {

        }

    }

    public double refundconfirm(boolean answer) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        Date date2 = searchbooking.getEndDate();
        int price;
        price = PlotHelper.searchPlot(searchbooking.getPlotID()).getPrice();


        int ratething = (int) (date1.getDay() - date2.getDay());
        ratething = ratething * price;

        if (answer == true) {
            return (searchbooking.getTotal() - ratething);
        } else {
            return searchbooking.getTotal();
        }
    }
}


