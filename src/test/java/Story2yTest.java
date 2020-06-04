

import campground_data.*;
import campground_ui.GuestManagerWindow;
import campground_ui.MainWindow;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;



public class Story2yTest
{
    private GuestHelper guestHelper = new GuestHelper();
    private ArrayList<Guest> guests = guestHelper.getGuestAccounts();
    private Address dummyAddress = new Address(123, 100, "Macca Street", "Saskatoon", "SK", "Canada", "S7N2W6");
    private Guest dummyGuest = new Guest("Julius", "Tuba", "julius3591@saskpolytech.ca", "3061234567", PaymentType.Credit, "123412341234",dummyAddress);

    @Before
    public void setUpGuest()
    {
        guestHelper.addGuest(dummyGuest);
        guests = guestHelper.getGuestAccounts();
    }


    /**
     *A1.1
     */
    @Test
    public void testViewGuests()
    {
        ArrayList<Guest> allGuests = guests;
        for(Guest obGuest: allGuests)
        {
            if(obGuest == null)
            {
                continue;
            }
            System.out.println(obGuest.toString());
        }
        assertEquals(allGuests,guestHelper.getGuestAccounts());

    }


    /**
     * A1.2 and A1.3 Testing the search function
     */
    @Test
    public void testSearchGuest()
    {
        assertEquals(dummyGuest, guestHelper.searchGuest("3061234567"));
        assertEquals(null, guestHelper.searchGuest("3061234568"));
    }

}
