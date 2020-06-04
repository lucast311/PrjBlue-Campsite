package campground_ui;

import campground_data.BookingType;
import campground_data.Owner;
import campground_data.OwnerHelper;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManageOwnerWindow extends Stage {

    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obButtonBox;

    private ListView<Owner> lvOwnerList;
    private TextField txtFirstName, txtLastName, txtPhoneNum, txtEmail, txtPermissions;
    private Label lblFirst, lblLast, lblPhone, lblEmail, lblPermissions, lblUserId, lblOnsite;
    private Text txtuserId;
    private CheckBox cbOnSite;

    private ListView<Owner> ownerListView;
    private OwnerHelper ownerHelper = new OwnerHelper();
    private ArrayList<Owner> owners = ownerHelper.getOwnerList();
    private Button btnPass, btnNew, btnRemove, btnEdit, btnClose, btnSave, btnCancel;

    public ManageOwnerWindow(Stage parent, Owner curOwner)
    {
        // initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obButtonBox = new VBox();

        // initialize borderpane top that will show a list of owners
        lvOwnerList = new ListView<>();
        lvOwnerList.setPrefSize(600, 200);
        lvOwnerList.setEditable(false);

        // populate owner list view
        showAllOwners();

        // initialize text fields and labels that will display owner attributes
        txtFirstName = new TextField();
        txtLastName = new TextField();
        txtPhoneNum = new TextField();
        txtEmail = new TextField();
        txtPermissions = new TextField();
        txtuserId = new Text();
        cbOnSite = new CheckBox();
        lblFirst = new Label("First Name");
        lblLast = new Label("Last Name");
        lblPhone = new Label("Phone Number");
        lblEmail = new Label("Email Address");
        lblUserId = new Label("UserID");
        lblPermissions = new Label("Permissions");
        lblOnsite = new Label("On Site");

        //initialize buttons that will allow window functionality
        btnPass = new Button("Change Password");
        btnNew = new Button("New");
        btnRemove = new Button("Remove");
        btnEdit = new Button("Edit");
        btnClose = new Button("Close");
        btnSave = new Button("Save");
        btnCancel = new Button("Cancel");

        obButtonBox.getChildren().addAll(btnPass, btnNew, btnRemove, btnEdit, btnClose);

        obBPane.setTop(lvOwnerList);
        obBPane.setLeft(obGPane);
        obBPane.setCenter(obButtonBox);

        //Layout parameters
        obBPane.setPadding(new Insets(10));
        this.setMinWidth(1000);
        this.setMinHeight(510);

        obGPane.setHgap(10);
        obGPane.setVgap(5);
        obGPane.setPadding(new Insets(15, 0, 0, 10));

        obButtonBox.setSpacing(5);
        obButtonBox.setPadding(new Insets(159, 0, 0, 15));

        obGPane.add(lblFirst, 0, 0);
        obGPane.add(txtFirstName, 1, 0);
        obGPane.add(lblLast, 0, 1);
        obGPane.add(txtLastName, 1, 1);
        obGPane.add(lblUserId, 0, 2);
        obGPane.add(txtuserId, 1, 2);
        obGPane.add(lblEmail, 0, 3);
        obGPane.add(txtEmail, 1, 3);
        obGPane.add(lblPhone, 0, 4);
        obGPane.add(txtPhoneNum, 1, 4);
        obGPane.add(lblPermissions, 0, 5);
        obGPane.add(txtPermissions, 1, 5);
        obGPane.add(lblOnsite, 0, 6);
        obGPane.add(cbOnSite, 1, 6);

        //Create event handlers
        lvOwnerList.getSelectionModel().selectedItemProperty().addListener(e -> {
            populateFields();
        });

        btnEdit.setOnAction(e -> {
            toggleTextfields();
            btnPass.setDisable(true);
            btnRemove.setDisable(true);
            btnNew.setDisable(true);
            obButtonBox.getChildren().remove(3);
            obButtonBox.getChildren().remove(3);
            obButtonBox.getChildren().add(3, btnSave);
            obButtonBox.getChildren().add(4, btnCancel);
        });

        btnCancel.setOnAction(e -> {
            toggleTextfields();
            populateFields();
            btnPass.setDisable(false);
            btnRemove.setDisable(false);
            btnNew.setDisable(false);
            obButtonBox.getChildren().remove(3);
            obButtonBox.getChildren().remove(3);
            obButtonBox.getChildren().add(3, btnEdit);
            obButtonBox.getChildren().add(4, btnClose);
        });

        btnClose.setOnAction(e -> {
            close();
        });

        this.setScene(new Scene(obBPane, 1000, 500));
        this.setTitle("Cest Lake - Employee Manager");
        this.setOnShowing(e -> {
            showAllOwners();
            toggleTextfields();
        });
        this.initOwner(parent);
        this.initModality(Modality.NONE);
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
        lvOwnerList.getItems().clear();

        for(Owner owner : owners)
        {
            lvOwnerList.getItems().add(owner);
        }
    }

    private void clearAllFields()
    {

    }

    private void populateFields()
    {
        Owner ownerToDisplay = (Owner) lvOwnerList.getSelectionModel().getSelectedItem();

        txtFirstName.setText(ownerToDisplay.getFirstName());
        txtLastName.setText(ownerToDisplay.getLastName());
        txtuserId.setText(ownerToDisplay.getUserId());
        txtEmail.setText(ownerToDisplay.getEmail());
        txtPhoneNum.setText(ownerToDisplay.getPhoneNumber());
        txtPermissions.setText(Integer.toString(ownerToDisplay.getPermissions()));
        cbOnSite.setSelected(ownerToDisplay.getOnSite());
    }
}
