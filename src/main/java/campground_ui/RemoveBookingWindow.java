package campground_ui;

import campground_data.Booking;
import campground_data.BookingHelper;
import campground_data.DatabaseFile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;


/**
 * This class is responsible for everything that is displayed on the Ui for remove booking
 */

public class RemoveBookingWindow extends Stage {

    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obButtonBox;

    private ListView taBookingList;

    private TextField txtGuestID, txtBookingID, txtAccommodationID, txtStartDate, txtEndDate, txtType, txtMemberCount,
            txtTotalPrice;
    private Button btnRemove, btnSearch, btnClose;

    private Alert obAlertMain;

    private BookingHelper helper = new BookingHelper();
    ArrayList<Booking> allBookings = helper.getBookingList();
    private DatabaseFile dbFile;


    public static void main(String[] args)
    {
        Application.launch(args);
    }

    /**
     * Initializes all UI components/controls
     * @param
     */
    public RemoveBookingWindow(Stage parent)
    {
        //Initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obButtonBox = new VBox();


        //Initializes top text area that displays list of bookings
        taBookingList = new ListView();
        taBookingList.setPrefWidth(600);
        taBookingList.setPrefHeight(200);
        taBookingList.setEditable(false);



        //Initializes text fields for the form
        txtGuestID = new TextField();
        txtBookingID = new TextField();
        txtAccommodationID = new TextField();
        txtStartDate = new TextField();
        txtEndDate = new TextField();
        txtType = new TextField();
        txtMemberCount = new TextField();
        txtTotalPrice = new TextField();

        //Add text fields to Grid Pane
        obGPane.add(new Label("Guest ID"), 0,0);
        obGPane.add(txtGuestID, 1, 0);
        obGPane.add(new Label("Booking ID"), 0, 1);
        obGPane.add(txtBookingID, 1, 1);
        obGPane.add(new Label("Accommodation ID"), 0, 2);
        obGPane.add(txtAccommodationID, 1, 2);
        obGPane.add(new Label("Start Date"), 0, 3);
        obGPane.add(txtStartDate, 1, 3);
        obGPane.add(new Label("End Date"),0, 4);
        obGPane.add(txtEndDate, 1, 4);
        obGPane.add(new Label("Type"),0, 5);
        obGPane.add(txtType, 1, 5);
        obGPane.add(new Label("Member Count"), 0, 6);
        obGPane.add(txtMemberCount, 1, 6);
        obGPane.add(new Label("Total Price"), 0, 7);
        obGPane.add(txtTotalPrice, 1, 7);

        //Disable all fields except Guest ID Field
        for(Node arg : obGPane.getChildren())
        {
            if(arg instanceof TextField)
            {
                arg.setDisable(true);
            }
        }
        txtGuestID.setDisable(false);


        //Initialize Buttons
        btnRemove = new Button("Remove");
        btnSearch = new Button("Search");
        btnClose = new Button("Close");

        //Add Buttons to VBox
        obButtonBox.getChildren().addAll(btnRemove, btnSearch, btnClose);


        //Layout parameters for improved UI design
        obBPane.setPadding(new Insets(10));
        this.setMinWidth(1000);
        this.setMinHeight(510);

        obGPane.setHgap(10);
        obGPane.setVgap(5);
        obGPane.setPadding(new Insets(15,0,0,10));

        obButtonBox.setSpacing(5);
        obButtonBox.setPadding(new Insets(159, 0, 0, 15));

        btnRemove.setStyle("-fx-background-color: indianred; -fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 3;");
        btnSearch.setStyle("-fx-background-color: lightgreen; -fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 3;");
        btnClose.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 3;");


        //Setting other Panes into main Border Pane
        obBPane.setTop(taBookingList);
        obBPane.setLeft(obGPane);
        obBPane.setCenter(obButtonBox);


        //Event Handlers section
        btnClose.setOnAction(e -> {
            this.close();
        });

        btnRemove.setOnAction(e -> {
            removeBooking();
        });

        taBookingList.getSelectionModel().selectedItemProperty().addListener(e -> {
            populateFields();
        });

        btnSearch.setOnAction(e -> {
            searchBooking();
        });


        this.setScene(new Scene(obBPane, 1000, 500));
        this.setTitle("Cest Lake - Remove Booking");
        this.setOnShowing(e -> {
            loadAllBookings();
        });
        this.initOwner(parent);
        this.initModality(Modality.WINDOW_MODAL);
    }


    //Loads all the bookings onto the GUI text area
    public void loadAllBookings()
    {
        taBookingList.getItems().clear();

        for(Booking obVal : allBookings)
        {
            taBookingList.getItems().add(obVal);
        }

    }


