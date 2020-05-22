package campground_data;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
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
    private static Date bookingstartDate = null;
    private static Date refundendDate;
    private static BookingType bookingtype;
    private static int bookingmemberCount;
    private static int bookingplotID;


    public static void main(String[] args) {

        Owner harry = new Owner("harry", "louis", "Pa$$w0rd", "555-555-5555", "test@gmail.com", 3, true);
        ownerList.add(harry);
        LogIn();

        //Guest added for testing, ID will be 1
        guestHelper.addGuest(new Guest("Test", "Mctester", "mctester@gmail.com", "3060203345", PaymentType.Credit, "1563 1222 1589 5489", new Address(121, 0, "Test Cres.", "Saskatoon", "Saskatchewan", "Canada", "S1N2P3")));
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
                    ModifyBookingScreen();
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
        int nGuestID = 0;
        Date startDate = null;
        Date endDate = null;
        BookingType type = null;
        int memberCount = 0;
        int plotID = 0;

        System.out.println("New Booking:");

        boolean bGuestID = false;
        do{
            System.out.print("Please enter a GuestID:");
            nGuestID = Integer.valueOf(obIn.nextLine());

//            if(guestHelper.checkGuestId(nGuestID))
//            {
//                bGuestID = true;
//            }
//            else
//            {
//                System.out.println("Invalid Guest ID");
//            }

            System.out.println("");
            System.out.println("");
        } while (!bGuestID);

        boolean bStartDate = false;
        do{
            System.out.print("Please enter a Start Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.nextLine().split("/");
            try{
                SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                startDate = sdformat.parse(sFields[0] + "-" + sFields[1] + "-" + sFields[2] + "-23-59-59");
                if(startDate.getTime() - new Date().getTime() >= 0)
                {
                    bStartDate = true;
                }
                else
                {
                    System.out.println("Start date cannot be earlier than the current date");
                }
            }catch (Exception e)
            {
                System.out.println("Invalid Date");
            }

            System.out.println("");
            System.out.println("");
        } while (!bStartDate);

        boolean bEndDate = false;
        do{
            System.out.print("Please enter an End Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.nextLine().split("/");
            try{
                SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                endDate = sdformat.parse(sFields[0] + "-" + sFields[1] + "-" + sFields[2] + "-00-00-00");
                if(endDate.getTime() - startDate.getTime() > 0)
                {
                    bEndDate = true;
                }
                else
                {
                    System.out.println("End Date cannot be on or before the Start Date");
                }
            }catch (Exception e)
            {
                System.out.println("Invalid Date");
            }

            System.out.println("");
            System.out.println("");
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

            System.out.println("");
            System.out.println("");
        } while (!bPlotType);

        boolean bMemberCount = false;
        do{
            System.out.print("Please enter the amount of members staying on the plot (1-8):");
            try{
                int sVal = Integer.parseInt(obIn.nextLine());
                if(sVal >= 1 && sVal <=8)
                {
                    memberCount = sVal;
                    bMemberCount = true;
                }
                else
                {
                    System.out.println("Member count must be from 1 to 8 members");
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid member count");
            }

            System.out.println("");
            System.out.println("");
        } while (!bMemberCount);

        boolean bPlotID = false;
        do{
            System.out.print("Please enter the PlotID:");
            try{
                int nVal = Integer.parseInt(obIn.nextLine());
                //ADD PLOT ID LIST FOR CRITERIA, AND PLOT ID VERIFICATION
                plotID = nVal;
                bPlotID = true;
            }
            catch(Exception e)
            {
                System.out.println("Invalid PlotID");
            }

            System.out.println("");
            System.out.println("");
        } while (!bPlotID);

        boolean bConfirm = false;
        do{
            System.out.println("Confirm Booking? (Y/N)");
            System.out.println("GuestID = " + nGuestID);
//            System.out.println("Start Date = " + startDate.getDate() + "/" + startDate.getMonth() + "/" + startDate.getYear());
//            System.out.println("End Date = " + endDate.getDate() + "/" + endDate.getMonth() + "/" + endDate.getYear());
            System.out.println("Start Date = " + startDate);
            System.out.println("End Date = " + endDate);
            System.out.println("Booking Type = " + type);
            System.out.println("Member Count = " + memberCount);
            System.out.println("PlotID = " + plotID);

            switch (obIn.nextLine().toUpperCase()) {
                case "Y":
                    Booking booking = new Booking(plotID, nGuestID, startDate, endDate, type, memberCount);
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
                    System.out.println("Booking not created");
                    bConfirm = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }

            System.out.println("");
            System.out.println("");
        } while (!bConfirm);
    }
    public static void ModifyBookingScreen() {
        int nGuestID = 0;
        String sGuestID;


        boolean bGuestID = false;
        do {
            System.out.print("Please enter a GuestID:");
            nGuestID = Integer.valueOf(obIn.nextLine());
            sGuestID = (obIn.nextLine());

            if (guestHelper.checkGuestId(nGuestID)) {
                bGuestID = true;
                searchbooking = bookingHelper.search(sGuestID);
                bookingstartDate = searchbooking.getStartDate();
                refundendDate = searchbooking.getEndDate();
                bookingtype = searchbooking.getType();
                bookingmemberCount = searchbooking.getMemberCount();
                bookingplotID = searchbooking.getPlotID();
            } else {
                System.out.println("Invalid Guest ID");
            }

            System.out.println("");
            System.out.println("");
        } while (!bGuestID);

        System.out.println("Modify which? ");
        System.out.print("Home: [1]Start Date [2]End Date [3]Booking Type [4]Member Count [5]PlotID");
        System.out.println("GuestID = " + nGuestID);
        System.out.println("Start Date = " + bookingstartDate);
        System.out.println("End Date = " + refundendDate);
        System.out.println("Booking Type = " + bookingtype);
        System.out.println("Member Count = " + bookingmemberCount);
        System.out.println("PlotID = " + bookingplotID);
        switch (obIn.nextLine()) {
            case "1":
                BookingstartdateScreen();
                break;
            case "2":
                BookingenddateScreen();

                break;
            case "3":
                BookingtypeScreen();

                break;
            case "4":
                BookingmemberScreen();

                break;
            case "5":
                BookingplotidScreen();

                break;
            default:
                System.out.println("Invalid option, please try again");
                break;
        }

        /*
            boolean bConfirm = false;
            do {
                System.out.println("Confirm Booking? (Y/N)");
                System.out.println("GuestID = " + nGuestID);
                System.out.println("Start Date = " + refundstartDate);
                System.out.println("End Date = " + bookingendDate);
                System.out.println("Booking Type = " + bookingtype);
                System.out.println("Member Count = " + bookingmemberCount);
                System.out.println("PlotID = " + bookingplotID);

                switch (obIn.nextLine().toUpperCase()) {
                    case "Y":
                        Booking booking = new Booking(bookingplotID, nGuestID, refundstartDate, bookingendDate, bookingtype, bookingmemberCount);
                        if (bookingHelper.addBooking(booking)) {
                            System.out.println("Successfully added booking");
                        } else {
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

                System.out.println("");
                System.out.println("");
            } while (!bConfirm);

         */

    }
    public static void BookingstartdateScreen(){
        boolean bStartDate = false;
        do {
            System.out.print("Please enter a Start Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.nextLine().split("/");
            try {
                bookingstartDate = new Date(Integer.parseInt(sFields[2]), Integer.parseInt(sFields[1]) - 1, Integer.parseInt(sFields[0]));
                if (bookingstartDate.compareTo(new Date()) > -1) { //might change this later
                    bStartDate = true;
                    searchbooking.changeStart(bookingstartDate);

                } else {
                    System.out.println("Start Date cannot be before current date ");

                }
            } catch (Exception e) {
                System.out.println("Invalid Date");
            }

            System.out.println("");
            System.out.println("");
        } while (!bStartDate);
        System.out.print("Success");
        //back to main
        bookingManagerScreen();


    }
    public static void BookingenddateScreen(){ //refund
        boolean bEndDate = false;
        do {
            System.out.print("Please enter an End Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.nextLine().split("/");
            try {
                Date bookingenddate = refundendDate;
                refundendDate = new Date(Integer.parseInt(sFields[2]), Integer.parseInt(sFields[1]) - 1, Integer.parseInt(sFields[0]));
                if (refundendDate.compareTo(bookingstartDate) > 0) {
                    if (refundendDate.compareTo(bookingenddate) < 0) {
                        bEndDate = true;
                        refundConfirm();
                    }else {
                        bEndDate = true;
                        searchbooking.changeEnd(refundendDate);
                        System.out.print("Success");
                    }
                }else {

                    System.out.println("End Date cannot be on or before the Start Date");
                }
            } catch (Exception e) {
                System.out.println("Invalid Date");
            }

            System.out.println("");
            System.out.println("");
        } while (!bEndDate);

        //back to main
        bookingManagerScreen();

    }
    public static void BookingtypeScreen(){
        boolean bPlotType = false;
        do {
            System.out.print("Please enter a plot type (Cabin/Site):");
            switch (obIn.nextLine().toUpperCase()) {
                case "CABIN":
                    bookingtype = BookingType.Cabin;
                    searchbooking.setType(bookingtype);
                    bPlotType = true;
                    break;
                case "SITE":
                    bookingtype = BookingType.Site;
                    searchbooking.setType(bookingtype);
                    bPlotType = true;
                    break;
                default:
                    System.out.println("Invalid Plot Type");
                    break;
            }

            System.out.println("");
            System.out.println("");
        } while (!bPlotType);
        System.out.print("Success");
        //back to main
        bookingManagerScreen();
    }
    public static void BookingmemberScreen(){
        boolean bMemberCount = false;
        do {
            System.out.print("Please enter the amount of members staying on the plot (1-8):");
            int sVal = Integer.parseInt(obIn.nextLine());
            if (sVal >= 1 || sVal <= 8) {
                bookingmemberCount = sVal;
                searchbooking.setMemberCount(bookingmemberCount);
                bMemberCount = true;
            } else {
                System.out.println("Member count must be from 1 to 8 members");
            }

            System.out.println("");
            System.out.println("");
        } while (!bMemberCount);
        System.out.print("Success");
        //back to main
        bookingManagerScreen();
    }
    public static void BookingplotidScreen(){
        boolean bPlotID = false;
        do {
            System.out.print("Please enter the PlotID:");
            int nVal = Integer.parseInt(obIn.nextLine());
            //ADD PLOT ID LIST FOR CRITERIA, AND PLOT ID VERIFICATION
            if( plotHelper.searchPlot(nVal) != null){
                bookingplotID = nVal;
                searchbooking.setPlotID(bookingplotID);
                bPlotID = true;
            }else{
                System.out.println("invalid plotID");
            }


            System.out.println("");
            System.out.println("");
        } while (!bPlotID);
        System.out.print("Success");
        //back to main
        bookingManagerScreen();
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
                    System.out.println("Enter phone number to find guest to edit: ");
                    Guest guestToEdit = guestHelper.searchGuest(obIn.nextLine());
                    if (guestToEdit == null) {
                        System.out.println("There is no guest found");
                    }
                    else {
                        guestHelper.changeGuestInfo(guestToEdit);
                    }
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
                    modifyPlotTypesScreen();
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


    public static void modifyPlotTypesScreen() {
        System.out.println("Please select an option: [1]Change Plot Attributes  [2]Change Cabin Attributes  [3]Change Site Attributes  [4]Back");
        switch (obIn.next()) {
            case "1":
                modifyPlotsScreen();
                break;
            case "2":
                modifyCabinScreen();
                break;
            case "3":
                modifySiteScreen();
                break;
            case "4":
                plotManagerScreen();
                break;
            default:
                System.out.println("Invalid option, please try again");
                modifyPlotTypesScreen();
                break;
        }
    }

    public static void modifyPlotsScreen()
    {
        System.out.println("Please enter a Plot ID:");
        int nPlotID=obIn.nextInt();
        Plot obFound=plotHelper.searchPlot(nPlotID);
        if(obFound!=null)
        {
            System.out.println("Plot Found!");
            System.out.println("Please select an attribute to be changed: [1]PlotID  [2]Occupancy  [3]Price  [4]Under Renovation? [5]Booked? [6]Back");

            switch(obIn.next())
            {
                case "1":
                    System.out.println("Please enter a new plot ID: ");
                    obFound.setPlotID(obIn.nextInt());
                    break;
                case "2":
                    System.out.println("Please enter a new occupancy value: ");
                    obFound.setOccupancy(obIn.nextInt());
                    break;
                case "3":
                    System.out.println("Please enter a new price:");
                    obFound.setPrice(obIn.nextDouble());
                    break;
                case "4":
                    System.out.println("Is the plot under renovation? [Y/N]");
                    switch(obIn.next())
                    {
                        case "Y":
                        case "y":
                            obFound.setUnderReno(true);
                            break;
                        case "N":
                        case "n":
                            obFound.setUnderReno(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifyPlotsScreen();
                    }
                    break;
                case "5":
                    System.out.println("Is the plot booked? [Y/N]");
                    switch(obIn.next())
                    {
                        case "Y":
                        case "y":
                            obFound.setBooked(true);
                            break;
                        case "N":
                        case "n":
                            obFound.setBooked(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifyPlotsScreen();
                    }
                    break;
                case "6":
                    modifyPlotTypesScreen();
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    modifyPlotsScreen();
                    break;
            }
        }
        else
        {
            System.out.println("No Plot found with that ID. Try again");
            modifyPlotsScreen();
        }

    }

    public static void modifyCabinScreen()
    {
        System.out.println("Please enter a Cabin Number:");
        int nCabinNum=obIn.nextInt();
        Plot obFound=plotHelper.searchPlot(nCabinNum); //Needs to be changed to Cabin
        if(obFound!=null)
        {
            System.out.println("Cabin Found!");
            System.out.println("Please select an attribute to be changed: [1]Cabin Number  [2]Occupancy  [3]Type  [4]Price  [5]Under Renovation? [6]Booked? [7]Back");

            switch(obIn.next())
            {
                case "1":
                    System.out.println("Please enter a new cabin number: ");
                    obFound.setPlotID(obIn.nextInt());
                    break;
                case "2":
                    System.out.println("Please enter a new occupancy value: ");
                    obFound.setOccupancy(obIn.nextInt());
                    break;
                case "4":
                    System.out.println("Please enter a new price:");
                    obFound.setPrice(obIn.nextDouble());
                    break;
                case "5":
                    System.out.println("Is the cabin under renovation? [Y/N]");
                    switch(obIn.next())
                    {
                        case "Y":
                        case "y":
                            obFound.setUnderReno(true);
                            break;
                        case "N":
                        case "n":
                            obFound.setUnderReno(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifyCabinScreen();
                    }
                    break;
                case "6":
                    System.out.println("Is the cabin booked? [Y/N]");
                    switch(obIn.next())
                    {
                        case "Y":
                        case "y":
                            obFound.setBooked(true);
                            break;
                        case "N":
                        case "n":
                            obFound.setBooked(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifyCabinScreen();
                    }
                    break;
                case "7":
                    modifyPlotTypesScreen();
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    modifyCabinScreen();
                    break;
            }
        }
        else
        {
            System.out.println("No Cabin found with that number. Try again");
            modifyCabinScreen();
        }
    }

    public static void modifySiteScreen()
    {
        System.out.println("Please enter a Site Number:");
        int nSiteNum=obIn.nextInt();
        Plot obFound=plotHelper.searchPlot(nSiteNum);//Needs to be changed to Site
        if(obFound!=null)
        {
            System.out.println("Site Found!");
            System.out.println("Please select an attribute to be changed: [1]Site Number  [2]Occupancy  [3]Type  [4]Price  " +
                    "[5]Under Renovation? [6]Booked? [7]Serviced? [8]Back");

            switch(obIn.next())
            {
                case "1":
                    System.out.println("Please enter a new site number: ");
                    obFound.setPlotID(obIn.nextInt());
                    break;
                case "2":
                    System.out.println("Please enter a new occupancy value: ");
                    obFound.setOccupancy(obIn.nextInt());
                    break;
                case "4":
                    System.out.println("Please enter a new price:");
                    obFound.setPrice(obIn.nextDouble());
                    break;
                case "5":
                    System.out.println("Is the site under renovation? [Y/N]");
                    switch(obIn.next())
                    {
                        case "Y":
                        case "y":
                            obFound.setUnderReno(true);
                            break;
                        case "N":
                        case "n":
                            obFound.setUnderReno(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifySiteScreen();
                    }
                    break;
                case "6":
                    System.out.println("Is the site booked? [Y/N]");
                    switch(obIn.next())
                    {
                        case "Y":
                        case "y":
                            obFound.setBooked(true);
                            break;
                        case "N":
                        case "n":
                            obFound.setBooked(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifySiteScreen();
                    }
                    break;
                case "7":
                    System.out.println("Is the site serviced? [Y/N]");
                    switch(obIn.next())
                    {
                        case "Y":
                        case "y":
                            //obFound.setServiced(true);
                            break;
                        case "N":
                        case "n":
                            //obFound.setServiced(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifySiteScreen();
                    }
                    break;
                case "8":
                    modifyPlotTypesScreen();
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    modifySiteScreen();
                    break;
            }
        }
        else
        {
            System.out.println("No Cabin found with that number. Try again");
            modifySiteScreen();
        }
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




//    public static Object search(Object obVal)
//    {}


        public static void refundConfirm(){
            //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.print("Actions: Refund for remaining days?");
            Date date3 = new Date();
            Date date4 = searchbooking.getEndDate();
            int price;
            int plotid = bookingplotID;
            //plotHelper.searchPlot(plotid);
            Plot priceplot = PlotHelper.searchPlot(plotid);//whyyyyyyyy
                price = (int) priceplot.getPrice();
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
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

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
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setPaid(true);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

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
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

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
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

                                break;
                            case "Y":

                                //bookingHelper.removeBooking(searchbooking);
                                searchbooking.setPaid(true);
                                searchbooking.setTotal((searchbooking.getTotal() - ratething));
                                searchbooking.changeEnd(refundendDate);
                                System.out.println("Success");
                                //move to main
                                bookingManagerScreen();

                                break;

                            default:

                                break;


                        }
                    }

                    break;
                case "N":
                    //refundConfirm();
                    System.out.print("Total amount: " + price);
                    searchbooking.setPaid(true);
                    searchbooking.changeEnd(refundendDate);
                    System.out.println("Success");
                    //move to main
                    bookingManagerScreen();

                    break;
                case "No":
                    //refundConfirm();
                    System.out.print("Total amount: " + price);
                    searchbooking.setPaid(true);
                    searchbooking.changeEnd(refundendDate);
                    System.out.println("Success");
                    //move to main
                    bookingManagerScreen();
                    break;
                default:
                    //error message
                    System.out.print("Please input yes,y or no,n");
                    refundConfirm();
                    break;


            }
        }


    public static PlotHelper getPlotHelper()
    {
        return plotHelper;
    }


}


