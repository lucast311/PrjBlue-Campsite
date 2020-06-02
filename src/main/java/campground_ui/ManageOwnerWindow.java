package campground_ui;

import campground_data.Owner;
import campground_data.OwnerHelper;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManageOwnerWindow extends Stage {
    private TextField firstName;
    private TextField lastName;
    private Text userId;
    private TextField phoneNum;
    private TextField email;
    private TextField permissions;
    private CheckBox onSite;

    private ListView<Owner> ownerListView;
    private OwnerHelper ownerHelper = new OwnerHelper();
    private ArrayList<Owner> owners = ownerHelper.getOwnerList();
    private Button btnPass, btnNew, btnRemove, btnEdit, btnClose;

    public ManageOwnerWindow(Stage parent)
    {

    }

    private void showAllOwners()
    {

    }

    private void clearAllFields()
    {

    }

    private void populateFields()
    {

    }
}
