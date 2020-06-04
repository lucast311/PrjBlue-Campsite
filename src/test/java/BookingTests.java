
import static org.junit.Assert.*;

import campground_data.BookingHelper;
import campground_data.BookingType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import campground_data.Booking;
import org.junit.Test;

public class BookingTests 
{
	//BookingHelper Tests
	
	@Test
	public void testCreateBookingHelper() 
	{
		//this creates the list upon creation by reading from the bookings.obj file using DatabaseFile
		
		
	}
	
	@Test
	public void testAddBooking()
	{
		
	}
	
	@Test
	public void testRemoveBooking()
	{
//		BookingHelper helper = new BookingHelper();
//		ArrayList<Booking> obBookingList = new ArrayList<>();
//		Booking booking1 = new Booking(1,1, new Date(2020,5,25), new Date(2020,5,27), BookingType.Cabin, 3);
//		Booking booking2 = new Booking(2,1, new Date(2020,6,4), new Date(2020,6,7), BookingType.Site, 2);
//
//		helper.addBooking(booking1);
//		helper.addBooking(booking2);
//
//		helper.removeBooking(booking1);
//
//		assertFalse(obBookingList.contains(booking1));

	}
	
	@Test
	public void testChangeBookingDate()
	{
		
	}
	
	@Test
	public void testGetBookingListBlank()
	{
		BookingHelper helper=new BookingHelper();
		ArrayList<Booking> obBookingList;
		Date startDate= new Date(2020,7,20);
		Date endDate= new Date(2020,7,25);
		Booking obNew=new Booking(2,1,startDate,
				endDate, BookingType.Cabin,4);

		helper.addBooking(obNew);
		obBookingList=helper.getBookingList();

		assertEquals(obBookingList.get(obBookingList.size()-1),obNew);

	}
	
	@Test
	public void testGetBookingListByYear()
	{
		BookingHelper helper=new BookingHelper();
		ArrayList<Booking> obBookingList;
		Date startDate2020= new Date(2020,7,20);
		Date endDate2020= new Date(2020,7,25);
		Date startDate2019= new Date(2019,7,20);
		Date endDate2019= new Date(2019,7,25);

		Booking obNew2020=new Booking(2,1,startDate2020,
				endDate2020, BookingType.Cabin,4);

		Booking obNew2019=new Booking(2,1,startDate2019,
				endDate2019, BookingType.Cabin,4);

		helper.addBooking(obNew2019);
		helper.addBooking(obNew2020);
		obBookingList=helper.getBookingListByYear(2019);

		assertEquals(obBookingList.contains(obNew2019),true);
	}
	
	@Test
	public void testSearch()
	{
		
	}
	
	//Booking Tests
	
	@Test
	public void testCreateBookingWithData()
	{
		Date startDate= new Date(2020,7,20);
		Date endDate= new Date(2020,7,25);
		Booking obBooking=new Booking(2,1,startDate,
				endDate, BookingType.Cabin,4);

		assertEquals(2,obBooking.getPlotID());
		assertEquals(1,obBooking.getGuestID());
		assertEquals(startDate,obBooking.getStartDate());
		assertEquals(endDate,obBooking.getEndDate());
		assertEquals(BookingType.Cabin,obBooking.getType());
		assertEquals(4,obBooking.getMemberCount());
	}
	
	@Test
	public void testCreateBookingBlank()
	{
		Booking obBooking=new Booking();

		assertEquals(0,obBooking.getPlotID());
		assertEquals(0,obBooking.getGuestID());
		assertEquals(new Date(),obBooking.getStartDate());
		assertEquals(new Date(),obBooking.getEndDate());
		assertNull(null,obBooking.getType());
		assertEquals(1,obBooking.getMemberCount());
	}
	
	@Test
	public void testGetters()
	{
		Date startDate= new Date(2020,7,20);
		Date endDate= new Date(2020,7,25);
		Booking obBooking=new Booking(2,1,startDate,endDate, BookingType.Cabin,4);

		//Actually passes, when bookings are made there is no way to check which booking ID is given to "obBooking"
//		assertEquals(2,obBooking.getBookingID());
		assertEquals(2,obBooking.getPlotID());
		assertEquals(1,obBooking.getGuestID());
		assertEquals(startDate,obBooking.getStartDate());
		assertEquals(endDate,obBooking.getEndDate());
		assertEquals(BookingType.Cabin,obBooking.getType());
		assertEquals(4,obBooking.getMemberCount());
		assertEquals(false,obBooking.getPaid());
		assertEquals(0,obBooking.getTotal(),0.001);
		assertEquals(0,obBooking.getDiscount(),0.001);


	}
	
	@Test
	public void testSetters()
	{
		Date startDate= new Date(2020,7,20);
		Date endDate= new Date(2020,7,25);
		Booking obBooking=new Booking(2,1,startDate,
				endDate, BookingType.Cabin,4);

		//valid values
		obBooking.setType(BookingType.Site);
		obBooking.setPlotID(2);
		obBooking.setPaid(true);
		obBooking.setDiscount(50);
		obBooking.setMemberCount(6);
		obBooking.setTotal(100.0);

		assertEquals(2,obBooking.getPlotID());
		assertEquals(BookingType.Site,obBooking.getType());
		assertEquals(6,obBooking.getMemberCount());
		assertEquals(true,obBooking.getPaid());
		assertEquals(100,obBooking.getTotal(),0.001);
		assertEquals(50,obBooking.getDiscount(),0.001);


		obBooking.setType(BookingType.Cabin);
		obBooking.setPaid(false);
		//invalid values
		obBooking.setPlotID(0);
		obBooking.setDiscount(-1);
		obBooking.setMemberCount(0);
		obBooking.setTotal(-1);

		assertEquals(2,obBooking.getPlotID());
		assertEquals(BookingType.Cabin,obBooking.getType());
		assertEquals(6,obBooking.getMemberCount());
		assertEquals(false,obBooking.getPaid());
		assertEquals(100,obBooking.getTotal(),0.001);
		assertEquals(50,obBooking.getDiscount(),0.001);
	}
	
	@Test
	public void testChangers()
	{
		Date startDate= new Date(2020,7,20);
		Date endDate= new Date(2020,7,25);

		Date newStart= new Date(2020,6,15);
		Date newEnd= new Date(2020,8,10);

		Date failStart=new Date(2019,4,3);
		Date failEnd=new Date(2020,5,19);

		Booking obBooking=new Booking(2,2,startDate, endDate, BookingType.Cabin,4);

		//Proper valid values tested
		obBooking.changeStart(newStart);
		obBooking.changeEnd(newEnd);
		assertEquals(newStart,obBooking.getStartDate());
		assertEquals(newEnd,obBooking.getEndDate());

		//Fail conditions tested.
		//Note: will print 2 error messages to the console, and dates will not be changed from newStart and newEnd if working correctly
		obBooking.changeStart(failStart);
		obBooking.changeEnd(failEnd);
		assertEquals(newStart,obBooking.getStartDate());
		assertEquals(newEnd,obBooking.getEndDate());
	}
	

}
