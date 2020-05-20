import campground_data.*;

import org.junit.*;


import java.util.*;

import static org.junit.Assert.*;



public class Story1rTest extends BookingHelper
{
    private Booking booking;
    private Booking booking2;

    @Before
    public void setUpBookingList()
    {
        booking = new Booking();
        booking2 = new Booking(1, "3060203923", new Date(2020, 5, 20), new Date(2020, 6, 5), bookingType.Site, 2);
        BookingHelper bookingList = new BookingHelper();
        bookingList.addBooking(booking);
        bookingList.addBooking(booking2);
    }



    @Test
    public void testChangeBookingDate()
    {
        String bookingIDNotFound = "The booking you want to change is not in the system. Please enter a valid booking ID.";
        String startDateInvalidMessage = "Please enter a date that is not earlier than the previous start date.";
        String startDateInvalidMessage2 = "Please enter a start date that is not after  the end date.";
        String endDateInvalidMessage = "Please enter a date not earlier than the start date.";
        Booking booking3 = new Booking(1, "3060203923", new Date(2020, 5, 22), new Date(2020, 6, 5), bookingType.Site, 2);
        booking3.setBookingID(2);

        assertEquals(bookingIDNotFound, changeBookingDate(10, new Date(2020,5, 22), new Date(2020,6,4)));
        assertEquals(startDateInvalidMessage, changeBookingDate(2, new Date(2020,4, 20), new Date(2020,6,5)));
        assertEquals(startDateInvalidMessage2,changeBookingDate(2, new Date(2020,6, 20), new Date(2020,6,5)));
        assertEquals(endDateInvalidMessage,changeBookingDate(2, new Date(2020,5, 22), new Date(2020,5,19)));
        assertEquals(booking3,changeBookingDate(2, new Date(2020,5, 24), new Date(2020,6,4)));
    }
}


