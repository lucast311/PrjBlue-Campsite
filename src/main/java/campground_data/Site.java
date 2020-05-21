package campground_data;

public class Site extends Plot {
    private boolean serviced;
    private SiteType type; //help

    public Site(int siteNum, int occupancy, SiteType type, double price, boolean underReno, boolean booked, boolean serviced) {    super(siteNum, occupancy, price, underReno, booked);
        this.serviced = serviced;
        this.type = type;

    }

    public boolean isServiced() {
        return serviced;
    }

    public void setServiced(boolean serviced) {
        this.serviced = serviced;
    }

    public SiteType getSiteType() {
        return type;
    }

    public void setSiteType(SiteType siteType) { type = siteType; }

    @Override
    public String toString() {
        return "Site{" +
                "serviced=" + serviced +
                ", SiteType=" + type +
                '}';
    }
}
//public class Site extends Plot
//{
//    private boolean serviced;
//    public enum SiteType
//    {
//        Group,
//        Individual;
//    }
//    private SiteType type;
//
//    public Site(int plotID, boolean serviced, double price, SiteType type, boolean underReno, int occupancy)
//    {
//
//    }
//
//    public Site()
//    {
//
//
//    }
//
//    public void setServiced(boolean serviced)
//    {
//
//    }
//
//    public void setType()
//    {
//
//    }
//
//    public boolean getServiced()
//    {
//        return this.serviced;
//    }
//
//    public SiteType getType()
//    {
//        return this.type;
//    }
//
//    @Override
//    public String toString()
//    {
//
//>>>>>>> 997588a8933c5e2232b42b0a406e88d66140a52f
//    }
//}
