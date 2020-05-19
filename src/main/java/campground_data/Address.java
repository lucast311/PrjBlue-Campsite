package campground_data;

public class Address
{
    private int streetNum;
    private String aptNum;
    private String streetName;
    private String city_Town;
    private String province;
    private String country;
    private String postalCode;

    public Address(int streetNum, String aptNum, String streetName, String city, String state, String country, String postal)
    {
        this.streetNum = streetNum;
        this.aptNum = aptNum;
        this.streetName = streetName;
        this.city_Town = city;
        this.province = state;
        this.country = country;
        this.postalCode = postal;
    }

    public Address()
    {

    }

    public int getStreetNum() {
        return streetNum;
    }

    public String getAptNum() {
        return aptNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity_Town() {
        return city_Town;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setAptNum(String aptNum) {
        this.aptNum = aptNum;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCity_Town(String city_Town) {
        this.city_Town = city_Town;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNum=" + streetNum +
                ", aptNum='" + aptNum + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city_Town='" + city_Town + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
