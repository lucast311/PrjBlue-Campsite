package campground_data;

import javax.validation.Valid;
import java.util.ArrayList;

public class GuestHelper {

    private ArrayList<Guest> guestAccounts = new ArrayList<>();
    private DatabaseFile DBFile;

    public GuestHelper() {
        DBFile=new DatabaseFile();
        this.guestAccounts = DBFile.readGuests();
    }

    public void addGuest(Guest newGuest) {
        guestAccounts.add(newGuest);
        DBFile.saveRecords(guestAccounts);
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

        //Why is there phone number validation in here
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

    public boolean checkGuestId(int guestID)
    {
        for(Guest guest : guestAccounts)
        {
            if(guest.getGuestID() == guestID)
            {
                return true;
            }
        }
        return false;
    }

}
