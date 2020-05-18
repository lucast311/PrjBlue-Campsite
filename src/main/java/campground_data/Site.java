package campground_data;

<<<<<<< HEAD
public class Site extends Plot {
    private boolean serviced;
    private type SiteType; //help

    public Site (int SiteNum, boolean serviced, double price, Type type, boolean underReno, int occupancy){
        super(price, underReno, occupancy);
        this.serviced = serviced;
        this.SiteType = type;

    }

    public boolean isServiced() {
        return serviced;
    }

    public void setServiced(boolean serviced) {
        this.serviced = serviced;
    }

    public type getSiteType() {
        return SiteType;
    }

    public void setSiteType(type siteType) {
        SiteType = siteType;
    }

    @Override
    public String toString() {
        return "Site{" +
                "serviced=" + serviced +
                ", SiteType=" + SiteType +
                '}';
=======
public class Site extends Plot
{
    private boolean serviced;
    public enum SiteType
    {
        Group,
        Individual;
    }
    private SiteType type;

    public Site(int plotID, boolean serviced, double price, SiteType type, boolean underReno, int occupancy)
    {

    }

    public Site()
    {


    }

    public void setServiced(boolean serviced)
    {

    }

    public void setType()
    {

    }

    public boolean getServiced()
    {
        return this.serviced;
    }

    public SiteType getType()
    {
        return this.type;
    }

    @Override
    public String toString()
    {
        
>>>>>>> 997588a8933c5e2232b42b0a406e88d66140a52f
    }
}
