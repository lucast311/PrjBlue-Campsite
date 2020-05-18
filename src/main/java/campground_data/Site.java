package campground_data;

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
        
    }
}
