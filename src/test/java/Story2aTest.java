import campground_data.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Story2aTest {

    static Booking booking1;

    @Test
    public void createNewBooking()
    {
        booking1 = new Booking();

//        AccommodationHelper accommodationHelper = new AccommodationHelper();
//        accommodationHelper.addAccommodation(new Site(100, 4, 20, Site.SiteType.Individual, false, false));
//        accommodationHelper.addAccommodation(new Site(101, 4, 20, Site.SiteType.Individual, false, false));
//        accommodationHelper.addAccommodation(new Site(102, 4, 20, Site.SiteType.Individual, false, false));
//        accommodationHelper.addAccommodation(new Site(103, 4, 20, Site.SiteType.Individual, false, false));
//        accommodationHelper.addAccommodation(new Site(104, 4, 20, Site.SiteType.Individual, false, false));
//
//        accommodationHelper.addAccommodation(new Site(105, 8, 20, Site.SiteType.Group, false, false));
//        accommodationHelper.addAccommodation(new Site(106, 8, 20, Site.SiteType.Group, false, false));
//        accommodationHelper.addAccommodation(new Site(107, 8, 20, Site.SiteType.Group, false, false));
//        accommodationHelper.addAccommodation(new Site(108, 8, 20, Site.SiteType.Group, false, false));
//        accommodationHelper.addAccommodation(new Site(109, 8, 20, Site.SiteType.Group, false, false));
//
//        accommodationHelper.addAccommodation(new Site(200, 8, 32, Site.SiteType.Group, false, true));
//        accommodationHelper.addAccommodation(new Site(201, 8, 32, Site.SiteType.Group, false, true));
//        accommodationHelper.addAccommodation(new Site(202, 8, 32, Site.SiteType.Group, false, true));
//        accommodationHelper.addAccommodation(new Site(203, 8, 32, Site.SiteType.Group, false, true));
//        accommodationHelper.addAccommodation(new Site(204, 8, 32, Site.SiteType.Group, false, true));
//
//        accommodationHelper.addAccommodation(new Cabin(300, 4, Cabin.CabinType.Basic, 50, false));
//        accommodationHelper.addAccommodation(new Cabin(301, 4, Cabin.CabinType.Basic, 50, false));
//        accommodationHelper.addAccommodation(new Cabin(302, 4, Cabin.CabinType.Deluxe, 50, false));
//        accommodationHelper.addAccommodation(new Cabin(303, 4, Cabin.CabinType.Deluxe, 50, false));


        assertTrue(booking1.setGuestID(1));
        assertTrue(booking1.setGuestID(001));

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

        assertTrue(booking1.setAccommodationID(100));
        assertFalse(booking1.setAccommodationID(0));
    }
}
