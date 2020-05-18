package campground_data;

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
    }
}
