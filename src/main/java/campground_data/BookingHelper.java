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

    }

    public ArrayList<Booking> getBookingList()
    {
        return this.bookings;
    }

    public ArrayList<Booking> getBookingList(int year)
    {

    }

    public Booking searchBookingID(String guestID)
    {

    }




    }

}
