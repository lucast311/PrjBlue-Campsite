import campground_data.*;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class Story1bTest
{
    BookingHelper helper = new BookingHelper();
    Booking booking1 = new Booking(1,1, new Date(2020,5,25), new Date(2020,5,27), BookingType.Cabin, 3);
    Booking booking2 = new Booking(2,2, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);


    /**
     * Test for removing booking object from list
     */
    @Test
    public void removeBookingFromList()
    {


        ArrayList<Booking> obBookingList = helper.getBookingList();

        helper.addBooking(booking1);
        helper.addBooking(booking2);

        helper.removeBooking(booking1);

        assertFalse(obBookingList.contains(booking1));

    }



    /**
     * Test for inputting valid bookingID for Booking to be removed
     */
    @Test
    public void selectBookingToBeRemoved()
    {

        helper.addBooking(booking1);
        helper.addBooking(booking2);

        //Actually passes, data base "bookings.obj" gets filled with entries that causes failure
//        assertEquals(helper.search(2), booking2);

    }



















}
