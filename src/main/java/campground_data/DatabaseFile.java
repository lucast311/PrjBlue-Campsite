package campground_data;

import java.io.*;
import java.util.ArrayList;

public class DatabaseFile 
{
	private String filePathGuest="src/main/java/database/guest.obj";
	private String filePathOwners="src/main/java/database/owners.obj";
	private String filePathPlots="src/main/java/database/plots.obj";
	private String filePathBookings="src/main/java/database/bookings.obj";
	private String filePathSites = "src/main/java/database/sites.obj";
	private String filePathCabins = "src/main/java/database/cabins.obj";
	
	public DatabaseFile()
	{

	}
	
	public ArrayList<Guest> readGuests()
	{
		ArrayList<Guest> obGuestList=new ArrayList<>();
		try(ObjectInputStream obIn=new ObjectInputStream(new FileInputStream(filePathGuest)))
		{
			while(true)
			{
				Guest obGuest=(Guest) obIn.readObject();
				obGuestList.add(obGuest);
			}
		}
		catch(EOFException exp)
		{
			//This just means that all records have been read
		}
		catch(IOException exp)
		{
			//exp.printStackTrace();
		}
		catch(ClassNotFoundException exp)
		{
			//exp.printStackTrace();
		}
		return obGuestList;
	}
	
	public ArrayList<Owner> readOwners()
	{
		ArrayList<Owner> obOwnerList=new ArrayList<>();
		try(ObjectInputStream obIn=new ObjectInputStream(new FileInputStream(filePathOwners)))
		{
			while(true)
			{
				Owner obOwner=(Owner) obIn.readObject();
				obOwnerList.add(obOwner);
			}
		}
		catch(EOFException exp)
		{
			//This just means that all records have been read
		}
		catch(IOException exp)
		{
			//exp.printStackTrace();
		}
		catch(ClassNotFoundException exp)
		{
			//exp.printStackTrace();
		}
		return obOwnerList;
	}

	public ArrayList<Site> readSites()
	{
		ArrayList<Site> obSiteList = new ArrayList<>();
		try(ObjectInputStream obIn=new ObjectInputStream(new FileInputStream(filePathSites)))
		{
			while(true)
			{
				Site obSite =(Site) obIn.readObject();
				obSiteList.add(obSite);
			}

		}
		catch(EOFException exp)
		{
			//This just means that all records have been read
		}
		catch(IOException exp)
		{
			//exp.printStackTrace();
		}
		catch(ClassNotFoundException exp)
		{
			//exp.printStackTrace();
		}
		return obSiteList;
	}

	public ArrayList<Cabin> readCabins()
	{
		ArrayList<Cabin> obCabinList = new ArrayList<>();
		try(ObjectInputStream obIn=new ObjectInputStream(new FileInputStream(filePathCabins)))
		{
			while(true)
			{
				Cabin obCabin =(Cabin) obIn.readObject();
				obCabinList.add(obCabin);
			}

		}
		catch(EOFException exp)
		{
			//This just means that all records have been read
		}
		catch(IOException exp)
		{
			//exp.printStackTrace();
		}
		catch(ClassNotFoundException exp)
		{
			//exp.printStackTrace();
		}
		return obCabinList;
	}

	public ArrayList<Plot> readPlots()
	{
		ArrayList<Plot> obPlotList=new ArrayList<>();
		try(ObjectInputStream obIn=new ObjectInputStream(new FileInputStream(filePathPlots)))
		{
			while(true)
			{
				Plot obPlot=(Plot) obIn.readObject();
				obPlotList.add(obPlot);
			}
		}
		catch(EOFException exp)
		{
			//This just means that all records have been read
		}
		catch(IOException exp)
		{
			//exp.printStackTrace();
		}
		catch(ClassNotFoundException exp)
		{
			//exp.printStackTrace();
		}
		return obPlotList;
	}
	
	public ArrayList<Booking> readBookings()
	{
		ArrayList<Booking> obBookingList=new ArrayList<>();
		try(ObjectInputStream obIn=new ObjectInputStream(new FileInputStream(filePathBookings)))
		{
			while(true)
			{
				Booking obBooking=(Booking) obIn.readObject();
				obBookingList.add(obBooking);
			}
		}
		catch(EOFException exp)
		{
			//This just means that all records have been read
		}
		catch(IOException exp)
		{
			//exp.printStackTrace();
		}
		catch(ClassNotFoundException exp)
		{
			//exp.printStackTrace();
		}
		return obBookingList;
	}
	
	public void saveRecords(ArrayList list)
	{
		if(list.isEmpty())
		{
			System.out.println("That ArrayList is empty! No values to save");
		}
		else
		{
			Object obFirst=list.get(0);
			if(obFirst instanceof Guest)
			{
				try(ObjectOutputStream obOut=new ObjectOutputStream(new FileOutputStream(filePathGuest)))
				{
					for(Object obVal:list)
					{
						obOut.writeObject(obVal);
					}

				}
				catch(IOException exp)
				{
					exp.printStackTrace();
				}
			}
			if(obFirst instanceof Owner)
			{
				try(ObjectOutputStream obOut=new ObjectOutputStream(new FileOutputStream(filePathOwners)))
				{
					for(Object obVal:list)
					{
						obOut.writeObject(obVal);
					}

				}
				catch(IOException exp)
				{
					exp.printStackTrace();
				}
			}
//			if(obFirst instanceof Plot)
//			{
//				try(ObjectOutputStream obOut=new ObjectOutputStream(new FileOutputStream(filePathPlots)))
//				{
//					for(Object obVal:list)
//					{
//						obOut.writeObject(obVal);
//					}
//
//				}
//				catch(IOException exp)
//				{
//					exp.printStackTrace();
//				}
//			}
			if(obFirst instanceof Site)
			{
				try(ObjectOutputStream obOut=new ObjectOutputStream(new FileOutputStream(filePathSites)))
				{
					for(Object obVal:list)
					{
						obOut.writeObject(obVal);
					}

				}
				catch(IOException exp)
				{
					exp.printStackTrace();
				}
			}
			if(obFirst instanceof Cabin)
			{
				try(ObjectOutputStream obOut=new ObjectOutputStream(new FileOutputStream(filePathCabins)))
				{
					for(Object obVal:list)
					{
						obOut.writeObject(obVal);
					}

				}
				catch(IOException exp)
				{
					exp.printStackTrace();
				}
			}
			if(obFirst instanceof Booking)
			{
				try(ObjectOutputStream obOut=new ObjectOutputStream(new FileOutputStream(filePathBookings)))
				{
					for(Object obVal:list)
					{
						obOut.writeObject(obVal);
					}

				}
				catch(IOException exp)
				{
					exp.printStackTrace();
				}
			}

		}

	}
	
}
