package campground_data;

public class Cabin extends Plot {
    private CabinType type; //help

    public Cabin(int cabinNum, int occupancy, CabinType CabinType, double price, boolean underReno, boolean booked) {
        super(cabinNum, occupancy, price, underReno, booked);
        this.type = CabinType;
    }

    public CabinType getCabinType() {
        return type;
    }

    public void setCabinType(CabinType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cabin{" +
                "CabinType=" + type +
                '}';
    }
}

