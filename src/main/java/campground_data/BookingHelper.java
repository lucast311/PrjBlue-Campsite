package campground_data;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BookingHelper extends Booking{

    private ArrayList<Booking> bookings;
    private Booking searchbooking;
=======
import java.util.ArrayList;
import java.util.Date;

public class BookingHelper {

    private ArrayList<Booking> bookings;
>>>>>>> remotes/origin/master

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

<<<<<<< HEAD
    public Booking searchBookingID(String guestID)
=======
    public Booking search(String guestID)
>>>>>>> remotes/origin/master
    {

    }




<<<<<<< HEAD
    }
=======
>>>>>>> remotes/origin/master

}
