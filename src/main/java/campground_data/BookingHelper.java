package campground_data;

import java.util.ArrayList;
import java.util.Date;

public class BookingHelper {

    private ArrayList<Booking> bookings;
    private DatabaseFile DBFile;

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
        return this.bookings.remove(booking);
    }

//    public boolean changeBookingDate(int bookingID, Date startDate, Date endDate)
//    {
//    }

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

//    public Booking search(String guestID)
//    {
//
//    }





}
