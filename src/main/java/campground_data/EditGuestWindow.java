package campground_data;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will be the window that opens up when edit guest is clicked on the guest manager window.
 * This will effectively allow changes to be made a specific guest and will save those changes to the
 * database.
 */
public class EditGuestWindow extends Stage {

    private GridPane obPane = new GridPane();

    //Text fields
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField emailField = new TextField();
    private TextField phoneNumberField = new TextField();
    private TextField creditCardNumField = new TextField();
    private TextField streetNumField = new TextField();
    private TextField aptNumField = new TextField();
    private TextField streetNameField = new TextField();
    private TextField cityTownField = new TextField();
    private TextField provinceField = new TextField();
    private TextField countryField = new TextField();
    private TextField postalCodeField = new TextField();
    private TextField memberCountField = new TextField();

    //Labels for corresponding text fields
    private Label firstNameLabel = new Label("First Name:\t");
    private Label lastNameLabel = new Label("Last Name:\t");
    private Label emailLabel = new Label("Email:\t");
    private Label phoneNumberLabel = new Label("Phone Number:\t");
    private Label creditCardNumLabel = new Label("Credit Card Number:\t");
    private Label streetNumLabel = new Label("Street Number:\t");
    private Label aptNumLabel = new Label("Apt Number:\t");
    private Label streetNameLabel = new Label("Street Name:\t");
    private Label cityTownLabel = new Label("City/Town:\t");
    private Label provinceLabel = new Label("Province:\t");
    private Label countryLabel = new Label("Country:\t");
    private Label postalCodeLabel = new Label("Postal Code:\t");

    //Titles for each section
    private Text txtContactInformation = new Text("Contact Information");
    private Text txtPaymentInformation = new Text("Payment Information");
    private Text txtAddressInformation = new Text("Address Information");
    private Text txtCampingInformation = new Text("Camping Information");

    //Main Title for window
    private Text txtWindowInformation = new Text("Edit Guest Information");

    //Combobox and title for the payment method section
    private ComboBox<PaymentType> cboPaymentMethod = new ComboBox<>();
    private Label paymentMethodLabel = new Label("Payment Method:\t");

    //Buttons to save and cancel
    private Button btnSaveChanges = new Button();
    private Button btnCancelChanges = new Button();

    //Guest object that will be edited
    private Guest obGuest;

    //Validation helper to assist in ensuring that the guest objects are valid
    private ValidationHelper vh = new ValidationHelper();

    //Database file to write guest changes to
    private DatabaseFile dbfile = new DatabaseFile();

