package campground_data;

public class Cabin extends Plot{
    private type CabinType; //help

    public Cabin(int cabinNum ,int occupancy, type CabinType, double price, boolean underReno) {
        super(price, underReno, occupancy);
        this.type = type;
    }

    public type getCabinType() {
        return CabinType;
    }

    public void setCabinType(type cabinType) {
        CabinType = cabinType;
    }

    @Override
    public String toString() {
        return "Cabin{" +
                "CabinType=" + CabinType +
                '}';
    }
}
