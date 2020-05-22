package campground_data;

public class Cabin extends Plot {
    private CabinType type;

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