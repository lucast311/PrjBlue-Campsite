package campground_data;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BookingHelper extends Booking{

    private ArrayList<Booking> bookings;
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

    public boolean changeBookingDate(int bookingID, Date startDate, Date endDate)
    {
        return false;
    }

    public ArrayList<Booking> getBookingList()
    {
        return this.bookings;
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

    public static Booking search(String guestID)
    {
        for(int i=0;i < bookings.size(); i++) {
            if (bookings.get(i).getGuestID().equals(guestID)) {
                return bookings.get(i);
            }
        }
        return null;
    }


}
