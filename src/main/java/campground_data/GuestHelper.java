package campground_data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GuestHelper {

    private ArrayList<Guest> guestAccounts = new ArrayList<>();
    private DatabaseFile DBFile;
    private ValidationHelper validationHelper = new ValidationHelper();

    public GuestHelper() {
        DBFile = new DatabaseFile();
        this.guestAccounts = DBFile.readGuests();
    }

    public void addGuest(Guest newGuest) {
        guestAccounts.add(newGuest);
        DBFile.saveRecords(guestAccounts);
    }


    public void removeGuest(Guest guestToRemove) {

        guestAccounts.remove(guestToRemove);

    }

    public void changeGuestInfo(Guest guest) {
        Scanner read = new Scanner(System.in);

        System.out.print("First Name: ");
        guest.setFirstName(read.nextLine());
        System.out.print("Last Name: ");
        guest.setLastName(read.nextLine());
        System.out.print("Email: ");
        guest.setEmail(read.nextLine());
        System.out.print("Phone Number: ");
        guest.setPhoneNumber(read.nextLine());
        System.out.print("Payment Method Select [C]redit, [D]ebit, C[A]sh: ");
        switch (read.nextLine().toUpperCase()) {
            case "C":
                guest.setPaymentMethod(PaymentType.Credit);
                break;
            case "D":
                guest.setPaymentMethod(PaymentType.Debit);
                break;
            case "A":
                guest.setPaymentMethod(PaymentType.Cash);
                break;
            default:
                System.out.print("You selected an invalid character, no changes made to payment method.\n");
                break;
        }
        System.out.print("Credit Card Number (If none enter all zeroes): ");
        guest.setCreditCardNum(read.nextLine());
        System.out.print("Street Number: ");
        guest.getAddress().setStreetNum(Integer.parseInt(read.nextLine()));
        System.out.print("Apt Number: ");
        guest.getAddress().setAptNum(Integer.parseInt(read.nextLine()));
        System.out.print("Street Name: ");
        guest.getAddress().setStreetName(read.nextLine());
        System.out.print("City/Town: ");
        guest.getAddress().setCity_Town(read.nextLine());
        System.out.print("Province: ");
        guest.getAddress().setProvince(read.nextLine());
        System.out.print("Country: ");
        guest.getAddress().setCountry(read.nextLine());
        System.out.print("Postal Code (A1A1A1): ");
        guest.getAddress().setPostalCode(read.nextLine());

        //output errors if any otherwise output new car
        HashMap<String, String> errors = validationHelper.getErrors(guest);
        if (errors.size() == 0) {
            System.out.println("Success:");
            System.out.println(guest.toString());
        } else {
            for (String errMsg : errors.values()) {
                System.out.println("Errors:");
                System.out.println(errMsg);
            }
        }
    }

    public ArrayList<Guest> getGuestAccounts() {
        return this.guestAccounts;
    }

    public Guest searchGuest(String phoneNumber) {
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
