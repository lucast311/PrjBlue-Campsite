package campground_data;

<<<<<<< HEAD
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
=======
public class Cabin extends Plot
{
    public enum CabinType
    {
        Deluxe,
        Basic;
    }

    public CabinType;

    public Cabin(int plotID, int occupancy, CabinType Type, boolean underReno)
    {

    }

    public Cabin()
    {

    }

    public void setType()
    {

    }

    public CabinType getType()
    {

    }

    @Override
    public String toString()
    {
        return String.format();
>>>>>>> 997588a8933c5e2232b42b0a406e88d66140a52f
    }
}
