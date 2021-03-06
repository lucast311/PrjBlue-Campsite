package campground_data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class Accommodation implements Serializable {

    @Min(value = 1, message = "ID must be greater than or equal to 1")
    private int AccommodationID;

    @Min(value = 1, message = "Occupancy must be greater than or equal to 1")
    @Max(value = 8, message = "Occupancy must be less than or equal to 8")
    private int occupancy;

    @Min(value = 1, message = "Price must be greater than or equal to 1")
    private double price;

    private boolean underReno;

    private boolean booked;


    public Accommodation(int AccommodationID, int occupancy, double price, boolean underReno, boolean booked) {
        this.AccommodationID = AccommodationID;
        this.occupancy = occupancy;
        this.price = price;
        this.underReno = underReno;
        this.booked = booked;

    }

    public Accommodation() {

    }

    public int getAccommodationID() {
        return AccommodationID;
    }

    public void setAccommodationID(int accommodationID) {
        this.AccommodationID = accommodationID;
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

    @Override
    public String toString() {
        return String.format("AccommodationID: %s\nOccupancy: %d\nPrice: %f\nUnder Maintenance: %s\nBooked: %s",
                this.AccommodationID, this.occupancy, this.price, this.underReno ? "True" : "False", this.booked ? "True" : "False");
    }

}
