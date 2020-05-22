package campground_data;

import java.util.ArrayList;



public class PlotHelper {
        private static ArrayList<Plot> plots;
    private DatabaseFile DBFile;


    public PlotHelper() {
        DBFile=new DatabaseFile();
        this.plots = DBFile.readPlots();}


    //public void addPlot(ArrayList<Plot> plots) { //not donethis.plots = plots;

    public void removePlot(ArrayList<Plot> plots) { //not done
        this.plots = plots;
    }

    public boolean addPlot(Plot newPlot)
    {
        plots.add(newPlot);
        DBFile.saveRecords(plots);
        return plots.contains(newPlot);
    }

    public static ArrayList<Plot> getPlotList() {
        ArrayList<Plot> PlotTemp = plots;

    return PlotTemp;
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



    public static Plot searchPlot(int plotID) {

        for (int i = 0; i < getPlotList().size(); i++) {
                if (getPlotList().get(i).getPlotID() == (plotID)) {
                        return plots.get(i);
                }
        }
        return null;
    }

}

