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
    static Booking booking1 = new Booking(1,1, new Date(2020,5,19), new Date(2020,5,27), BookingType.Cabin, 3);
    static Booking booking2 = new Booking(2,2, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);


    @Test
    public void testsearch(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        BookingHelper bookingHelper = new BookingHelper(); //is this ok?
        //bookingHelper.search("0000002");

        assertEquals(bookingHelper.search(1), booking1);
        assertEquals(bookingHelper.search(1), booking2);
        assertNull(bookingHelper.search(-1));
    }


    @Test
    public void testcancelwithoutrefund()
    {
        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move back to current
        //assertEquals(businessManager.cancelConfirm("yes"), );


    }


    @Test
    public void testcancelwithrefund()
    {

        BusinessManager businessManager = new BusinessManager();

        //how do i test void
        //how to i move to next thing
        //assertEquals(businessManager.cancelConfirm("yes"), );

    }

    @Test
    public void testrefundyes()
    {

        BusinessManager businessManager = new BusinessManager(); //is this ok?
        Date date1 = new Date();
        Date date2 = booking1.getEndDate();
        int price;
        price = (int) BusinessManager.getPlotHelper().searchPlot(booking1.getPlotID()).getPrice();

        int ratething = (int) (date1.getDate() - date2.getDate());
        ratething = ratething * price;

        double result = booking1.getTotal() - ratething;


        assertEquals(businessManager.refundconfirm("yes"), result);
    }

    @Test
    public void testrefundno()
    {
        BusinessManager businessManager = new BusinessManager();

        double result = booking1.getTotal();

        assertEquals(businessManager.refundconfirm("no"), result);
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
