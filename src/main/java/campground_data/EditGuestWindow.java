package campground_data;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class EditGuestWindow extends Stage {

    private GridPane obPane = new GridPane();

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
    private Label memberCountLabel = new Label("Member Count:\t");

    private Text txtContactInformation = new Text("Contact Information");
    private Text txtPaymentInformation = new Text("Payment Information");
    private Text txtAddressInformation = new Text("Address Information");
    private Text txtCampingInformation = new Text("Camping Information");



    private ComboBox<PaymentType> cboPaymentMethod = new ComboBox<>();
    private Label paymentMethodLabel = new Label("Payment Method:\t");

    private Button btnSaveChanges = new Button();
    private Button btnCancelChanges = new Button();

    private Guest obGuest;

    private ValidationHelper vh = new ValidationHelper();

    public EditGuestWindow(Guest guest)
    {
        this.obGuest = guest;

        btnSaveChanges.setText("Save Changes");
        btnCancelChanges.setText("Cancel Changes");

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
        memberCountField.setText(String.valueOf(guest.getMemberCount()));

        cboPaymentMethod.getItems().setAll(FXCollections.observableArrayList(PaymentType.values()));
        cboPaymentMethod.setValue(guest.getPaymentMethod());

        if (cboPaymentMethod.getValue() == PaymentType.Credit)
        {
            creditCardNumField.setDisable(false);
        }
        else
        {
            creditCardNumField.setDisable(true);
        }

        txtContactInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        txtAddressInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        txtCampingInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        txtPaymentInformation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        obPane.add(txtContactInformation,0, 0);

        obPane.add(firstNameLabel, 0, 1);
        obPane.add(firstNameField, 1,1);

        obPane.add(lastNameLabel, 0, 2);
        obPane.add(lastNameField, 1, 2);

        obPane.add(emailLabel, 0, 3);
        obPane.add(emailField, 1, 3);

        obPane.add(phoneNumberLabel, 0, 4);
        obPane.add(phoneNumberField, 1, 4);

        obPane.add(txtPaymentInformation, 0, 5);

        obPane.add(paymentMethodLabel, 0, 6);
        obPane.add(cboPaymentMethod, 1, 6);

        obPane.add(creditCardNumLabel, 0, 7);
        obPane.add(creditCardNumField, 1, 7);

        obPane.add(txtAddressInformation, 0, 8);

        obPane.add(streetNumLabel, 0, 9);
        obPane.add(streetNumField, 1, 9);

        obPane.add(aptNumLabel, 0, 10);
        obPane.add(aptNumField, 1, 10);

        obPane.add(streetNameLabel, 0, 11);
        obPane.add(streetNameField, 1, 11);

        obPane.add(cityTownLabel, 0, 12);
        obPane.add(cityTownField, 1, 12);

        obPane.add(provinceLabel, 2, 9);
        obPane.add(provinceField, 3,9);

        obPane.add(countryLabel, 2, 10);
        obPane.add(countryField, 3, 10);

        obPane.add(postalCodeLabel, 2, 11);
        obPane.add(postalCodeField, 3, 11);

        obPane.add(txtCampingInformation, 2, 0);

        obPane.add(memberCountLabel, 2, 1);
        obPane.add(memberCountField, 3, 1);

        obPane.setHgap(10);
        obPane.setVgap(5);

        obPane.add(btnSaveChanges, 1, 17);
        obPane.add(btnCancelChanges, 2, 17);

        this.setTitle("Edit Guest - Cest Campgrounds and Cabins");
        this.setResizable(false);
        this.setScene(new Scene(obPane, 600, 450));

        cboPaymentMethod.setOnAction(e -> {
            if (cboPaymentMethod.getValue() != PaymentType.Credit)
            {
                creditCardNumField.setDisable(true);
            }
            else { creditCardNumField.setDisable(false); }
        });

        btnSaveChanges.setOnAction(e -> {
            Address dummyAddress = new Address(Integer.parseInt(streetNumField.getText()), Integer.parseInt(aptNumField.getText()),
                    streetNameField.getText(), cityTownField.getText(), provinceField.getText(), countryField.getText(), postalCodeField.getText());

            Guest obGuestDummy = new Guest(firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneNumberField.getText(),
                    cboPaymentMethod.getValue(), creditCardNumField.isDisabled() ? "0000000000000000" : creditCardNumField.getText(),
                    Integer.parseInt(memberCountField.getText()), dummyAddress);

            if (vh.isValid(obGuestDummy))
            {
                obGuest = obGuestDummy;
                Stage newStage = new Stage();
                BorderPane obPane = new BorderPane();
                Text obText = new Text("Changes Saved!");
                VBox vBox = new VBox();
                obPane.setTop(vBox);
                vBox.getChildren().add(obText);

                newStage.setTitle("Success");
                newStage.setScene(new Scene(obPane, 60, 60));
                this.close();
                newStage.show();
            }
            else
            {
                Stage newStage = new Stage();
                BorderPane obPane = new BorderPane();
                Text obText = new Text("");
                String sVal = "";
                VBox vBox = new VBox();
                obPane.setTop(vBox);

                HashMap<String, String> errors = vh.getErrors(obGuestDummy);
                for (String error : errors.values())
                {
                    sVal += error + "\n";
                }

                obText.setText(sVal);
                vBox.getChildren().add(obText);

                newStage.setTitle("Error");
                newStage.setScene(new Scene(obPane, 60, 60));
                newStage.show();
            }
        });

        btnCancelChanges.setOnAction(e -> {
            this.close();
        });
    }
}
