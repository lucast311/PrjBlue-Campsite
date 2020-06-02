package campground_ui;

import campground_data.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private TextField txtError, txtsuccess;
    private Button btnNew,btnRemove, btnSave, btnClose;
    private ToggleGroup group;
    private CheckBox checkpaid;
    public Boolean refundno;



private Booking obBooking;
    private BookingHelper helper = new BookingHelper();
    ArrayList<Booking> allBookings = helper.getBookingList();
//Validation helper to assist in ensuring that the guest objects are valid
private ValidationHelper vh = new ValidationHelper();
//Database file to write changes to
    private DatabaseFile dbfile = new DatabaseFile();
    private GridPane obGRpane;

    //refund stuff
    private Booking searchbooking; //grab from edit window
    private int ratething;
    private Button buttonyes;
    private Button buttonno;
    private Text confirm;
    private Text remainder;
    private Button buttonsubmit;
    private Button buttoncancel;
    private TextField inputtext;
    private boolean yesclicked;
    private boolean nothingclicked = true;
    private Date newEnddate; //grab from edit window
    private AccommodationHelper AccHelper = new AccommodationHelper();

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
        obGRpane = new GridPane();
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

        //add to box
        //obdiscountBox.getChildren().addAll(txtdiscountnew,rb1, rb2, rb3, rb4);

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
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String startDate= formatter.format(obBooking.getStartDate());
                    String endDate= formatter.format(obBooking.getEndDate());
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
        obGPane.add(new Label("End Date"),0, 4);
        obGPane.add(txtEndDate, 1, 4);
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
        btnNew = new Button("New");
        //btnRemove = new Button("Remove");
        btnSave = new Button("Save");
        btnClose = new Button("Close");

        //Add Buttons to VBox
        obButtonBox.getChildren().addAll(txtdiscountnew,rb1, rb2, rb3, rb4, checkpaid,btnNew, btnSave, btnClose);


        //Layout parameters for improved UI design
        obBPane.setPadding(new Insets(10));
        obStage.setMinWidth(800);
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

                //if(Integer.parseInt(txtAccommodationID.getText()) == 0){
                //    txtError.setText("invalid AccommodationID");
                //}else {
                    obBooking.setnAccommodationID(Integer.parseInt(txtAccommodationID.getText()));
                //}

                //helper.updateBookingDate(allBookings, Integer.parseInt(txtBookingID.getText()), newStartDate, newEndDate, "src/main/java/database/bookings.obj");


                SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                //obBooking.changeEnd(txtEndDate.getText())
                String sFields = txtEndDate.getText();
                try {

                    Date newendDate;
                    //oldendDate = sdformat.parse(sFields[0] + "-" + sFields[1] + "-" + sFields[2] + "-00-00-00");
                    String datestring = sFields; //help it keeps adding years
                    newendDate = formatter2.parse(datestring);
                    if (newendDate.compareTo(obBooking.getStartDate()) >= 0) {
                        if (newendDate.compareTo(obBooking.getEndDate()) < 0) {

                            newEnddate = newendDate;
                            searchbooking = obBooking;
                            refundwindow();
                            //refundConfirm();
                            System.out.println("End Date refund success");

                        } else {

                            obBooking.changeEnd(newendDate);

                            //System.out.println("End Date success");

                        }
                    } else {
                        //System.out.println("End Date cannot be before start date ");
                        txtError.setText("End Date cannot be before start date ");
                    }
                }catch (Exception e){
                    //System.out.println("Help");
                    txtError.setText("invalid date");

                }
                //obBooking.changeStart(txtStartDate.getText())
                String sFields2 = txtStartDate.getText();
                try {
                    Date bookingstartDate;
                    // = new Date(Integer.parseInt(sFields2[2]), Integer.parseInt(sFields2[1]) , Integer.parseInt(sFields2[0])); //help it keeps adding years
                    String datestring2 = sFields2; //help it keeps adding years
                    bookingstartDate = formatter2.parse(datestring2);
                    if (bookingstartDate.compareTo(new Date()) > -1) { //might change this later

                        obBooking.changeStart(bookingstartDate);
                        //System.out.println("Start Date success");

                    } else {
                        //System.out.println("Start Date cannot be before current date ");
                        txtError.setText("Start Date cannot be before current date");

                    }
                } catch (Exception e) {
                    //System.out.println("Help");
                    txtError.setText("invalid date");


                }

                //if booking end date is after start date and before end date
                //send to refund
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

                //go back to menu


            }
        });
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //go to new booking


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

    private void refundwindow() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Modify Booking Refund");

        buttonyes = new Button();
        buttonyes.setText("Yes");


        buttonno = new Button();
        buttonno.setText("No");

        buttonsubmit = new Button();
        buttonsubmit.setText("Submit");

        buttoncancel = new Button();
        buttoncancel.setText("Cancel");

        inputtext = new TextField();


        confirm = new Text();
        confirm.setText("would you like to refund for remaining days?");

        remainder = new Text();
        remainder.setText("Remainder:");

        BorderPane borderPane = new BorderPane();
        //needs square for yes and no buttons to go into

        borderPane.setPadding(new Insets(50));
        VBox paneCenter = new VBox();
        HBox buttonbar1 = new HBox();
        HBox buttonbar2 = new HBox();
        HBox remainderbar = new HBox();
        VBox centerbar = new VBox();
        paneCenter.setSpacing(15);
        borderPane.setTop(paneCenter);
        borderPane.setCenter(centerbar);

        borderPane.setBottom(buttonbar2);
        paneCenter.getChildren().add(confirm);
        buttonbar1.getChildren().add(buttonyes);
        buttonbar1.getChildren().add(buttonno);
        remainderbar.getChildren().add(remainder);
        remainderbar.getChildren().add(inputtext);
        buttonbar2.getChildren().add(buttonsubmit);
        buttonbar2.getChildren().add(buttoncancel);
        centerbar.getChildren().addAll(buttonbar1, remainderbar);
        centerbar.setAlignment(Pos.CENTER);
        buttonbar1.setAlignment(Pos.CENTER);
        buttonbar2.setAlignment(Pos.CENTER);
        remainderbar.setAlignment(Pos.CENTER);
        paneCenter.setAlignment(Pos.CENTER);
        buttonyes.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //if not paid
                if(!searchbooking.getPaid()) {
                    //gotta do that
                    //refundConfirm(newEnddate);
                    ratething = refundConfirmInt1(searchbooking,newEnddate);
                    int resultratething = (int) searchbooking.getTotal() - ratething;

                    inputtext.setText(resultratething + "$");
                }else{
                    ratething = refundConfirmInt1(searchbooking,newEnddate);
                    inputtext.setText( "-" + ratething + "$");
                }
                yesclicked = true;
                nothingclicked = false;

            }
        });
        buttonno.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int price = (int) searchbooking.getTotal();
                inputtext.setText(price + "$");
                yesclicked = false;
                nothingclicked = false;


            }
        });

        buttonsubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(nothingclicked == true){
                    //do error message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("You need to click yes or no");

                    alert.showAndWait();
                } else if(yesclicked == true){
                    //message success for refund yes and changed end date
                    //ratething = refundConfirmInt1(searchbooking,newEnddate);
                    searchbooking.setTotal((searchbooking.getTotal() - ratething));
                    //gotta do that
                    searchbooking.changeEnd(newEnddate);
                    dbfile.saveRecords(allBookings);
                    primaryStage.close();

                }else {
                    //message success for refund no and changed end date
                    //gotta do that
                    searchbooking.changeEnd(newEnddate);
                    dbfile.saveRecords(allBookings);
                    primaryStage.close();

                }

            }
        });

        buttoncancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //go back to booking manager
                primaryStage.close();

            }
        });



        //borderPane.getChildren().addAll(confirm,buttonyes, buttonno, buttonsubmit, buttoncancel, inputtext, remainder);

        Scene scene = new Scene(borderPane, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int refundConfirmInt1( Booking searchbooking2, Date newEnddate) { //may need to be moved but for now here it stays
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // add updateBookingDate(ArrayList<Booking> bookings, int bookingID, Date newStartDate, Date newEndDate, String sFile) for validation
        Date newDate = newEnddate;
        Date date4 = searchbooking2.getEndDate();
        Date date5 = searchbooking2.getStartDate();
        int price;
        int Accommodationid = searchbooking.getAccommodationID();
        Accommodation priceAccommodation2 = AccHelper.searchAccommodation(Accommodationid); //help
        price = (int) priceAccommodation2.getPrice();
        if (price ==0){
            price = 100;
        }
        long startTime2 = newDate.getTime();//diff from old
        long endTime2 = date4.getTime();
        long startTime3 = date5.getTime(); // diff old start and old end
        long endTime3 = date4.getTime();
        long diffTime2 = endTime2 - startTime2;
        long diffTime3 = endTime3 - startTime3;
        long diffDays2 = diffTime2 / (1000 * 60 * 60 * 24);
        long diffDays3 = diffTime3 / (1000 * 60 * 60 * 24);

        int ratething2 = (int)  (diffDays2 / diffDays3);
        ratething2 = price / ratething2;
        return ratething2;
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
