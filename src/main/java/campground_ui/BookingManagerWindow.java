package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This class is responsible for everything that displays on the UI for Booking Manager Window
 */
public class BookingManagerWindow extends Stage {

    private VBox obVPane;
    private GridPane obGPane;
    private BorderPane obBPane;

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
        obGPane = new GridPane();
        obBPane = new BorderPane();

        //Initialize Buttons
        btnAddBooking = new Button("Add Booking");
        btnRemoveBooking = new Button("Remove Booking");
        btnModifyBooking = new Button("Modify Booking");
        btnCurrentBookings = new Button("View Bookings");
        btnClose = new Button("Close");

        //Initialize Label
        lblMain = new Label("Select an option to Manage Bookings");

        //Add Buttons to VBox
        obVPane.getChildren().addAll(lblMain);

        //Add components to GridPane
        obGPane.add(btnAddBooking, 0 , 0);
        obGPane.add(btnRemoveBooking, 1 , 0);
        obGPane.add(btnModifyBooking, 0 , 1);
        obGPane.add(btnCurrentBookings, 1 , 1);
        obGPane.add(btnClose, 1 , 2);


        //Layout parameters for improved usability
        obVPane.setAlignment(Pos.CENTER);
        obVPane.setSpacing(5);
        lblMain.setPadding(new Insets(20,0,0,0));
        lblMain.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");

        obGPane.setAlignment(Pos.CENTER);
        obGPane.setPadding(new Insets(10));
        obGPane.setHgap(10);
        obGPane.setVgap(10);

        for(Node arg : obGPane.getChildren())
        {
            if(arg instanceof Button)
            {
                ((Button) arg).setMinHeight(100);
                ((Button) arg).setMinWidth(200);
                ((Button) arg).setStyle("-fx-font-size: 15px");

            }

            if(arg instanceof Button && arg == btnClose)
            {
                ((Button) arg).setStyle("-fx-background-color: indianred; -fx-font-size: 15px; -fx-border-color: black; -fx-border-width: 1px;");

            }
        }

        //Add panes to main BorderPane
        obBPane.setTop(obVPane);
        obBPane.setCenter(obGPane);

        //Event Handlers
        btnAddBooking.setOnAction(e -> {

        });

        btnRemoveBooking.setOnAction(e -> {
            RemoveBookingWindow removeBookingWindow = new RemoveBookingWindow(parent);
            removeBookingWindow.showAndWait();
        });

        btnModifyBooking.setOnAction(e -> {

            EditBookingWindow editBookingWindow = new EditBookingWindow(parent);
            editBookingWindow.showAndWait();
        });

        btnCurrentBookings.setOnAction(e -> {
            ViewBookingWindow viewBookingWindow=new ViewBookingWindow(parent);
            viewBookingWindow.showAndWait();
        });

        btnClose.setOnAction(e -> {
            this.close();
        });

        this.setScene(new Scene(obBPane, 800, 400));
        this.setTitle("Cest Lake - Booking Manager");
        this.initOwner(parent);
        this.initModality(Modality.APPLICATION_MODAL);
    }




}
