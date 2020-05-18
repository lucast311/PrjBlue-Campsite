package campground_data;

import org.json.simple.JSONObject;

public class Owner {

    private String firstName;
    private String lastName;
    private String userId;
    private String password;
    private String phoneNumber;
    private String email;
    private int permissions;
    private Boolean onSite;

    public Owner(String first, String last, String password, String phone, String email, int permissions, Boolean onSite)
    {
        this.firstName = first;
        this.lastName = last;
        this.userId = first + "." + last;
        this.password = password;
        this.phoneNumber = phone;
        this.email = email;
        this.permissions = permissions > 0 && permissions < 4 ? permissions : 1;
        this.onSite = onSite;
    }

    public Owner()
    {

    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setPhone(String phone)
    {
        this.phoneNumber = phone;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPermissions(int level)
    {
        this.permissions = level;
    }

    public void setOnSite(Boolean onSite)
    {
        this.onSite = onSite;
    }

    public void changePassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public String getEmail()
    {
        return this.email;
    }

    public int getPermissions()
    {
        return this.permissions;
    }

    public Boolean getOnSite()
    {
        return this.onSite;
    }

    @Override
    public String toString()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName",this.firstName);
        jsonObject.put("lastName",this.lastName);
        jsonObject.put("userId",this.userId);
        jsonObject.put("password",this.password);
        jsonObject.put("phoneNumber",this.phoneNumber);
        jsonObject.put("email",this.email);
        jsonObject.put("permissions",this.permissions);
        jsonObject.put("onSite",this.onSite);
        return jsonObject.toString();
    }
}
