package campground_ui;

import campground_data.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

/***
 * example of console user interface
 */
public class MainConsole {

    private static GuestHelper guestHelper = new GuestHelper();
    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {

//        Guest newGuest = new Guest();
//        newGuest.setFirstName("Richie");
//        newGuest.setLastName("Saysana");
//        newGuest.setCreditCardNum("1234567891234567");
//        newGuest.setPhoneNumber("3063701769");
//        newGuest.setEmail("saysanarichie@gmail.com");
//        newGuest.setPaymentMethod(PaymentType.Credit);
//        newGuest.setAddress(new Address(123, 123, "jksgdn", "asdg", "asgsgd", "sdgh", "A1A1A1"));
//
//        guestHelper.addGuest(newGuest);
//
//        boolean quit = false;
//        do{
//            System.out.print("Actions:[A]dd Guest, [S]earch Guest, [Q]uit: ");
//            switch (read.nextLine().toUpperCase()) {
//                case "S":
//                    searchGuest();
//                    break;
//                case "A":
//                    inputSomething();
//                    break;
//                default:
//                    quit = true;
//                    break;
//            }
//            System.out.println("");
//            System.out.println("");
//        } while (!quit);
//        System.out.println("Quit Application");
       }

    private static void searchGuest(){

//        if (guestHelper.getGuestAccounts().size() > 0)
//        {
//            String sPhone = "";
//            System.out.println("Please enter a guest's phone number to search.");
//            sPhone = read.nextLine();
//            Guest guest = guestHelper.searchGuest(sPhone);
//
//            if (guest == null) {
//                System.out.println("Guest not found.");
//            }
//            else {
//                System.out.printf("%s\n", guest.toString());
//                System.out.println("Actions: [C]hange info, Enter any other key to go back");
//                switch (read.nextLine().toUpperCase())
//                {
//                    case "C":
//                        guest.changeInfo();
//                }
//            }
//        }

/*

        if(campLedger.getGuests().values().size()>0) {
            int i = 0;
            System.out.println("Cars in system:");
            for (Guest guest : campLedger.getGuests().values()) {
                System.out.println(++i + ": " + guest);
            }
        }else{
            System.out.println("No Cars in system:");
        }

*/

    }
    private static void inputSomething()
    {
/*

        Guest guest = new Guest();
        System.out.print("Guest ID: ");
        guest.setGuestID(Integer.parseInt(read.nextLine()));
        System.out.print("First Name: ");
        guest.setFirstName(read.nextLine());

        //output errors if any otherwise output new car
        HashMap<String,String> errors =campLedger.validationHelper().getErrors(guest);
        if(errors.size()==0) {
            System.out.println("Success:");
            System.out.println("Added:" + campLedger.guestController().addGuest(guest));
        }else{
            for(String errMsg : errors.values()) {
                System.out.println("Errors:");
                System.out.println(errMsg);
            }
        }
*/


    }
}
