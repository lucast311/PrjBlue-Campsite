package campground_data;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class BusinessManager {

    private Owner currUser;
    private BookingHelper bookingHelper;
    private PlotHelper plotHelper;
    private OwnerHelper ownerHelper = new OwnerHelper();
    private GuestHelper guestHelper;
    private static ArrayList<Owner> ownerList = OwnerHelper.getOwnerList();

    private static Scanner obIn = new Scanner(System.in);

    public Booking searchbooking;

    public static void main(String[] args) {

        Owner harry = new Owner("harry", "louis", "Pa$$w0rd", "555-555-5555", "test@gmail.com", 3, true);
        ownerList.add(harry);
        LogIn();
        boolean quit = false;
        do {
            System.out.print("Actions:[L]ist Something, [A]dd Something, [Q]uit: "); // figure out where to put in the menus
            switch (obIn.nextLine().toUpperCase()) {
                case "L":
//                    listSomething();
                    break;
                case "A":
//                    inputSomething();
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

    public static void LogIn() {
        boolean user = false;
        boolean pass = false;
        while (!user) {
            System.out.println("Enter your UserID");
            String userID = obIn.nextLine();
            if (validateId(userID)) {
                user = true;
            } else {
                System.out.println("UserID not found");
            }
        }
        while (!pass) {
            System.out.println("Enter your password");
            String userPass = obIn.nextLine();
            if (validatePassword(userPass)) {
                pass = true;
                System.out.println("Log In successful. Select from the following menu items");
                System.out.println();
            } else {
                System.out.println("Password incorrect, try again");
            }
        }
    }

    public void managebooking() { //needs to be like mel
        // figure out where to put in the menus
        System.out.print("Actions: [C] Cancel booking [N] go back");
        switch (obIn.nextLine().toUpperCase()) {
            case "C":
                cancelbooking();

                break;
            case "N":
                //go back to main

                break;
            default:

                break;

        }
        System.out.println("");
        System.out.println("");

    }

    public static boolean validateId(String userID) {
        for (Owner owner : ownerList) {
            if (owner.getUserId().compareTo(userID) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePassword(String password) {
        for (Owner owner : ownerList) {
            if (owner.getPassword().compareTo(password) == 0) {
                return true;
            }
        }
        return false;
    }

    public void cancelbooking() {
        System.out.print("input booking id");
        searchbooking = BookingHelper.search(obIn.nextLine().toUpperCase());
        //needs validation for error

        //if pass
        System.out.println("Success");
        System.out.print("Found: " + (searchbooking.getGuestID()));
        cancelConfirm();
    }


//    public static Object search(Object obVal)
//    {}

    public void cancelConfirm() {
        //go to the next
        // if passed start date
        Date date1 = new Date();
        Date date2 = searchbooking.getStartDate();
        long startTime = date1.getTime();
        long endTime = date2.getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        switch (obIn.nextLine().toUpperCase()) {
            case "Yes":
                if (diffDays > 0) {
                    //move to refund confirm
                    refundConfirm();

                } else {
                    //cancel successful
                    System.out.println("Success:");
                    bookingHelper.removeBooking(searchbooking);
                }
                break;
            case "Y":
                if (diffDays > 0) {
                    //move to refund confirm


                } else {
                    //cancel successful
                    System.out.println("Success:");
                    bookingHelper.removeBooking(searchbooking);
                }
                break;

            //error message


        }
    }

        public void refundConfirm(){
            //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.print("Actions: Refund for remaining days?");
            Date date3 = new Date();
            Date date4 = searchbooking.getEndDate();
            int price;
            price = (int) PlotHelper.searchPlot(searchbooking.getPlotID()).getPrice();
            long startTime2 = date3.getTime();
            long endTime2 = date4.getTime();
            long diffTime2 = endTime2 - startTime2;
            long diffDays2 = diffTime2 / (1000 * 60 * 60 * 24);

            int ratething = (int) diffDays2;
            ratething = price / ratething;

            switch (obIn.nextLine().toUpperCase()) {
                case "Yes":

                    if (searchbooking.getPaid() == true) {
                        System.out.print("Total amount refunded: " + ratething);
                        System.out.print("Done?");
                        //if yes
                        switch (obIn.nextLine().toUpperCase()) {
                            case "Yes":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                System.out.println("Success");
                                //move to main

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                System.out.println("Success");
                                //move to main

                                break;

                            default:

                                break;
                        }

                    } else {
                        System.out.print("Total amount refunded: " + (searchbooking.getTotal() - ratething));
                        System.out.print("Done?");
                        //if yes
                        switch (obIn.nextLine().toUpperCase()) {
                            case "Yes":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setPaid(true);
                                System.out.println("Success");
                                //move to main

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setPaid(true);
                                System.out.println("Success");
                                //move to main

                                break;

                            default:

                                break;

                        }
                    }


                    break;

                case "Y":
                    if (searchbooking.getPaid() == true) {
                        System.out.print("Total amount refunded: " + ratething);
                        System.out.print("Actions: Done?");
                        //if yes
                        switch (obIn.nextLine().toUpperCase()) {
                            case "Yes":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                System.out.println("Success");
                                //move to main

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                System.out.println("Success");
                                //move to main

                                break;

                            default:

                                break;

                        }
                    } else {
                        System.out.print("Total amount refunded: " + (searchbooking.getTotal() - ratething));
                        System.out.print("Actions: Done?");
                        //if yes
                        switch (obIn.nextLine().toUpperCase()) {
                            case "Yes":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setPaid(true);
                                System.out.println("Success");
                                //move to main

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setPaid(true);
                                System.out.println("Success");
                                //move to main

                                break;

                            default:

                                break;

                            //move to main
                        }
                    }

                    break;
                case "N":
                    refundConfirm();
                    break;
                case "No":
                    refundConfirm();
                    break;
                default:
                    //error message
                    break;


            }
        }
    }


}


