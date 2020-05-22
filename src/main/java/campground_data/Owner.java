package campground_data;

import org.json.simple.JSONObject;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Owner implements Serializable {

    private String firstName;
    private String lastName;

    @Size(min = 5, max = 51, message = "UserID must be between 5 and 51 characters")
    @Pattern(regexp = "^[a-z]{2,20}[.]{1}[a-z]{2,30}$", message = "UserID must be in the format firstname.fastname")
    private String userId;

    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters in length")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$/i", message = "Password can consist of letters, number and symbols but not spaces")
    private String password;

    private String phoneNumber;
    private String email;

    @Size(min = 1, max = 3, message = "User permissions can be 1-3, 1 being read-only; 3 being full-control")
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

    public boolean changePassword(String password)
    {
        if(password.length()>7)
        {
            this.password = password;
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getPassword() { return this.password; }

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
