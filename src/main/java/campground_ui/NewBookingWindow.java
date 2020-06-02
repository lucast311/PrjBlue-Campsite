package campground_ui;

import campground_data.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewBookingWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage obStage;
    //Criteria fields
    private static TextField tfStartDate = new TextField();
    private static TextField tfEndDate = new TextField();
    private static ComboBox cbAccommodationType = new ComboBox();
    private static TextField tfMemberCount = new TextField();
    private static ComboBox cbAccommodationID = new ComboBox();
    private static TextField tfGuestID = new TextField();
    private static Button btnAdd = new Button();
    private static Button btnClear = new Button();
    private static Button btnCancel = new Button();

    private static boolean bStartDateGood = false;
    private static boolean bEndDateGood = false;
    private static boolean bAccommodationType = false;
    private static boolean bMemberCountGood = false;
    private static boolean bAccommodationID = false;
    private static boolean bGuestID = false;
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

        obCriteria.getChildren().add(new Label("Start Date (dd/mm/yyyy)"));
        obCriteria.getChildren().add(tfStartDate);

        obCriteria.getChildren().add(new Label("End Date (dd/mm/yyyy)"));
        obCriteria.getChildren().add(tfEndDate);

        obCriteria.getChildren().add(new Label("Accommodation Type"));
        cbAccommodationType.getItems().add(0, "Cabin");
        cbAccommodationType.getItems().add(1, "Site");
        obCriteria.getChildren().add(cbAccommodationType);

        obCriteria.getChildren().add(new Label("Member Count"));
        obCriteria.getChildren().add(tfMemberCount);

        obCriteria.getChildren().add(new Label("Accommodation ID"));
        obCriteria.getChildren().add(cbAccommodationID);

        obCriteria.getChildren().add(new Label("Guest ID"));
        obCriteria.getChildren().add(tfGuestID);

        //Listeners
        tfStartDate.focusedProperty().addListener(e-> {
            updateStartDate();
            refreshAccommodationIDs();
        });
        tfEndDate.focusedProperty().addListener(e-> {
            updateEndDate();
            refreshAccommodationIDs();
        });
        cbAccommodationType.setOnAction(e-> {
            updateAccommodationType();
            refreshAccommodationIDs();
        });
        tfMemberCount.focusedProperty().addListener(e-> {
            updateMemberCount();
            refreshAccommodationIDs();
        });
        cbAccommodationID.setOnAction(e-> {
            updateAccommodationID();
        });
        tfGuestID.focusedProperty().addListener(e-> {
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
        String style = "-fx-text-fill: -fx-text-inner-color;\n" +
                "    -fx-highlight-fill: derive(-fx-control-inner-background,-20%);\n" +
                "    -fx-highlight-text-fill: -fx-text-inner-color;\n" +
                "    -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%);\n" +
                "    -fx-background-color: linear-gradient(to bottom, derive(-fx-text-box-border, -10%), -fx-text-box-border),\n" +
                "        linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background);\n" +
                "    -fx-background-insets: 0, 1;\n" +
                "    -fx-background-radius: 3, 2;\n" +
                "    -fx-cursor: text;\n" +
                "    -fx-padding: 0.333333em 0.583em 0.333333em 0.583em;";
        tfStartDate.setText("");
        tfStartDate.setStyle(style);
        tfEndDate.setText("");
        tfEndDate.setStyle(style);
        cbAccommodationType.setValue("");
        tfMemberCount.setText("");
        tfMemberCount.setStyle(style);
        cbAccommodationID.setValue("");
        cbAccommodationID.setItems(null);
        tfGuestID.setText("");
        tfGuestID.setStyle(style);
    }

    private static void cancelButton()
    {
        obStage.close();
    }

    private static void addButton()
    {
        if(bStartDateGood && bEndDateGood && bAccommodationType && bMemberCountGood && bAccommodationID && bGuestID)
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
            Date startDate = new Date();
            String[] sFields = tfStartDate.getText().split("/");
            int nDate = 0;
            int nMonth = 0;
            int nYear = 0;

            if (!sFields[0].equals("")) {
                nDate = Integer.parseInt(sFields[0]);
                startDate.setDate(nDate);
            }
            if (!sFields[1].equals("")) {
                nMonth = Integer.parseInt(sFields[1]) - 1;
                startDate.setMonth(nMonth);
            }
            if (!sFields[2].equals("")) {
                nYear = Integer.parseInt(sFields[2]);
                startDate.setYear(nYear);
            }

            if (startDate.getDate() == nDate && startDate.getMonth() == nMonth && startDate.getYear() == nYear) {
                if (obBooking.changeStart(startDate)) {
                    tfStartDate.setStyle("-fx-background-color: green");
                    bStartDateGood = true;
                } else {
                    tfStartDate.setStyle("-fx-background-color: red");
                    bStartDateGood = false;
                }
            } else {
                tfStartDate.setStyle("-fx-background-color: red");
                bStartDateGood = false;
            }
        }
        catch(Exception e)
        {
            tfStartDate.setStyle("-fx-background-color: red");
            bStartDateGood = false;
        }
    }
    private static void updateEndDate()
    {
        try {
            Date endDate = new Date();
            String[] sFields = tfEndDate.getText().split("/");
            int nDate = 0;
            int nMonth = 0;
            int nYear = 0;

            if(!sFields[0].equals(""))
            {
                nDate = Integer.parseInt(sFields[0]);
                endDate.setDate(nDate);
            }
            if(!sFields[1].equals(""))
            {
                nMonth = Integer.parseInt(sFields[1])-1;
                endDate.setMonth(nMonth);
            }
            if(!sFields[2].equals(""))
            {
                nYear = Integer.parseInt(sFields[2]);
                endDate.setYear(nYear);
            }

            if(endDate.getDate() == nDate && endDate.getMonth() == nMonth && endDate.getYear() == nYear)
            {
                if(obBooking.changeEnd(endDate))
                {
                    tfEndDate.setStyle("-fx-background-color: green");
                    bEndDateGood = true;
                }
                else
                {
                    tfEndDate.setStyle("-fx-background-color: red");
                    bEndDateGood = false;
                }
            }
            else
            {
                tfEndDate.setStyle("-fx-background-color: red");
                bEndDateGood = false;
            }
        }
        catch(Exception e)
        {
            tfEndDate.setStyle("-fx-background-color: red");
            bEndDateGood = false;
        }
    }
    private static void updateAccommodationType()
    {
        if(obBooking.setType(cbAccommodationType.getValue().toString()))
        {
            bAccommodationType = true;
        }
        else
        {
            bAccommodationType = false;
        }
    }
    private static void updateMemberCount()
    {
        try{
            int memberCount = Integer.parseInt(tfMemberCount.getText());
            if(obBooking.setMemberCount(memberCount))
            {
                tfMemberCount.setStyle("-fx-background-color: green");
                bMemberCountGood = true;
            }
            else
            {
                tfMemberCount.setStyle("-fx-background-color: red");
                bMemberCountGood = false;
            }
        }
        catch(Exception e)
        {
            tfMemberCount.setStyle("-fx-background-color: red");
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
                    bAccommodationID = true;
                }
                else
                {
                    bAccommodationID = false;
                }
            } catch (Exception e) {
                Alert obAlert = new Alert(Alert.AlertType.ERROR, "There was an error using that accommodation ID", ButtonType.OK);
                obAlert.show();
                bAccommodationID = false;
            }
        }
        else
        {
            bAccommodationID = false;
        }
    }

    private static void updateGuestId()
    {
        try{
            int guestId = Integer.parseInt(tfGuestID.getText());
            if(obBooking.setGuestID(guestId))
            {
                tfGuestID.setStyle("-fx-background-color: green");
                bGuestID = true;
            }
            else
            {
                tfGuestID.setStyle("-fx-background-color: red");
                bGuestID = false;
            }
        }
        catch(Exception e)
        {
            tfGuestID.setStyle("-fx-background-color: red");
            bGuestID = false;
        }
    }

    private static void refreshAccommodationIDs()
    {
        if(bStartDateGood && bEndDateGood && bAccommodationType && bMemberCountGood)
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
