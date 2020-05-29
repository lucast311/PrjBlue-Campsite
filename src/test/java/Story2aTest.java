import campground_data.Booking;
import campground_data.BookingHelper;
import campground_data.BookingType;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Story2aTest {

    static Booking booking1;
    static Booking booking2;

    @Test
    public void createNewBooking()
    {
        booking1 = new Booking();

        assertTrue(booking1.setGuestID(1));
        assertFalse(booking1.setGuestID(001));

        assertTrue(booking1.changeStart(new Date(2021,5,28)));
        assertFalse(booking1.changeStart(new Date(2019,5,28)));

        assertTrue(booking1.changeEnd(new Date(2021,5,30)));
        assertFalse(booking1.changeEnd(new Date(2019,5,30)));
        assertFalse(booking1.changeEnd(new Date(2021,5,28)));
        assertFalse(booking1.changeEnd(new Date(2021,5,27)));

        assertTrue(booking1.setType("Cabin"));
        assertTrue(booking1.setType("Site"));
        assertFalse(booking1.setType("1"));
        assertFalse(booking1.setType("a0"));

        assertTrue(booking1.setMemberCount(8));
        assertTrue(booking1.setMemberCount(1));
        assertFalse(booking1.setMemberCount(9));
        assertFalse(booking1.setMemberCount(0));

        assertTrue(booking1.setAccommodationID(101));
        assertFalse(booking1.setAccommodationID(2));
    }
}