    //Removes booking from list
    public void removeBooking()
    {
        Booking obFound = (Booking)taBookingList.getSelectionModel().getSelectedItem();

        if (obFound instanceof Booking)
        {
            obAlertMain = new Alert(Alert.AlertType.CONFIRMATION);
            obAlertMain.setTitle("Remove Confirmation");
            obAlertMain.setHeaderText("Selected booking will be removed");
            obAlertMain.setContentText("Continue with deletion?");

            Optional<ButtonType> result = obAlertMain.showAndWait();

            if (result.get() == ButtonType.OK){
                helper.removeBooking(obFound);

                obAlertMain = new Alert(Alert.AlertType.INFORMATION);
                obAlertMain.setTitle("Success");
                obAlertMain.setHeaderText("Selected booking has been successfully removed");
                obAlertMain.showAndWait();
                txtGuestID.requestFocus();
            }
            else
            {
                loadAllBookings();
            }

        }
        else
        {
            obAlertMain = new Alert(Alert.AlertType.ERROR);
            obAlertMain.setTitle("Remove Error");
            obAlertMain.setHeaderText("No booking selected");
            obAlertMain.setContentText("Please select a booking to continue");
            obAlertMain.showAndWait();
            txtGuestID.requestFocus();
        }

        loadAllBookings();
    }

    //Populates text fields with information from selected object
    public void populateFields()
    {
        Booking obBookingToDisplay = (Booking) taBookingList.getSelectionModel().getSelectedItem();

        txtGuestID.setText(Integer.toString(obBookingToDisplay.getGuestID()));
        txtBookingID.setText(Integer.toString(obBookingToDisplay.getBookingID()));
        txtAccommodationID.setText(Integer.toString(obBookingToDisplay.getPlotID()));

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d");
        int nYearStart = obBookingToDisplay.getStartDate().getYear();
        int nYearEnd = obBookingToDisplay.getEndDate().getYear();

        txtStartDate.setText(dateFormat.format(obBookingToDisplay.getStartDate()) + ", " + nYearStart);
        txtEndDate.setText(dateFormat.format(obBookingToDisplay.getEndDate()) + ", " + nYearEnd);
        txtType.setText(obBookingToDisplay.getType().toString());
        txtMemberCount.setText(Integer.toString(obBookingToDisplay.getMemberCount()));
        txtTotalPrice.setText("$" + Double.toString(obBookingToDisplay.getTotal()));

    }


    //Searches booking from list then selects it on the Text View
    public void searchBooking()
    {
        String sGuestIdToSearch = txtGuestID.getText().trim();
        if (!sGuestIdToSearch.matches(".*[0-9].*"))
        {
            obAlertMain = new Alert(Alert.AlertType.ERROR);
            obAlertMain.setTitle("Input Error");
            obAlertMain.setHeaderText("Invalid Guest ID");
            obAlertMain.setContentText("Please only enter numbers in the Guest ID field");
            obAlertMain.showAndWait();
            txtGuestID.requestFocus();

        }

        //Error if guest ID field is empty
        else if (sGuestIdToSearch.equalsIgnoreCase("")|| sGuestIdToSearch == null)
        {
            obAlertMain = new Alert(Alert.AlertType.ERROR);
            obAlertMain.setTitle("Input Error");
            obAlertMain.setHeaderText("Guest ID field cannot be empty");
            obAlertMain.setContentText("Enter a Guest ID to continue");
            obAlertMain.showAndWait();
            txtGuestID.requestFocus();
        }
        else
        {
            int nGuestIdToSearch = Integer.parseInt(txtGuestID.getText());

            if (nGuestIdToSearch < 1)
            {
                obAlertMain = new Alert(Alert.AlertType.ERROR);
                obAlertMain.setTitle("Input Error");
                obAlertMain.setHeaderText("Guest ID cannot be less than 1");
                obAlertMain.setContentText("Please enter a Guest ID that is more than or equal to 1");
                obAlertMain.showAndWait();
                txtGuestID.requestFocus();
            }
            else
            {
                Booking obBookingToSearch = helper.search(nGuestIdToSearch);

                if(obBookingToSearch == null)
                {
                    obAlertMain = new Alert(Alert.AlertType.ERROR);
                    obAlertMain.setTitle("Search Failed");
                    obAlertMain.setHeaderText("No booking with the entered Guest ID found");
                    obAlertMain.setContentText("Please enter another Guest ID");
                    obAlertMain.showAndWait();
                    txtGuestID.requestFocus();
                }
                else
                {
                    int nIndexOfObject = allBookings.indexOf(obBookingToSearch);

                    taBookingList.getSelectionModel().select(nIndexOfObject);
                    taBookingList.getFocusModel().focus(nIndexOfObject);
                }
            }
        }


    }






















}
