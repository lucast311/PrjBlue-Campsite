package campground_ui;

import campground_data.Booking;
import campground_data.BookingHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewBookingWindow extends Application
{
    private BookingHelper bookingHelper=new BookingHelper();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage obStage) throws Exception
    {
        BorderPane obBP=new BorderPane();
        GridPane obGrid=new GridPane();
        VBox vBox=new VBox();

        String[] saMonths={"","January","February","March","April","May","June","July","August","September","October","November","December"}; //List of months for filtering with
        String[] saYears={"","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"}; //list of years for filtering with between 2010 and 2030

        //Button Stuff
        Button obCloseBtn=new Button("Close");
        Button obViewAllBtn=new Button("View All Bookings");
        obViewAllBtn.setMinHeight(50);
        Button obViewCurrentBtn=new Button("View Current Bookings");
        obViewCurrentBtn.setMinHeight(50);
        Button obApply=new Button("Apply Filters");
        obApply.setMinHeight(50);

        //Regular text label Stuff
        Text obFilters=new Text("Filters:");
        Text obFilterMonthYear=new Text("Filter by Month and/or Year:");

        //TextArea Stuff
        TextArea obResults=new TextArea();
        obResults.setEditable(false);
        obResults.setWrapText(true);
        obResults.setText("");
        obResults.setPrefRowCount(40); //40 rows can be written to textarea before scrolling required
        obResults.setPrefColumnCount(100);//75 characters wide textarea per 40, 100= ~185-190 characters wide
        vBox.getChildren().add(obResults);
        obBP.setRight(vBox);

        //RadioButton Stuff
        ToggleGroup obGroup=new ToggleGroup();
        RadioButton obCabins=new RadioButton("View Cabins Only");
        RadioButton obSites=new RadioButton("View Sites Only");
        RadioButton obBoth=new RadioButton("View Both");
        obCabins.setToggleGroup(obGroup);
        obSites.setToggleGroup(obGroup);
        obBoth.setToggleGroup(obGroup);

        //ComboBox Stuff
        final ComboBox cbMonth=new ComboBox(FXCollections.observableArrayList(saMonths)); //ComboBox built from list of months
        cbMonth.getSelectionModel().selectFirst(); //By default the first item in the list is selected, which in this case is blank
        final ComboBox cbYear=new ComboBox(FXCollections.observableArrayList(saYears)); //ComboBox built from list of years
        cbYear.getSelectionModel().selectFirst();  //By default the first item in the list is selected, which in this case is blank

        //Setting up the grid positions and nodes at those positions
        obGrid.add(obCloseBtn,0,0);
        obGrid.add(obViewAllBtn,0,1);
        obGrid.add(obViewCurrentBtn,0,2);
        obGrid.add(obFilters,0,3);
        obGrid.add(obCabins,0,4);
        obGrid.add(obSites,0,5);
        obGrid.add(obBoth,0,6);
        obGrid.add(obFilterMonthYear,0,7);
        obGrid.add(cbMonth,0,8);
        obGrid.add(cbYear,0,9);
        obGrid.add(obApply,0,10);
        obGrid.setVgap(30);
        obGrid.setHgap(30);
        obGrid.setTranslateY(20);
        obGrid.setTranslateX(20);

        obBP.setCenter(obGrid);

        obStage.setScene(new Scene(obBP,1400,675)); //width,height
        obStage.setTitle("View Bookings");
        obStage.show();

        //Button click-handler code
        obCloseBtn.setOnAction(e->{
            obStage.close();
        });

        obViewAllBtn.setOnAction(e->{
            final ArrayList<Booking> bookingFull=bookingHelper.getBookingList();
            //Add arraylist values to TextArea obResults
        });

        obViewCurrentBtn.setOnAction(e->{
            final ArrayList<Booking> bookingsCurrent=bookingHelper.getCurrentBookings();
            //Add arraylist values to TextArea obResults
        });

        obApply.setOnAction(e->{
            String radFilter="Both";
            String cbsMonth=cbMonth.getValue().toString();
            String cbsYear=cbYear.getValue().toString();
            if(obCabins.isSelected())
            {
                radFilter="Cabin";
            }
            else
            {
                if(obSites.isSelected())
                {
                    radFilter="Site";
                }
                else
                {
                    if(obBoth.isSelected())
                    {
                        radFilter="Both";
                    }
                }
            }

            System.out.println(radFilter+"\n"+cbsMonth+"\n"+cbsYear+"\n");
            ArrayList<Booking> obFilterResults=complexFilter(radFilter,cbsMonth,cbsYear);
            //Add arraylist values to TextArea obResults

        });
    }

    public ArrayList<Booking> complexFilter(String sCabinSite, String sMonth, String sYear)
    {
        ArrayList<Booking> obReturn;
        int nMonth;
        int nYear;

        switch(sMonth)
        {
            case "January":
                nMonth=1;
                break;
            case "February":
                nMonth=2;
                break;
            case "March":
                nMonth=3;
                break;
            case "April":
                nMonth=4;
                break;
            case "May":
                nMonth=5;
                break;
            case "June":
                nMonth=6;
                break;
            case "July":
                nMonth=7;
                break;
            case "August":
                nMonth=8;
                break;
            case "September":
                nMonth=9;
                break;
            case "October":
                nMonth=10;
                break;
            case "November":
                nMonth=11;
                break;
            case "December":
                nMonth=12;
                break;
            default:
                nMonth=0;
                break;
        }

        if(sYear.equalsIgnoreCase(""))
        {
            nYear=0;
        }
        else
        {
            nYear=Integer.parseInt(sYear);
        }

        if(nYear==0)
        {
            if(nMonth==0)
            {
                if(sCabinSite.equalsIgnoreCase("Cabin"))
                {
                    obReturn=bookingHelper.getCabinOnly();
                }
                else
                {
                    if(sCabinSite.equalsIgnoreCase("Site"))
                    {
                        obReturn=bookingHelper.getSiteOnly();
                    }
                    else //sCabinSite=="Both"
                    {
                        obReturn=bookingHelper.getBookingList();
                    }
                }
            }
            else //nMonth!=0
            {
                ArrayList<Booking> obTemp=bookingHelper.getBookingListByMonth(nMonth);
                if(sCabinSite.equalsIgnoreCase("Cabin"))
                {
                    obReturn=bookingHelper.getCabinOnly(obTemp);
                }
                else
                {
                    if(sCabinSite.equalsIgnoreCase("Site"))
                    {
                        obReturn=bookingHelper.getSiteOnly(obTemp);
                    }
                    else //sCabinSite=="Both"
                    {
                        obReturn=bookingHelper.getBookingListByMonth(nMonth);
                    }
                }
            }
        }
        else //nYear!=0
        {
            if(nMonth==0)
            {
                ArrayList<Booking> obTemp=bookingHelper.getBookingListByYear(nYear);
                if(sCabinSite.equalsIgnoreCase("Cabin"))
                {
                    obReturn=bookingHelper.getCabinOnly(obTemp);
                }
                else
                {
                    if(sCabinSite.equalsIgnoreCase("Site"))
                    {
                        obReturn=bookingHelper.getSiteOnly(obTemp);
                    }
                    else //sCabinSite=="Both"
                    {
                        obReturn=obTemp;
                    }
                }
            }
            else //nMonth!=0
            {
                ArrayList<Booking> obTemp=bookingHelper.getBookingList(nYear,nMonth);
                if(sCabinSite.equalsIgnoreCase("Cabin"))
                {
                    obReturn=bookingHelper.getCabinOnly(obTemp);
                }
                else
                {
                    if(sCabinSite.equalsIgnoreCase("Site"))
                    {
                        obReturn=bookingHelper.getSiteOnly(obTemp);
                    }
                    else //sCabinSite=="Both"
                    {
                        obReturn=obTemp;
                    }
                }
            }
        }

        return obReturn;
    }
}
