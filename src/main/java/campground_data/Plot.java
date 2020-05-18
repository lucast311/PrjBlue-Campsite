package campground_data;

public class Plot {
    private String plotID;
    private int occupancy;
    private boolean underReno;
    private boolean booked;


    public Plot(String plotID, int occupancy, boolean underReno, boolean booked) {
        this.plotID = plotID;
        this.occupancy = occupancy;
        this.underReno = underReno;
        this.booked = booked;


    }

    public String getPlotID() {
        return plotID;
    }

    public void setPlotID(String plotID) {
        this.plotID = plotID;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public boolean isUnderReno() {
        return underReno;
    }

    public void setUnderReno(boolean underReno) {
        this.underReno = underReno;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

}
