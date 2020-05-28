package campground_data;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

    private ComboBox<PaymentType> paymentMethodBox = new ComboBox<>();

    private Button saveChanges;
    private Button cancelChanges;

    private Guest obGuest;

    private ValidationHelper vh = new ValidationHelper();

    public EditGuestWindow(Guest guest)
    {
        this.obGuest = guest;

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

        paymentMethodBox.getItems().setAll(FXCollections.observableArrayList(PaymentType.values()));
        paymentMethodBox.setValue(guest.getPaymentMethod());

        obPane.add(firstNameField, 0,0);
        obPane.add(lastNameField, 0, 1);
        obPane.add(emailField, 0, 2);
        obPane.add(phoneNumberField, 0, 3);
        obPane.add(creditCardNumField, 0, 4);
        obPane.add(streetNumField, 0, 5);
        obPane.add(aptNumField, 0, 6);
        obPane.add(streetNameField, 0, 7);
        obPane.add(cityTownField, 0, 8);
        obPane.add(provinceField, 0,9);
        obPane.add(countryField, 0, 10);
        obPane.add(postalCodeField, 0, 11);
        obPane.add(memberCountField, 1, 0);
        obPane.add(paymentMethodBox, 0, 12);

        this.setScene(new Scene(obPane, 450, 450));


    }
}
