package campground_data;

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
                plots.remove(plot);
        }

        public ArrayList<Plot> getPlotList()
        {
            return this.plots;
        }

        public Plot searchPlot(int plotID)
        {
                Plot plotToReturn = null;
                for (Plot plot : plots)
                {
                        if (plot.getPlotID() == plotID)
                        {
                                plotToReturn = plot;
                        }
                }

                return plotToReturn;
        }
}

