package campground_data;

import java.io.Serializable;

public class Cabin extends Plot implements Serializable {
    private CabinType type; //help

    public enum CabinType {
        Deluxe,
        Basic;
    }

    public Cabin(int cabinNum, int occupancy, CabinType type, double price, boolean underReno) {
        super(cabinNum, occupancy, price, underReno, false);
        this.type = type;
    }

    public CabinType getCabinType() {
        return this.type;
    }

    public void setCabinType(CabinType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cabin{" +
                "CabinType=" + this.type +
                '}';
    }
}