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

    private static int bookingguestid;
    private static Date bookingstartDate = null;
    private static Date refundendDate;
    private static BookingType bookingtype;
    private static int bookingmemberCount;
    private static int bookingplotID;


    public static void main(String[] args) {

        createOwners();
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
            System.out.println("Home: [1]Booking Manager [2]Guest Manager [3]Plot Manager [4]Owner Manager [5]Quit:");
            switch (obIn.next()) {
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
            System.out.println("Booking Manager: [1]Add Booking [2]Remove Booking [3]Modify Booking [4]Find Booking [5]View Current Bookings [6]Back:");
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

                    break;
                case "5":
                    viewCurrentBookingsScreen();
                    break;
//                case"6":
//                    changeBookingDateScreen();
//                    break;
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

//    public static void changeBookingDateScreen()
//    {
//        String guestID;
//        int bookingID;
//        Date newStartDate;
//        Date newEndDate;
//        boolean bChanged;
//        int answer;
//        do
//        {
//            bChanged = false;
//            System.out.println("Please enter a guest ID.");
//            guestID = obIn.next();
//            System.out.println(bookingHelper.search(guestID));
//            System.out.println("Please enter a booking ID");
//            bookingID = Integer.parseInt(obIn.next());
//            System.out.println("Please enter the new Start Date in the format (dd/mm/yyyy):");
//            String[] sFields = obIn.next().split("/");
//            newStartDate = new Date(Integer.parseInt(sFields[0]), Integer.parseInt(sFields[1]), Integer.parseInt(sFields[2]));
//            System.out.println("Please enter the new Start Date in the format (dd/mm/yyyy):");
//            sFields = obIn.next().split("/");
//            newEndDate = new Date(Integer.parseInt(sFields[0]), Integer.parseInt(sFields[1]), Integer.parseInt(sFields[2]));
//            bookingHelper.changeBookingDate(bookingID, newStartDate, newEndDate);
//            if (!bookingHelper.changeBookingDate(bookingID, newStartDate, newEndDate))
//            {
//                bChanged = true;
//
//            }
//            else
//            {
//                System.out.println("Please try again");
//            }
//
//
//        } while (!bChanged);
//
//        System.out.println(bookingHelper.searchBookingId(bookingID));
//        System.out.println("[1] Home screen [2] Change another booking's date");
//        answer = Integer.parseInt(obIn.next());
//        if (answer == 1)
//        {
//            homeScreen();
//        }
//
//        if (answer == 2)
//        {
//            changeBookingDateScreen();
//        }
//    }

    //OneDrive Change Booking Screen
    //Change Booking Date - This will prompt the owner to enter a guestID. The system will display a list of bookings for that specific ID.
    // The system will prompt the owner for the bookingID, new start date, and new end date. If the system successfully changes the dates,
    // the booking with the new dates will be displayed.

    public static void removeBookingScreen()
    {

        System.out.println("Please enter a Guest ID to find booking to remove or [0]Back: ");

        int guestID=obIn.nextInt();
        if(guestID==0)
        {
            bookingManagerScreen();
        }
        else
        {
            Booking obFound=bookingHelper.search(guestID);
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
        switch (obIn.next()) {
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
                obBookingList=bookingHelper.getBookingListByYear(nYear);
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
            System.out.println("Please enter a GuestID:");
            try{
                nGuestID = Integer.valueOf(obIn.next());
                if(guestHelper.checkGuestId(nGuestID))
                {
                    bGuestID = true;
                }
                else
                {
                    System.out.println("Invalid Guest ID");
                }
            }
            catch(Exception e)
            {
                System.out.println("GuestID must be an number");
            }

            System.out.println("");
            System.out.println("");
        } while (!bGuestID);

        boolean bStartDate = false;
        do{
            System.out.println("Please enter a Start Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.next().split("/");
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
            System.out.println("Please enter an End Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.next().split("/");
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
            System.out.println("Please enter a plot type (Cabin/Site):");
            switch (obIn.next().toUpperCase()) {
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
            System.out.println("Please enter the amount of members staying on the plot (1-8):");
            try{
                int sVal = Integer.parseInt(obIn.next());
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
            System.out.println("Please enter the PlotID:");
            try{
                int nVal = Integer.parseInt(obIn.next());
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
            System.out.println("Start Date = " + startDate);
            System.out.println("End Date = " + endDate);
            System.out.println("Booking Type = " + type);
            System.out.println("Member Count = " + memberCount);
            System.out.println("PlotID = " + plotID);

            switch (obIn.next().toUpperCase()) {
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

        boolean bbookingID = false;
        do {
            System.out.println("Please enter a Booking ID or [0]Back:");
            int bookingID = obIn.nextInt();
            if (bookingID == 0) {
                bookingManagerScreen();
            } else {
                Booking obFound = bookingHelper.searchBookingId(bookingID);
                if (obFound != null) {
                    System.out.println("Found a booking with that ID!");
                    System.out.println(obFound.toString());
                    bbookingID = true;
                    searchbooking = obFound;
                    bookingguestid = searchbooking.getGuestID();
                    bookingstartDate = searchbooking.getStartDate();
                    refundendDate = searchbooking.getEndDate();
                    bookingtype = searchbooking.getType();
                    bookingmemberCount = searchbooking.getMemberCount();
                    bookingplotID = searchbooking.getPlotID();

                    //int plotID, int guestID, Date startDate, Date endDate, BookingType type, int memberCount
                    System.out.println("Modify which? ");
                    System.out.println("Home: [1]Start Date [2]End Date [3]Booking Type [4]Member Count [5]PlotID [6]Back");
                    System.out.println("GuestID = " + bookingguestid);
                    System.out.println("Start Date = " + bookingstartDate);
                    System.out.println("End Date = " + refundendDate);
                    System.out.println("Booking Type = " + bookingtype);
                    System.out.println("Member Count = " + bookingmemberCount);
                    System.out.println("PlotID = " + bookingplotID);
                    switch (obIn.nextInt()) {

                        case 1:
                            BookingstartdateScreen();
                            break;
                        case 2:
                            BookingenddateScreen();

                            break;
                        case 3:
                            BookingtypeScreen();

                            break;
                        case 4:
                            BookingmemberScreen();

                            break;
                        case 5:
                            BookingplotidScreen();

                            break;
                        case 6:
                            bookingManagerScreen();

                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            break;
                    }


                } else {
                    System.out.println("Invalid Booking ID");
                }
            }
        } while (!bbookingID);
    }



    public static void BookingstartdateScreen(){
        boolean bStartDate = false;
        do {
            System.out.println("Please enter a Start Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.next().split("/");
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
        System.out.println("Success");
        //back to main
        bookingManagerScreen();


    }
    public static void BookingenddateScreen(){ //refund
        boolean bEndDate = false;
        do {
            System.out.println("Please enter an End Date in the format (dd/mm/yyyy):");
            String[] sFields = obIn.next().split("/");
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
                        System.out.println("Success");
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
            System.out.println("Please enter a plot type (Cabin/Site):");
            switch (obIn.next().toUpperCase()) {
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
        System.out.println("Success");
        //back to main
        bookingManagerScreen();
    }
    public static void BookingmemberScreen(){
        boolean bMemberCount = false;
        do {
            System.out.println("Please enter the amount of members staying on the plot (1-8):");
            int sVal = Integer.parseInt(obIn.next());
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
        System.out.println("Success");
        //back to main
        bookingManagerScreen();
    }
    public static void BookingplotidScreen(){
        boolean bPlotID = false;
        do {
            System.out.println("Please enter the PlotID:");
            int nVal = Integer.parseInt(obIn.next());
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
        System.out.println("Success");
        //back to main
        bookingManagerScreen();
    }


    public static void guestManagerScreen()
    {
        boolean back = false;
        do{
            System.out.println("Guest Manager: [1]Add Guest [2]Remove Guest [3]Edit Guest [4]Find Guest [5]Back:");
            switch (obIn.next()) {
                case "1":
                    ;
                    break;
                case "2":
                    ;
                    break;
                case "3":
                    System.out.println("Enter phone number to find guest to edit: ");
                    Guest guestToEdit = guestHelper.searchGuest(obIn.next());
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
            System.out.println("Plot Manager: [1]Modify Plot [2]Back:");
            switch (obIn.next()) {
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
        switch (obIn.next()) {
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
        int nCabinNum=Integer.parseInt(obIn.next());
        Cabin obFound=plotHelper.searchCabin(nCabinNum); //Needs to be changed to Cabin
        if(obFound!=null)
        {
            System.out.println("Cabin Found!");
            System.out.println("Please select an attribute to be changed: [1]Cabin Number  [2]Occupancy  [3]Type  [4]Price  [5]Under Renovation? [6]Booked? [7]Back");

            switch(obIn.next())
            {
                case "1":
                    System.out.println("Please enter a new cabin number: ");
                    obFound.setPlotID(Integer.parseInt(obIn.next()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "2":
                    System.out.println("Please enter a new occupancy value: ");
                    obFound.setOccupancy(Integer.parseInt(obIn.next()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "3":
                    System.out.println("Select: [B]asic [D]eluxe");
                    switch (obIn.next().toUpperCase())
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
                    break;
                case "4":
                    System.out.println("Please enter a new price:");
                    obFound.setPrice(Double.parseDouble(obIn.next()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "5":
                    System.out.println("Is the cabin under renovation? [Y/N]");
                    switch(obIn.next().toUpperCase())
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
                    switch(obIn.next().toUpperCase())
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
        int nSiteNum=Integer.parseInt(obIn.next());
        Site obFound=plotHelper.searchSite(nSiteNum);
        if(obFound!=null)
        {
            System.out.println("Site Found!");
            System.out.println("Please select an attribute to be changed: [1]Site Number  [2]Occupancy  [3]Type  [4]Price  " +
                    "[5]Under Renovation? [6]Booked? [7]Serviced? [8]Back");

            switch(obIn.next())
            {
                case "1":
                    System.out.println("Please enter a new site number: ");
                    obFound.setPlotID(Integer.parseInt(obIn.next()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "2":
                    System.out.println("Please enter a new occupancy value: ");
                    obFound.setOccupancy(Integer.parseInt(obIn.next()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "3":
                    System.out.println("Select: [G]roup [I]ndividual");
                    switch (obIn.next().toUpperCase())
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
                    break;
                case "4":
                    System.out.println("Please enter a new price:");
                    obFound.setPrice(Double.parseDouble(obIn.next()));
                    //print toString to display changes
                    System.out.println("Changes made:");
                    System.out.println(obFound.toString());
                    break;
                case "5":
                    System.out.println("Is the site under renovation? [Y/N]");
                    switch(obIn.next().toUpperCase())
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
                    switch(obIn.next().toUpperCase())
                    {
                        case "Y":
                            //print toString to display changes
                            obFound.setBooked(true);
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        case "N":
                            //print toString to display changes
                            obFound.setBooked(false);
                            System.out.println("Changes made:");
                            System.out.println(obFound.toString());
                            break;
                        default:
                            System.out.println("Invalid option, please try again");
                            modifySiteScreen();
                    }
                    break;
                case "7":
                    System.out.println("Is the site serviced? [Y/N]");
                    switch(obIn.next().toUpperCase())
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
            System.out.println("No Site found with that number. Try again");
            modifySiteScreen();
        }
    }


    public static void ownerManagerScreen()
    {
        boolean back = false;
        do{
            System.out.println("Owner Manager: [1]Add Owner [2]Remove Owner [3]Modify Owner [4]Find Owner [5]Back:");
            switch (obIn.next()) {
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
            String userID = obIn.next();

            currUser = validateId(userID);

            if(currUser == null)
            {
                System.out.println("UserID not found");
            }
        }
        while (!pass)
        {
            System.out.println("Enter your password");
            String userPass = obIn.next();
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
            String sPass = obIn.next();
            System.out.println("Enter new password again to confirm");
            String sConfirm = obIn.next();
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

    public int refundConfirmInt(Booking searchbooking2, Date newEnddate, double price, TimeUnit timeUnit) {

        Date newDate = newEnddate;
        System.out.println(searchbooking2.getBookingID());
        Date date4 = searchbooking2.getEndDate();
        Date date5 = searchbooking2.getStartDate();


        long diffInMillies1 = Math.abs(newDate.getTime() - date5.getTime());

        long diff1 = timeUnit.convert(diffInMillies1, TimeUnit.MILLISECONDS);

        long diffInMillies2 = Math.abs(date4.getTime() - date5.getTime());

        long diff2 = timeUnit.convert(diffInMillies2, TimeUnit.MILLISECONDS);


        double dif1 = (double) Math.ceil(diff1/30.00);
        double dif2 = (double) Math.ceil(diff2/30.00);
        System.out.println(dif1 + "days1");
        System.out.println(dif2 + "days2");

        double pricething = (dif2 * price);
        double ratething2 =   (dif1 / dif2);

        double ratething3 = (pricething * ratething2);

        System.out.println("got refund");
        return (int) ratething3;
    }

    public static AccommodationHelper getAccommodationHelper()
    {
        return plotHelper;
    }
}


