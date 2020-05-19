package campground_data;

import campground_data.bookingType;
import java.util.Date;


public class Booking 
{
	private int nBookingID;
	private String sGuestID;
	private int nPlotID;
	private Date startDate;
	private Date endDate;
	private bookingType type;
	private boolean bPaid;
	private double dTotal;
	private double dDiscountRate;
	private int nMemberCount;
	
	public Booking(int plotID, String guestID, Date startDate, Date endDate, bookingType type, int memberCount)
	{
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
	
	public void setType(bookingType type)
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

	public int getBookingID()
	{
		return this.nBookingID;
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
	
	public bookingType getType()
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
	
	@Override
	public String toString()
	{
		return String.format("BookingID: %d, GuestID: %s, Plot ID: %d, Paid?: %b, Total: %d, Members: %d",
				this.nBookingID, this.sGuestID, this.nPlotID, this.bPaid, this.dTotal, this.nMemberCount);
	}
}
