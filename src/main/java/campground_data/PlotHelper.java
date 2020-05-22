package campground_data;

import java.util.ArrayList;

public class PlotHelper
{
    private ArrayList<Plot> plots = new ArrayList<>();
    private DatabaseFile DBFile;

    private ArrayList<Cabin> cabins = new ArrayList<>();
    private ArrayList<Site> sites;

    private DatabaseFile DBFile;

    public PlotHelper() {
        DBFile = new DatabaseFile();
        this.plots = DBFile.readPlots();
        this.sites = DBFile.readSites();
        this.cabins = DBFile.readCabins();
    }

    public Site addSite(Site newSite)
    {
        this.sites.add(newSite);
        return newSite;
    }

    public boolean addCabin(Cabin newCabin)
    {
        return this.cabins.add(newCabin);
    }

    public boolean addPlot(Plot newPlot)
    {
        plots.add(newPlot);
        DBFile.saveRecords(plots);
        return plots.contains(newPlot);
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

    public Plot searchPlot(int plotID) {
        for (int i = 0; i < getPlotList().size(); i++) {
                if (getPlotList().get(i).getPlotID() == (plotID)) {
                        return plots.get(i);
                }
        }
        return null;
    }

}

