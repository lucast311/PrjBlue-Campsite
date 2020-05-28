package campground_data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OwnerHelper {

    private static ArrayList<Owner> ownerList;
    private static DatabaseFile DBFile;

    public OwnerHelper() {
        DBFile=new DatabaseFile();
        ownerList = DBFile.readOwners();
    }

    public static Owner addOwner(Owner newOwner) {
        ownerList.add(newOwner);
        DBFile.saveRecords(ownerList);
        return newOwner;
    }

    public void removeOwner(Owner obj) {

    }

    public static ArrayList<Owner> getOwnerList() {
        return ownerList;
    }

    public Owner searchOwner(String userId) {
        return null;
    }

    public Owner validateUser(String userID, String Password) { return null; }
}
