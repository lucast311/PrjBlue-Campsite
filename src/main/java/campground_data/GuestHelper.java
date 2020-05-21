package campground_data;

import javax.validation.Valid;
import java.util.ArrayList;

public class GuestHelper {

    private ArrayList<Guest> guestAccounts = new ArrayList<>();

    public GuestHelper() {

    }

    public void addGuest(Guest newGuest) {
        guestAccounts.add(newGuest);
    }


    public void removeGuest(Guest guestToRemove) {

        guestAccounts.remove(guestToRemove);

    }

    public ArrayList<Guest> getGuestAccounts() {
        return this.guestAccounts;
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
