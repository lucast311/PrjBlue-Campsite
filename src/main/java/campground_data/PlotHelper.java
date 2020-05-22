package campground_data;

import java.util.ArrayList;


public class PlotHelper extends Plot {
        private static ArrayList<Plot> plots;


    public PlotHelper() {
        super();
        this.plots = plots;}

        public PlotHelper(int plotID, int occupancy, double price, boolean underReno, boolean booked) {
                super(plotID, occupancy, price, underReno, booked);
        }

    //public void addPlot(ArrayList<Plot> plots) { //not donethis.plots = plots;

    public void removePlot(ArrayList<Plot> plots) { //not done
        this.plots = plots;
    }
    public boolean addPlot(Plot newPlot)
    {
        return plots.add(newPlot);
    }
    public static ArrayList<Plot> getPlotList() {
        ArrayList<Plot> PlotTemp = plots;

    return PlotTemp;
    }

    public void removePlot(Plot plot) {
        plots.remove(plot);
    }

    public static Plot searchPlot(int plotID) {
        for (int i = 0; i < getPlotList().size(); i++) {
                if (getPlotList().get(i).getPlotID() == (plotID)) {
                        return getPlotList().get(i);
                }
        }
        return null;
    }

}

