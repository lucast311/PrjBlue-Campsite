package campground_ui;

import campground_data.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GuestManagerWindow extends Application
{
    private BorderPane borderPane = new BorderPane();
    private GridPane guestsPane = new GridPane();
    private Button btnSearch = new Button("Search");
    private Button btnViewAllGuests = new Button("View All Guests");
    private TextField txtSearchField = new TextField();
    private HBox topBox = new HBox();
    private HBox searchBox = new HBox();
    private HBox buttonsBox = new HBox();
    private Button btnAddGuest = new Button("Add Guest");
    private Button btnEditGuest = new Button("Edit Guest");
    private Button btnBack = new Button("Back");
    private GuestHelper guestHelper = new GuestHelper();
    private HBox spacerLeft = new HBox();
    private HBox spacerRight = new HBox();
    private Button btnRefresh = new Button("Refresh");
    private ArrayList<Guest> guests = guestHelper.getGuestAccounts();
    private int guestCount = (int) guests.stream().map(Guest::getPhoneNumber).distinct().count();
    private int nRow = 1;
    private ListView guestList = new ListView();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
//         Address dummyAddress = new Address(123, 100, "Macca Street", "Saskatoon", "SK", "Canada", "S7N2W6");
//         Guest dummyGuest = new Guest("Julius", "Tuba", "julius3591@saskpolytech.ca", "3061234567", PaymentType.Credit, "123412341234",dummyAddress);
//
//        guestHelper.addGuest(dummyGuest);
//        guestHelper.addGuest(dummyGuest);
        
        //set the sizes of buttons
        btnSearch.setPrefSize(110, 50);
        btnViewAllGuests.setPrefSize(150, 50);
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
        searchBox.getChildren().addAll(txtSearchField,btnSearch, btnRefresh, btnViewAllGuests);

        //container settings for the controls on the top of the window
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(50, 0, 50, 0));
        topBox.setSpacing(900);
        topBox.getChildren().addAll(searchBox, btnBack);

        //container settings for the controls on the bottom of the window
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(200);
        buttonsBox.setPadding(new Insets(50, 0, 50, 0));
        buttonsBox.getChildren().addAll(btnAddGuest,btnEditGuest);

        //container settings for the controls on the center of the window
        guestsPane.setAlignment(Pos.TOP_CENTER);
        guestsPane.setPadding(new Insets(0, 50, 0, 50));
        guestsPane.setStyle("-fx-border-color: black");

        //set the Hboxes inside the border pane
        borderPane.setCenter(guestList);
        borderPane.setTop(topBox);
        borderPane.setBottom(buttonsBox);

        guestList.setPrefHeight(200);
        guestList.setPrefWidth(600);
        guestList.setEditable(false);




        //set the headers for the list of guests
        setHeader(guestsPane);
        //sort guests by guestID (ascending)
        guests.sort((x,y) -> (int) x.getGuestID() - y.getGuestID());

        //loop through every guest and add populate the rows with guest information
        for (Guest obGuest : guests)
        {
            addRow(guestsPane, obGuest, nRow++);
            if (nRow > guestCount)
            {
                nRow = 1;
                break;
            }
        }
        btnViewAllGuests.setVisible(false);

        //call event handlers for the textfield and buttons
        txtSearchFieldOnAction(txtSearchField);
        btnSearchClicked(btnSearch);
        btnViewAllGuestsClicked(btnViewAllGuests);
        btnRefreshClicked(btnRefresh);

        //Event handler to allow you to open information on guest to edit
        btnEditGuest.setOnAction(e -> {
            int nIndex = guestList.getSelectionModel().getSelectedIndex();
            if (nIndex != -1)
            {
                Guest obGuest = guests.get(guestList.getSelectionModel().getSelectedIndex());
                EditGuestWindow guestWindow = new EditGuestWindow(obGuest, nIndex);
                guestWindow.initModality(Modality.WINDOW_MODAL);
                guestWindow.initOwner(primaryStage);

                guestWindow.show();
            }

        });

        primaryStage.setScene(new Scene(borderPane, 1200, 800));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Guest Manager");
        primaryStage.setOnShowing(e -> {
            loadGuests();
        });
        primaryStage.show();
    }


    /**
     * This method will set the headers for the first row
     * @param gridPane
     */
    public void setHeader(GridPane gridPane)
    {
        gridPane.add(new Text("Guest ID\t\t"), 0, 0);
        gridPane.add(new Text("First Name\t\t"), 1, 0);
        gridPane.add(new Text("Last Name\t\t"), 2, 0);
        gridPane.add(new Text("Email\t\t"), 3, 0);
        gridPane.add(new Text("Phone Number\t\t"), 4, 0);
        gridPane.add(new Text("Payment Method\t"), 5, 0);
        gridPane.add(new Text("Credit Card Number\t\t"), 6, 0);
        gridPane.add(new Text("Street Number\t\t"), 7, 0);
        gridPane.add(new Text("Apt Number\t\t"), 8, 0);
        gridPane.add(new Text("Street Name\t\t"), 9, 0);
        gridPane.add(new Text("City/Town\t\t"), 10, 0);
        gridPane.add(new Text("Province\t\t"), 11, 0);
        gridPane.add(new Text("Country\t\t"), 12, 0);
        gridPane.add(new Text("Postal Code\t\t"), 13, 0);
    }

    /**
     * Helper method to populate the subsequent rows with guest info
     * @param gridPane
     * @param obGuest
     * @param nRow
     */
    public void addRow(GridPane gridPane, Guest obGuest, int nRow)
    {
            gridPane.add(new Text(String.valueOf(obGuest.getGuestID())), 0, nRow);
            gridPane.add(new Text(obGuest.getFirstName()), 1, nRow);
            gridPane.add(new Text(obGuest.getLastName()), 2, nRow);
            gridPane.add(new Text(obGuest.getEmail() + "\t\t"), 3, nRow);
            gridPane.add(new Text(obGuest.getPhoneNumber()), 4, nRow);
            gridPane.add(new Text(String.valueOf(obGuest.getPaymentMethod())), 5, nRow);
            gridPane.add(new Text(obGuest.getCreditCardNum()), 6, nRow);

            //The Address object is broken down to its different attributes
            gridPane.add(new Text(String.valueOf(obGuest.getAddress().getStreetNum()) + "\t\t"), 7, nRow);
            gridPane.add(new Text(String.valueOf(obGuest.getAddress().getAptNum()) + "\t\t"), 8, nRow);
            gridPane.add(new Text(obGuest.getAddress().getStreetName() + "\t\t"), 9, nRow);
            gridPane.add(new Text(obGuest.getAddress().getCity_Town() + "\t\t"), 10, nRow);
            gridPane.add(new Text(obGuest.getAddress().getProvince() + "\t\t"), 11, nRow);
            gridPane.add(new Text(obGuest.getAddress().getCountry() + "\t\t"), 12, nRow);
            gridPane.add(new Text(obGuest.getAddress().getPostalCode() + "\t\t"), 13, nRow);
    }

    /**
     * Event handler for the text field. This method will search for a guest that corresponds to the phone number entered when the return key is pressed.
     * Will display an error alert if the text field is empty or if the phone number does not correspond to any guest.
     * @param textField
     */
    public void txtSearchFieldOnAction(TextField textField)
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

            Guest guest = guestHelper.searchGuest(txtSearchField.getText());

            if (guest == null)
            {
                Alert nullError = new Alert(Alert.AlertType.ERROR);
                nullError.setHeaderText("Guest does not exist");
                nullError.setContentText("The guest you are searching for is not in the database");
                nullError.showAndWait();
                txtSearchField.setText("");
                return;
            }

            //before populating the gridpane, clear everything first
            guestsPane.getChildren().clear();

            //set the headers
            setHeader(guestsPane);

            //populate the row after the header with the guest info
            addRow(guestsPane,guest,1);

            //reset the text field
            txtSearchField.setText("");

            //set btnViewAllGuests to be visible
            btnViewAllGuests.setVisible(true);

        });
    }

    /**
     * Event handler for the "View All Guests" button. This will display all the guests in the database when the button is clicked.
     * @param btn
     */
    public void btnViewAllGuestsClicked(Button btn)
    {
        btnViewAllGuests.setOnAction(e -> {
            txtSearchField.setText("");
            guestsPane.getChildren().clear();
            setHeader(guestsPane);
            for (Guest obGuest : guests)
            {
                addRow(guestsPane,obGuest,nRow++);
                if (nRow > guestCount)
                {
                    break;
                }
            }
            btnViewAllGuests.setVisible(false);
        });
    }

    /**
     * Event handler for the "Search" button. This method will search for a guest that corresponds to the phone number entered when the return key is pressed.
     * Will display an error alert if the text field is empty or if the phone number does not correspond to any guest.
     * @param btn
     */
    public void btnSearchClicked(Button btn)
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

            Guest guest = guestHelper.searchGuest(txtSearchField.getText());

            if (guest == null)
            {
                Alert nullError = new Alert(Alert.AlertType.ERROR);
                nullError.setHeaderText("Guest does not exist");
                nullError.setContentText("The guest you are searching for is not in the database");
                nullError.showAndWait();
                txtSearchField.setText("");
                return;
            }


            

            //before populating the gridpane, clear everything first
            guestsPane.getChildren().clear();

            //set the headers
            setHeader(guestsPane);

            //populate the row after the header with the guest info
            addRow(guestsPane,guest,1);

            //reset the text field
            txtSearchField.setText("");

            //set btnViewAllGuests to be visible
            btnViewAllGuests.setVisible(true);
        });
    }

    /**
     * Event handler for the "Refresh" button.
     * @param btn
     */
    public void btnRefreshClicked(Button btn)
    {
        btnRefresh.setOnAction(e -> {
            txtSearchField.setText("");
            guestsPane.getChildren().clear();
            setHeader(guestsPane);
            for (Guest obGuest : guests)
            {
                addRow(guestsPane,obGuest,nRow++);
                if (nRow > guestCount)
                {
                    break;
                }
            }
            btnViewAllGuests.setVisible(false);
        });
    }

    public void loadGuests()
    {

        guestList.getItems().clear();
        for (Guest guest : guests)
        {
            guestList.getItems().addAll(guest);
        }
    }





}
