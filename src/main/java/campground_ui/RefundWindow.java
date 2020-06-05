package campground_ui;

import campground_data.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RefundWindow extends Stage {

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
    //public Date newEnddate; //grab from edit window
    private AccommodationHelper AccHelper = new AccommodationHelper();
    private BookingHelper helper = new BookingHelper();
    ArrayList<Booking> allBookings = helper.getBookingList();
    //private DatabaseFile dbfile = new DatabaseFile();


    //this method will show the javafx window for refunding
    public RefundWindow(Stage primaryStage, Booking searchbooking, Date newEnddate, DatabaseFile dbfile) {
        //Stage primaryStage = new Stage();
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
                if(searchbooking.getPaid()) {
                    //gotta do that
                    double price;
                    int Accommodationid = searchbooking.getAccommodationID();
                    Accommodation priceAccommodation2;
                    if(AccHelper.searchAccommodation(Accommodationid) == null){
                        System.out.println("no plot or accommodation");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("You have an invalid accommodation connected");
                        alert.showAndWait();
                    }else {
                        priceAccommodation2 = AccHelper.searchAccommodation(Accommodationid);
                        price = priceAccommodation2.getPrice();
                        //System.out.println(price);
                        ratething = refundConfirmInt(searchbooking, newEnddate, price, TimeUnit.DAYS);
                        double paid = searchbooking.getTotal();

                        inputtext.setText(paid + " - " + ratething + "$");
                    }




                }else{

                    double price;
                    int Accommodationid = searchbooking.getAccommodationID();
                    //System.out.println(Accommodationid);
                    Accommodation priceAccommodation2;
                    if(AccHelper.searchAccommodation(Accommodationid) == null){
                        System.out.println("no plot or accommodation");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("You have an invalid accommodation connected");
                        alert.showAndWait();
                    }else {
                        priceAccommodation2 = AccHelper.searchAccommodation(Accommodationid);
                        price =  priceAccommodation2.getPrice();
                        //System.out.println(price);

                        ratething = refundConfirmInt(searchbooking, newEnddate, price, TimeUnit.DAYS);
                        int resultratething = ratething;

                        inputtext.setText(resultratething + "$");
                    }
                }
                yesclicked = true;
                nothingclicked = false;

            }
        });

        buttonno.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(searchbooking.getPaid()) {
                    inputtext.setText("0$");
                    yesclicked = false;
                    nothingclicked = false;
                }else {
                    Accommodation priceAccommodation2;
                    int Accommodationid = searchbooking.getAccommodationID();
                    if(AccHelper.searchAccommodation(Accommodationid) == null){
                        System.out.println("no plot or accommodation");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("You have an invalid accommodation connected");
                        alert.showAndWait();
                    }else {
                        priceAccommodation2 = AccHelper.searchAccommodation(Accommodationid);

                        long diffInMillies2 = Math.abs(searchbooking.getEndDate().getTime() - searchbooking.getStartDate().getTime());

                        long diff2 = TimeUnit.DAYS.convert(diffInMillies2, TimeUnit.MILLISECONDS);
                        //double dif2 = (double) Math.ceil(diff2 / 30.00);

                        double price = priceAccommodation2.getPrice();
                        price = (diff2 * price);
                        inputtext.setText(price + "$");
                    }

                }

                yesclicked = false;
                nothingclicked = false;

            }
        });

        buttonsubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(nothingclicked == true){
                    //do error message
                    //need to click one or the other to submit
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("You need to click yes or no");

                    alert.showAndWait();
                } else if(yesclicked == true){

                    searchbooking.setTotal((searchbooking.getTotal() - ratething));
                    searchbooking.changeEnd(newEnddate);
                    dbfile.saveRecords(allBookings);
                    close();

                }else if(yesclicked == false){
                    //message success for refund no and changed end date
                    //gotta do that
                    searchbooking.changeEnd(newEnddate);
                    dbfile.saveRecords(allBookings);
                    close();

                }

            }
        });




        Scene scene = new Scene(borderPane, 500, 350);
       this.setScene(scene);
        this.setTitle("Cest Lake - Refund");
        //primaryStage.show();
        this.initOwner(primaryStage);
        this.initModality(Modality.WINDOW_MODAL);

        buttoncancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //go back to booking manager
                close();

            }
        });
    }

    //for getting a refund for remaining days
    public int refundConfirmInt(Booking searchbooking2, Date newEnddate, double price, TimeUnit timeUnit) {

        Date newDate = newEnddate;
        System.out.println(searchbooking2.getBookingID());
        Date date4 = searchbooking2.getEndDate();
        Date date5 = searchbooking2.getStartDate();


        long diffInMillies1 = Math.abs(newDate.getTime() - date5.getTime());

        long diff1 = timeUnit.convert(diffInMillies1, TimeUnit.MILLISECONDS);

        long diffInMillies2 = Math.abs(date4.getTime() - date5.getTime());

        long diff2 = timeUnit.convert(diffInMillies2, TimeUnit.MILLISECONDS);

        double pricething = (diff2 * price);
        double ratething2 =   ((double)diff1 / diff2);

        double ratething3 = (pricething * ratething2);

        //System.out.println("got refund");
        return (int) ratething3;
    }
}
