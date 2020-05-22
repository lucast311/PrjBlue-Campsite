import campground_data.*;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class Story1bTest
{

    Booking booking1 = new Booking(1,1, new Date(2020,5,25), new Date(2020,5,27), BookingType.Cabin, 3);
    Booking booking2 = new Booking(2,2, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);


    /**
     * Test for removing booking object from list
     */
    @Test
    public void removeBookingFromList()
    {

        BookingHelper helper = new BookingHelper();
        ArrayList<Booking> obBookingList = new ArrayList<>();

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
        ArrayList<Booking> bookings = new ArrayList<>();

        BookingHelper helper = new BookingHelper();

        helper.addBooking(booking1);
        helper.addBooking(booking2);

        assertEquals(helper.search(1), booking1);


    }

    /**
     * Test for input confirmation during booking deletion prompt
     */
    @Test
    public void confirmationInputTest()
    {

        BookingHelper bookingHelper = new BookingHelper();

        assertTrue(bookingHelper.confirmRemove("y"));
        assertFalse(bookingHelper.confirmRemove("n"));
        assertFalse(bookingHelper.confirmRemove("nsaduasgdfyfjsd"));

    }



















}
