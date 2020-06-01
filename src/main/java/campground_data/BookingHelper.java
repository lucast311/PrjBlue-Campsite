package campground_data;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
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

    public boolean removeBooking(Booking booking) //seems to be creating null pointers with previous implementation
    {
        boolean bRemoved = false;

        if(booking!=null)
        {
            this.bookings.remove(booking);
            bRemoved=true;
            DBFile.saveRecords(this.bookings);
        }

//        Iterator<Booking> it = bookings.iterator();
//        while (it.hasNext())
//        {
//            Booking args = it.next();
//            if (args.getBookingID() == booking.getBookingID())
//            {
//                it.remove();
//                DBFile.saveRecords(bookings);
//                bRemoved = true;
//            }
//        }

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


        updateBookingDate(bookings, bookingID, newStartDate, newEndDate, "src/main/java/database/bookings.obj");
        System.out.println("The booking dates have been changed.");
        return true;
    }

    public void updateBookingDate(ArrayList<Booking> bookings, int bookingID, Date newStartDate, Date newEndDate, String sFile) //Why does this exist? This functionality already exists
    {
        List<Booking> obBookingList = bookings.stream()
                .map(x -> {
                    if (x.getBookingID() == bookingID)
                    {
                        return new Booking(x.getPlotID(), x.getGuestID(),newStartDate,newEndDate,x.getType(), x.getMemberCount());
                    }
                    return x;
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

    public ArrayList<Booking> getCurrentBookings()
    {
        ArrayList<Booking> obReturn=new ArrayList<>();
        Date today=new Date();
        for(Booking obVal: bookings)
        {
            if(obVal.getStartDate().after(today))
            {
                obReturn.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        return obReturn;
    }

    public ArrayList<Booking> getBookingList()
    {
        return this.bookings;
    }


    public ArrayList<Booking> getBookingListByYear(int year)
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

    public ArrayList<Booking> getBookingListByMonth(int month)
    {
        ArrayList<Booking> BookingTemp=new ArrayList<>();
        for(Booking obVal:bookings)
        {
            if(obVal.getStartDate().getMonth()==month)
            {
                BookingTemp.add(obVal);
            }
        }
        return BookingTemp;
    }

    public ArrayList<Booking> getBookingList(int year,int month)
    {
        ArrayList<Booking> BookingTemp=new ArrayList<>();
        for(Booking obVal:bookings)
        {
            if(obVal.getStartDate().getYear()==year)
            {
                if(obVal.getStartDate().getMonth()==month)
                {
                    BookingTemp.add(obVal);
                }
            }
        }
        return BookingTemp;
    }

    public ArrayList<Booking> getCabinOnly(ArrayList<Booking> bookingList)
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: bookingList)
        {
            if(obVal.getType()==BookingType.Cabin)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        return bookingTemp;
    }

    public ArrayList<Booking> getCabinOnly()
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: this.bookings)
        {
            if(obVal.getType()==BookingType.Cabin)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        return bookingTemp;
    }

    public ArrayList<Booking> getSiteOnly(ArrayList<Booking> bookingList)
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: bookingList)
        {
            if(obVal.getType()==BookingType.Site)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        return bookingTemp;
    }

    public ArrayList<Booking> getSiteOnly()
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: this.bookings)
        {
            if(obVal.getType()==BookingType.Site)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        return bookingTemp;
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
