
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
		Booking obNew=new Booking(2,"TestGuestID",startDate,
				endDate, BookingType.Cabin,4);

		helper.addBooking(obNew);
		obBookingList=helper.getBookingList();

		assertEquals(obBookingList.get(0),obNew);

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

		Booking obNew2020=new Booking(2,"TestGuestID",startDate2020,
				endDate2020, BookingType.Cabin,4);

		Booking obNew2019=new Booking(2,"TestGuestID",startDate2019,
				endDate2019, BookingType.Cabin,4);

		helper.addBooking(obNew2019);
		helper.addBooking(obNew2020);
		obBookingList=helper.getBookingList(2019);

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
		Booking obBooking=new Booking(2,"TestGuestID",startDate,
				endDate, BookingType.Cabin,4);

		assertEquals(2,obBooking.getPlotID());
		assertEquals("TestGuestID",obBooking.getGuestID());
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
		assertEquals("",obBooking.getGuestID());
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
		Booking obBooking=new Booking(2,"TestGuestID",startDate,
				endDate, BookingType.Cabin,4);

		assertEquals(1,obBooking.getBookingID());
		assertEquals(2,obBooking.getPlotID());
		assertEquals("TestGuestID",obBooking.getGuestID());
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
		Booking obBooking=new Booking(2,"TestGuestID",startDate,
				endDate, BookingType.Cabin,4);

		obBooking.setType(BookingType.Site);
		obBooking.setPlotID(3);
		obBooking.setPaid(true);
		obBooking.setDiscount(50);
		obBooking.setMemberCount(6);
		obBooking.setTotal(150.0);

		assertEquals(3,obBooking.getPlotID());
		assertEquals(BookingType.Site,obBooking.getType());
		assertEquals(6,obBooking.getMemberCount());
		assertEquals(true,obBooking.getPaid());
		assertEquals(150,obBooking.getTotal(),0.001);
		assertEquals(50,obBooking.getDiscount(),0.001);
	}
	
	@Test
	public void testChangers()
	{
		Date startDate= new Date(2020,7,20);
		Date endDate= new Date(2020,7,25);
		Date newStart= new Date(2020,6,15);
		Date newEnd= new Date(2020,8,10);

		Booking obBooking=new Booking(2,"TestGuestID",startDate,
				endDate, BookingType.Cabin,4);


		obBooking.changeStart(newStart);
		obBooking.changeEnd(newEnd);

		assertEquals(newStart,obBooking.getStartDate());
		assertEquals(newEnd,obBooking.getEndDate());
	}
	

}
