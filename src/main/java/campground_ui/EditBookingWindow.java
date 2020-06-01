package campground_ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.scene.Node;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditBookingWindow extends Application{ //help from mel's window

    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obButtonBox, obdiscountBox;

    private TextArea taBookingList;

    private TextField txtGuestID, txtBookingID, txtAccommodationID, txtStartDate, txtEndDate, txtType, txtMemberCount,
            txtTotalPrice;
    private Button btnNew,btnRemove, btnSave, btnClose;
    private ToggleGroup group;
    private CheckBox checkpaid;


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Application.launch(args);
    }

    @Override
    public void start(Stage obStage) throws Exception {

        //Initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obButtonBox = new VBox();
        obdiscountBox = new VBox();
        group = new ToggleGroup();


        //Initializes top text area that displays list of bookings
        taBookingList = new TextArea();
        taBookingList.setPrefWidth(600);
        taBookingList.setPrefHeight(200);
        //taBookingList.setDisable(true);


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
        // for(Node arg : obGPane.getChildren())
        // {
        //if(arg instanceof TextField)
        //{
        //arg.setDisable(true);
        //}
        //}
        //txtGuestID.setDisable(false);

        //initailize discount
        Label txtdiscountnew =  new Label("Discount Applied");

        RadioButton rb1 = new RadioButton("None");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("5%");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("10%");
        rb3.setToggleGroup(group);

        RadioButton rb4 = new RadioButton("15%");
        rb4.setToggleGroup(group);

        //add to box
        //obdiscountBox.getChildren().addAll(txtdiscountnew,rb1, rb2, rb3, rb4);

        //Initialized paid
        checkpaid = new CheckBox("Paid");
        //if paid have
        //checkpaid.setSelected(true);

        //Initialize Buttons
        btnNew = new Button("New");
        btnRemove = new Button("Remove");
        btnSave = new Button("Save");
        btnClose = new Button("Close");

        //Add Buttons to VBox
        obButtonBox.getChildren().addAll(txtdiscountnew,rb1, rb2, rb3, rb4, checkpaid,btnNew, btnRemove, btnSave, btnClose);


        //Layout parameters for improved UI design
        obBPane.setPadding(new Insets(10));
        obStage.setMinWidth(800);
        obStage.setMinHeight(510);

        obGPane.setHgap(10);
        obGPane.setVgap(5);
        obGPane.setPadding(new Insets(15,0,0,10));

        obButtonBox.setSpacing(5);
        obButtonBox.setPadding(new Insets(15, 0, 0, 15));



        //Setting other Panes into main Border Pane
        obBPane.setTop(taBookingList);
        obBPane.setLeft(obGPane);
        obBPane.setCenter(obButtonBox);


        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //need to check everything then
                //change everything that was inputted
                //DBFile.saveRecords(bookings);
                //go back to menu


            }
        });
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //go to new booking


            }
        });

        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //DBFile.saveRecords(bookings);
                //go back to menu


            }
        });
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //go back to menu


            }
        });

        obStage.setScene(new Scene(obBPane, 800, 500));
        obStage.setTitle("Cest Lake - Edit Booking");
        obStage.show();
    }


}
