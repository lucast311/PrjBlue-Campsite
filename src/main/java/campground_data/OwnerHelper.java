package campground_data;

import java.util.ArrayList;

public class OwnerHelper {

    private ArrayList<Owner> ownerList;

    public OwnerHelper() {

    }

    public Owner addOwner(Owner newOwner) {
        ownerList.add(newOwner);
        return newOwner;
    }

    public void removeOwner(Owner obj) {

    }

    public ArrayList<Owner> getOwnerList() {
        return null;
    }

    public Owner searchOwner(String userId) {
        return null;
    }


}
