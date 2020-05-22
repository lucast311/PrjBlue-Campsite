package campground_data;

import java.io.Serializable;

public class Site extends Plot implements Serializable {
    private boolean serviced;
    private SiteType type;

    public enum SiteType {
        Group,
        Individual
    }

    public Site(int siteNum, int occupancy, double price, SiteType type, boolean underReno,  boolean serviced) {
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
        return String.format("%s\nType: %s\nServiced: %s", super.toString(), this.type, serviced ? "True" : "False");
    }
}
