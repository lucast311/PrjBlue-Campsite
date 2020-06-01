package campground_data;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BookingHelper {

    private ArrayList<Booking> bookings;

    private static boolean found = false;

    private DatabaseFile DBFile;
    private Booking searchbooking;


    public BookingHelper() {
        DBFile=new DatabaseFile();
        this.bookings = DBFile.readBookings();
    }

    public boolean addBooking(Booking booking) {
        this.bookings.add(booking);
        DBFile.saveRecords(bookings);
        return this.bookings.contains(booking);
    }

    public boolean removeBooking(Booking booking)
    {
        boolean bRemoved = false;

        Iterator<Booking> it = bookings.iterator();
        while (it.hasNext())
        {
            Booking args = it.next();
            if (args.getBookingID() == booking.getBookingID())
            {
                it.remove();
                DBFile.saveRecords(bookings);
                bRemoved = true;
            }
        }

        return bRemoved;

    }

    public Booking findBooking(int id)
    {
        ArrayList<Booking> temp = new ArrayList<>();
        for (Booking obBooking : bookings)
        {
            if (obBooking.getBookingID() == id)
            {
                temp.add(obBooking);
            }
        }
        if(temp.isEmpty())
        {
            return null;
        }
        else
        {
           return temp.get(0);
        }
    }

//    public boolean findBooking(int id)
//    {
//        for (Booking obBooking : bookings)
//        {
//            if (obBooking.getBookingID() == id)
//            {
//                return true;
//
//            }
//        }
//        return false;
//    }

    public boolean changeBookingDate(int bookingID, Date newStartDate, Date newEndDate) {
        //Booking booking = findBooking(bookingID);
        if (findBooking(bookingID) == null) {
            System.out.println("The booking you want to change is not in the system. Please enter a valid booking ID.");
            return false;
        }

        Booking booking = searchBookingId(bookingID);

        if (booking.getStartDate().compareTo(newStartDate) > 0) {
            System.out.println("Please enter a  start date that is not earlier than the previous start date.");
            return false;
        }

        if (booking.getEndDate().compareTo(newStartDate) < 0) {
            System.out.println("Please enter a start date that is not after the end date.");
            return false;
        }

        if (booking.getStartDate().compareTo(newEndDate) > 0) {
            System.out.println("Please enter an end date not earlier than the start date.");
            return false;
        }


       // updateBookingDate(bookings, bookingID, newStartDate, newEndDate, "src/main/java/database/bookings.obj");
        System.out.println("The booking dates have been changed.");
        return true;
    }

    //validation and setting for booking date changes
   /* public void updateBookingDate(ArrayList<Booking> bookings, int bookingID, Date newStartDate, Date newEndDate, String sFile) //help
    {
        List<Booking> obBookingList = bookings.stream()
                .map(x -> {
                    if (x.getBookingID() == bookingID)
                    {

                        List<Booking> obBookingList2 = bookings.stream()
                                .map(y -> {
                                    if(y.getAccommodationID() == x.getAccommodationID()){ //for validation
                                        Date date4 = newEndDate;
                                        Date date5 = y.getStartDate();
                                        long startTime3 = date5.getTime(); // diff old start and old end
                                        long endTime3 = date4.getTime();
                                        long diffTime3 = endTime3 - startTime3;
                                        long diffDays3 = diffTime3 / (1000 * 60 * 60 * 24);
                                        Date date6 = newStartDate;
                                        Date date7 = y.getEndDate();
                                        long startTime4 = date6.getTime(); // diff old start and old end
                                        long endTime4 = date7.getTime();
                                        long diffTime4 = endTime4 - startTime4;
                                        long diffDays4 = diffTime4 / (1000 * 60 * 60 * 24);
                                        if(diffDays3 > 0 && diffDays4 < 0){
                                            //error cause its over a startdate or enddate of a booking with the same accommodationID
                                        }else{
                                            return new Booking(x.getAccommodationID(), x.getGuestID(),newStartDate,newEndDate,x.getType(), x.getMemberCount());
                                        }
                                    }

                                    return x;
                               }).collect(Collector.toList()); //help

                        try
                        {
                            FileOutputStream fileOut = new FileOutputStream(sFile);
                            ObjectOutputStream obOut = new ObjectOutputStream(fileOut);
                            for (Booking booking : obBookingList2)
                            {
                                obOut.writeObject(booking);
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }



                    }
                    return null; //what
                })
                .collect(Collectors.toList());

        try
        {
            FileOutputStream fileOut = new FileOutputStream(sFile);
            ObjectOutputStream obOut = new ObjectOutputStream(fileOut);
            for (Booking booking : obBookingList)
            {
                    obOut.writeObject(booking);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    */


    public ArrayList<Booking> getBookingList() //help
    {
        ArrayList<Booking> BookingTemp= this.bookings;
        return BookingTemp;
    }


    public ArrayList<Booking> getBookingList(int year)
    {
        ArrayList<Booking> BookingTemp=new ArrayList<>();
        for(Booking obVal:bookings)
        {
            if(obVal.getStartDate().getYear()==year)
            {
                BookingTemp.add(obVal);
            }
        }
        return BookingTemp;
    }

    public Booking searchGuestID(int guestID)
    {
        Booking bookingToReturn = null;
        int guestIDtemp=guestID;
        Scanner obIn=new Scanner(System.in);

        if(guestID < 1)
        {
            System.out.println("Guest ID cannot be less than the number 1. Please enter a valid Booking ID");
            //Insert routine to ask user again for Booking ID
            System.out.println("Re-enter the guest ID: ");
            guestIDtemp=obIn.nextInt();
            searchGuestID(guestIDtemp);
        }
        else
        {
            for(Booking bookingitem : bookings)
            {
                if(bookingitem.getGuestID() == guestID)
                {
                    bookingToReturn = bookingitem;
                }
            }
        }
        return bookingToReturn;
    }

    public Booking searchBookingId(int bookingID)
    {
        Booking bookingToReturn = null;

        for(Booking booking : bookings)
        {
            if(booking.getBookingID() == bookingID)
            {
                    bookingToReturn = booking;
            }
        }
        return bookingToReturn;
    }

    public boolean confirmRemove(String sConfirm)
    {
        if(sConfirm.trim().equals("y"))
        {
            return true;
        }
        else if (sConfirm.trim().equals("n"))
        {
            return false;
        }
        else
        {
            System.out.println("Invalid response. Please answer with either \"yes\" or \"no\"");
            return false;
        }
    }


    public Booking search(int guestID)
    {
        for(Booking booking : bookings)
        {
            if(booking.getGuestID() == guestID)
            {
                return booking;
            }
        }

//        for(int i=0;i < getBookingList().size(); i++) {
//            if ((getBookingList().get(i).getGuestID()) == guestID) {
//                return getBookingList().get(i);
//
//            }
//        }
        return null;
    }


}
