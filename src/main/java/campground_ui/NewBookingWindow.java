package campground_ui;

import campground_data.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewBookingWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage obStage;

    //Criteria fields
    private static DatePicker dpStartDate = new DatePicker();
    private static DatePicker dpEndDate = new DatePicker();
    private static ComboBox cbAccommodationType = new ComboBox();
    private static Spinner spMemberCount = new Spinner();
    private static ComboBox cbAccommodationID = new ComboBox();
    private static ComboBox cbGuestID = new ComboBox();
    private static Button btnAdd = new Button();
    private static Button btnClear = new Button();
    private static Button btnCancel = new Button();

    private static boolean bStartDateGood = false;
    private static boolean bEndDateGood = false;
    private static boolean bAccommodationTypeGood = false;
    private static boolean bMemberCountGood = false;
    private static boolean bAccommodationIDGood = false;
    private static boolean bGuestIDGood = false;

    private static GuestHelper guestHelper = new GuestHelper();
    private static BookingHelper bookingHelper = new BookingHelper();
    private static Booking obBooking = new Booking();



    @Override
    public void start(Stage primaryStage) {
        obStage = primaryStage;
        BorderPane obPane = new BorderPane();

        //Criteria fields setup
        VBox obCriteria = new VBox();
        obCriteria.setSpacing(10);
        obCriteria.setAlignment(Pos.CENTER_LEFT);
        obPane.setLeft(obCriteria);

        obCriteria.getChildren().add(new Label("Start Date"));
        obCriteria.getChildren().add(dpStartDate);
        dpStartDate.setEditable(false);

        obCriteria.getChildren().add(new Label("End Date"));
        obCriteria.getChildren().add(dpEndDate);
        dpEndDate.setEditable(false);

        obCriteria.getChildren().add(new Label("Accommodation Type"));
        cbAccommodationType.getItems().add("Cabin");
        cbAccommodationType.getItems().add("Site");
        obCriteria.getChildren().add(cbAccommodationType);

        obCriteria.getChildren().add(new Label("Member Count"));
        obCriteria.getChildren().add(spMemberCount);
        spMemberCount.setEditable(false);
        spMemberCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8));

        obCriteria.getChildren().add(new Label("Accommodation ID"));
        obCriteria.getChildren().add(cbAccommodationID);

        obCriteria.getChildren().add(new Label("Guest ID"));
        obCriteria.getChildren().add(cbGuestID);
        cbGuestID.getItems().addAll(guestHelper.getGuestAccounts());
        cbGuestID.setVisibleRowCount(4);

        //Listeners
        dpStartDate.setOnAction(e-> {
            updateStartDate();
            refreshAccommodationIDs();
        });
        dpEndDate.setOnAction(e-> {
            updateEndDate();
            refreshAccommodationIDs();
        });
        cbAccommodationType.setOnAction(e-> {
            updateAccommodationType();
            refreshAccommodationIDs();
        });
        spMemberCount.setOnMouseClicked(e-> {
            updateMemberCount();
            refreshAccommodationIDs();
        });
        cbAccommodationID.setOnAction(e-> {
            updateAccommodationID();
        });
        cbGuestID.focusedProperty().addListener(e-> {
            updateGuestId();
        });

        btnAdd.setText("Add");
        btnAdd.setOnAction(e-> {
            addButton();
        });
        btnClear.setText("Clear");
        btnClear.setOnAction(e-> {
            clearButton();
        });
        btnCancel.setText("Cancel");
        btnCancel.setOnAction(e-> {
            cancelButton();
        });

        HBox obButtons = new HBox();
        obButtons.setSpacing(10);
        obButtons.getChildren().add(btnAdd);
        obButtons.getChildren().add(btnClear);
        obButtons.getChildren().add(btnCancel);
        obCriteria.getChildren().add(obButtons);


        primaryStage.setScene(new Scene(obPane, 800, 500));
        primaryStage.setTitle("New Booking");
        primaryStage.show();
    }

    private static void clearButton()
    {
        dpStartDate.setValue(null);
        dpStartDate.setStyle("-fx-background-color: linear-gradient(to bottom, derive(-fx-text-box-border, -10%), -fx-text-box-border),\n" +
                "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background); \n" +
                " -fx-background-insets: 0, 1;");
        dpEndDate.setValue(null);
        dpEndDate.setStyle("-fx-background-color: linear-gradient(to bottom, derive(-fx-text-box-border, -10%), -fx-text-box-border),\n" +
                "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background); \n" +
                " -fx-background-insets: 0, 1;");
        cbAccommodationType.setValue("");
        spMemberCount.decrement(8);
        cbAccommodationID.setValue("");
        cbAccommodationID.setItems(null);
        cbGuestID.setValue("");
    }

    private static void cancelButton()
    {
        obStage.close();
    }

    private static void addButton()
    {
        if(bStartDateGood && bEndDateGood && bAccommodationTypeGood && bMemberCountGood && bAccommodationIDGood && bGuestIDGood)
        {
            Alert obAlert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to create this booking?", ButtonType.YES, ButtonType.NO);
            obAlert.show();
            obAlert.setOnCloseRequest(e-> {
                if(obAlert.getResult() == ButtonType.YES)
                {
                    if(bookingHelper.addBooking(obBooking))
                    {
                        Alert obSuccess = new Alert(Alert.AlertType.INFORMATION, "Successfully added the booking!", ButtonType.OK);
                        obSuccess.show();
                        obSuccess.setOnCloseRequest(x->{
                            obStage.close();
                        });
                    }
                    else
                    {
                        Alert obUnsuccessful = new Alert(Alert.AlertType.ERROR, "There was an error adding the booking!", ButtonType.OK);
                        obUnsuccessful.show();
                    }
                }
                else
                {
                    obAlert.close();
                }
            });
        }
        else
        {
            Alert obAlert = new Alert(Alert.AlertType.ERROR, "Form is incomplete!", ButtonType.OK);
            obAlert.show();
        }

    }

    private static void updateStartDate()
    {
        try {
            if (obBooking.changeStart(new Date(dpStartDate.getValue().getYear(), dpStartDate.getValue().getMonthValue()-1, dpStartDate.getValue().getDayOfYear()))) {
                dpStartDate.setStyle("-fx-background-color: green");
                bStartDateGood = true;
            } else {
                dpStartDate.setStyle("-fx-background-color: red");
                bStartDateGood = false;
            }
        }
        catch(Exception e)
        {
            dpStartDate.setStyle("-fx-background-color: red");
            bStartDateGood = false;
        }
    }

    private static void updateEndDate()
    {
        try {
            if (obBooking.changeEnd(new Date(dpEndDate.getValue().getYear(), dpEndDate.getValue().getMonthValue()-1, dpEndDate.getValue().getDayOfYear()))) {
                dpEndDate.setStyle("-fx-background-color: green");
                bEndDateGood = true;
            } else {
                dpEndDate.setStyle("-fx-background-color: red");
                bEndDateGood = false;
            }
        }
        catch(Exception e)
        {
            dpEndDate.setStyle("-fx-background-color: red");
            bEndDateGood = false;
        }
    }
    private static void updateAccommodationType()
    {
        if(obBooking.setType(cbAccommodationType.getValue().toString()))
        {
            bAccommodationTypeGood = true;
        }
        else
        {
            bAccommodationTypeGood = false;
        }
    }
    private static void updateMemberCount()
    {
        try{
            if(obBooking.setMemberCount(Integer.parseInt(spMemberCount.getValue().toString())))
            {
                spMemberCount.setStyle("-fx-background-color: green");
                bMemberCountGood = true;
            }
            else
            {
                spMemberCount.setStyle("-fx-background-color: red");
                bMemberCountGood = false;
            }
        }
        catch(Exception e)
        {
            spMemberCount.setStyle("-fx-background-color: red");
            bMemberCountGood = false;
        }
    }
    private static void updateAccommodationID()
    {
        if(cbAccommodationID.getValue() != "")
        {
            try {
                if(obBooking.setAccommodationID(((Plot) cbAccommodationID.getValue()).getPlotID()))
                {
                    bAccommodationIDGood = true;
                }
                else
                {
                    bAccommodationIDGood = false;
                    Alert obAlert = new Alert(Alert.AlertType.ERROR, "There was an error using that accommodation ID", ButtonType.OK);
                    obAlert.show();
                }
            } catch (Exception e) {
                Alert obAlert = new Alert(Alert.AlertType.ERROR, "There was an error using that accommodation ID", ButtonType.OK);
                obAlert.show();
                bAccommodationIDGood = false;
            }
        }
        else
        {
            bAccommodationIDGood = false;
        }
    }

    private static void updateGuestId()
    {
        try{
            if(obBooking.setGuestID(((Guest)cbGuestID.getValue()).getGuestID()))
            {
                bGuestIDGood = true;
            }
            else
            {
                bGuestIDGood = false;
                Alert obAlert = new Alert(Alert.AlertType.ERROR, "There was an error using that Guest ID", ButtonType.OK);
                obAlert.show();
            }
        }
        catch(Exception e)
        {
            Alert obAlert = new Alert(Alert.AlertType.ERROR, "There was an error using that Guest ID", ButtonType.OK);
            obAlert.show();
            bGuestIDGood = false;
        }
    }

    private static void refreshAccommodationIDs()
    {
        if(bStartDateGood && bEndDateGood && bAccommodationTypeGood && bMemberCountGood)
        {
            PlotHelper plotHelper = new PlotHelper();
            List availableAccommodations = plotHelper.getPlotList().stream()
                    .filter(x->{
                        if(obBooking.getType() == BookingType.Site && x instanceof Site) {
                            Site site = (Site) x;

                            if (obBooking.getMemberCount() > 4 && site.getSiteType() == Site.SiteType.Group)
                            {
                                if(!site.isBooked() && !site.isUnderReno())
                                {
                                    for(Booking booking : bookingHelper.getBookingList())
                                    {
                                        if(booking.getPlotID() == x.getPlotID())
                                        {
                                            if(booking.getStartDate().after(obBooking.getEndDate()))
                                            {
                                                return true;
                                            }
                                            else if(booking.getEndDate().before(obBooking.getStartDate()))
                                            {
                                                return true;
                                            }
                                        }
                                        else
                                        {
                                            return true;
                                        }
                                    }
                                }
                            }
                            else if(obBooking.getMemberCount() < 4 && site.getSiteType() == Site.SiteType.Individual)
                            {
                                if(!site.isBooked() && !site.isUnderReno())
                                {
                                    for(Booking booking : bookingHelper.getBookingList())
                                    {
                                        if(booking.getPlotID() == x.getPlotID())
                                        {
                                            if(booking.getStartDate().after(obBooking.getEndDate()))
                                            {
                                                return true;
                                            }
                                            else if(booking.getEndDate().before(obBooking.getStartDate()))
                                            {
                                                return true;
                                            }
                                        }
                                        else
                                        {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        else if(obBooking.getType() == BookingType.Cabin && x instanceof Cabin)
                        {
                            Cabin cabin = (Cabin) x;

                            if (obBooking.getMemberCount() > 4 && cabin.getCabinType() == Cabin.CabinType.Basic)
                            {
                                for(Booking booking : bookingHelper.getBookingList())
                                {
                                    if(booking.getPlotID() == x.getPlotID())
                                    {
                                        if(booking.getStartDate().after(obBooking.getEndDate()))
                                        {
                                            return true;
                                        }
                                        else if(booking.getEndDate().before(obBooking.getStartDate()))
                                        {
                                            return true;
                                        }
                                    }
                                    else
                                    {
                                        return true;
                                    }
                                }
                            }
                            else if(obBooking.getMemberCount() < 4 && cabin.getCabinType() == Cabin.CabinType.Deluxe)
                            {
                                for(Booking booking : bookingHelper.getBookingList())
                                {
                                    if(booking.getPlotID() == x.getPlotID())
                                    {
                                        if(booking.getStartDate().after(obBooking.getEndDate()))
                                        {
                                            return true;
                                        }
                                        else if(booking.getEndDate().before(obBooking.getStartDate()))
                                        {
                                            return true;
                                        }
                                    }
                                    else
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            cbAccommodationID.getItems().addAll(availableAccommodations);
            cbAccommodationID.setVisibleRowCount(4);
        }
    }

}
