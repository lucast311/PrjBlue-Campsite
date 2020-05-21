package java;

import java.util.Date;

public class Booking implements Serializable
{
	private int nBookingID;
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
		Date currentDate=new Date(); //Actual current date at the time of running this method
		int nYear=1900+currentDate.getYear();
		int nMonth=currentDate.getMonth();
		int nDay=currentDate.getDate();
		Date compareDate=new Date(nYear,nMonth,nDay); //Stripped down version of current date without the time
		if(newStart.before(compareDate)) //checks if newStart comes before compareDate, which is not allowed
		{
			System.out.println("You cannot change the start date to a date before the current one");
		}
		else
		{
			this.startDate=newStart;
		}
	}
	
	public void changeEnd(Date newEnd)
	{
		//Check for end not being equal or before the start date
		if(newEnd==this.startDate)
		{
			System.out.println("Invalid End Date! Choose a later date");
		}
		if(newEnd.before(this.startDate))
		{
			System.out.println("Invalid End Date! Choose a later date");
		}
		else
		{
			this.endDate=newEnd;
		}
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
			System.out.println("Total cannot be set below 0");
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
	
	@Override
	public String toString()
	{
		return String.format("BookingID: %d, GuestID: %s, Plot ID: %d, Paid?: %b, Total: %f, Members: %d",
				this.nBookingID, this.sGuestID, this.nPlotID, this.bPaid, this.dTotal, this.nMemberCount);
	}
}
