package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This class is responsible for everything that displays on the UI for Booking Manager Window
 */
public class BookingManagerWindow extends Stage {

    private VBox obVPane;

    private Button btnAddBooking, btnRemoveBooking, btnModifyBooking, btnCurrentBookings, btnClose;

    private Label lblMain;

    public static void main(String[] args) {

        Application.launch(args);
    }

    /**
     * Initializes all UI components and controls
     * @param
     */
    public BookingManagerWindow (Stage parent)
    {
        //Initialize layout
        obVPane = new VBox();

        //Initialize Buttons
        btnAddBooking = new Button("Add Booking");
        btnRemoveBooking = new Button("Remove Booking");
        btnModifyBooking = new Button("Modify Booking");
        btnCurrentBookings = new Button("View Bookings");
        btnClose = new Button("Close");

        //Initialize Label
        lblMain = new Label("Select an option to Manage Bookings");

        //Add Buttons to VBox
        obVPane.getChildren().addAll(lblMain, btnCurrentBookings, btnAddBooking, btnRemoveBooking, btnModifyBooking, btnClose);

        //Layout parameters for improved usability
        obVPane.setAlignment(Pos.CENTER);
        obVPane.setSpacing(5);
        lblMain.setPadding(new Insets(0,0,20,0));
        lblMain.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");


        //Event Handlers
        btnAddBooking.setOnAction(e -> {

        });

        btnRemoveBooking.setOnAction(e -> {
            RemoveBookingWindow removeBookingWindow = new RemoveBookingWindow(parent);
            removeBookingWindow.showAndWait();
        });

        btnModifyBooking.setOnAction(e -> {

        });

        btnCurrentBookings.setOnAction(e -> {

        });

        btnClose.setOnAction(e -> {
            this.close();
        });

        this.setScene(new Scene(obVPane, 250, 250));
        this.setTitle("Cest Lake - Booking Manager");
        this.initOwner(parent);
        this.initModality(Modality.APPLICATION_MODAL);
    }




}
