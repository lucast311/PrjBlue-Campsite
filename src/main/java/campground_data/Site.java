package campground_data;

import java.io.Serializable;

public class Site extends Plot implements Serializable {
    private boolean serviced;
    private SiteType type; //help

    public enum SiteType {
        Group,
        Individual
    }

    public Site(int siteNum, int occupancy, SiteType type, double price, boolean serviced, boolean underReno) {
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
