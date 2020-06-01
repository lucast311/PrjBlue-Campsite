package campground_ui;

import campground_data.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
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
    private static boolean bAccommodationTypeGood = false;
    private static boolean bMemberCountGood = false;
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
//            update();
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
        tfGuestID.setText("");
        tfGuestID.setStyle(style);
    }

    private static void cancelButton()
    {
        obStage.close();
    }

    private static void addButton()
    {

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
        obBooking.setType(cbAccommodationType.getValue().toString());
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
    private static void updateGuestId()
    {
        try{
            int guestId = Integer.parseInt(tfGuestID.getText());
            if(obBooking.setGuestID(guestId))
            {
                tfGuestID.setStyle("-fx-background-color: green");
            }
            else
            {
                tfGuestID.setStyle("-fx-background-color: red");
            }
        }
        catch(Exception e)
        {
            tfGuestID.setStyle("-fx-background-color: red");
        }
    }

    private static void refreshAccommodationIDs()
    {
        if(bStartDateGood && bEndDateGood && bAccommodationTypeGood && bMemberCountGood)
        {
            PlotHelper plotHelper = new PlotHelper();
            ArrayList availableAccommodations = plotHelper.getPlotList().stream()
                    .filter(x->{
                        if(obBooking.getType() == BookingType.Site && x instanceof Site) {
                            Site site = (Site) x;

                            if (obBooking.getMemberCount() > 4 && site.getSiteType() == Site.SiteType.Individual)
                            {

                            }
                            else if(obBooking.getMemberCount() < 4 && site.getSiteType() == Site.SiteType.Individual)
                            {

                            }
                        }
                        else if(obBooking.getType() == BookingType.Cabin && x instanceof Cabin)
                        {
                            Cabin cabin = (Cabin) x;
                        }
                    })
                    .collect(Collectors.toCollection());
        }
    }

}
