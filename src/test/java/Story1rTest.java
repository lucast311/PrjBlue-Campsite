import campground_data.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class Story1rTest
{
    BookingHelper helper=new BookingHelper();
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

//        booking2 = new Booking(1, 23, new Date(2020, 5, 20), new Date(2020, 6, 5), BookingType.Site, 2);
//        helper.addBooking(booking);
//        helper.addBooking(booking2);

    //}

    /**
     * A1.1
     * Validate booking id input
     */
    @Test
    public void testInvalidBookingID()
    {
        String bookingIDNotFound = "The booking you want to change is not in the system. Please enter a valid booking ID.";
        assertFalse(bookingIDNotFound, helper.changeBookingDate(3,  new Date(2020, 5, 20), new Date(2020, 6, 4)));
    }

    /**
     *
     * B1.1 & B1.2 Validate start date input
     */
    @Test
    public  void testInvalidStartDate()
    {
        String startDateInvalidMessage = "Please enter a  start date that is not earlier than the previous start date.";
        assertFalse(startDateInvalidMessage, helper.changeBookingDate(2, new Date(2020,4, 20), new Date(2020,6,5)));


        String startDateInvalidMessage2 = "Please enter a start date that is not after the end date.";
        assertFalse(startDateInvalidMessage2,helper.changeBookingDate(2, new Date(2020,6, 20), new Date(2020,6,5)));
    }

    /**
     * C1.1 Validate end date input
     */
    @Test
    public void testInvalidEndDate()
    {
        String endDateInvalidMessage = "Please enter an end date not earlier than the start date.";
        assertFalse(endDateInvalidMessage,helper.changeBookingDate(2, new Date(2020,5, 22), new Date(2020,5,19)));
    }

    /**
     * D1.1 Validate date change
     */
    @Test
    public void testDateChange()
    {
        String bookingChanged = "The booking dates have been changed.";
        assertTrue(bookingChanged,helper.changeBookingDate(4, new Date(2020,5, 24), new Date(2020,6,4)));
    }
}
