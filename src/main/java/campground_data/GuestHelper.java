package campground_data;

import java.util.ArrayList;

public class GuestHelper {

    private ArrayList<Guest> guestAccounts;

    public GuestHelper() {

    }

    public Guest addGuest(Guest newGuest) {

        guestAccounts.add(newGuest);
        return newGuest;

    }

    public void removeGuest(Guest guestToRemove) {

        guestAccounts.remove(guestToRemove);

    }

    public Guest searchGuest(String phoneNumber)
    {
        Guest guestToReturn = null;

        for (Guest guest : guestAccounts)
        {
            if (guest.getPhoneNumber().equals(phoneNumber))
            {
                guestToReturn = guest;
            }
        }
        
        return guestToReturn;
    }

}
