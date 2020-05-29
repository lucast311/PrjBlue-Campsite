
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
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
        assertEquals(bookingTemp,bookingHelper.getCabinOnly());
    }

    @Test
    public void testViewSitesOnly()
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: obList)
        {
            if(obVal.getType()==BookingType.Site)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        assertEquals(bookingTemp,bookingHelper.getSiteOnly());
    }

    @Test
    public void testGetBookingListByMonth()
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: obList)
        {
            if(obVal.getStartDate().getMonth()== Calendar.MAY)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        assertEquals(bookingTemp,bookingHelper.getBookingListByMonth(4));
    }

    @Test
    public void testGetBookingListByYear()
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: obList)
        {
            if(obVal.getStartDate().getYear()==2019)
            {
                bookingTemp.add(obVal);
                //System.out.println(obVal); //un-comment to have list print on console
            }
        }
        assertEquals(bookingTemp,bookingHelper.getBookingListByYear(2019));
    }

    @Test
    public void testGetBookingListByMonthAndYear()
    {
        ArrayList<Booking> bookingTemp=new ArrayList<>();
        for(Booking obVal: obList)
        {
            if(obVal.getStartDate().getYear()==2020)
            {
                if(obVal.getStartDate().getMonth()==Calendar.JUNE)
                {
                    bookingTemp.add(obVal);
                    //System.out.println(obVal); //un-comment to have list print on console
                }
            }
        }
        assertEquals(bookingTemp,bookingHelper.getBookingList(2020,5));
    }
}
