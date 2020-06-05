package campground_ui;

import campground_data.Owner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private GridPane obGPane;
    private BorderPane obBPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
//        while (currUser.getPermissions() < 1)
//        {
//            LoginWindow lw = new LoginWindow(primaryStage);
//            lw.showAndWait();
//        }

        //Initialize layout
        obVPane = new VBox();
        obGPane = new GridPane();
        obBPane = new BorderPane();

        //Initialize label
        lblUserName = new Label("Hello " + currUser.getFirstName() + ". What would you like to do today?");

        //Initialize Buttons
        btnBookingManager = new Button("Manage Bookings");
        btnGuestManager = new Button("Manage Guests");
        btnAccommodationManager = new Button("Manage Accommodations");
        btnAccommodationManager.setDisable(true);//we have not done any functionality for this as it was not of priority
        btnOwnerManager = new Button("Manage Employees");
        btnFinancialReports = new Button("Financial Reports");
        btnExit = new Button("Exit");


        //Add components to VBox
        obVPane.getChildren().addAll(lblUserName);

        //Add components to GridPane
        obGPane.add(btnBookingManager, 0 , 0);
        obGPane.add(btnGuestManager, 1 , 0);
        obGPane.add(btnAccommodationManager, 0 , 1);
        obGPane.add(btnOwnerManager, 1 , 1);
        obGPane.add(btnFinancialReports, 0 , 2);
        obGPane.add(btnExit, 1 , 2);

        //Layout parameters for improved usability
        obVPane.setAlignment(Pos.CENTER);
        obVPane.setSpacing(5);
        lblUserName.setPadding(new Insets(20,0,0,0));
        lblUserName.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");

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

            if(arg instanceof Button && arg == btnExit)
            {
                ((Button) arg).setStyle("-fx-background-color: indianred; -fx-font-size: 15px; -fx-border-color: black; -fx-border-width: 1px;");

            }
        }

        //Adding Panes into main BorderPane
        obBPane.setTop(obVPane);
        obBPane.setCenter(obGPane);

        //Event Handlers
        btnBookingManager.setOnAction(e -> {
            BookingManagerWindow bookingManagerWindow = new BookingManagerWindow(primaryStage);
            bookingManagerWindow.show();
        });

        btnGuestManager.setOnAction(e -> {
            GuestManagerWindow guestManagerWindow = new GuestManagerWindow(primaryStage);
            guestManagerWindow.showAndWait();

        });

        btnAccommodationManager.setOnAction(e -> {

        });

        btnOwnerManager.setOnAction(e -> {

        });

        btnFinancialReports.setOnAction(e -> {
            FinancialReportWindow financialReportWindow=new FinancialReportWindow(primaryStage);
            financialReportWindow.showAndWait();
        });

        btnExit.setOnAction(e -> {
            System.exit(0);
        });

        primaryStage.setScene(new Scene(obBPane, 800, 400));
        primaryStage.setTitle("Cest Lake - Main Window");
        primaryStage.show();



    }

    public static void setUser(Owner user)
    {
        currUser = user;
    }
}
