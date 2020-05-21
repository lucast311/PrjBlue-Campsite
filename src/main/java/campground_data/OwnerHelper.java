package campground_data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OwnerHelper {

    private static ArrayList<Owner> ownerList;

    public OwnerHelper() {

    }

    public static Owner addOwner(Owner newOwner) {
        ownerList.add(newOwner);
        return newOwner;
    }

    public void removeOwner(Owner obj) {

    }

    public static ArrayList<Owner> getOwnerList() {
        ownerList = new ArrayList<Owner>();
        return ownerList;
    }

    public Owner searchOwner(String userId) {
        return null;
    }


}
