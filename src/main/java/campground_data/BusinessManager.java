package campground_data;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class BusinessManager {

    private static Owner currUser;
    private static BookingHelper bookingHelper = new BookingHelper();
    private static PlotHelper plotHelper = new PlotHelper();
    private static OwnerHelper ownerHelper = new OwnerHelper();
    private static GuestHelper guestHelper = new GuestHelper();
    private static ArrayList<Owner> ownerList = OwnerHelper.getOwnerList();

    private static Scanner obIn = new Scanner(System.in);

    private static Booking searchbooking;

    public static void main(String[] args) {

        Owner harry = new Owner("harry", "louis", "Pa$$w0rd", "555-555-5555", "test@gmail.com", 3, true);
        ownerList.add(harry);
        LogIn();

        homeScreen();
    }

    public static void homeScreen()
    {
        boolean quit = false;
        do{
            System.out.print("Home: [1]Booking Manager [2]Guest Manager [3]Plot Manager [4]Owner Manager [5]Quit:");
            switch (obIn.nextLine()) {
                case "1":
                    bookingManagerScreen();
                    break;
                case "2":
                    guestManagerScreen();
                    break;
                case "3":
                    plotManagerScreen();
                    break;
                case "4":
                    ownerManagerScreen();
                    break;
                case "5":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    homeScreen();
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!quit);
        System.out.println("Quit Application");
        System.exit(0);
    }

    public static void bookingManagerScreen()
    {
        boolean back = false;
        do{
            System.out.print("Booking Manager: [1]Add Booking [2]Remove Booking [3]Modify Booking [4]Find Booking [5]View Current Bookings [6]Back:");
            switch (obIn.next()) {
                case "1":
                    addBookingScreen();
                    break;
                case "2":
                    removeBookingScreen();
                    break;
                case "3":
                    ;
                    break;
                case "4":
                    ;
                    break;
                case "5":
                    viewCurrentBookingsScreen();
                    break;
                case "6":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    bookingManagerScreen();
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!back);
        System.out.println("Back to home screen");
        homeScreen();
    }

    public static void removeBookingScreen()
    {
        System.out.println("Please Enter a booking ID for removal: [0]Back ");
        int bookingID=obIn.nextInt();
        if(bookingID==0)
        {
            bookingManagerScreen();
        }
        else
        {
            Booking obFound=bookingHelper.searchBookingId(bookingID);
            if(obFound!=null)
            {
                System.out.println("Found a booking with that ID!");
                System.out.println(obFound.toString());
                System.out.println("Do you wish to remove this booking from the records? [Y/N]");
                switch(obIn.next())
                {
                    case "Y":
                    case "y":
                        bookingHelper.removeBooking(obFound);
                        System.out.println("Booking removed");
                        break;
                    case "N":
                    case "n":
                        removeBookingScreen();
                        break;
                    default:
                        System.out.println("Invalid option, please try again");
                        removeBookingScreen();
                        break;
                }
            }
            else
            {
                System.out.println("No booking found with that ID. Try again");
                removeBookingScreen();
            }
        }
    }

    public static void viewCurrentBookingsScreen()
    {
        ArrayList<Booking> obBookingList;
        System.out.println("Please choose an option: [1]View All Bookings [2]View Bookings for a Certain Year");
        switch (obIn.nextLine()) {
            case "1":
                obBookingList = bookingHelper.getBookingList();
                for(Booking obVal: obBookingList)
                {
                    System.out.println(obVal.toString());
                }
                bookingManagerScreen();
                break;
            case "2":
                System.out.println("Enter a year to view all bookings for that year:");
                int nYear=obIn.nextInt();
                obBookingList=bookingHelper.getBookingList(nYear);
                for(Booking obVal: obBookingList)
                {
                    System.out.println(obVal.toString());
                }
                bookingManagerScreen();
                break;
            default:
                System.out.println("Invalid option, please try again");
                viewCurrentBookingsScreen();
                break;
        }
    }
    public static void addBookingScreen()
    {
        String sGuestID = "";
        Date startDate = null;
        Date endDate = null;
        BookingType type = null;
        int memberCount = 0;
        int plotID = 0;

        boolean bGuestID = false;
        do{
            System.out.print("Please enter a GuestID:");
            sGuestID = obIn.nextLine();
            //ADD GUEST VERIFICATION
            bGuestID = true;

        } while (!bGuestID);

        boolean bStartDate = false;
        do{
            System.out.print("Please enter a Start Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.nextLine().split("/");
            try{
                startDate = new Date(Integer.parseInt(sFields[2]),Integer.parseInt(sFields[1])-1, Integer.parseInt(sFields[0]));
                bStartDate = true;
            }catch (Exception e)
            {
                System.out.println("Invalid Date");
            }
        } while (!bStartDate);

        boolean bEndDate = false;
        do{
            System.out.print("Please enter an End Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.nextLine().split("/");
            try{
                endDate = new Date(Integer.parseInt(sFields[2]),Integer.parseInt(sFields[1])-1, Integer.parseInt(sFields[0]));
                bEndDate = true;
            }catch (Exception e)
            {
                System.out.println("Invalid Date");
            }
        } while (!bEndDate);

        boolean bPlotType = false;
        do{
            System.out.print("Please enter a plot type (Cabin/Site):");
            switch (obIn.nextLine().toUpperCase()) {
                case "CABIN":
                    type = BookingType.Cabin;
                    bPlotType = true;
                    break;
                case "SITE":
                    type = BookingType.Site;
                    bPlotType = true;
                    break;
                default:
                    System.out.println("Invalid Plot Type");
                    break;
            }
        } while (!bPlotType);

        boolean bMemberCount = false;
        do{
            System.out.print("Please enter the amount of members staying on the plot:");
            int sVal = Integer.parseInt(obIn.nextLine());
            if(sVal >= 1 || sVal <=8)
            {
                memberCount = sVal;
                bMemberCount = true;
            }
            else
            {
                System.out.println("Member count must be from 1 to 8 members");
            }

        } while (!bMemberCount);

        boolean bPlotID = false;
        do{
            System.out.print("Please enter the PlotID:");
            int nVal = Integer.parseInt(obIn.nextLine());
            //ADD PLOT ID LIST FOR CRITERIA, AND PLOT ID VERIFICATION
            plotID = nVal;
            bPlotID = true;
        } while (!bPlotID);

        boolean bConfirm = false;
        do{
            System.out.println("Confirm Booking? (Y/N)");
            System.out.println("GuestID = " + sGuestID);
            System.out.println("Start Date = " + startDate);
            System.out.println("End Date = " + endDate);
            System.out.println("Booking Type = " + type);
            System.out.println("Member Count = " + memberCount);
            System.out.println("PlotID = " + plotID);

            switch (obIn.nextLine().toUpperCase()) {
                case "Y":
                    Booking booking = new Booking(plotID, sGuestID, startDate, endDate, type, memberCount);
                    if(bookingHelper.addBooking(booking))
                    {
                        System.out.println("Successfully added booking");
                    }
                    else
                    {
                        System.out.println("Unsuccessful booking");
                    }

                    bConfirm = true;
                    break;
                case "N":
                    System.out.println("Booking not added");
                    bConfirm = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        } while (!bConfirm);



    }

    public static void guestManagerScreen()
    {
        boolean back = false;
        do{
            System.out.print("Guest Manager: [1]Add Guest [2]Remove Guest [3]Edit Guest [4]Find Guest [5]Back:");
            switch (obIn.nextLine()) {
                case "1":
                    ;
                    break;
                case "2":
                    ;
                    break;
                case "3":
                    ;
                    break;
                case "4":
                    ;
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    guestManagerScreen();
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!back);
        System.out.println("Back to home screen");
        homeScreen();
    }

    public static void plotManagerScreen()
    {
        boolean back = false;
        do{
            System.out.print("Plot Manager: [1]Add Plot [2]Remove Plot [3]Modify Plot [4]Find Plot [5]Back:");
            switch (obIn.nextLine()) {
                case "1":
                    ;
                    break;
                case "2":
                    ;
                    break;
                case "3":
                    ;
                    break;
                case "4":
                    ;
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    plotManagerScreen();
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!back);
        System.out.println("Back to home screen");
        homeScreen();
    }

    public static void ownerManagerScreen()
    {
        boolean back = false;
        do{
            System.out.print("Owner Manager: [1]Add Owner [2]Remove Owner [3]Modify Owner [4]Find Owner [5]Back:");
            switch (obIn.nextLine()) {
                case "1":
                    ;
                    break;
                case "2":
                    ;
                    break;
                case "3":
                    ;
                    break;
                case "4":
                    ;
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    ownerManagerScreen();
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!back);
        System.out.println("Back to home screen");
        homeScreen();
    }

    public static void LogIn()
    {
        boolean user = false;
        boolean pass = false;
        while (!user)
        {
            System.out.println("Enter your UserID");
            String userID = obIn.nextLine();
            if(validateId(userID))
            {
                user = true;
            }
            else
            {
                System.out.println("UserID not found");
            }
        }
        while (!pass)
        {
            System.out.println("Enter your password");
            String userPass = obIn.nextLine();
            if(validatePassword(userPass))
            {
                pass = true;
                System.out.println("Log In successful. Select from the following menu items");
                System.out.println();
            }
            else
            {
                System.out.println("Password incorrect, try again");
            }
        }
    }
    public void managebooking() {
    }
    public static boolean validateId(String userID)
    {
        for (Owner owner : ownerList)
        {
            if(owner.getUserId().compareTo(userID) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePassword(String password)
    {
        for (Owner owner : ownerList)
        {
            if(owner.getPassword().compareTo(password) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public static Booking cancelbooking(String phoneNum) {
        searchbooking = bookingHelper.search(phoneNum);
        //needs validation for error
        return bookingHelper.search(phoneNum);
    }

//    public static Object search(Object obVal)
//    {}

    public void cancelConfirm(String answer ) {
        if (answer.equals("y") || answer.equals("yes")) {
            //go to the next
            // if passed start date
            Date date1 = new Date();
            Date date2 = searchbooking.getStartDate();
            //Date does not work??????
            if (date2.getDate() - date1.getDate() > 0 ){
                if (date2.getMonth() - date1.getMonth() >= 0){
                    //move to refund confirm
                }else {
                    //cancel successful
                    bookingHelper.removeBooking(searchbooking);
                }

            }else{
                //cancel successful
                bookingHelper.removeBooking(searchbooking);
            }
        } else if (answer.equals("n") || answer.equals("no") || answer.equals("nope")) {
            //go back to current booking
            //move into mainconsole instead

        }
            //error message


    }

    public double refundconfirm(String answer) {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        Date date2 = searchbooking.getEndDate();
        int price;
        price = (int) plotHelper.searchPlot(searchbooking.getPlotID()).getPrice();

        int ratething = (int) (date1.getDate() - date2.getDate());
        ratething = ratething * price;

        if (answer.equals("y") || answer.equals("yes")) {
            return (searchbooking.getTotal() - ratething);
        } else if (answer.equals("n") || answer.equals("no") || answer.equals("nope")){
            return searchbooking.getTotal();
        }
            //error message
        return 0;
    }

    public static PlotHelper getPlotHelper()
    {
        return plotHelper;
    }

}


