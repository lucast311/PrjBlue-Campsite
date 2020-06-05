package campground_data;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable
{
	private static int nBookingIDCount = 1;
	private int nBookingID;
	private static final long serialVersionUID = 1L;
	private int nGuestID;
	private int nPlotID;
	private Date startDate;
	private Date endDate;
	private BookingType type;
	private boolean bPaid;
	private double dTotal;
	private double dDiscountRate;
	private int nMemberCount;
	
	public Booking(int plotID, int guestID, Date startDate, Date endDate, BookingType type, int memberCount)
	{
		this.nBookingID = nBookingIDCount++;
		this.nGuestID =guestID;
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
		this.nBookingID = nBookingIDCount++;
		this.nGuestID =0;
		this.nPlotID=0;
		this.startDate=new Date();
		this.endDate=new Date();
		this.type=null;
		this.bPaid=false;
		this.dTotal=0;
		this.dDiscountRate=0;
		this.nMemberCount=1;
	}

    public boolean changeStart(Date newStart)
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
			return true;
		}

		return false;
	}
	
	public boolean changeEnd(Date newEnd)
	{
		//Check for end not being equal or before the start date
		if(newEnd.equals(this.startDate))
		{
			System.out.println("Invalid End Date! Choose a later date");
			return false;
		}
		if(newEnd.before(this.startDate))
		{
			System.out.println("Invalid End Date! Choose a later date");
			return false;
		}
		else
		{
			this.endDate=newEnd;
			return true;
		}
	}
	
	public boolean setType(String type)
	{
		if(type.equalsIgnoreCase("Cabin"))
		{
			this.type = BookingType.Cabin;
			return true;
		}
		if(type.equalsIgnoreCase("Site"))
		{
			this.type = BookingType.Site;
			return true;
		}

		return false;
	}
	
	public void setPaid(boolean bPaid)
	{
		this.bPaid=bPaid;
	}
	
	public boolean setMemberCount(int nMemberCount)
	{
		if(nMemberCount<=0 || nMemberCount > 8)
		{
			System.out.println("That member count is invalid");
		}
		else
		{
			this.nMemberCount=nMemberCount;
			return true;
		}

		return false;
	}

	public boolean setGuestID(int nGuestID)
	{
		GuestHelper guestHelper = new GuestHelper();
		if(guestHelper.checkGuestId(nGuestID))
		{
			this.nGuestID = nGuestID;
			return true;
		}

		return false;
	}
	
	public boolean setAccommodationID(int nAccommodationID)
	{
		if(BusinessManager.plotHelper.checkAccommodationID(nAccommodationID))
		{
			return true;
		}

		return false;
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

	public void setBookingID(int ID){ nBookingID =  ID; }

	public void setDiscount(double dDiscount)
	{
		if(dDiscount<0 || dDiscount>100)
		{
			System.out.println("That discount is invalid");
		}
		else
		{
			this.dDiscountRate=dDiscount;
			setTotal(dTotal*(1-(dDiscount/100))); //automatically adjusts total based on set discount

		}

	}

	public int getBookingID()
	{
		return this.nBookingID;
	}

	public int getGuestID()
	{
		return this.nGuestID;
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
		return String.format("BookingID: %d, GuestID: %d, Plot ID: %d, Paid?: %b, Total: %f, Members: %d Start Date: %s %d End Date: %s %d\n",
				this.nBookingID, this.nGuestID, this.nPlotID, this.bPaid, this.dTotal, this.nMemberCount,
				this.startDate.toString(),this.startDate.getYear(),this.endDate.toString(),this.endDate.getYear());
	}
}
