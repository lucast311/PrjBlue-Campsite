package campground_data;



import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class BookingHelper {

    private ArrayList<Booking> bookings;
    private static boolean found = false;

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

    public Booking changeBookingDate(int bookingID, Date startDate, Date endDate)
    {
        return null;
    }

    public ArrayList<Booking> getBookingList()
    {
        return this.bookings;
    }

    public ArrayList<Booking> getBookingList(int year)
    {
       return null;
    }

    public Booking search(String guestID)
    {

        return null;
    }



}
