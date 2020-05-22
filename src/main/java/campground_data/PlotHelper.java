package campground_data;

import java.util.ArrayList;

public class PlotHelper
{
    private ArrayList<Plot> plots = new ArrayList<>();
    private DatabaseFile DBFile;

    public PlotHelper() {
        DBFile=new DatabaseFile();
        this.plots = DBFile.readPlots();
    }

    public boolean addPlot(Plot newPlot)
    {
        plots.add(newPlot);
        DBFile.saveRecords(plots);
        return plots.contains(newPlot);
    }
    public ArrayList<Plot> getPlotList() {

        return plots;
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

