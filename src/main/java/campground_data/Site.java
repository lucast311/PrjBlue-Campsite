package campground_data;

public class Site extends Plot {
    private boolean serviced;
    private SiteType type; //help

    public enum SiteType {
        Group,
        Individual;
    }

    public Site(int siteNum, boolean serviced, double price, SiteType type, boolean underReno, int occupancy) {
        super(siteNum, occupancy, price, underReno, false);
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
        return this.type;
    }

    public void setSiteType(SiteType type) {
         this.type = type;
    }

    @Override
    public String toString() {
        return "Site{" +
                "serviced=" + serviced +
                ", SiteType=" + type +
                '}';
    }
}
