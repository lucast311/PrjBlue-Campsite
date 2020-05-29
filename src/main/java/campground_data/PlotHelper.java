package campground_data;

import java.util.ArrayList;

public class PlotHelper
{
        private static ArrayList<Plot> plots;
        private DatabaseFile dbFile;

        public  PlotHelper()
        {
                dbFile = new DatabaseFile();
                this.plots = dbFile.readPlots();
        }

        public boolean addPlot(Plot newPlot)
        {
                this.plots.add(newPlot);
                dbFile.saveRecords(plots);
                return this.plots.contains(newPlot);
        }

        public void removePlot(Plot plot)
        {
                plots.remove(plot);
        }

        public ArrayList<Plot> getPlotList()
        {
            return this.plots;
        }

        public Cabin searchCabin(int plotID)
        {
                Cabin plotToReturn = null;
                for (Plot plot : plots)
                {
                        if (plot.getPlotID() == plotID)
                        {
                                plotToReturn = (Cabin) plot;
                        }
                }

                return plotToReturn;
        }

        public Site searchSite(int plotID)
        {
                Site plotToReturn = null;
                for (Plot plot : plots)
                {
                        if (plot.getPlotID() == plotID)
                        {
                                plotToReturn = (Site) plot;
                        }
                }

                return plotToReturn;
        }

        public static Plot searchPlot(int plotID)
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

        public boolean checkAccommodationID(int nID)
        {
                for(Plot plot : plots)
                {
                        if(plot.getPlotID() == nID)
                        {
                                return true;
                        }
                }

                return false;
        }
}

