package campground_ui;

import campground_data.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.scene.Node;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditBookingWindow extends Application{ //help from mel's window

    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obButtonBox, obdiscountBox;

    private ListView taBookingList;

    private TextField txtGuestID, txtBookingID, txtAccommodationID, txtStartDate, txtEndDate, txtType, txtMemberCount,
            txtTotalPrice;
    private Button btnNew,btnRemove, btnSave, btnClose;
    private ToggleGroup group;
    private CheckBox checkpaid;
    public Boolean refundno;

    //Guest object that will be edited

private Booking obBooking;
    private BookingHelper helper = new BookingHelper();
    ArrayList<Booking> allBookings = helper.getBookingList();
//Validation helper to assist in ensuring that the guest objects are valid
private ValidationHelper vh = new ValidationHelper();
//Database file to write changes to
    private DatabaseFile dbfile = new DatabaseFile();

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Application.launch(args);
    }

    //need something to grab whats selected
    @Override
    public void start(Stage obStage) throws Exception {



        //Initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obButtonBox = new VBox();
        obdiscountBox = new VBox();
        group = new ToggleGroup();


        //Initializes top text area that displays list of bookings
        taBookingList = new ListView();
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


        taBookingList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //System.out.println("clicked on " + taBookingList.getSelectionModel().getSelectedItem());
                obBooking = (Booking) taBookingList.getSelectionModel().getSelectedItem();
                if(obBooking == null){

                }else {

                    //Setting default text to the text fields from the passed in guest
                    txtBookingID.setText(String.valueOf(obBooking.getBookingID()));
                    txtGuestID.setText(String.valueOf(obBooking.getGuestID()));
                    txtAccommodationID.setText(String.valueOf(obBooking.getAccommodationID()));
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
                    String startDate= formatter.format(obBooking.getStartDate());
                    String endDate= formatter.format(obBooking.getStartDate());
                    //txtEndDate.setText(String.valueOf(obBooking.getEndDate()));
                    //txtStartDate.setText(String.valueOf(obBooking.getStartDate()));
                    txtStartDate.setText(startDate);
                    txtEndDate.setText(endDate);
                    txtType.setText(String.valueOf(obBooking.getType()));
                    txtTotalPrice.setText(String.valueOf(obBooking.getTotal()));
                    txtMemberCount.setText(String.valueOf(obBooking.getMemberCount()));
                    if(obBooking.getPaid() == true){
                        //if paid have
                        checkpaid.setSelected(true);
                    }else{
                        checkpaid.setSelected(false);
                    }
                }
            }
        });



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
                //need to validate everything then
                //change everything that was inputted
                obBooking.setnAccommodationID(Integer.parseInt(txtAccommodationID.getText()));
                //obBooking.changeEnd(txtEndDate.getText())
                String[] sFields = txtEndDate.getText().split("/");
                try {
                    //SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                    Date newendDate;
                    Date bookingenddate = obBooking.getEndDate();
                    //oldendDate = sdformat.parse(sFields[0] + "-" + sFields[1] + "-" + sFields[2] + "-00-00-00");
                    newendDate = new Date(Integer.parseInt(sFields[2]), Integer.parseInt(sFields[1]) - 1, Integer.parseInt(sFields[0]));
                    if (newendDate.compareTo(obBooking.getStartDate()) > 0) {
                        if (newendDate.compareTo(bookingenddate) < 0) {

                            //refundConfirm();
                        } else {

                            //searchbooking.changeEnd(refundendDate);

                        }
                    }
                }catch (Exception e){
                    System.out.println("Invalid Date");
                }
                //obBooking.changeStart(txtStartDate.getText())
                String[] sFields2 = txtStartDate.getText().split("/");
                try {
                    Date bookingstartDate = new Date(Integer.parseInt(sFields2[2]), Integer.parseInt(sFields2[1]) - 1, Integer.parseInt(sFields2[0]));
                    if (bookingstartDate.compareTo(new Date()) > -1) { //might change this later

                        //searchbooking.changeStart(bookingstartDate);

                    } else {
                        //System.out.println("Start Date cannot be before current date ");

                    }
                } catch (Exception e) {
                    System.out.println("Invalid Date");
                }

                //if booking end date is after start date and before end date
                //send to refund
                obBooking.setType(BookingType.valueOf(txtType.getText()));
                // may change depending on discounts and refund
                obBooking.setTotal(Double.parseDouble(txtTotalPrice.getText()));
                obBooking.setMemberCount(Integer.parseInt(txtMemberCount.getText()));

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
        obStage.setOnShowing(e -> {
            loadAllBookings();
        });
        obStage.show();
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


}
