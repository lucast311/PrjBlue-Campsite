package campground_ui;

import campground_data.Guest;
import campground_data.GuestHelper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GuestManagerWindow extends Application
{
    private BorderPane borderPane = new BorderPane();
    private GridPane guestsPane = new GridPane();
    private ArrayList<Guest> guests = new ArrayList<>();
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

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        //set the sizes of buttons
        btnSearch.setPrefSize(110, 50);
        btnViewAllGuests.setPrefSize(150, 50);
        btnBack.setPrefSize(100, 50);
        btnAddGuest.setPrefSize(110, 50);
        btnEditGuest.setPrefSize(110, 50);

        //set properties for txtSearchField
        txtSearchField.setPromptText("Enter guest's phone number");
        txtSearchField.setPrefSize(200, 50);
        txtSearchField.setFocusTraversable(false);

        //this HBox will contain txtsearchField, btnSearch, and btnViewAllGuests
        searchBox.setSpacing(10);
        searchBox.getChildren().addAll(txtSearchField,btnSearch, btnViewAllGuests);

        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(50, 0, 50, 0));
        topBox.setSpacing(1000);
        topBox.getChildren().addAll(searchBox, btnBack);

        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(200);
        buttonsBox.setPadding(new Insets(50, 0, 50, 0));
        buttonsBox.getChildren().addAll(btnAddGuest,btnEditGuest);

        guestsPane.setAlignment(Pos.TOP_CENTER);
        guestsPane.setPadding(new Insets(0, 50, 0, 50));
        guestsPane.setStyle("-fx-border-color: black");




        borderPane.setCenter(guestsPane);
        borderPane.setTop(topBox);
        borderPane.setBottom(buttonsBox);



        //set the headers for the list of guests
        setHeader(guestsPane);


        int nRow = 1;
        ArrayList<Guest> guests = guestHelper.getGuestAccounts();
        guests.sort((x,y) -> (int) x.getGuestID() - y.getGuestID());

        //count the number of guests in the guests ArrayList
        int guestCount = (int) guests.stream().map(Guest::getPhoneNumber).distinct().count();

        //loop through every guest and add populate the rows with guest information
        for (Guest obGuest : guests)
        {
            addRow(guestsPane, obGuest, nRow++);
            if (nRow > guestCount)
            {
                break;
            }
        }

        txtSearchField.setOnAction(e -> {
            if (txtSearchField.getText() == null)
            {

            }

            Guest guest = guestHelper.searchGuest(txtSearchField.getText());

            if (guest == null)
            {
                Alert
            }

        });

        btnSearch.setOnAction(e -> {

        });




        primaryStage.setScene(new Scene(borderPane, 1200, 800));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Guest Manager");
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



}
