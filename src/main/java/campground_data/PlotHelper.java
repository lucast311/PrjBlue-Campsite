package campground_data;

import java.util.ArrayList;

public class PlotHelper extends Plot {
        private ArrayList<Plot> plots = new ArrayList<>();


    public PlotHelper(ArrayList<Plot> plots) {
        this.plots = plots;
    }
        public PlotHelper(int plotID, int occupancy, double price, boolean underReno, boolean booked) {
                super(plotID, occupancy, price, underReno, booked);
        }

    public void addPlot(ArrayList<Plot> plots) { //not done
        this.plots = plots;
    }
    public void removePlot(ArrayList<Plot> plots) { //not done
        this.plots = plots;
    }
        public Plot addPlot(Plot newPlot) {
                return newPlot;
        }

    public ArrayList<Plot> getPlotList() {
        return plots;
    }
        public void removePlot(Plot plot) {
                plots.remove(plot);

        }

    public ArrayList<Plot> searchPlot(int plotID) { //not done
        return plots;
    }
        public ArrayList<Plot> getPlotList() {
                return this.plots;
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

