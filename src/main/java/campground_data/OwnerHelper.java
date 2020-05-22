package campground_data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OwnerHelper {

    private ArrayList<Owner> ownerList;
    private DatabaseFile DBFile;

    public OwnerHelper() {
        DBFile = new DatabaseFile();
        this.ownerList = DBFile.readOwners();
    }

    public Owner addOwner(Owner newOwner) {
        ownerList.add(newOwner);
        return newOwner;
    }

    public void removeOwner(Owner obj) {

    }

    public ArrayList<Owner> getOwnerList() {
        return this.ownerList;
    }

    public Owner searchOwner(String userId) {
        return null;
    }


}
