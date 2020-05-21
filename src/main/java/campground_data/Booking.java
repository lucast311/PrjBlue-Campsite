package campground_data;

import campground_data.BookingType;

import java.io.Serializable;
import java.util.Date;


public class Booking implements Serializable
{

	private int nBookingID = 1;
	private static final long serialVersionUID = 1L;
	private String sGuestID;
	private int nPlotID;
	private Date startDate;
	private Date endDate;
	private BookingType type;
	private boolean bPaid;
	private double dTotal;
	private double dDiscountRate;
	private int nMemberCount;
	
	public Booking(int plotID, String guestID, Date startDate, Date endDate, BookingType type, int memberCount)
	{

		this.nBookingID++;

		this.nBookingID=1;

		this.sGuestID=guestID;
		this.nPlotID=plotID;
		this.startDate=startDate;
		this.endDate=endDate;
		this.type=type;
		this.bPaid=false;
		this.dTotal=0;
		this.dDiscountRate=0;
		this.nMemberCount=memberCount;
	}
	
	public Booking()
	{

		this.nBookingID++;
		this.sGuestID="Test" + this.nBookingID++;
		this.nBookingID=1;
		this.sGuestID="";
		this.nPlotID=0;
		this.startDate=new Date();
		this.endDate=new Date();
		this.type=null;
		this.bPaid=false;
		this.dTotal=0;
		this.dDiscountRate=0;
		this.nMemberCount=1;
	}

    public void changeStart(Date newStart)
	{
		this.startDate=newStart;
	}
	
	public void changeEnd(Date newEnd)
	{
		this.endDate=newEnd;
	}
	
	public void setType(BookingType type)
	{
		this.type=type;
	}
	
	public void setPaid(boolean bPaid)
	{
		this.bPaid=bPaid;
	}
	
	public void setMemberCount(int nMemberCount)
	{
		if(nMemberCount<=0)
		{
			System.out.println("That member count is invalid");
		}
		else
		{
			this.nMemberCount=nMemberCount;
		}

	}
	
	public void setPlotID(int nPlotID)
	{
		if(nPlotID<=0 || nPlotID>=5)
		{
			System.out.println("That plot ID is invalid");
		}
		else
		{
			this.nPlotID=nPlotID;
		}

	}
	
	public void setTotal(double dTotal)
	{
		if(dTotal<0)
		{
			System.out.println("That total is invalid");
		}
		else
		{
			this.dTotal=dTotal;
		}

	}

	public void setBookingID(int ID){ this.nBookingID =  ID; }
	
	public void setDiscount(double dDiscount)
	{
		if(dDiscount<0 || dDiscount>100)
		{
			System.out.println("That discount is invalid");
		}
		else
		{
			this.dDiscountRate=dDiscount;
		}

	}



	public String getGuestID()
	{
		return this.sGuestID;
	}
	
	public Date getStartDate()
	{
		return this.startDate;
	}
	
	public Date getEndDate()
	{
		return this.endDate;
	}
	
	public BookingType getType()
	{
		return this.type;
	}
	
	public boolean getPaid()
	{
		return this.bPaid;
	}
	
	public double getTotal()
	{
		return this.dTotal;
	}
	
	public double getDiscount()
	{
		return this.dDiscountRate;
	}
	
	public int getMemberCount()
	{
		return this.nMemberCount;
	}
	
	public int getPlotID()
	{
		return this.nPlotID;
	}

	public int getBookingID()
	{
		return this.nBookingID;
	}
	
	@Override
	public String toString()
	{
		return String.format("BookingID: %d, GuestID: %s, Plot ID: %d, Paid?: %b, Total: %f, Members: %d",
				this.nBookingID, this.sGuestID, this.nPlotID, this.bPaid, this.dTotal, this.nMemberCount);
	}
}
