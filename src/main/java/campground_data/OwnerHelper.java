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
