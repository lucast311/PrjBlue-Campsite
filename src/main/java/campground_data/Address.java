package campground_data;

import javax.validation.constraints.*;

public class Address
{
    @Min(value = 1, message = "Street number must be at least 1")
    private int streetNum;

    @Min(value = 0, message = "Apt number must be at least 0, 0 means it does not exist")
    private int aptNum;

    @Size(max = 30, message = "The street name must be less than or equal to 30 characters")
    @NotEmpty(message = "The street name must be greater than 0 characters")
    private String streetName;

    @Size(max = 30, message = "The city or town must be less than or equal to 30 characters")
    @NotEmpty(message = "The city or town must be greater than 0 characters")
    private String city_Town;

    @Size(max = 30, message = "The province must be less than or equal to 30 characters")
    @NotEmpty(message = "The province must be greater than 0 characters")
    private String province;

    @Size(max = 30, message = "The country must be less than or equal to 30 characters")
    @NotEmpty(message = "The country must be greater than 0 characters")
    private String country;

    @Pattern(regexp = "^[A-Za-z]\\d[A-Za-z]\\d[A-Za-z]\\d$", message = "Postal code must be in the format A1A1A1")
    private String postalCode;

    public Address(int streetNum, int aptNum, String streetName, String city, String state, String country, String postal)
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

    public int getAptNum() {
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

    public void setAptNum(int aptNum) {
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
