import campground_data.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class story2otest { //need date serializable??????
    //old will work on it
    //tests bad and should use console

    static Guest guest1 = new Guest("Jo", "wow", "jowow@gmail.com", "3069999999", PaymentType.Credit, "44567777", new Address());
    static Guest guest2 = new Guest("greg", "pop", "gregpop@gmail.com", "3068888888", PaymentType.Cash, "44565555", new Address());
    static Booking booking1 = new Booking(100,1, new Date(2020,5,19), new Date(2020,5,27), BookingType.Cabin, 3);
    static Booking booking2 = new Booking(200,2, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);
    BookingHelper bookingHelper=new BookingHelper();
    AccommodationHelper plothelper = new AccommodationHelper();

   static Date startDate= new Date(2020,7,20);
   static Date endDate= new Date(2020,7,25);

    static Booking obBooking=new Booking(2,1,startDate, endDate, BookingType.Cabin,4);
    static Date endDate2 = new Date(2020, 7,23 );
    static Date startDate2 = new Date(2020, 7,21 );
    static Accommodation acc1 = new Accommodation(2, 4, 400, false, true);

    @Test
    public void testrefundresultget(){

        BusinessManager businessManager = new BusinessManager();
        int result = businessManager.refundConfirmInt(obBooking, endDate2);


        //0.4 of accommodation price of acc1 = 160
        assertTrue(((int) 160) == result);
        assertFalse(((int) 161) == result);

    }

    @Test
    public void testsearchplotid(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        //plots.add(Site1);

        BookingHelper bookingHelper = new BookingHelper();
        AccommodationHelper accommodationHelper = new AccommodationHelper();

        //assertEquals(bookingHelper.getPlotID(), booking1.getPlotID());
        //assertEquals(plotHelper.);




    }


    @Test
    public void testrefundyes() //work on
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        Site site1 = new Site(100, 4,32.00, Site.SiteType.Individual, true, false);

        bookings.add(booking1);
        bookings.add(booking2);



        //assertEquals(businessManager.refundconfirm("yes"), ratething);
    }

    @Test
    public void testrefundyespaid()
    {
        ArrayList<Booking> bookings = bookingHelper.getBookingList();
//        bookings.add(booking1);
//        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager(); //is this ok?




    }

    @Test
    public void testrefundno()
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        BusinessManager businessManager = new BusinessManager();

        double result = booking1.getTotal();

        //assertEquals(businessManager.refundconfirm(), result);
    }




}
