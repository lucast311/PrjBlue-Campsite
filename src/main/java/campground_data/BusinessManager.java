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

    public Booking cancelbooking(String phoneNum) {
        searchbooking = BookingHelper.search(phoneNum);
        //needs validation for error
        return BookingHelper.search(phoneNum);
    }

    public void cancelConfirm(String answer ) {
        if (answer.equals("y") || answer.equals("yes")) {
            //go to the next
            // if passed start date
            Date date1 = new Date();
            Date date2 = searchbooking.getStartDate();
            if (date2.getDate() - date1.getDate() > 0 ){
                if (date2.getMonth() - date1.getMonth() >= 0){
                    //move to refund confirm
                }else {
                    //cancel successful
                    bookingHelper.removeBooking(searchbooking);
                }

            }else{
                //cancel successful
                bookingHelper.removeBooking(searchbooking);
            }
        } else if (answer.equals("n") || answer.equals("no") || answer.equals("nope")) {
            //go back to current booking

        }
            //error message


    }

    public double refundconfirm(String answer) {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        Date date2 = searchbooking.getEndDate();
        int price;
        price = PlotHelper.searchPlot(searchbooking.getPlotID()).getPrice();

        int ratething = (int) (date1.getDate() - date2.getDate());
        ratething = ratething * price;

        if (answer.equals("y") || answer.equals("yes")) {
            return (searchbooking.getTotal() - ratething);
        } else if (answer.equals("n") || answer.equals("no") || answer.equals("nope")){
            return searchbooking.getTotal();
        }
            //error message
        return 0;
    }
}


