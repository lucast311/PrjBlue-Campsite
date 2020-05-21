package campground_data;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BookingHelper extends Booking{

    private ArrayList<Booking> bookings;
    private Booking searchbooking;


    public BookingHelper() {
        this.bookings = new ArrayList<Booking>();
    }

    public boolean addBooking(Booking booking) {
        return this.bookings.add(booking);
    }

    public boolean removeBooking(Booking booking)
    {
        return this.bookings.remove(booking);
    }

    public boolean changeBookingDate(int bookingID, Date startDate, Date endDate)
    {

        return false;
    }

    public ArrayList<Booking> getBookingList()
    {
        return this.bookings;
    }

    public ArrayList<Booking> getBookingList(int year)
    {

        return null;
    }



    public static Booking search(String guestID)
    {
        for(int i=0;i < bookings.size(); i++) {
            if (bookings.get(i).getGuestID().equals(guestID)) {
                return bookings.get(i);
            }

        }

        return null;
    }


    }



