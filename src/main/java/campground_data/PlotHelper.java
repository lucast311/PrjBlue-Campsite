package campground_data;

//<<<<<<< HEAD
//import java.util.ArrayList;
//
//public class PlotHelper {
//    private ArrayList<Plot> plots;
//
//    public PlotHelper(ArrayList<Plot> plots) {
//        this.plots = plots;
//    }
//
//    public void addPlot(ArrayList<Plot> plots) { //not done
//        this.plots = plots;
//    }
//    public void removePlot(ArrayList<Plot> plots) { //not done
//        this.plots = plots;
//    }
//
//    public ArrayList<Plot> getPlotList() {
//        return plots;
//    }
//
//    public ArrayList<Plot> searchPlot(int plotID) { //not done
//        return plots;
//    }
//
//}
//=======

import java.util.ArrayList;

public class PlotHelper
{
    private ArrayList<Plot> plots = new ArrayList<>();

    public  PlotHelper()
    {

    }

    public Plot addPlot(Plot newPlot)
    {
        return newPlot;
    }

    public void removePlot(Plot plot)
    {

    }

    public ArrayList<Plot> getPlotList()
    {
        return this.plots;
    }

    public Plot searchPlot(int plotID)
    {
        Plot plot = null;
        return plot;
    }
}

