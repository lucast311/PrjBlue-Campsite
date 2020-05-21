package campground_data;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
        return temp.get(0);
    }

    public boolean changeBookingDate(int bookingID, Date newStartDate, Date newEndDate) {
        Booking booking = findBooking(bookingID);
        if (findBooking(bookingID) == null) {
            System.out.println("The booking you want to change is not in the system. Please enter a valid booking ID.");
            return false;
        }

        if (booking.getStartDate().compareTo(newStartDate) >= 0) {
            System.out.println("Please enter a  start date that is not earlier than the previous start date.");
            return false;
        }

        if (booking.getEndDate().compareTo(newStartDate) <= 0) {
            System.out.println("Please enter a start date that is not after the end date.");
            return false;
        }

        if (booking.getStartDate().compareTo(newEndDate) >= 0) {
            System.out.println("Please enter an end date not earlier than the start date.");
            return false;
        }


        booking.changeStart(newStartDate);
        booking.changeEnd(newEndDate);
        System.out.println("The booking dates have been changed.");
        return true;
    }


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
            for(Booking bookingitem : bookings)
            {
                if(bookingitem.getBookingID() == bookingID)
                {
                    bookingToReturn = bookingitem;
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


    public Booking search(String guestID)
    {
        for(int i=0;i < getBookingList().size(); i++) {
            if ((getBookingList().get(i).getGuestID()) == (Integer.parseInt(guestID))) {
                return getBookingList().get(i);

            }
        }
        return null;
    }


}
