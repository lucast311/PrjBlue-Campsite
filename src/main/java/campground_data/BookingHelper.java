package campground_data;

import java.util.ArrayList;
import java.util.Date;

public class BookingHelper {

    private ArrayList<Booking> bookings;

    public BookingHelper() {
        this.bookings = new ArrayList<>();
    }

    public boolean addBooking(Booking booking) {
        return this.bookings.add(booking);
    }

    public boolean removeBooking(Booking booking)
    {


        return this.bookings.remove(booking);
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
        return null;
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






}
