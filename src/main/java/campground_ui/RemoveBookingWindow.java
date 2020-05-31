package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is responsible for everything that is displayed on the Ui for remove booking
 */

public class RemoveBookingWindow extends Application {

    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obButtonBox;

    private TextArea taBookingList;

    private TextField txtGuestID, txtBookingID, txtAccommodationID, txtStartDate, txtEndDate, txtType, txtMemberCount,
            txtTotalPrice;
    private Button btnRemove, btnSave, btnClose;


    public static void main(String[] args)
    {
        Application.launch(args);
    }

    /**
     * Initializes all UI components/controls
     * @param obStage
     */

    @Override
    public void start(Stage obStage)
    {
        //Initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obButtonBox = new VBox();


        //Initializes top text area that displays list of bookings
        taBookingList = new TextArea();
        taBookingList.setPrefWidth(600);
        taBookingList.setPrefHeight(200);
        taBookingList.setDisable(true);


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
        btnSave = new Button("Save");
        btnClose = new Button("Close");

        //Add Buttons to VBox
        obButtonBox.getChildren().addAll(btnRemove, btnSave, btnClose);


        //Layout parameters for improved UI design
        obBPane.setPadding(new Insets(10));
        obStage.setMinWidth(800);
        obStage.setMinHeight(510);

        obGPane.setHgap(10);
        obGPane.setVgap(5);
        obGPane.setPadding(new Insets(15,0,0,10));

        obButtonBox.setSpacing(5);
        obButtonBox.setPadding(new Insets(159, 0, 0, 15));

        btnRemove.setStyle("-fx-background-color: indianred; -fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 3;");
        btnSave.setStyle("-fx-background-color: lightgreen; -fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 3;");
        btnClose.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 3;");


        //Setting other Panes into main Border Pane
        obBPane.setTop(taBookingList);
        obBPane.setLeft(obGPane);
        obBPane.setCenter(obButtonBox);

        obStage.setScene(new Scene(obBPane, 800, 500));
        obStage.setTitle("Cest Lake - Remove Booking");
        obStage.show();
    }



















}
