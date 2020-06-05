package campground_data;

import javafx.scene.control.PasswordField;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class OwnerHelper {

    private static ArrayList<Owner> ownerList;
    private static DatabaseFile DBFile;
    private ValidationHelper vh = new ValidationHelper();

    public OwnerHelper() {
        DBFile=new DatabaseFile();
        ownerList = DBFile.readOwners();
    }

    public static Owner addOwner(Owner newOwner) {
        ownerList.add(newOwner);
        DBFile.saveRecords(ownerList);
        return newOwner;
    }

    public static ArrayList<Owner> getOwnerList() {
        return ownerList;
    }

    public boolean removeOwner(Owner obj) {
        boolean bRemoved = false;
        if(obj!=null)
        {
            this.ownerList.remove(obj);
            bRemoved=true;
            DBFile.saveRecords(this.ownerList);
        }
        return bRemoved;
    }

    public boolean changePassword(Owner owner, String pass1, String pass2)
    {
        Owner obVal = owner;
        boolean bRequest = false;
        if(pass1.equals(pass2))
        {
            String first = obVal.getFirstName();
            String last = obVal.getLastName();
            String email = obVal.getEmail();
            String phone = obVal.getPhoneNumber();
            int permission = obVal.getPermissions();
            boolean onsite = obVal.getOnSite();
            Owner test = new Owner(first, last, pass1, phone, email, permission, onsite);
            if(vh.isValid(test)) {
                for(Owner obOwner : this.ownerList)
                {
                    removeOwner(obOwner);

                    addOwner(test);
                    bRequest = true;
                }


            }
        }
        return bRequest;
    }

    public void editOwner(Owner obj) {

    }

    public Owner searchOwner(String userId) {
        return null;
    }

    public Owner validateUser(String userID, String password) {
        Owner currUser = new Owner();
        for (Owner owner : ownerList)
        {
            if(owner.getUserId().compareTo(userID) == 0)
            {
                currUser = owner;
                break;
            }
            else
            {
               currUser = null;
            }
        }
        if(currUser != null && currUser.getPassword().compareTo(password) == 0)
        {
            return currUser;
        }
        return null;
    }
}
