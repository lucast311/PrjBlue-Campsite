import campground_data.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class story2otest { //need date serializable??????
    //old will work on it
    //tests bad and should use console

    static Guest guest1 = new Guest("Jo", "wow", "jowow@gmail.com", "3069999999", PaymentType.Credit, "44567777", new Address());
    static Guest guest2 = new Guest("greg", "pop", "gregpop@gmail.com", "3068888888", PaymentType.Cash, "44565555", new Address());
    static Booking booking1 = new Booking(2,1, new Date(2020,5,19), new Date(2020,5,27), BookingType.Cabin, 3);
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
    public void testChangeBookingDate() //helps story 2o
    {
        Date startDate= new Date(2020,7,20);
        Date endDate= new Date(2020,7,25);

        Booking obBooking=new Booking(2,1,startDate, endDate, BookingType.Cabin,4);
        Date endDate2 = new Date(2020, 7,24 );
        Date startDate2 = new Date(2020, 7,21 );

        obBooking.changeEnd(endDate2);
        obBooking.changeStart(startDate2);

        assertEquals(endDate2, obBooking.getEndDate());
        assertEquals(startDate2, obBooking.getStartDate());

    }


    @Test
    public void testrefundresultget(){

        BusinessManager businessManager = new BusinessManager();
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(acc1);
        Accommodation priceacc;
         priceacc = plothelper.searchAccommodation(obBooking.getAccommodationID());
        double price = priceacc.getPrice();
        //int result = businessManager.refundConfirmInt2(obBooking, endDate2, price);
        int result = businessManager.refundConfirmInt3(obBooking,endDate2, price, TimeUnit.DAYS);

        //0.39 of accommodation price of acc1 = 156
        assertTrue(((int) 156) == result);
        assertFalse(((int) 157) == result);

    }

    @Test
    public void testsearchplotid(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(acc1);

        BookingHelper bookingHelper = new BookingHelper();
        AccommodationHelper accommodationHelper = new AccommodationHelper();

        assertEquals(acc1.getAccommodationID(), booking1.getAccommodationID());
        //assertEquals(plotHelper.);




    }


    @Test
    public void testrefundyespaid()
    {
        ArrayList<Booking> bookings = bookingHelper.getBookingList();
//        bookings.add(booking1);
//        bookings.add(booking2);
        BusinessManager businessManager = new BusinessManager();
    }

    @Test
    public void testrefundnopaid()
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        //bookings.add(booking1);
        //bookings.add(booking2);
        BusinessManager businessManager = new BusinessManager();

        //double result = booking1.getTotal();

        //assertEquals(businessManager.refundconfirm(), result);
    }

    @Test
    public void testchangedateValidation(){ //help
        //test when dates conflict with others with the same accommedation



    }




}
