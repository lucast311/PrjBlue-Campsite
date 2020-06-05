package campground_ui;

import campground_data.BookingType;
import campground_data.*;
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
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class ManageOwnerWindow extends Stage {

    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obPassBox;
    private VBox obButtonBox;

    private ListView<Owner> lvOwnerList;
    private TextField txtFirstName, txtLastName, txtPhoneNum, txtEmail, txtPermissions, txtCurPass, txtPass1, txtPass2;
    private Label lblFirst, lblLast, lblPhone, lblEmail, lblPermissions, lblUserId, lblOnsite, lblCurPass, lblPass1, lblPass2;
    private Text txtuserId;
    private CheckBox cbOnSite;
    private Alert obAlertMain;

    private OwnerHelper ownerHelper = new OwnerHelper();
    private ArrayList<Owner> owners = ownerHelper.getOwnerList();
    private Button btnPass, btnNew, btnRemove, btnEdit, btnClose, btnSave, btnCancel, btnOK, btnCancelPass;
    private Owner selectedOwner;
    private Owner current;
    private ValidationHelper vh = new ValidationHelper();

    public ManageOwnerWindow(Stage parent, Owner currOwner)
    {
        current = currOwner;

        // initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obPassBox = new VBox();
        obButtonBox = new VBox();

        // initialize borderpane top that will show a list of owners
        lvOwnerList = new ListView<>();
        lvOwnerList.setPrefSize(700, 200);
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
        txtCurPass = new TextField();
        txtPass1 = new TextField();
        txtPass2 = new TextField();
        cbOnSite = new CheckBox();
        lblFirst = new Label("First Name");
        lblLast = new Label("Last Name");
        lblPhone = new Label("Phone Number");
        lblEmail = new Label("Email Address");
        lblUserId = new Label("UserID");
        lblPermissions = new Label("Permissions");
        lblOnsite = new Label("On Site");
        lblCurPass = new Label("Current Password");
        lblPass1 = new Label("New Password");
        lblPass2 = new Label("Confirm Password");


        //initialize buttons that will allow window functionality
        btnPass = new Button("Change Password");
        btnNew = new Button("New");
        btnRemove = new Button("Remove");
        btnEdit = new Button("Edit");
        btnClose = new Button("Close");
        btnSave = new Button("Save");
        btnCancel = new Button("Cancel");
        btnCancelPass = new Button("Cancel");
        btnOK = new Button("Save");

        obButtonBox.getChildren().addAll(btnPass, btnNew, btnRemove, btnEdit, btnClose);

        obBPane.setTop(lvOwnerList);
        obBPane.setLeft(obGPane);
        obBPane.setCenter(obButtonBox);
        obBPane.setRight(obPassBox);

        //Layout parameters
        obBPane.setPadding(new Insets(10));
        this.setMinWidth(900);
        this.setMinHeight(510);

        obGPane.setHgap(10);
        obGPane.setVgap(5);
        obGPane.setPadding(new Insets(15, 0, 0, 10));

        obButtonBox.setSpacing(5);
        obButtonBox.setPadding(new Insets(15, 0, 0, 15));
        obPassBox.setSpacing(5);

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

        btnNew.setOnAction(e -> {
            selectedOwner = null;
            clearAllFields();
            toggleTextfields();
            obButtonBox.getChildren().removeAll(btnPass, btnRemove, btnNew, btnClose, btnEdit);
            obButtonBox.getChildren().addAll(btnSave, btnCancel);

        });

        btnEdit.setOnAction(e -> {
            selectedOwner = lvOwnerList.getSelectionModel().getSelectedItem();
            toggleTextfields();
            obButtonBox.getChildren().removeAll(btnPass, btnRemove, btnNew, btnClose, btnEdit);
            obButtonBox.getChildren().addAll(btnSave, btnCancel);
        });

        btnCancel.setOnAction(e -> {
            selectedOwner = null;
            toggleTextfields();
            obButtonBox.getChildren().removeAll(btnSave, btnCancel);
            obButtonBox.getChildren().addAll(btnPass, btnNew, btnRemove, btnEdit, btnClose);
        });

        btnSave.setOnAction(e -> {
            String password= "";
            if(selectedOwner != null) {
                password = selectedOwner.getPassword();
            }
            if(password == null || password.equals(""))
            {
                password = "Pa$$word";
            }

            String tempPermission = "";
            for(int i=0; i<txtPermissions.getText().length(); i++)
            {
                if(txtPermissions.getText().isEmpty())
                {
                    break;
                }
                char temp= txtPermissions.getText().charAt(i);
                if(temp>='1' && temp<='9')
                {
                    tempPermission+=temp;
                }
                else
                {
                    continue;
                }
            }
            Owner newOwner = null;
            if(tempPermission != "")
            {
                newOwner = new Owner(txtFirstName.getText(), txtLastName.getText(), password, txtPhoneNum.getText(),
                        txtEmail.getText(), Integer.parseInt(tempPermission), cbOnSite.isSelected());
                if(vh.isValid(newOwner)) {
                    ownerHelper.removeOwner(selectedOwner);
                    ownerHelper.addOwner(newOwner);
                    toggleTextfields();
                    showAllOwners();
                    obButtonBox.getChildren().removeAll(btnSave, btnCancel);
                    obButtonBox.getChildren().addAll(btnPass, btnNew, btnRemove, btnEdit, btnClose);
                }
                else {
                    Text obText = new Text("");
                    String sVal = "";

                    HashMap<String, String> errors = vh.getErrors(newOwner);
                    //This will print out all the errors in the guest object
                    for (String error : errors.values())
                    {
                        sVal += error + "\n";
                    }

                    obText.setText(sVal);
                    obAlertMain = new Alert(Alert.AlertType.ERROR);
                    obAlertMain.setHeaderText(sVal);
                    obAlertMain.showAndWait();
                }
            }

        });

        btnClose.setOnAction(e -> {
            close();
        });

        btnPass.setOnAction(e -> {
            obPassBox.getChildren().addAll(lblCurPass, txtCurPass, lblPass1, txtPass1, lblPass2, txtPass2, btnOK, btnCancelPass);
            txtCurPass.setText("");
            txtPass1.setText("");
            txtPass2.setText("");
            toggleTextfields();
        });

        btnOK.setOnAction(e -> {
            changePass();
        });

        btnCancelPass.setOnAction(e -> {
            obPassBox.getChildren().removeAll(lblCurPass, txtCurPass, lblPass1, txtPass1, lblPass2, txtPass2, btnOK, btnCancelPass);
            toggleTextfields();
        });

        btnRemove.setOnAction(e -> {
            Owner owner = (Owner)lvOwnerList.getSelectionModel().getSelectedItem();
            removeOwner(owner);
        });

        this.setScene(new Scene(obBPane, 900, 450));
        this.setTitle("Cest Lake - Employee Manager");
        this.setOnShowing(e -> {
            showAllOwners();
            toggleTextfields();
        });
        this.initOwner(parent);
        this.initModality(Modality.NONE);
    }

    /**
     * validation logic for change password function. Should be in ownerhelper but I was having problems passing
     * the objects correctly and ran out of time.
     */
    private void changePass()
    {
        selectedOwner = lvOwnerList.getSelectionModel().getSelectedItem();
        if(txtCurPass.getText().equals(selectedOwner.getPassword())) //check the user entered the correct password for the owner selected
        {
            if(txtPass1.getText().equals(txtPass2.getText())) //check that the new passwords match
            {
                Owner newOwner = new Owner(txtFirstName.getText(), txtLastName.getText(), txtPass1.getText(), txtPhoneNum.getText(),
                        txtEmail.getText(), Integer.parseInt(txtPermissions.getText()), cbOnSite.isSelected());

                if(vh.isValid(newOwner)) { //run the validation helper to make sure the new owner has all valid fields
                    ownerHelper.removeOwner(selectedOwner);
                    ownerHelper.addOwner(newOwner);
                    obAlertMain = new Alert(Alert.AlertType.INFORMATION);
                    obAlertMain.setTitle("Success");
                    obAlertMain.setHeaderText("Your password has been changed");
                    obPassBox.getChildren().removeAll(lblCurPass, txtCurPass, lblPass1, txtPass1, lblPass2, txtPass2, btnOK, btnCancelPass);
                    toggleTextfields();
                }
            }else { //display error if the passwords don't match
                obAlertMain = new Alert(Alert.AlertType.ERROR);
                obAlertMain.setTitle("Password Error");
                obAlertMain.setHeaderText("Password not changed");
                obAlertMain.setContentText("Please ensure the passwords are at least 8 characters and that they match");
            }

        }
        else { //display error if the password is incorrect for the selected owner
            obAlertMain = new Alert(Alert.AlertType.ERROR);
            obAlertMain.setTitle("Password Error");
            obAlertMain.setHeaderText("Password not changed");
            obAlertMain.setContentText("The current password is incorrect");
        }
        obAlertMain.showAndWait();
        showAllOwners();
    }

    /**
     * toggle textfields editability
     */
    private void toggleTextfields()
    {
        for(Node field : obGPane.getChildren())
        {
            if((field instanceof TextField || field instanceof CheckBox) && !field.isDisabled())
            {
                field.setDisable(true);
            }
            else
            {
                field.setDisable(false);
            }
        }
    }

    /**
     * show all owners in the ownerlist in the list view
     */
    private void showAllOwners()
    {
        lvOwnerList.getItems().clear();

        for(Owner owner : owners)
        {
            lvOwnerList.getItems().add(owner);
        }
    }

    /**
     * clear all fields to be for new user creation
     */
    private void clearAllFields()
    {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtuserId.setText("");
        txtEmail.setText("");
        txtPhoneNum.setText("");
        txtPermissions.setText("");
        cbOnSite.setSelected(false);
    }

    /**
     * removes an owner from the ownerlist and then saves the new list to database
     * @param owner
     */
    private void removeOwner(Owner owner)
    {
        if(owner instanceof Owner)
        {
            obAlertMain = new Alert(Alert.AlertType.CONFIRMATION);
            obAlertMain.setTitle("Remove Employee");
            obAlertMain.setHeaderText("Selected employee will be removed");
            obAlertMain.setContentText("Confirm remove?");

            Optional<ButtonType> result = obAlertMain.showAndWait();

            if(result.get() == ButtonType.OK) {
                ownerHelper.removeOwner(owner);
                obAlertMain = new Alert(Alert.AlertType.INFORMATION);
                obAlertMain.setTitle("Success");
                obAlertMain.setHeaderText("Selected employee has been removed");
                obAlertMain.showAndWait();
            }
            else
            {
                showAllOwners();
            }
        }
        else
        {
            obAlertMain = new Alert(Alert.AlertType.ERROR);
            obAlertMain.setTitle("Remove Error");
            obAlertMain.setHeaderText("No employee selected");
            obAlertMain.setContentText("Please select an employee to continue");
            obAlertMain.showAndWait();
        }
        showAllOwners();
    }

    /**
     * populates all of the text fields with the data that can be modified in the edit method
     */
    private void populateFields()
    {
        Owner ownerToDisplay = (Owner) lvOwnerList.getSelectionModel().getSelectedItem();

        if(ownerToDisplay != null) {
            txtFirstName.setText(ownerToDisplay.getFirstName());
            txtLastName.setText(ownerToDisplay.getLastName());
            txtuserId.setText(ownerToDisplay.getUserId());
            txtEmail.setText(ownerToDisplay.getEmail());
            txtPhoneNum.setText(ownerToDisplay.getPhoneNumber());
            txtPermissions.setText(Integer.toString(ownerToDisplay.getPermissions()));
            cbOnSite.setSelected(ownerToDisplay.getOnSite());
        }
    }
}
