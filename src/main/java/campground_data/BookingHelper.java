package campground_data;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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


    public ArrayList<Booking> getBookingList()
    {
        return this.bookings;
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
        for(int i=0;i < bookings.size(); i++)
        {
            if (bookings.get(i).getGuestID() == guestID) {
                return bookings.get(i);
            }
        }
        return null;
    }


}
