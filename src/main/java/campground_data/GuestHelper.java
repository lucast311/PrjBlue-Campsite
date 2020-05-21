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


        if (phoneNumber.length() != 10)
        {
            System.out.println("Invalid phone number length. Please enter a 10 digit phone number");
            //insert routine to ask user again for phone number
        }
        else
        {
            for (Guest guest : guestAccounts)
            {
                if (guest.getPhoneNumber().equals(phoneNumber))
                {
                    guestToReturn = guest;
                }

            }
        }
        return guestToReturn;
    }

}
