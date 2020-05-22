package campground_data;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BookingHelper extends Booking{

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
                bRemoved = true;
            }
        }

        return bRemoved;

    }


    public boolean changeBookingDate(int bookingID, Date newStartDate, Date newEndDate)
    {

        if (!findBooking(bookingID)) {
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

    public void updateBookingDate(ArrayList<Booking> bookings, int bookingID, Date newStartDate, Date newEndDate, String sFile)
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


    public ArrayList<Booking> getBookingList()
    {
        return this.bookings;
    }

    public boolean findBooking(int id)
    {

        for (Booking obBooking : bookings)
        {
            if (obBooking.getBookingID() == id)
            {
                return true;

            }
        }
        return false;
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

    public Booking searchGuestID(String guestID)
    {
        return null;
    }

    public Booking searchBookingId(int bookingID)
    {
        Booking bookingToReturn = null;


        if(bookingID < 1)
        {
            System.out.println("Booking ID cannot be less than the number 1. Please enter a valid Booking ID");
            //Insert routine to ask user again for Booking ID
        }
        else
        {
            for(Booking booking : bookings)
            {
                if(booking.getBookingID() == bookingID)
                {
                    bookingToReturn = booking;
                }
            }
        }
        return bookingToReturn;
    }

    public boolean confirmRemove(String sConfirm)
    {
        //Will update this once I figure out the regex to handle all edge cases
        if(sConfirm.trim().equals("yes"))
        {
            return true;
        }
        else if (sConfirm.trim().equals("no"))
        {
            return false;
        }
        else //when any other string is entered
        {
            System.out.println("Invalid response. Please answer with either \"yes\" or \"no\"");
            return false;
        }
    }

    public Booking search(int guestID)
    {
        for(int i=0;i < bookings.size(); i++)
        {
            if (bookings.get(i).getGuestID() == guestID) {
                return bookings.get(i);
            }
        }
        return null;
    }


}
