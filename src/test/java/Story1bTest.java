import campground_data.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class Story1bTest
{

    Booking booking1 = new Booking(1,1, new Date(2020,5,25), new Date(2020,5,27), BookingType.Cabin, 3);
    Booking booking2 = new Booking(2,1, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);
    Guest guest1 = new Guest("John", "Doe", "johndoe@gmail.com", "3069999999", PaymentType.Credit, "4456777777777777", new Address());
    Guest guest2 = new Guest("Loki", "Odinson", "godofthunder@gmail.com", "3067777777", PaymentType.Cash, "4456555555555555", new Address());


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

        assertFalse(bookings.contains(booking1));

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

        assertEquals(bookingHelper.searchBookingId(1), booking1);
        assertNull(bookingHelper.searchBookingId(5));
        assertNull(bookingHelper.searchBookingId(-1));


    }

    /**
     * Test for input confirmation during booking deletion prompt
     */
    @Test
    public void confirmationInputTest()
    {

        BookingHelper bookingHelper = new BookingHelper();

        assertTrue(bookingHelper.confirmRemove("yes"));
        assertFalse(bookingHelper.confirmRemove("no"));
        assertFalse(bookingHelper.confirmRemove("nsaduasgdfyfjsd"));

    }



















}
