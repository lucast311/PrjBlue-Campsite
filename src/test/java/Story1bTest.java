import campground_data.Booking;
import campground_data.BookingHelper;
import campground_data.Cabin;
import org.junit.Test;

import java.bookingType;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class Story1bTest
{

    static Booking booking1 = new Booking(1,"0000001", new Date(2020,5,25), new Date(2020,5,27), bookingType.Cabin, 3);
    static Booking booking2 = new Booking(2,"0000002", new Date(2020,6,4), new Date(2020,6,7), bookingType.Cabin, 2);


    /**
     * Test for removing booking object from list
     */
    @Test
    public void removeBookingFromList()
    {

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);


        BookingHelper bookingHelper = new BookingHelper();
        bookingHelper.removeBooking(booking1);

        assertTrue(!bookings.contains(booking1));

    }

    /**
     * Test for inputting valid guest phone number for reservation search
     */
    @Test
    public void reservationSearch()
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        BookingHelper bookingHelper = new BookingHelper();
        //Will complete soon



    }

    /**
     * Test for inputting valid bookingID for Booking to be removed
     */
    @Test
    public void selectBookingToBeRemoved()
    {
        booking1.setBookingID(1);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);



        BookingHelper bookingHelper = new BookingHelper();
        bookingHelper.searchBookingId(1);

        assertTrue(bookingHelper.searchBookingId(1).equals(booking1));
        assertTrue(bookingHelper.searchBookingId(5).equals(null));
        assertTrue(bookingHelper.searchBookingId(-1).equals(null));


    }

    /**
     * Test for input confirmation during booking deletion prompt
     */
    @Test
    public void confirmationInputTest()
    {


    }



















}
