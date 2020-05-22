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
    private static DatabaseFile dbfile = new DatabaseFile();
    private static ArrayList<Owner> ownerList = ownerHelper.getOwnerList();
    private static ArrayList<Plot> sites = plotHelper.getPlotList();

    private static Scanner obIn = new Scanner(System.in);


    private static Booking searchbooking;
    private static Date bookingstartDate = null;
    private static Date refundendDate;
    private static BookingType bookingtype;
    private static int bookingmemberCount;
    private static int bookingplotID;


    public static void main(String[] args) {

        LogIn();

        //Guest added for testing, ID will be 1
        guestHelper.addGuest(new Guest("Test", "Mctester", "mctester@gmail.com", "3060203345", PaymentType.Credit, "1563 1222 1589 5489", new Address(121, 0, "Test Cres.", "Saskatoon", "Saskatchewan", "Canada", "S1N2P3")));

        //adding site for testing
        plotHelper.addPlot(new Site(2, 4, 50, Site.SiteType.Group, true, true));

        //adding cabin for testing
        plotHelper.addPlot(new Cabin(1, 4, Cabin.CabinType.Deluxe, 100, false));

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

            if(guestHelper.checkGuestId(nGuestID))
            {
                bGuestID = true;
            }
            else
            {
                System.out.println("Invalid Guest ID");
            }

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
//                        refundConfirm();
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
            System.out.print("Plot Manager: [1]Modify Plot [2]Back:");
            switch (obIn.nextLine()) {
                case "1":
                    modifyPlotTypesScreen();
                    break;
                case "2":
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
        System.out.println("Please select an option: [1]Change Cabin Attributes  [2]Change Site Attributes  [3]Back");
        switch (obIn.nextLine()) {
//            case "1":
//                modifyPlotsScreen();
//                break;
            case "1":
                modifyCabinScreen();
                break;
            case "2":
                modifySiteScreen();
                break;
            case "3":
                plotManagerScreen();
                break;
            default:
                System.out.println("Invalid option, please try again");
                modifyPlotTypesScreen();
                break;
        }
    }

    public static void modifyCabinScreen()
    {
        System.out.println("Please enter a Cabin Number:");
        int nCabinNum=Integer.parseInt(obIn.nextLine());
        Cabin obFound=plotHelper.searchCabin(nCabinNum); //Needs to be changed to Cabin
        if(obFound!=null)
        {
            System.out.println("Cabin Found!");
            System.out.println("Please select an attribute to be changed: [1]Cabin Number  [2]Occupancy  [3]Type  [4]Price  [5]Under Renovation? [6]Booked? [7]Back");

            switch(obIn.nextLine())
            {
                case "1":
                    System.out.println("Please enter a new cabin number: ");
                    obFound.setPlotID(Integer.parseInt(obIn.nextLine()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "2":
                    System.out.println("Please enter a new occupancy value: ");
                    obFound.setOccupancy(Integer.parseInt(obIn.nextLine()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "3":
                    System.out.println("Select: [B]asic [D]eluxe");
                    switch (obIn.nextLine().toUpperCase())
                    {
                        case "B":
                            obFound.setCabinType(Cabin.CabinType.Basic);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        case "D":
                            obFound.setCabinType(Cabin.CabinType.Deluxe);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            //print toString to display changes
                            System.out.println("Changes not made:");
                            System.out.println(obFound.toString());
                            modifyCabinScreen();
                            break;
                    }
                case "4":
                    System.out.println("Please enter a new price:");
                    obFound.setPrice(Double.parseDouble(obIn.nextLine()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "5":
                    System.out.println("Is the cabin under renovation? [Y/N]");
                    switch(obIn.nextLine().toUpperCase())
                    {
                        case "Y":
                            obFound.setUnderReno(true);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        case "N":
                            obFound.setUnderReno(false);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        default:
                            System.out.println("Invalid option, please try again");

                            modifyCabinScreen();
                    }
                    break;
                case "6":
                    System.out.println("Is the cabin booked? [Y/N]");
                    switch(obIn.nextLine().toUpperCase())
                    {
                        case "Y":
                            obFound.setBooked(true);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        case "N":
                            obFound.setBooked(false);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifyCabinScreen();
                    }
                    break;
                case "7":
                    modifyPlotTypesScreen();
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    //print toString to display changes
                    System.out.println("Changes not made:");
                    System.out.println(obFound.toString());
                    modifyCabinScreen();
                    break;
            }

            //print toString to display changes
            System.out.println(obFound.toString());
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
        int nSiteNum=Integer.parseInt(obIn.nextLine());
        Site obFound=plotHelper.searchSite(nSiteNum);
        if(obFound!=null)
        {
            System.out.println("Site Found!");
            System.out.println("Please select an attribute to be changed: [1]Site Number  [2]Occupancy  [3]Type  [4]Price  " +
                    "[5]Under Renovation? [6]Booked? [7]Serviced? [8]Back");

            switch(obIn.nextLine())
            {
                case "1":
                    System.out.println("Please enter a new site number: ");
                    obFound.setPlotID(Integer.parseInt(obIn.nextLine()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "2":
                    System.out.println("Please enter a new occupancy value: ");
                    obFound.setOccupancy(Integer.parseInt(obIn.nextLine()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "3":
                    System.out.println("Select: [G]roup [I]ndividual");
                    switch (obIn.nextLine().toUpperCase())
                    {
                        case "G":
                            obFound.setSiteType(Site.SiteType.Group);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        case "I":
                            obFound.setSiteType(Site.SiteType.Individual);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            //print toString to display changes
                            System.out.println("Changes not made:");
                            System.out.println(obFound.toString());
                            modifySiteScreen();
                            break;
                    }
                case "4":
                    System.out.println("Please enter a new price:");
                    obFound.setPrice(Double.parseDouble(obIn.nextLine()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "5":
                    System.out.println("Is the site under renovation? [Y/N]");
                    switch(obIn.nextLine().toUpperCase())
                    {
                        case "Y":
                            obFound.setUnderReno(true);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        case "N":
                            obFound.setUnderReno(false);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifySiteScreen();
                    }
                    break;
                case "6":
                    System.out.println("Is the site booked? [Y/N]");
                    switch(obIn.nextLine().toUpperCase())
                    {
                        case "Y":
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            obFound.setBooked(true);
                            break;
                        case "N":
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            obFound.setBooked(false);
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifySiteScreen();
                    }
                    break;
                case "7":
                    System.out.println("Is the site serviced? [Y/N]");
                    switch(obIn.nextLine().toUpperCase())
                    {
                        case "Y":
                            obFound.setServiced(true);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        case "N":
                            obFound.setServiced(false);
                            //print toString to display changes
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifySiteScreen();
                    }
                    break;
                case "8":
                    modifyPlotTypesScreen();
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    //print toString to display changes
                    System.out.println("Changes not made:");
                    System.out.println(obFound.toString());
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
                    forceChangePassword(currUser);
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
        boolean pass = false;
        while (currUser == null)
        {
            System.out.println("Enter your UserID");
            String userID = obIn.nextLine();

            currUser = validateId(userID);

            if(currUser == null)
            {
                System.out.println("UserID not found");
            }
        }
        while (!pass)
        {
            System.out.println("Enter your password");
            String userPass = obIn.nextLine();
            if(validatePassword(currUser, userPass))
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
    public void managebooking()
    {
    }
    public static Owner validateId(String userID)
    {
        for (Owner owner : ownerList)
        {
            if(owner.getUserId().compareTo(userID) == 0)
            {
                return owner;
            }
        }
        return null;
    }

    public static boolean validatePassword(Owner owner, String password)
    {
        if(owner.getPassword().compareTo(password) == 0)
        {
            if(password.equals(""))
            {
                forceChangePassword(owner);
            }
            return true;
        }
        return false;
    }

    public static void forceChangePassword(Owner owner)
    {
        boolean changed = false;
        while(!changed)
        {
            System.out.println("Please change your password");
            System.out.println("Enter new password");
            String sPass = obIn.nextLine();
            System.out.println("Enter new password again to confirm");
            String sConfirm = obIn.nextLine();
            if(sPass.equals(sConfirm))
            {

                if(owner.changePassword(sPass)) {
                    Thread th1 = new Thread(() -> {
                        dbfile.saveRecords(ownerList);
                    });
                    th1.start();
                    changed = true;
                    System.out.println("Password changed successfully");
                }
                else
                {
                    System.out.println("New password must be at least 8 characters");
                }
            }
            else
            {
                System.out.println("The passwords do not match, change rejected");
            }
        }
    }

//    public static Object search(Object obVal)
//    {}


        public static void refundConfirm() {
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


    public static void createOwners()
    {
        Owner harry = new Owner("harry", "louis", "Mounta1nM@n", "555-555-5555", "hlouis@cestlake.ca", 3, true);
        Owner mary = new Owner("mary", "louis", "F1uffyC@ts", "555-555-5555", "mlouis@cestlake.ca", 3, true);
        Owner tom = new Owner("tom", "louis", "", "498-2772-7512", "tlouis@cestlake.ca", 2, false);
        Owner sarah = new Owner("sarah", "louis", "", "872-848-1480", "slouis@cestlake.ca", 2, false);
        Owner guest = new Owner("guest", "login", "Pa$$w0rd", "n/a", "info@cestlake.ca", 1, false);
        ownerList.add(harry);
        ownerList.add(mary);
        ownerList.add(tom);
        ownerList.add(sarah);
        ownerList.add(guest);
        dbfile.saveRecords(ownerList);
    }

    public static void createPlots()
    {
        Site site1 = new Site(100, 4,32.00, Site.SiteType.Individual, true, false);
        Site site2 = new Site(101, 4,32.00, Site.SiteType.Individual,  true, false);
        Site site3 = new Site(102, 4,32.00, Site.SiteType.Individual,  true, false);
        Site site4 = new Site(103, 4,32.00, Site.SiteType.Individual,  true, false);
        Site site5 = new Site(104, 4,32.00, Site.SiteType.Individual,  true, false);
        Site site6 = new Site(105, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site7 = new Site(106, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site8 = new Site(107, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site9 = new Site(108, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site10 = new Site(109, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site11 = new Site(110, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site12 = new Site(111, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site13= new Site(112, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site14 = new Site(113, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site15 = new Site(114, 4,20.00, Site.SiteType.Individual,  false, false);
        Site site16 = new Site(115, 8,64.00, Site.SiteType.Group,  true, false);
        Site site17 = new Site(116, 8,64.00,  Site.SiteType.Group, true, false);
        Site site18 = new Site(117, 8,64.00, Site.SiteType.Group,  true, false);
        Site site19 = new Site(118, 8,64.00, Site.SiteType.Group,  true, false);
        Site site20 = new Site(119, 8,64.00, Site.SiteType.Group,  true, false);
        Site site21 = new Site(120, 8,40.00, Site.SiteType.Group,  false, false);
        Site site22 = new Site(121, 8,40.00, Site.SiteType.Group, false, false);
        Site site23 = new Site(122, 8,40.00, Site.SiteType.Group,  false, false);
        Site site24 = new Site(123, 8,40.00, Site.SiteType.Group,  false, false);
        Site site25 = new Site(124, 8,40.00, Site.SiteType.Group,  false, false);

        sites.add(site1);
        sites.add(site2);
        sites.add(site3);
        sites.add(site4);
        sites.add(site5);
        sites.add(site6);
        sites.add(site7);
        sites.add(site8);
        sites.add(site9);
        sites.add(site10);
        sites.add(site11);
        sites.add(site12);
        sites.add(site13);
        sites.add(site14);
        sites.add(site15);
        sites.add(site16);
        sites.add(site17);
        sites.add(site18);
        sites.add(site19);
        sites.add(site20);
        sites.add(site21);
        sites.add(site22);
        sites.add(site23);
        sites.add(site24);
        sites.add(site25);
        dbfile.saveRecords(sites);
    }

    public static PlotHelper getPlotHelper()
    {
        return plotHelper;
    }
}


