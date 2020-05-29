package campground_ui;


import campground_data.Booking;
import campground_data.Accommodation;
import campground_data.AccommodationHelper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Date;

public class RefundGui extends Application {

    private static Booking searchbooking;
    private int ratething;
    Button buttonyes;
    Button buttonno;
    Text confirm;
    Text remainder;
    Button buttonsubmit;
    Button buttoncancel;
    TextField inputtext;
    boolean yesclicked;
    boolean nothingclicked = true;
    Date newEnddate;

    //below is temporary for checking
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception { //this window will only show if newEndDate is Startdate < newEndDate < oldEndDate
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
                //gotta do that
                //refundConfirm(newEnddate);
                //ratething = refundConfirmInt(searchBooking,newEnddate);
                int resultratething = (int) searchbooking.getTotal() - ratething;

                inputtext.setText(String.valueOf(resultratething) + "$");
                yesclicked = true;
                nothingclicked = false;

            }
        });
        buttonno.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int price = (int) searchbooking.getTotal();
                inputtext.setText(String.valueOf(price) + "$");
                yesclicked = false;
                nothingclicked = false;

            }
        });

        buttonsubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(nothingclicked == true){
                    //do error message
                } else if(yesclicked == true){
                    //message success for refund yes and changed end date
                    searchbooking.setTotal((searchbooking.getTotal() - ratething));
                    //gotta do that
                    //searchbooking.changeEnd(newEnddate);
                }else {
                    //message success for refund no and changed end date
                    //gotta do that
                    //searchbooking.changeEnd(newEnddate);
                }

            }
        });

        buttoncancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //go back to booking manager
            }
        });



        //borderPane.getChildren().addAll(confirm,buttonyes, buttonno, buttonsubmit, buttoncancel, inputtext, remainder);

        Scene scene = new Scene(borderPane, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void refundConfirm(Date newEnddate) { //may need to be moved but for now here it stays
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date newDate = newEnddate;
        Date date4 = searchbooking.getEndDate();
        Date date5 = searchbooking.getStartDate();
        int price;
        int Accommodationid = searchbooking.getAccommodationID();
        Accommodation priceplot = AccommodationHelper.searchAccommodation(Accommodationid); //help
        price = (int) priceplot.getPrice();
        long startTime2 = newDate.getTime();//diff from old
        long endTime2 = date4.getTime();
        long startTime3 = date5.getTime(); // diff old start and old end
        long endTime3 = date4.getTime();
        long diffTime2 = endTime2 - startTime2;
        long diffTime3 = endTime3 - startTime3;
        long diffDays2 = diffTime2 / (1000 * 60 * 60 * 24);
        long diffDays3 = diffTime3 / (1000 * 60 * 60 * 24);

        ratething = (int)  (diffDays2 / diffDays3);
        ratething = price / ratething;
    }

    public int refundConfirmInt( Booking searchbooking2, Date newEnddate) { //may need to be moved but for now here it stays
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date newDate = newEnddate;
        Date date4 = searchbooking2.getEndDate();
        Date date5 = searchbooking2.getStartDate();
        int price;
        int Accommodationid = searchbooking.getAccommodationID();
        Accommodation priceAccommodation2 = AccommodationHelper.searchAccommodation(Accommodationid); //help
        price = (int) priceAccommodation2.getPrice();
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



}
