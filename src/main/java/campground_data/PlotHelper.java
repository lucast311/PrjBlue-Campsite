package campground_data;

import java.util.ArrayList;

public class PlotHelper
{
    private ArrayList<Plot> plots = new ArrayList<>();

    public PlotHelper() {
    }

    public void addPlot(ArrayList<Plot> plots) { //not done
        this.plots = plots;
    }
    public void removePlot(ArrayList<Plot> plots) { //not done
        this.plots = plots;
    }
    public boolean addPlot(Plot newPlot)
    {
        return plots.add(newPlot);
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
                        return getPlotList().get(i);
                }
        }
        return null;
    }

}

