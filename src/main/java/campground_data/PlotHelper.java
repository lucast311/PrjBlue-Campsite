package campground_data;

import java.util.ArrayList;

public class PlotHelper
{
    private static ArrayList<Plot> plots = new ArrayList<>();
    private DatabaseFile DBFile;

    private static ArrayList<Cabin> cabins = new ArrayList<>();
    private static ArrayList<Site> sites = new ArrayList<>();



    public PlotHelper() {
        DBFile = new DatabaseFile();
        this.plots = DBFile.readPlots();
        this.sites = DBFile.readSites();
        this.cabins = DBFile.readCabins();
    }

    public boolean addSite(Site newSite)
    {
        this.sites.add(newSite);
        DBFile.saveRecords(sites);
        return sites.contains(newSite);
    }

    public boolean addCabin(Cabin newCabin)
    {
        cabins.add(newCabin);
        DBFile.saveRecords(cabins);
        return cabins.add(newCabin);
    }

    public boolean addPlot(Plot newPlot)
    {
        plots.add(newPlot);
        DBFile.saveRecords(plots);
        return plots.contains(newPlot);
    }

    public ArrayList<Site> getSiteList()
    {
        return this.sites;
    }

    public ArrayList<Cabin> getCabinList()
    {
        return this.cabins;
    }

    public ArrayList<Plot> getPlotList() {
        return this.plots;
    }
    public void removePlot(Plot plot) {

        plots.remove(plot);
    }

    public boolean checkPlotId(int plotID)
    {
        for(Plot plot : plots)
        {
            if(plot.getPlotID() == plotID)
            {
                return true;
            }
        }
        return false;
    }



    public static Plot searchPlot(int plotID) {

        for (int i = 0; i < getPlotList().size(); i++) {
                if (getPlotList().get(i).getPlotID() == (plotID)) {
                        return plots.get(i);
                }
        }
        return null;
    }

}

