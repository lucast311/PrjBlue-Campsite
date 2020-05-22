package campground_data;

public class Site extends Plot {
    private boolean serviced;
    private SiteType type;

    public Site(int siteNum, int occupancy, SiteType type, double price, boolean underReno, boolean booked, boolean serviced) {
        super(siteNum, occupancy, price, underReno, booked);
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
