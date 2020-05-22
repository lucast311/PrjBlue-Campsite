import campground_data.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class Story1aTest {

    static Booking booking1;
    static Booking booking2;

    @Test
    public void createBooking()
    {
        booking1 = new Booking();
        booking2 = new Booking(1,1,new Date(2020,5,20), new Date(2020,5,22), BookingType.Cabin, 2);

        BookingHelper bookingHelper = new BookingHelper();
        bookingHelper.addBooking(booking1);
        bookingHelper.addBooking(booking2);


        assertTrue(booking1 != null && booking1 instanceof Booking);
        assertTrue(booking2 != null && booking2 instanceof Booking);

        assertTrue(bookingHelper.getBookingList().contains(booking1));
        assertTrue(bookingHelper.getBookingList().contains(booking2));
    }
}
