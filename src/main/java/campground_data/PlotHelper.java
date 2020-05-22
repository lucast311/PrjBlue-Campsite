package campground_data;

import java.util.ArrayList;

public class PlotHelper
{
    private ArrayList<Plot> plots = new ArrayList<>();

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

    public Plot addPlot(Plot newPlot)
    {
        plots.add(newPlot);
        return newPlot;

    }

    public ArrayList<Cabin> getCabinList()
    {
        return this.cabins;
    }

    public ArrayList<Site> getSiteList()
    {
        return this.sites;
    }

    public ArrayList<Plot> getPlotList() {
        return this.plots;
    }
    public void removePlot(Plot plot) {
        plots.remove(plot);
    }

    public Plot searchPlot(int plotID) {
        for (int i = 0; i < getPlotList().size(); i++) {
                if (getPlotList().get(i).getPlotID() == (plotID)) {
                        return getPlotList().get(i);
                }
        }
        return null;
    }

}

