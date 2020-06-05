package campground_ui;

import campground_data.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewBookingWindow extends Stage {



    private static Stage obStage;

    //Criteria fields
    private static DatePicker dpStartDate = new DatePicker();
    private static DatePicker dpEndDate = new DatePicker();
    private static ComboBox cbAccommodationType = new ComboBox();
    private static Spinner spMemberCount = new Spinner();
    private static ComboBox cbAccommodationID = new ComboBox();
    private static ComboBox cbGuestID = new ComboBox();
    private static TextField tfPhoneNumber = new TextField();
    private static Button btnAdd = new Button();
    private static Button btnClear = new Button();
    private static Button btnCancel = new Button();

    //Booleans to check if fields are valid for booking
    private static boolean bStartDateGood = false;
    private static boolean bEndDateGood = false;
    private static boolean bAccommodationTypeGood = false;
    private static boolean bMemberCountGood = false;
    private static boolean bAccommodationIDGood = false;
    private static boolean bGuestIDGood = false;

    //Helpers instantiated
    private static GuestHelper guestHelper = new GuestHelper();
    private static BookingHelper bookingHelper = new BookingHelper();
    private static AccommodationHelper accommodationHelper = new AccommodationHelper();

    //The booking object to be modified
    private static Booking obBooking = new Booking();

    /**
     * This method will open up a javafx window with all the criteria fields for a booking and options to add the booking, clear the fields, or cancel the booking
     * @param primaryStage
     */
    public NewBookingWindow(Stage primaryStage) {
        obStage = primaryStage;
        BorderPane obPane = new BorderPane();

        //Criteria fields setup
        VBox obCriteria = new VBox();
        obCriteria.setSpacing(10);
        obCriteria.setAlignment(Pos.CENTER_LEFT);
        obCriteria.setMaxWidth(160);
        obPane.setCenter(obCriteria);

        obCriteria.getChildren().add(new Label("Start Date"));
        dpStartDate.setPrefWidth(160);
        dpStartDate.setEditable(false);
        obCriteria.getChildren().add(dpStartDate);

        obCriteria.getChildren().add(new Label("End Date"));
        dpEndDate.setPrefWidth(160);
        dpEndDate.setEditable(false);
        obCriteria.getChildren().add(dpEndDate);

        obCriteria.getChildren().add(new Label("Accommodation Type"));
        cbAccommodationType.getItems().add("Cabin");
        cbAccommodationType.getItems().add("Site");
        cbAccommodationType.setPrefWidth(160);
        obCriteria.getChildren().add(cbAccommodationType);

        obCriteria.getChildren().add(new Label("Member Count"));
        spMemberCount.setEditable(false);
        spMemberCount.setPrefWidth(160);
        spMemberCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8));
        updateMemberCount();
        obCriteria.getChildren().add(spMemberCount);

        obCriteria.getChildren().add(new Label("Accommodation ID"));
        cbAccommodationID.setPrefWidth(160);
        obCriteria.getChildren().add(cbAccommodationID);

        obCriteria.getChildren().add(new Label("Guest ID"));
        tfPhoneNumber.setPromptText("Enter guest phone number");
        tfPhoneNumber.setPrefWidth(160);
        obCriteria.getChildren().add(tfPhoneNumber);
        cbGuestID.getItems().addAll(guestHelper.getGuestAccounts());
        cbGuestID.setPrefWidth(160);
        cbGuestID.setVisibleRowCount(4);
        obCriteria.getChildren().add(cbGuestID);

        //Listeners for each field
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
        tfPhoneNumber.setOnKeyReleased(e-> {
            updateGuestIdList();
        });
        cbGuestID.setOnAction(e-> {
            updateGuestId();
        });

        //Listeners for the buttons
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

        //Layout for buttons at bottom of form
        HBox obButtons = new HBox();
        obButtons.setSpacing(13);
        obButtons.getChildren().add(btnAdd);
        obButtons.getChildren().add(btnClear);
        obButtons.getChildren().add(btnCancel);
        obCriteria.getChildren().add(obButtons);

        //displays the GUI
        this.setScene(new Scene(obPane, 300, 500));
        this.setTitle("New Booking");
        this.initOwner(primaryStage);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * When the clear button is clicked this method is called, it clears and resets the styles of the criteria boxes
     * as well as setting all the boolean validation attributes to false
     */
    private static void clearButton()
    {
        bStartDateGood = false;
        bEndDateGood = false;
        bAccommodationTypeGood = false;
        bMemberCountGood = false;
        bAccommodationIDGood = false;
        bGuestIDGood = false;

        dpStartDate.setValue(null);
        dpStartDate.setStyle("-fx-background-color: linear-gradient(to bottom, derive(-fx-text-box-border, -10%), -fx-text-box-border),\n" +
                "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background); \n" +
                " -fx-background-insets: 0, 1;");

        dpEndDate.setValue(null);
        dpEndDate.setStyle("-fx-background-color: linear-gradient(to bottom, derive(-fx-text-box-border, -10%), -fx-text-box-border),\n" +
                "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background); \n" +
                " -fx-background-insets: 0, 1;");

        cbAccommodationType.setValue(null);

        spMemberCount.decrement(8);

        cbAccommodationID.getItems().clear();
        cbAccommodationID.setValue(null);

        tfPhoneNumber.setText("");

        cbGuestID.setValue(null);
    }

    /**
     * When the cancel button is clicked this method is called and the form GUI is closed
     */
    private void cancelButton()
    {
        this.close();
    }

    /**
     * When the add button is clicked, this method is called,
     * it checks all the boolean validation for the fields and proceeds to prompt for
     * confirmation and if confirmed will try to add the booking to the system
     * returning a success or fail message
     */
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

    /**
     * This method is called when the start date field is modified,
     * this will check if the booking date can be set to the selected date,
     * setting the field to green if valid or red if invalid
     */
    private static void updateStartDate()
    {
        if(dpStartDate.getValue() != null)
        {
            try {
                Date date = new Date();
                date.setYear(dpStartDate.getValue().getYear());
                date.setMonth(dpStartDate.getValue().getMonthValue()-1);
                date.setDate(dpStartDate.getValue().getDayOfMonth());
                if (obBooking.changeStart(date)) {
                    dpStartDate.setStyle("-fx-background-color: green");
                    bStartDateGood = true;
                } else {
                    dpStartDate.setStyle("-fx-background-color: red");
                    bStartDateGood = false;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                bStartDateGood = false;
            }
        }
    }

    /**
     * This method is called when the end date field is modified,
     * this will check if the booking date can be set to the selected date,
     * setting the field to green if valid or red if invalid
     */
    private static void updateEndDate()
    {
        if(dpEndDate.getValue() != null)
        {
            try {
                Date date = new Date();
                date.setYear(dpStartDate.getValue().getYear());
                date.setMonth(dpStartDate.getValue().getMonthValue()-1);
                date.setDate(dpStartDate.getValue().getDayOfMonth());
                if (obBooking.changeEnd(date)) {
                    dpEndDate.setStyle("-fx-background-color: green");
                    bEndDateGood = true;
                } else {
                    dpEndDate.setStyle("-fx-background-color: red");
                    bEndDateGood = false;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                bEndDateGood = false;
            }
        }
    }

    /**
     * This method is called when the accommodation type field is modified,
     * this will set the bookings type to the selected type
     * if the type is a cabin a max of 4 members is set to the member counter
     */
    private static void updateAccommodationType()
    {
        if(cbAccommodationType.getValue() != null)
        {
            if(obBooking.setType(cbAccommodationType.getValue().toString()))
            {
                if(obBooking.getType() == BookingType.Cabin)
                {
                    spMemberCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,4));
                }
                else
                {
                    spMemberCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8));
                }
                bAccommodationTypeGood = true;
            }
            else
            {
                bAccommodationTypeGood = false;
            }
        }
    }

    /**
     * This method is called when the member count field is updated,
     * this will set the bookings member count to the selected value
     */
    private static void updateMemberCount()
    {
        try{
            if(obBooking.setMemberCount(Integer.parseInt(spMemberCount.getValue().toString())))
            {
                bMemberCountGood = true;
            }
            else
            {
                bMemberCountGood = false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            bMemberCountGood = false;
        }
    }

    /**
     * This method is called when an accommodation ID field is modified,
     * this will set the bookings accommodation id to the selected accommodation
     */
    private static void updateAccommodationID()
    {
        if(cbAccommodationID.getValue() != null)
        {
            try {
                if(obBooking.setAccommodationID(((Accommodation) cbAccommodationID.getValue()).getAccommodationID()))
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
                e.printStackTrace();
                bAccommodationIDGood = false;
            }
        }
        else
        {
            bAccommodationIDGood = false;
        }
    }

    /**
     * This method is called when a phone number is entered as criteria for a guest account,
     * this will filter the available guest accounts with the given phone number
     */
    private static void updateGuestIdList()
    {
        if(tfPhoneNumber.getText() != null && !tfPhoneNumber.getText().equals(""))
        {
            List<Guest> guestAccounts = guestHelper.getGuestAccounts().stream()
                    .filter(x-> x.getPhoneNumber().contains(tfPhoneNumber.getText()))
                    .collect(Collectors.toList());
            cbGuestID.getItems().clear();
            cbGuestID.getItems().addAll(guestAccounts);
            cbAccommodationID.setVisibleRowCount(4);
        }
    }

    /**
     * This method is called when the guest id field is modified,
     * this will set the bookings guest id to the selected guest
     */
    private static void updateGuestId()
    {
        if(cbGuestID.getValue() != null) {
            try {
                if (obBooking.setGuestID(((Guest) cbGuestID.getValue()).getGuestID())) {
                    bGuestIDGood = true;
                } else {
                    bGuestIDGood = false;
                    Alert obAlert = new Alert(Alert.AlertType.ERROR, "There was an error using that Guest ID", ButtonType.OK);
                    obAlert.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                bGuestIDGood = false;
            }
        }
    }

    /**
     * This method is called anytime a field is modified,
     * this method will check if the criteria for an accommodation is good,
     * then will filter the list based on the given criteria
     */
    private static void refreshAccommodationIDs()
    {
        if(bStartDateGood && bEndDateGood && bAccommodationTypeGood && bMemberCountGood)
        {
            PlotHelper plotHelper = new PlotHelper();
            List availableAccommodations = accommodationHelper.getAccommodationList().stream()
                    .filter(x->{

                        //Filter for if accommodation is a site
                        if(obBooking.getType() == BookingType.Site && x instanceof Site) {
                            Site site = (Site) x;

                            //filter for if a group site is needed for greater than 4 members
                            if (obBooking.getMemberCount() > 4 && site.getSiteType() == Site.SiteType.Group)
                            {
                                if(!site.isUnderReno()) //checks if site is under reno/maintenance
                                {
                                    for(Booking booking : bookingHelper.getBookingList())//for loop to check each booking for conflicting dates
                                    {
                                        if(booking.getPlotID() == ((Accommodation)  x).getAccommodationID())
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
                            //filter for if an individual site is needed for less than or equal to 4 members
                            else if(obBooking.getMemberCount() < 4 && site.getSiteType() == Site.SiteType.Individual)
                            {
                                if(!site.isUnderReno())//checks if site is under reno/maintenance
                                {
                                    for(Booking booking : bookingHelper.getBookingList()) //for loop to check each booking for conflicting dates
                                    {
                                        if(booking.getPlotID() == x.getAccommodationID())
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
                        //Filter for accommodation if a cabin
                        else if(obBooking.getType() == BookingType.Cabin && x instanceof Cabin)
                        {
                            for(Booking booking : bookingHelper.getBookingList())
                            {
                                if(booking.getPlotID() == x.getAccommodationID())
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
                        return false;
                    })
                    .collect(Collectors.toList());

            cbAccommodationID.getItems().addAll(availableAccommodations);
            cbAccommodationID.setVisibleRowCount(4);
        }
    }

}
