import campground_data.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class story1otest { //need date serializable??????
    //old will work on it
    //tests bad and should use console

    static Guest guest1 = new Guest("Jo", "wow", "jowow@gmail.com", "3069999999", PaymentType.Credit, "44567777", new Address());
    static Guest guest2 = new Guest("greg", "pop", "gregpop@gmail.com", "3068888888", PaymentType.Cash, "44565555", new Address());
    static Booking booking1 = new Booking(100,1, new Date(2020,5,19), new Date(2020,5,27), BookingType.Cabin, 3);
    static Booking booking2 = new Booking(200,2, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);
    BookingHelper bookingHelper=new BookingHelper();
    //PlotHelper plothelper = new PlotHelper();


    @Test
    public void testsearch(){
        ArrayList<Booking> bookings =bookingHelper.getBookingList();
        bookingHelper.addBooking(booking1);
        bookingHelper.addBooking(booking2);

        //bookingHelper.search("0000002");



//        assertEquals(bookingHelper.searchGuestID(1), booking1);
//        assertEquals(bookingHelper.searchGuestID(2), booking2);
        assertNull(bookingHelper.search(-1));
        assertEquals(bookingHelper.searchBookingId(booking1.getBookingID()), booking1);
    }

    @Test
    public void testsearchplotid(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        //ArrayList<Plot> plots = new ArrayList<>();
        //plots.add(Site1);

        //BookingHelper bookingHelper = new BookingHelper();
        //PlotHelper plotHelper = new PlotHelper();

        //assertEquals(bookingHelper.getPlotID(), booking1.getPlotID());
        //assertEquals(plotHelper.);




    }


    @Test
    public void testcancelwithoutrefund()
    {
//        ArrayList<Booking> bookings = new ArrayList<>();
//        bookings.add(booking1);
//        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move back to current
        //assertEquals(businessManager.cancelConfirm("yes"), );


    }


    @Test
    public void testcancelwithrefund() //workon
    {
//        ArrayList<Booking> bookings = new ArrayList<>();
//        bookings.add(booking1);
//        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move to next thing
        //assertEquals(businessManager.cancelConfirm("yes"), );

    }

    @Test
    public void testrefundyes() //work on
    {
        ArrayList<Booking> bookings = bookingHelper.getBookingList();
        Site site1 = new Site(100, 4,32.00, Site.SiteType.Individual, true, false);
        //plothelper.addPlot(site1);

        bookings.add(booking1);
        bookings.add(booking2);

        Date date1 = new Date();
        Date date2 = booking1.getEndDate();
        int price;
        //price = (int) plothelper.searchSite(booking1.getPlotID()).getPrice();
        long startTime2 = date1.getTime();
        long endTime2 = date2.getTime();
        long diffTime2 = endTime2 - startTime2;
        long diffDays2 = diffTime2 / (1000 * 60 * 60 * 24);

        int ratething = (int) diffDays2;
        //ratething = price / ratething;



        //assertEquals(businessManager.refundconfirm("yes"), ratething);
    }

    @Test
    public void testrefundyespaid()
    {
        ArrayList<Booking> bookings = bookingHelper.getBookingList();
//        bookings.add(booking1);
//        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager(); //is this ok?
        Date date1 = new Date();
        Date date2 = booking1.getEndDate();
        int price;
        //price = (int) PlotHelper.searchPlot(booking1.getPlotID()).getPrice();
        long startTime2 = date1.getTime();
        long endTime2 = date2.getTime();
        long diffTime2 = endTime2 - startTime2;
        long diffDays2 = diffTime2 / (1000 * 60 * 60 * 24);

        int ratething = (int) diffDays2;
        //ratething = price / ratething;



        //assertEquals(businessManager.refundconfirm("yes"), ratething);
    }

    @Test
    public void testrefundno()
    {
        ArrayList<Booking> bookings = bookingHelper.getBookingList();
        bookings.add(booking1);
        bookings.add(booking2);
        BusinessManager businessManager = new BusinessManager();

        double result = booking1.getTotal();

        //assertEquals(businessManager.refundconfirm(), result);
    }




}
