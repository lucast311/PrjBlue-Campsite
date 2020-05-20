package campground_data;

public abstract class Plot {
    private int plotID;
    private int occupancy;
    private double price;
    private boolean underReno;
    private boolean booked;


    public Plot(int plotID, int occupancy, double price, boolean underReno, boolean booked) {
        this.plotID = plotID;
        this.occupancy = occupancy;
        this.price = price;
        this.underReno = underReno;
        this.booked = booked;
    }

    public int getPlotID() {
        return plotID;
    }

    public void setPlotID(int plotID) {
        this.plotID = plotID;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

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
