package campground_data;

import java.util.ArrayList;

public class PlotHelper
{
        private static ArrayList<Accommodation> plots;
        private DatabaseFile dbFile;

        public  PlotHelper()
        {
                dbFile = new DatabaseFile();
                this.plots = dbFile.readPlots();
        }

        public boolean addPlot(Accommodation newPlot)
        {
                this.plots.add(newPlot);
                dbFile.saveRecords(plots);
                return this.plots.contains(newPlot);
        }

        public void removePlot(Accommodation plot)
        {
                plots.remove(plot);
        }

        public ArrayList<Accommodation> getPlotList()
        {
            return this.plots;
        }

        public Cabin searchCabin(int plotID)
        {
                Cabin plotToReturn = null;
                for (Accommodation plot : plots)
                {
                        if (plot.getAccommodationID() == plotID)
                        {
                                plotToReturn = (Cabin) plot;
                        }
                }

                return plotToReturn;
        }

        public Site searchSite(int plotID)
        {
                Site plotToReturn = null;
                for (Accommodation plot : plots)
                {
                        if (plot.getAccommodationID() == plotID)
                        {
                                plotToReturn = (Site) plot;
                        }
                }

                return plotToReturn;
        }

        public static Accommodation searchPlot(int plotID)
        {
                Accommodation plotToReturn = null;
                for (Accommodation plot : plots)
                {
                        if (plot.getAccommodationID() == plotID)
                        {
                                plotToReturn = plot;
                        }
                }

                return plotToReturn;
        }

        public boolean checkAccommodationID(int nID)
        {
                for(Accommodation plot : plots)
                {
                        if(plot.getAccommodationID() == nID)
                        {
                                return true;
                        }
                }

                return false;
        }
}

