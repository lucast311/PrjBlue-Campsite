package campground_ui;

import campground_data.Guest;
import campground_data.GuestHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GuestManagerWindow extends Stage
{
    private BorderPane borderPane = new BorderPane();
    private Button btnSearch = new Button("Search");
    private TextField txtSearchField = new TextField();
    private HBox topBox = new HBox();
    private HBox searchBox = new HBox();
    private HBox buttonsBox = new HBox();
    private Button btnAddGuest = new Button("Add Guest");
    private Button btnEditGuest = new Button("Edit Guest");
    private Button btnBack = new Button("Back");
    private GuestHelper guestHelper = new GuestHelper();
    private Button btnRefresh = new Button("Refresh");
    private ArrayList<Guest> guests = guestHelper.getGuestAccounts();
    private ListView guestList = new ListView();

    public GuestManagerWindow(Stage parent)
    {

        /*
        This section was used to test the functions of the window

        Address dummyAddress = new Address(123, 100, "Macca Street", "Saskatoon", "SK", "Canada", "S7N2W6");
        Address dummyAddress2 = new Address(1235, 1999, "Nacho Business Streets", "Saskatoon", "SK", "Canada", "S7N2W6");
        Guest dummyGuest = new Guest("Captain", "Crunch", "capncrunch@saskpolytech.ca", "3061234567", PaymentType.Credit, "123412341234",dummyAddress);
        Guest dummyGuest2 = new Guest("Honey Nut", "Cheerios", "cheerio@saskpolytech.ca", "3061234562", PaymentType.Credit, "123412341234",dummyAddress2);
        guestHelper.addGuest(dummyGuest);
        guestHelper.addGuest(dummyGuest2);
        guestHelper.addGuest(dummyGuest2);
        guestHelper.addGuest(dummyGuest2);
        guestHelper.addGuest(dummyGuest);
        guestHelper.addGuest(dummyGuest);

        */


        //set the sizes of buttons
        btnSearch.setPrefSize(110, 50);
        btnBack.setPrefSize(100, 50);
        btnAddGuest.setPrefSize(110, 50);
        btnEditGuest.setPrefSize(110, 50);
        btnRefresh.setPrefSize(110, 50);

        //set properties for txtSearchField
        txtSearchField.setPromptText("Enter guest's phone number");
        txtSearchField.setPrefSize(200, 50);
        txtSearchField.setFocusTraversable(false);

        //this HBox will contain txtsearchField, btnSearch, and btnViewAllGuests
        searchBox.setSpacing(10);
        searchBox.getChildren().addAll(txtSearchField,btnSearch, btnRefresh);

        //container settings for the controls on the top of the window
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(50, 0, 50, 0));
        topBox.setSpacing(200);
        topBox.getChildren().addAll(searchBox, btnBack);

        //container settings for the controls on the bottom of the window
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(200);
        buttonsBox.setPadding(new Insets(50, 0, 50, 0));
        buttonsBox.getChildren().addAll(btnAddGuest,btnEditGuest);

        //set the properties of the ListView
        guestList.setEditable(false);

        //set the Hboxes and ListView inside the border pane
        borderPane.setCenter(guestList);
        borderPane.setTop(topBox);
        borderPane.setBottom(buttonsBox);




        //sort guests by guestID (ascending)
        guests.sort((x,y) -> (int) x.getGuestID() - y.getGuestID());
        loadGuests();


        /*Event Handlers*/
        btnBack.setOnAction(e -> { this.close(); });
        //call event handler methods for the textfield and buttons
        txtSearchFieldOnAction();
        btnSearchClicked();
        btnRefreshClicked();
        btnAddGuest.setOnAction(e -> {
            AddGuestWindow addGuestWindow = new AddGuestWindow(parent);
            addGuestWindow.initModality(Modality.APPLICATION_MODAL);
            addGuestWindow.initOwner(parent);
            addGuestWindow.show();
        });

        //call event handlers for the textfield and buttons
        txtSearchFieldOnAction();
        btnSearchClicked();
        btnRefreshClicked();

        this.setScene(new Scene(borderPane, 800, 600));
        this.setTitle("Guest Manager");
        this.initOwner(parent);
        this.initModality(Modality.APPLICATION_MODAL);
        //Event handler to allow you to open information on guest to edit
        btnEditGuest.setOnAction(e -> {
            int nIndex = guestList.getSelectionModel().getSelectedIndex();
            if (nIndex != -1)
            {
                Guest obGuest = guests.get(guestList.getSelectionModel().getSelectedIndex());
                EditGuestWindow guestWindow = new EditGuestWindow(obGuest, nIndex);
                guestWindow.initModality(Modality.APPLICATION_MODAL);
                guestWindow.initOwner(parent);

                guestWindow.show();
            }

        });

//        parent.setScene(new Scene(borderPane, 800, 600));
//        parent.setTitle("Guest Manager");
//        parent.show();
    }





    /**
     * Event handler for the text field. This method will search for a guest that corresponds to the phone number entered when the return key is pressed.
     * Will display an error alert if the text field is empty or if the phone number does not correspond to any guest
     */
    public void txtSearchFieldOnAction()
    {
        txtSearchField.setOnAction(e -> {
            if (txtSearchField.getText().equalsIgnoreCase(""))
            {
                Alert nullError = new Alert(Alert.AlertType.ERROR);
                nullError.setHeaderText("No phone number entered");
                nullError.setContentText("Please enter a guest's phone number");
                nullError.showAndWait();
                txtSearchField.setText("");
                return;
            }

            if (txtSearchField.getText().length() < 10 && txtSearchField.getText().length() > 0)
            {
                Alert shortStringError = new Alert(Alert.AlertType.ERROR);
                shortStringError.setHeaderText("Phone number too short");
                shortStringError.setContentText("Phone number must at least 10 digits");
                shortStringError.showAndWait();
                txtSearchField.setText("");
                return;
            }

            if (guestHelper.searchGuest(txtSearchField.getText()) == null)
            {
                Alert nullError = new Alert(Alert.AlertType.ERROR);
                nullError.setHeaderText("Guest does not exist");
                nullError.setContentText("The guest you are searching for is not in the database");
                nullError.showAndWait();
                txtSearchField.setText("");
                return;
            }

            //clear the listview first
            guestList.getItems().clear();

            for (Guest guest : guests)
            {
                if(guest.getPhoneNumber().equals(txtSearchField.getText()))
                {
                    //add the guest info to the list view
                    guestList.getItems().add(guest);
                }
            }

            txtSearchField.setText("");
        });
    }



    /**
     * Event handler for the "Search" button. This method will search for a guest that corresponds to the phone number entered when the return key is pressed.
     * Will display an error alert if the text field is empty or if the phone number does not correspond to any guest.
     */
    public void btnSearchClicked()
    {
        btnSearch.setOnAction(e -> {
            if (txtSearchField.getText().equalsIgnoreCase(""))
            {
                Alert nullError = new Alert(Alert.AlertType.ERROR);
                nullError.setHeaderText("No phone number entered");
                nullError.setContentText("Please enter a guest's phone number");
                nullError.showAndWait();
                txtSearchField.setText("");
                return;
            }

            if (txtSearchField.getText().length() < 10 && txtSearchField.getText().length() > 0)
            {
                Alert shortStringError = new Alert(Alert.AlertType.ERROR);
                shortStringError.setHeaderText("Phone number too short");
                shortStringError.setContentText("Phone number must at least 10 digits");
                shortStringError.showAndWait();
                txtSearchField.setText("");
                return;
            }

            if (guestHelper.searchGuest(txtSearchField.getText()) == null)
            {
                Alert nullError = new Alert(Alert.AlertType.ERROR);
                nullError.setHeaderText("Guest does not exist");
                nullError.setContentText("The guest you are searching for is not in the database");
                nullError.showAndWait();
                txtSearchField.setText("");
                return;
            }

            //clear the listview first
            guestList.getItems().clear();

            for (Guest guest : guests)
            {
                if(guest.getPhoneNumber().equals(txtSearchField.getText()))
                {
                    //add the guest info to the list view
                    guestList.getItems().add(guest);
                }
            }

            txtSearchField.setText("");
        });
    }

    /**
     * Event handler for the "Refresh" button.
     */
    public void btnRefreshClicked()
    {
        btnRefresh.setOnAction(e -> {
            txtSearchField.setText("");
            guestHelper.updateGuests();
            guests = guestHelper.getGuestAccounts();
            loadGuests();

        });
    }

    /**
     * This method will populate the guestList ListView with all the guests contained in the arrayList guests
     */
    public void loadGuests()
    {
        guestHelper.updateGuests();
        guests = guestHelper.getGuestAccounts();
        guestList.getItems().clear();
        for (Guest guest : guests)
        {
            guestList.getItems().add(guest);
        }
    }

}
