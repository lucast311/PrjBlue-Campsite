package campground_ui;

import campground_data.Owner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class is responsible for the GUI of the Main Window and will only open after a successful login
 */
public class MainWindow extends Application {


    private static Owner currUser = new Owner();
    private Label lblUserName;
    private Button btnBookingManager, btnGuestManager, btnAccommodationManager, btnOwnerManager, btnFinancialReports,
                btnExit;
    private VBox obVPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        while (currUser.getPermissions() < 1)
        {
            LoginWindow lw = new LoginWindow(primaryStage);
            lw.showAndWait();
        }

        //Initialize layout
        obVPane = new VBox();

        //Initialize label
        lblUserName = new Label("Hello " + currUser.getFirstName() + ". What would you like to do today?");

        //Initialize Buttons
        btnBookingManager = new Button("Manage Bookings");
        btnGuestManager = new Button("Manage Guests");
        btnAccommodationManager = new Button("Manage Accommodations");
        btnAccommodationManager.setDisable(true);//we have not done any functionality for this as it was not of priority
        btnOwnerManager = new Button("Manage Owners");
        btnFinancialReports = new Button("Financial Reports");
        btnExit = new Button("Exit");

        //Add components to VBox
        obVPane.getChildren().addAll(lblUserName, btnBookingManager, btnGuestManager, btnAccommodationManager, btnOwnerManager,
                btnFinancialReports, btnExit);

        //Layout parameters for improved usability
        obVPane.setAlignment(Pos.CENTER);
        obVPane.setSpacing(5);
        lblUserName.setPadding(new Insets(0,0,20,0));
        lblUserName.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");

        //Event Handlers
        btnBookingManager.setOnAction(e -> {
            BookingManagerWindow bookingManagerWindow = new BookingManagerWindow(primaryStage);
            bookingManagerWindow.showAndWait();
        });

        btnGuestManager.setOnAction(e -> {

        });

        btnAccommodationManager.setOnAction(e -> {

        });

        btnOwnerManager.setOnAction(e -> {

        });

        btnFinancialReports.setOnAction(e -> {

        });

        btnExit.setOnAction(e -> {
            System.exit(0);
        });

        primaryStage.setScene(new Scene(obVPane, 300, 250));
        primaryStage.setTitle("Cest Lake - Main Window");
        primaryStage.show();



    }

    public static void setUser(Owner user)
    {
        currUser = user;
    }
}
