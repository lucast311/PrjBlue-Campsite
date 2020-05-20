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

        return null;
    }

    public boolean confirmRemove(String sConfirm)
    {
        return false;
    }






}
