
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import campground_data.Booking;
import campground_data.BookingHelper;
import campground_data.BookingType;
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
       for(Booking obVal: obList)
       {
           //System.out.println(obVal); //un-comment to have list print on console
       }
        assertEquals(allBookings,bookingHelper.getBookingList());
   }

    @Test
    public void testViewCurrent()
    {
        ArrayList<Booking> obTemp=new ArrayList<>();
        Date today=new Date();
        for(Booking obVal: obList)
        {
            if(obVal.getStartDate().after(today))
            {
                obTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        assertEquals(obTemp,bookingHelper.getCurrentBookings());
    }

    @Test
    public void testViewCabinsOnly()
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: obList)
        {
            if(obVal.getType()==BookingType.Cabin)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }

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
