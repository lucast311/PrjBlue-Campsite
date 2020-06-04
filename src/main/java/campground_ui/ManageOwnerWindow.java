package campground_ui;

import campground_data.BookingType;
import campground_data.Owner;
import campground_data.OwnerHelper;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManageOwnerWindow extends Stage {

    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obButtonBox;

    private ListView<Owner> lvOwnerList;
    private TextField txtFirstName, txtLastName, txtPhoneNum, txtEmail, txtPermissions;
    private Text txtuserId;
    private CheckBox cbOnSite;

    private ListView<Owner> ownerListView;
    private OwnerHelper ownerHelper = new OwnerHelper();
    private ArrayList<Owner> owners = ownerHelper.getOwnerList();
    private Button btnPass, btnNew, btnRemove, btnEdit, btnClose;

    public ManageOwnerWindow(Stage parent)
    {
        // initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obButtonBox = new VBox();

        // initialize borderpane top that will show a list of owners
        lvOwnerList = new ListView<>();
        lvOwnerList.setPrefSize(600, 200);
        lvOwnerList.setEditable(false);

        // initialize text fields that will display owner attributes
        txtFirstName = new TextField();
        txtLastName = new TextField();
        txtPhoneNum = new TextField();
        txtEmail = new TextField();
        txtPermissions = new TextField();
        txtuserId = new Text();
        cbOnSite = new CheckBox();

        //initialize buttons that will allow window functionality
        btnPass = new Button("Change Password");
        btnNew = new Button("New");
        btnRemove = new Button("Remove");
        btnEdit = new Button("Edit");
        btnClose = new Button("Close");


    }


    /**
     * toggle textfields editability
     */
    private void toggleTextfields()
    {
        for(Node field : obGPane.getChildren())
        {
            if((field instanceof TextField || field instanceof Text) && !field.isDisabled())
            {
                field.setDisable(true);
            }
            else
            {
                field.setDisable(false);
            }
        }
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
