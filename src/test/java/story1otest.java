import campground_data.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class story1otest { //need date serializable??????
    //old will work on it
    //tests bad and should use console

    static Guest guest1 = new Guest("Jo", "wow", "jowow@gmail.com", "3069999999", PaymentType.Credit, "44567777", new Address());
    static Guest guest2 = new Guest("greg", "pop", "gregpop@gmail.com", "3068888888", PaymentType.Cash, "44565555", new Address());
    static Booking booking1 = new Booking(1,30699999, new Date(2020,5,19), new Date(2020,5,27), BookingType.Cabin, 3);
    static Booking booking2 = new Booking(2,30688888, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);
    //static Plot Plot1 = new Plot(1, 4, 300, false, true);
    //Site(int siteNum, boolean serviced, double price, SiteType type, boolean underReno, int occupancy) {
    static Site Site1 = new Site(1, true, 300, Site.SiteType.Group, false, 8);


    @Test
    public void testsearchbooking(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        BookingHelper bookingHelper = new BookingHelper(); //is this ok?

        //bookingHelper.search("0000002");

        assertEquals(bookingHelper.searchGuestID("0000001"), booking1);
        assertEquals(bookingHelper.searchGuestID("0000002"), booking2);
        assertNull(bookingHelper.search("-1"));
        assertEquals(bookingHelper.searchBookingId(1), booking1);
    }

    @Test
    public void testsearchplotid(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(Site1);

        BookingHelper bookingHelper = new BookingHelper();
        PlotHelper plotHelper = new PlotHelper();

        assertEquals(bookingHelper.getPlotID(), booking1.getPlotID());
        //assertEquals(plotHelper.);



    }


    @Test
    public void testcancelwithoutrefund()
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move back to current
        //assertEquals(businessManager.cancelConfirm("yes"), );


    }


    @Test
    public void testcancelwithrefund()
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move to next thing
        //assertEquals(businessManager.cancelConfirm("yes"), );

    }

    @Test
    public void testrefundyes()
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager(); //is this ok?
        Date date1 = new Date();
        Date date2 = booking1.getEndDate();
        int price;
        price = (int) PlotHelper.searchPlot(booking1.getPlotID()).getPrice();
        long startTime2 = date1.getTime();
        long endTime2 = date2.getTime();
        long diffTime2 = endTime2 - startTime2;
        long diffDays2 = diffTime2 / (1000 * 60 * 60 * 24);

        int ratething = (int) diffDays2;
        ratething = price / ratething;



        //assertEquals(businessManager.refundconfirm("yes"), ratething);
    }

    @Test
    public void testrefundyespaid()
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        BusinessManager businessManager = new BusinessManager(); //is this ok?
        Date date1 = new Date();
        Date date2 = booking1.getEndDate();
        int price;
        price = (int) PlotHelper.searchPlot(booking1.getPlotID()).getPrice();
        long startTime2 = date1.getTime();
        long endTime2 = date2.getTime();
        long diffTime2 = endTime2 - startTime2;
        long diffDays2 = diffTime2 / (1000 * 60 * 60 * 24);

        int ratething = (int) diffDays2;
        ratething = price / ratething;



        //assertEquals(businessManager.refundconfirm("yes"), ratething);
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

    @Test
    public void testvalidrefundconfirm()
    {

        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move to next thing
        //assertEquals(businessManager.cancelConfirm("yes"), );
        //assertEquals(businessManager.cancelConfirm("no"), );

        //assertNull(businessManager.cancelConfirm("nsaduasgdfyfjsd"));
    }

    @Test
    public void testvalidcancelconfirm()
    {
        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move back to current
        //assertEquals(businessManager.cancelConfirm("yes"), );
        //assertEquals(businessManager.cancelConfirm("no"), );

        //assertNull(businessManager.cancelConfirm("nsaduasgdfyfjsd"));


    }

}
