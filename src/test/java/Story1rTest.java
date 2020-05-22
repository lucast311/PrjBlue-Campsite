import campground_data.*;

import org.junit.*;


import java.util.*;

import static org.junit.Assert.*;



public class Story1rTest extends BookingHelper
{
    private Booking booking;
    private Booking booking2;
    BookingHelper bookingHelper;
    ArrayList<Booking> bookingList;

    @Before
    public void setUpBookingList()
    {
        booking = new Booking();
        booking2 = new Booking(1, 1, new Date(2020, 5, 20), new Date(2020, 6, 5), BookingType.Site, 2);
        bookingHelper= new BookingHelper();
        bookingList=bookingHelper.getBookingList();
        bookingHelper.addBooking(booking);
        bookingHelper.addBooking(booking2);
    }



    @Test
    public void testChangeBookingDate()
    {
        String bookingIDNotFound = "The booking you want to change is not in the system. Please enter a valid booking ID.";
        String startDateInvalidMessage = "Please enter a  start date that is not earlier than the previous start date.";
        String startDateInvalidMessage2 = "Please enter a start date that is not after the end date.";
        String endDateInvalidMessage = "Please enter an end date not earlier than the start date.";
        String bookingChanged = "The booking dates have been changed.";

        assertFalse(bookingIDNotFound, changeBookingDate(10, new Date(2020,5, 22), new Date(2020,6,4)));
        assertFalse(startDateInvalidMessage, changeBookingDate(2, new Date(2020,4, 20), new Date(2020,6,5)));
        assertFalse(startDateInvalidMessage2,changeBookingDate(2, new Date(2020,6, 20), new Date(2020,6,5)));
        assertFalse(endDateInvalidMessage,changeBookingDate(2, new Date(2020,5, 22), new Date(2020,5,19)));
        assertTrue(bookingChanged,changeBookingDate(2, new Date(2020,5, 24), new Date(2020,6,4)));
    }
}


