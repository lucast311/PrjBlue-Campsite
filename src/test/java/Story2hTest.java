
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
   public BookingHelper bookingHelper=new BookingHelper(); //initializes a BookingHelper to access methods with
   public ArrayList<Booking> obList=bookingHelper.getBookingList(); //initializes an arrayList of all Bookings

    /**
     *This test's sole purpose is to run and open up the ViewBookingWindow.
     *Test passes if the window opens
     */
   @Test
   public void testCreateViewBookingWindow()
   {
       //Running this test should launch the ViewBookingWindow class.
       //No way to test for anything other than if it launches or not
       Application.launch(ViewBookingWindow.class);
   }

    /**
     *This test checks whether or not all Bookings in the booking database is returned from getBookingList and prints out
     *Passes if both arrayLists are the same
     */
   @Test
   public void testViewAll()
   {
        ArrayList<Booking> allBookings=obList;
       for(Booking obVal: obList)
       {
           if(obVal==null)
           {
               continue;
           }
           System.out.println(obVal.toString()); //un-comment to have list print on console
       }
        assertEquals(allBookings,bookingHelper.getBookingList());
   }

    /**
     *Checks whether getCurrentBookings returns a arrayList of Bookings that have a start date of today onwards
     *Passes if ArrayList of Bookings have a start date of today onwards.
     */
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

    /**
     *Checks whether or not the getCabinOnly method returns an arrayList of Bookings where the type of booking is only Cabin
     *Has an additional declaration that allows an ArrayList to be passed in to be checked on, this method functions the exact same
     *as the one being tested here, so if this test passes, the other method will pass the same test too
     */
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
        assertEquals(bookingTemp,bookingHelper.getCabinOnly(obList));
    }

    /**
     *Checks whether or not the getSiteOnly method returns an arrayList of Bookings where the type of booking is only Site
     * Has an additional declaration that allows an ArrayList to be passed in to be checked on, this method functions the exact same
     * as the one being tested here, so if this test passes, the other method will pass the same test too
     */
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
        assertEquals(bookingTemp,bookingHelper.getSiteOnly(obList));
    }

    /**
     *Checks if the getBookingListByMonth method with a month specified returns an arrayList of Bookings
     *that have a start date during that month
     */
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

    /**
     *Checks if the getBookingListByYear method with a year specified returns an arrayList of Bookings
     *that have a start date during that year
     */
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

    /**
     *Checks if the getBookingList method with a year and month specified returns an arrayList of Bookings
     * that have a start date during that month and year
     */
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
