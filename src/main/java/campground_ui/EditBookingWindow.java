package campground_ui;

import campground_data.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditBookingWindow extends Stage{

    //window stuff
    private BorderPane obBPane;
    private GridPane obGPane;
    private VBox obButtonBox;

    private ListView taBookingList;

    private TextField txtGuestID, txtBookingID, txtAccommodationID, txtStartDate, txtEndDate, txtType, txtMemberCount,
            txtTotalPrice;
    private TextField txtError, txtsuccess;
    private Button btnSave, btnClose;
    private ToggleGroup group;
    private CheckBox checkpaid;
    private GridPane obGRpane;
    private static DatePicker dpStartDate = new DatePicker();
    private static DatePicker dpEndDate = new DatePicker();

    //booking stufff
    private Booking obBooking;
    private BookingHelper helper = new BookingHelper();
    ArrayList<Booking> allBookings = helper.getBookingList();
    private DatabaseFile dbfile = new DatabaseFile();




    //this is for javaFx window for editing window booking
    public EditBookingWindow(Stage obStage) {


        //Initialize panes
        obBPane = new BorderPane();
        obGPane = new GridPane();
        obGRpane = new GridPane();
        obButtonBox = new VBox();
        group = new ToggleGroup();


        //Initializes top text area that displays list of bookings
        taBookingList = new ListView();
        taBookingList.setPrefWidth(600);
        taBookingList.setPrefHeight(200);



        //Initializes text fields for the form
        txtGuestID = new TextField();
        txtGuestID.setDisable(true);
        txtBookingID = new TextField();
        txtBookingID.setDisable(true);
        txtAccommodationID = new TextField();
        txtStartDate = new TextField();
        txtEndDate = new TextField();
        txtType = new TextField();
        txtMemberCount = new TextField();
        txtTotalPrice = new TextField();
        txtError = new TextField();
        txtError.setDisable(true);
        txtError.setPrefWidth(250);
        txtsuccess = new TextField();
        txtsuccess.setDisable(true);


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


        taBookingList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                obBooking = (Booking) taBookingList.getSelectionModel().getSelectedItem();
                if(obBooking == null){

                }else {

                    //Setting default text to the text fields from the passed in guest
                    txtBookingID.setText(String.valueOf(obBooking.getBookingID()));
                    txtGuestID.setText(String.valueOf(obBooking.getGuestID()));
                    txtAccommodationID.setText(String.valueOf(obBooking.getAccommodationID()));
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String startDate= formatter.format(obBooking.getStartDate());
                    String endDate= formatter.format(obBooking.getEndDate());

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

                    if(obBooking.getDiscount() == 15){
                        rb1.setSelected(false);
                        rb4.setSelected(true);

                    }else if(obBooking.getDiscount() == 10){
                        rb1.setSelected(false);
                        rb3.setSelected(true);

                    }else if(obBooking.getDiscount() == 5){
                        rb1.setSelected(false);
                        rb2.setSelected(true);

                    } else {
                        rb1.setSelected(true);
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
        //obGPane.add(dpStartDate, 1, 3); //try
        obGPane.add(new Label("End Date"),0, 4);
        obGPane.add(txtEndDate, 1, 4);
        //obGPane.add(dpEndDate, 1, 3); //try
        obGPane.add(new Label("Type"),0, 5);
        obGPane.add(txtType, 1, 5);
        obGPane.add(new Label("Member Count"), 0, 6);
        obGPane.add(txtMemberCount, 1, 6);
        obGPane.add(new Label("Total Price"), 0, 7);
        obGPane.add(txtTotalPrice, 1, 7);

        obGRpane.add(new Label("Errors/Info"), 0,0);
        obGRpane.add(txtError, 1,0);
        obGRpane.add(new Label("Success"), 0,1);
        obGRpane.add(txtsuccess,1,1);

        //Initialized paid
        checkpaid = new CheckBox("Paid");


        //Initialize Buttons

        btnSave = new Button("Save");
        btnClose = new Button("Close");

        //Add Buttons to VBox
        obButtonBox.getChildren().addAll(txtdiscountnew,rb1, rb2, rb3, rb4, checkpaid, btnSave, btnClose);


        //Layout parameters for improved UI design
        obBPane.setPadding(new Insets(10));
        obStage.setMinWidth(1010);
        obStage.setMinHeight(510);

        obGPane.setHgap(10);
        obGPane.setVgap(5);
        obGPane.setPadding(new Insets(15,0,0,10));
        obGRpane.setHgap(10);
        obGRpane.setVgap(5);
        obGRpane.setPadding(new Insets(50, 50, 0, 10));

        obButtonBox.setSpacing(5);
        obButtonBox.setPadding(new Insets(15, 0, 0, 15));




        //Setting other Panes into main Border Pane
        obBPane.setTop(taBookingList);
        obBPane.setLeft(obGPane);
        obBPane.setCenter(obButtonBox);
        obBPane.setRight(obGRpane);


        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //need to validate everything then
                //change everything that was inputted

                txtError.setText("");

                if(!obBooking.setAccommodationID(Integer.parseInt(txtAccommodationID.getText())))
                {
                    txtError.setText("Accommodation ID is invalid");
                }

                SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                String sFields = txtEndDate.getText();
                //or
                //if(dpEndDate.getValue() != null)
                //obBooking.changeEnd(new Date(dpEndDate.getValue().getYear(), dpEndDate.getValue().getMonthValue()-1, dpEndDate.getValue().getDayOfYear()));
                try {

                    Date newendDate;
                    String datestring = sFields;
                    newendDate = formatter2.parse(datestring);
                    if (newendDate.compareTo(obBooking.getStartDate()) >= 0) {
                        if (newendDate.compareTo(obBooking.getEndDate()) < 0) {

                            //newEnddate = newendDate;
                            System.out.println(datestring);
                            //searchbooking = obBooking;

                            //EditBookingWindow editBookingWindow = new EditBookingWindow(parent);
                            //            editBookingWindow.showAndWait();
                             RefundWindow RefundWindow = new RefundWindow(obStage, obBooking, newendDate, dbfile);
                             RefundWindow.show();



                            System.out.println("End Date refund success");

                        } else {

                            obBooking.changeEnd(newendDate);



                        }
                    } else {
                        txtError.setText("End Date cannot be before start date ");
                    }
                }catch (Exception e){

                    txtError.setText("invalid date");

                }

                String sFields2 = txtStartDate.getText();
                //or
                //if(dpStartDate.getValue() != null)
                //obBooking.changeStart(new Date(dpStartDate.getValue().getYear(), dpStartDate.getValue().getMonthValue()-1, dpStartDate.getValue().getDayOfYear()));
                try {
                    Date bookingstartDate;

                    String datestring2 = sFields2;
                    bookingstartDate = formatter2.parse(datestring2);
                    if (bookingstartDate.compareTo(new Date()) > -1) {

                        obBooking.changeStart(bookingstartDate);


                    } else {

                        txtError.setText("Start Date cannot be before current date");

                    }
                } catch (Exception e) {

                    txtError.setText("invalid date");
                }


                if(txtType.getText().equals("Cabin")){
                    obBooking.setType(BookingType.Cabin);
                }else if(txtType.getText().equals("Site")){
                    obBooking.setType(BookingType.Site);
                }else{
                    txtError.setText("invalid Type, only Site or Cabin");
                }

                // may change depending on discounts and refund
                obBooking.setTotal(Double.parseDouble(txtTotalPrice.getText()));
                if(Integer.parseInt(txtMemberCount.getText()) > 0 &&Integer.parseInt(txtMemberCount.getText()) < 9){
                    obBooking.setMemberCount(Integer.parseInt(txtMemberCount.getText()));
                }else{
                    txtError.setText("invalid Member, only between 8 and 1");
                }


                if(Double.parseDouble(txtTotalPrice.getText()) > 0) {
                    if (rb2.isSelected() && !(obBooking.getDiscount() == 5.00)) {
                        //5% off
                        obBooking.setDiscount(5);
                    } else if (rb3.isSelected() && !(obBooking.getDiscount() == 10.00)) {
                        //10% off
                        obBooking.setDiscount(10);
                    } else if (rb4.isSelected() && !(obBooking.getDiscount() == 15.00)) {
                        //15% off
                        obBooking.setDiscount(15);
                    }
                }

                obBooking.setPaid(checkpaid.isSelected());

                txtsuccess.setText("Successfully Saved Booking "+ obBooking.getBookingID());
                dbfile.saveRecords(allBookings);



            }
        });


        this.setScene(new Scene(obBPane, 1000, 500));
        this.setTitle("Cest Lake - Edit Booking");
        this.setOnShowing(e -> {
            loadAllBookings();
        });
        //obStage.show();
        this.initOwner(obStage);
        this.initModality(Modality.WINDOW_MODAL);

        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //go back to menu
                close();


            }
        });
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
