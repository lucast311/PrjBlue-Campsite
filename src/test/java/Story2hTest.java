
import static org.junit.Assert.*;

import java.util.ArrayList;
import campground_data.Booking;
import campground_data.BookingHelper;
import campground_ui.ViewBookingWindow;
import javafx.application.Application;
import org.junit.Test;

public class Story2hTest
{
   public BookingHelper bookingHelper=new BookingHelper();
   public ArrayList<Booking> obList=bookingHelper.getBookingList();

   @Test
   public void testCreateViewBookingWindow()
   {
       //Running this test should launch the ViewBookingWindow class.
       //No way to test for anything other than if it launches or not
       Application.launch(ViewBookingWindow.class);
   }

   @Test
   public void testViewAll()
   {
        ArrayList<Booking> allBookings=obList;
        assertEquals(allBookings,bookingHelper.getBookingList());

   }

    @Test
    public void testViewCurrent()
    {

    }

    @Test
    public void testViewCabinsOnly()
    {

    }

    @Test
    public void testViewSitesOnly()
    {

    }

    @Test
    public void testGetBookingListByMonth()
    {

    }

    @Test
    public void testGetBookingListByYear()
    {

    }

    @Test
    public void testGetBookingListByMonthAndYear()
    {

    }
}