    /**
     * Constructor that puts together the window, as well as the event handlers for the buttons.
     * @param guest - guest that is passed in from the form on the view guest list window
     * @param nIndex - Index of the arraylist from the view guest list window
     */
    public EditGuestWindow(Guest guest, int nIndex)
    {
        this.obGuest = guest;

        //Assigning labels to the buttons
        btnSaveChanges.setText("Save Changes");
        btnCancelChanges.setText("Cancel Changes");

        btnCancelChanges.setStyle("-fx-background-color: Red");

        //Setting default text to the text fields from the passed in guest
        firstNameField.setText(guest.getFirstName());
        lastNameField.setText(guest.getLastName());
        emailField.setText(guest.getEmail());
        phoneNumberField.setText(guest.getPhoneNumber());
        creditCardNumField.setText(guest.getCreditCardNum());
        streetNameField.setText(guest.getAddress().getStreetName());
        streetNumField.setText(String.valueOf(guest.getAddress().getStreetNum()));
        aptNumField.setText(String.valueOf(guest.getAddress().getAptNum()));
        cityTownField.setText(guest.getAddress().getCity_Town());
        provinceField.setText(guest.getAddress().getProvince());
        countryField.setText(guest.getAddress().getCountry());
        postalCodeField.setText(guest.getAddress().getPostalCode());

        //Populating the combobox
        cboPaymentMethod.getItems().setAll(FXCollections.observableArrayList(PaymentType.values()));
        //Setting the default selected value to the value that was passed in from the guest
        cboPaymentMethod.setValue(guest.getPaymentMethod());

        //If the payment type passed in was credit, then it will disable the text field
        //This will not allow it to be changed if the guest has a different payment method
        if (cboPaymentMethod.getValue() == PaymentType.Credit)
        {
            creditCardNumField.setDisable(false);
        }
        else
        {
            creditCardNumField.setDisable(true);
        }

        //Setting fonts for the titles of each section
        txtContactInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        txtAddressInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        txtCampingInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        txtPaymentInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        //Setting fonts for main title
        txtWindowInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        //Constructing the GUI
        obPane.add(txtWindowInformation, 0, 0);

        obPane.add(txtContactInformation,0, 1);

        obPane.add(firstNameLabel, 0, 2);
        obPane.add(firstNameField, 1,2);

        obPane.add(lastNameLabel, 0, 3);
        obPane.add(lastNameField, 1, 3);

        obPane.add(emailLabel, 0, 4);
        obPane.add(emailField, 1, 4);

        obPane.add(phoneNumberLabel, 0, 5);
        obPane.add(phoneNumberField, 1, 5);

        obPane.add(txtPaymentInformation, 2, 1);

        obPane.add(paymentMethodLabel, 2, 2);
        obPane.add(cboPaymentMethod, 3, 2);

        obPane.add(creditCardNumLabel, 2, 3);
        obPane.add(creditCardNumField, 3, 3);

        obPane.add(txtAddressInformation, 0, 9);

        obPane.add(streetNumLabel, 0, 10);
        obPane.add(streetNumField, 1, 10);

        obPane.add(aptNumLabel, 0, 11);
        obPane.add(aptNumField, 1, 11);

        obPane.add(streetNameLabel, 0, 12);
        obPane.add(streetNameField, 1, 12);

        obPane.add(cityTownLabel, 0, 13);
        obPane.add(cityTownField, 1, 13);

        obPane.add(provinceLabel, 2, 10);
        obPane.add(provinceField, 3,10);

        obPane.add(countryLabel, 2, 11);
        obPane.add(countryField, 3, 11);

        obPane.add(postalCodeLabel, 2, 12);
        obPane.add(postalCodeField, 3, 12);

        obPane.setHgap(10);
        obPane.setVgap(5);

        obPane.add(btnSaveChanges, 1, 18);
        obPane.add(btnCancelChanges, 2, 18);

        //Taking in the database file and creating an arrayList from it
        ArrayList<Guest> guestList = dbfile.readGuests();

        obPane.setPadding(new Insets(10, 10, 10, 10));
        this.setTitle("Edit Guest - Cest Campgrounds and Cabins");
        this.setResizable(false);
        this.setScene(new Scene(obPane, 800, 400));

        //Event handler for payment method cbo. This will check to see
        //which value is selected and will disable the textfield accordingly
        cboPaymentMethod.setOnAction(e -> {
            if (cboPaymentMethod.getValue() != PaymentType.Credit)
            {
                creditCardNumField.setDisable(true);
            }
            else { creditCardNumField.setDisable(false); }
        });

        //event handler for save button
        btnSaveChanges.setOnAction(e -> {

            //Create dummy address
            Address dummyAddress = new Address(Integer.parseInt(streetNumField.getText()), Integer.parseInt(aptNumField.getText()),
                    streetNameField.getText(), cityTownField.getText(), provinceField.getText(), countryField.getText(), postalCodeField.getText());

            //Create dummy guest with dummyAddress assigned
            Guest obGuestDummy = new Guest(firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneNumberField.getText(),
                    cboPaymentMethod.getValue(), creditCardNumField.isDisabled() ? "0000 0000 0000 0000" : creditCardNumField.getText(), dummyAddress);

            //Checking to see if the guest object and address is valid, if not then it will display error messages
            if (vh.isValid(obGuestDummy) && vh.isValid(obGuestDummy.getAddress()))
            {
                //set the value of the guest element at the passed in index to the dummy guest, replacing the original
                guestList.set(nIndex, obGuestDummy);
                //save to database file
                dbfile.saveRecords(guestList);
                this.close();
                //display success message
                Alert successWindow = new Alert(Alert.AlertType.CONFIRMATION, "Changes Saved!");
                successWindow.show();
            }
            else
            {
                Text obText = new Text("");
                String sVal = "";

                HashMap<String, String> errors = vh.getErrors(obGuestDummy);
                //This will print out all the errors in the guest object
                for (String error : errors.values())
                {
                    sVal += error + "\n";
                }

                errors = vh.getErrors(obGuestDummy.getAddress());

                //This will print out all of the errors in the Address object contained in guest
                for (String error : errors.values())
                {
                    sVal += error + "\n";
                }

                obText.setText(sVal);
                Alert errorWindow = new Alert(Alert.AlertType.ERROR, sVal);
                errorWindow.show();
            }
        });

        //Simply closes the window without saving any changes
        btnCancelChanges.setOnAction(e -> {
            this.close();
        });
    }
}
