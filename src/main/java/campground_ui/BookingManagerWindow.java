package campground_ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * This class is responsible for everything that displays on the UI for Booking Manager Window
 */
public class BookingManagerWindow extends Application {

    private VBox obVPane;

    private Button btnAddBooking, btnRemoveBooking, btnModifyBooking, btnCurrentBookings, btnClose;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage obStage)
    {
        //Initialize layout
        obVPane = new VBox();

        //Initialize Buttons
        btnAddBooking = new Button("Add Booking");
        btnRemoveBooking = new Button("Remove Booking");
        btnModifyBooking = new Button("Modify Booking");
        btnCurrentBookings = new Button("View Bookings");
        btnClose = new Button("Close");

        //Add Buttons to VBox
        obVPane.getChildren().addAll(new Label("Select an option to Manage Bookings"), btnCurrentBookings, btnAddBooking, btnRemoveBooking, btnModifyBooking, btnClose);

        //Layout parameters for improved usability



        //Event Handlers

        obStage.setScene(new Scene(obVPane, 400, 500));
        obStage.setTitle("Cest Lake - Booking Manager");
        obStage.show();
    }
}
