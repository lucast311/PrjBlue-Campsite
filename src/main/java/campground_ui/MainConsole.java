package campground_ui;

import campground_data.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

/***
 * example of console user interface
 */
public class MainConsole {

    //private static CampLedger campLedger = new CampLedger();
    private static Scanner read = new Scanner(System.in);
    //help how do i move businessmanager?????????
    private static BusinessManager businessManager = BusinessManager(); //wrong
    private static Booking searchbooking;

    public static void main(String[] args) {

        boolean quit = false;
        do {
            //manage booking option
            System.out.print("Actions:[M] Manage bookings [L]ist Something, [A]dd Something, [Q]uit: ");
            switch (read.nextLine().toUpperCase()) {
                case "M":
                    managebooking();
                    break;
                case "L":
                    listSomething();
                    break;
                case "A":
                    inputSomething();
                    break;
                default:
                    quit = true;
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!quit);
        System.out.println("Quit Application");
    }

    private static void listSomething() {
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

    private static void inputSomething() {
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

    public static void managebooking() {
        //boolean quit = false;
        System.out.print("Actions: [C] Cancel booking [N] go back");
        switch (read.nextLine().toUpperCase()) {
            case "C":
                cancelbooking();
                break;
            case "N":

                break;
            default:

                break;

        }
        System.out.println("");
        System.out.println("");

    }

    public static void cancelbooking() {
        System.out.print("Input bookingID");
        searchbooking = businessManager.cancelbooking(read.nextLine().toUpperCase());
        //alot should be a switch case instead of through businessmanager
        cancelConfirm();


    }

    public static void cancelConfirm() {
        System.out.print("Actions: is this the correct?");
        switch (read.nextLine().toUpperCase()) {
            case "Yes":
                Date date1 = new Date();
                Date date2 = searchbooking.getStartDate();
                BookingHelper bookingHelper = null; //wrong
                //Date does not work??????
                if (date2.getDate() - date1.getDate() > 0) {
                    if (date2.getMonth() - date1.getMonth() >= 0) {
                        //move to refund confirm
                        refundConfirm();

                    } else {
                        //cancel successful
                        System.out.println("Success:");
                        bookingHelper.removeBooking(searchbooking);
                        //move to main
                    }

                } else {
                    //cancel successful
                    System.out.println("Success:");
                    bookingHelper.removeBooking(searchbooking);
                    //move to main
                }
                break;
            case "Y":
                Date date3 = new Date();
                Date date4 = searchbooking.getStartDate();
                BookingHelper bookingHelper2 = null; //wrong
                //Date does not work??????
                if (date4.getDate() - date3.getDate() > 0) {
                    if (date4.getMonth() - date3.getMonth() >= 0) {
                        //move to refund confirm
                        refundConfirm();


                    } else {
                        //cancel successful
                        System.out.println("Success:");
                        bookingHelper2.removeBooking(searchbooking);
                        //move to main
                    }

                } else {
                    //cancel successful
                    System.out.println("Success:");
                    bookingHelper2.removeBooking(searchbooking);
                    //move to main
                }
                break;
            case "No":
                //move to main

                break;
            case "Nope":
                //move to main

                break;
            case "N":
                //move to main

                break;
            default:

                break;

        }
        System.out.println("");
        System.out.println("");
    }

    public static void refundConfirm() {
        System.out.print("Actions: Refund for remaining days?");
        double total = businessManager.refundconfirm(read.nextLine().toUpperCase());
        //alot should be a switch case instead of through businessmanager
        System.out.print("Total amount refunded: " + total);
        System.out.print("Actions: Done?");
        switch (read.nextLine().toUpperCase()) {
            case "Yes":
                //move to main

                break;
            case "Y":
                //move to main

                break;
            case "N":
                refundConfirm();
                break;
            case "No":
            refundConfirm();
                    break;
            case "Nope":
                refundConfirm();
                break;
        }


    }
}

