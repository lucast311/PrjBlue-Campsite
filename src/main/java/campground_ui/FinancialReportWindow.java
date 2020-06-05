package campground_ui;

import campground_data.Booking;
import campground_data.BookingHelper;
import campground_data.BookingType;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

public class FinancialReportWindow extends Stage
{
    BookingHelper bookingHelper=new BookingHelper();

    public FinancialReportWindow(Stage obStage)
    {
        BorderPane obBP=new BorderPane();
        GridPane obGrid=new GridPane();
        HBox obHBox=new HBox();
        obHBox.setTranslateY(40);

        String[] saMonths={"","January","February","March","April","May","June","July","August","September","October","November","December"}; //List of months for filtering with
        String[] saYears={"","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"}; //list of years for filtering with between 2010 and 2030

        //Button Stuff
        Button btnClose=new Button("Close");
        Button btnGenerate=new Button("                    Generate Report                    "); //can't set width any other way, .setMinWidth does nothing
        btnGenerate.setMinHeight(50);

        //RadioButton Stuff
        ToggleGroup obGroup=new ToggleGroup();
        RadioButton rad1=new RadioButton("All Time");
        RadioButton rad2=new RadioButton("This Year");
        RadioButton rad3=new RadioButton("YTD");
        RadioButton rad4=new RadioButton("This Month");
        RadioButton rad5=new RadioButton("MTD");
        RadioButton rad6=new RadioButton("Specific Year");
        RadioButton rad7=new RadioButton("Specific Year and Month");

        rad1.setToggleGroup(obGroup); //assigns radiobutton group so that only 1 can be selected at a time
        rad2.setToggleGroup(obGroup);
        rad3.setToggleGroup(obGroup);
        rad4.setToggleGroup(obGroup);
        rad5.setToggleGroup(obGroup);
        rad6.setToggleGroup(obGroup);
        rad7.setToggleGroup(obGroup);

        //ComboBox Stuff
        final ComboBox cbMonth=new ComboBox(FXCollections.observableArrayList(saMonths)); //ComboBox built from list of months
        cbMonth.getSelectionModel().selectFirst(); //By default the first item in the list is selected, which in this case is blank
        cbMonth.setVisible(false);
        final ComboBox cbYear=new ComboBox(FXCollections.observableArrayList(saYears)); //ComboBox built from list of years
        cbYear.getSelectionModel().selectFirst();  //By default the first item in the list is selected, which in this case is blank
        cbYear.setVisible(false);

        //TextField and label stuff
        TextField tfExpenses=new TextField(); //textfield for expenses given by owner
        Text txtExpenses=new Text("Expenses for Selected Time Period (optional): $");
        Text txtYear=new Text("Specific Year:");
        txtYear.setVisible(false);
        Text txtMonth=new Text("Specific Month:");
        txtMonth.setVisible(false);
        Text txtBlank1=new Text("  "); //used to fill in space above Generate Report button
        Text txtBlank2=new Text("  "); //used to fill in space above Generate Report button

        //TextArea Stuff. This is what the report gets displayed in
        TextArea obResults=new TextArea();
        obResults.setEditable(false);
        obResults.setWrapText(true);
        obResults.setText("");
        obResults.setTranslateX(385);
        obResults.setPrefColumnCount(20); //86-87 is full window width roughly
        obHBox.getChildren().add(obResults);

        //Layout Stuff for laying out where everything is on the window
        obGrid.add(btnClose,0,0);
        obGrid.add(rad1,1,0);
        obGrid.add(rad2,1,1);
        obGrid.add(rad3,1,2);
        obGrid.add(rad4,1,3);
        obGrid.add(rad5,1,4);
        obGrid.add(rad6,1,5);
        obGrid.add(rad7,1,6);
        obGrid.add(txtYear,2,0);
        obGrid.add(cbYear,3,0);
        obGrid.add(txtMonth,2,1);
        obGrid.add(cbMonth,3,1);
        obGrid.add(txtExpenses,4,0);
        obGrid.add(tfExpenses,4,1);
        obGrid.add(txtBlank1,3,2);
        obGrid.add(txtBlank2,3,3);
        obGrid.add(btnGenerate,3,4);
        obGrid.setVgap(10);
        obGrid.setHgap(30);
        obGrid.setTranslateY(20);
        obGrid.setTranslateX(20);

        obBP.setTop(obGrid);
        obBP.setCenter(obHBox);

        this.setScene(new Scene(obBP,1000,500));
        this.setTitle("Financial Report");
        this.initOwner(obStage);
        this.initModality(Modality.APPLICATION_MODAL);

        btnClose.setOnAction(e->{
            this.close(); //closes the window
        });

        btnGenerate.setOnAction(e->{ //handles the generation of the report by filtering an arrayList based on what radiobutton is selected
            double expensesGiven=0;
            String expenseTemp="0";
            //this for loop cleans up the textfield for expenses given, basically it removes all values that aren't digits or periods
            for(int i=0;i<tfExpenses.getText().length();i++)
            {
                if(tfExpenses.getText().isEmpty())
                {
                    break;
                }
                char temp=tfExpenses.getText().charAt(i);
                if(temp>='0' && temp<='9' || temp=='.')
                {
                    expenseTemp+=temp;
                }
                else
                {
                    continue;
                }
            }
            expensesGiven=Double.parseDouble(expenseTemp); //clean version of the expenses given by the owner
            //System.out.println(expensesGiven);

            double grossIncome=0; //total income
            double netIncome=0; //gross income - expensesGiven
            double cabinIncome=0; //income from cabins
            double siteIncome=0; //income from sites
            double discountsGiven=0; //total $ worth in discounts given, not accurate, is below what it should be
            int numCabinBookings=0; //number of bookings that were cabins
            int numSiteBookings=0; //number of bookings that were sites
            int totalBookings=0; //total number of bookings
            String cabinSite="Both";
            ArrayList<Booking> obWholeBookingList=bookingHelper.getBookingList();
            if(rad1.isSelected()) //All Time
            {
                //use whole list
                for(Booking obVal:obWholeBookingList)
                {
                    grossIncome+=obVal.getTotal();
                    discountsGiven+=obVal.getTotal()*(1-(obVal.getDiscount()/100)); //not accurate, is based off of current total, rather than total before discount given
                    totalBookings++;
                    if(obVal.getType()== BookingType.Cabin)
                    {
                        cabinIncome+=obVal.getTotal();
                        numCabinBookings++;
                    }
                    if(obVal.getType()==BookingType.Site)
                    {
                        siteIncome+=obVal.getTotal();
                        numSiteBookings++;
                    }

                }
                netIncome=grossIncome-expensesGiven;
            }
            if(rad2.isSelected()) //This Year
            {
                //use this current year
                Date currentDate=new Date();
                int currentYear=1900+currentDate.getYear();
                ArrayList<Booking> List=bookingHelper.getBookingListByYear(currentYear);
                for(Booking obVal:List)
                {
                    grossIncome+=obVal.getTotal();
                    discountsGiven+=obVal.getTotal()*(1-(obVal.getDiscount()/100)); //not accurate, is based off of current total, rather than total before discount given
                    totalBookings++;
                    if(obVal.getType()== BookingType.Cabin)
                    {
                        cabinIncome+=obVal.getTotal();
                        numCabinBookings++;
                    }
                    if(obVal.getType()==BookingType.Site)
                    {
                        siteIncome+=obVal.getTotal();
                        numSiteBookings++;
                    }

                }
                netIncome=grossIncome-expensesGiven;
            }
            if(rad3.isSelected()) //Year to Date YTD
            {
                //start of year till now
                Date currentDate=new Date();
                int currentYear=1900+currentDate.getYear();
                int currentMonthInt=currentDate.getMonth();
                int currentDay=currentDate.getDay();
                Date currentClean=new Date(currentYear+1900,currentMonthInt,currentDay);
                ArrayList<Booking> List=complexFilter(obWholeBookingList,cabinSite,"",String.valueOf(currentYear));
                for(Booking obVal:List)
                {
                    if(obVal.getStartDate().after(currentClean))
                    {
                        continue;
                    }
                    grossIncome+=obVal.getTotal();
                    discountsGiven+=obVal.getTotal()*(1-(obVal.getDiscount()/100)); //not accurate, is based off of current total, rather than total before discount given
                    totalBookings++;
                    if(obVal.getType()== BookingType.Cabin)
                    {
                        cabinIncome+=obVal.getTotal();
                        numCabinBookings++;
                    }
                    if(obVal.getType()==BookingType.Site)
                    {
                        siteIncome+=obVal.getTotal();
                        numSiteBookings++;
                    }

                }
                netIncome=grossIncome-expensesGiven;
            }
            if(rad4.isSelected()) //This Month
            {
                //This whole month
                Date currentDate=new Date();
                int currentYear=1900+currentDate.getYear();
                int currentMonthInt=currentDate.getMonth();
                String currentMonthString="";
                switch(currentMonthInt)
                {
                    case 0:
                        currentMonthString="January";
                        break;
                    case 1:
                        currentMonthString="February";
                        break;
                    case 2:
                        currentMonthString="March";
                        break;
                    case 3:
                        currentMonthString="April";
                        break;
                    case 4:
                        currentMonthString="May";
                        break;
                    case 5:
                        currentMonthString="June";
                        break;
                    case 6:
                        currentMonthString="July";
                        break;
                    case 7:
                        currentMonthString="August";
                        break;
                    case 8:
                        currentMonthString="September";
                        break;
                    case 9:
                        currentMonthString="October";
                        break;
                    case 10:
                        currentMonthString="November";
                        break;
                    case 11:
                        currentMonthString="December";
                        break;
                }

                ArrayList<Booking> List=complexFilter(obWholeBookingList,cabinSite,currentMonthString,String.valueOf(currentYear));
                for(Booking obVal:List)
                {
                    grossIncome+=obVal.getTotal();
                    discountsGiven+=obVal.getTotal()*(1-(obVal.getDiscount()/100)); //not accurate, is based off of current total, rather than total before discount given
                    totalBookings++;
                    if(obVal.getType()== BookingType.Cabin)
                    {
                        cabinIncome+=obVal.getTotal();
                        numCabinBookings++;
                    }
                    if(obVal.getType()==BookingType.Site)
                    {
                        siteIncome+=obVal.getTotal();
                        numSiteBookings++;
                    }

                }
                netIncome=grossIncome-expensesGiven;
            }
            if(rad5.isSelected()) //Month to Date MTD
            {
                //Start of month till now
                Date currentDate=new Date();
                int currentYear=1900+currentDate.getYear();
                int currentMonthInt=currentDate.getMonth();
                String currentMonthString="";
                int currentDay=currentDate.getDay();
                Date currentClean=new Date(currentYear+1900,currentMonthInt,currentDay);
                switch(currentMonthInt)
                {
                    case 0:
                        currentMonthString="January";
                        break;
                    case 1:
                        currentMonthString="February";
                        break;
                    case 2:
                        currentMonthString="March";
                        break;
                    case 3:
                        currentMonthString="April";
                        break;
                    case 4:
                        currentMonthString="May";
                        break;
                    case 5:
                        currentMonthString="June";
                        break;
                    case 6:
                        currentMonthString="July";
                        break;
                    case 7:
                        currentMonthString="August";
                        break;
                    case 8:
                        currentMonthString="September";
                        break;
                    case 9:
                        currentMonthString="October";
                        break;
                    case 10:
                        currentMonthString="November";
                        break;
                    case 11:
                        currentMonthString="December";
                        break;
                }
                ArrayList<Booking> List=complexFilter(obWholeBookingList,cabinSite,currentMonthString,String.valueOf(currentYear));
                for(Booking obVal:List)
                {
                    if(obVal.getStartDate().after(currentClean))
                    {
                        continue;
                    }
                    grossIncome+=obVal.getTotal();
                    discountsGiven+=obVal.getTotal()*(1-(obVal.getDiscount()/100)); //not accurate, is based off of current total, rather than total before discount given
                    totalBookings++;
                    if(obVal.getType()== BookingType.Cabin)
                    {
                        cabinIncome+=obVal.getTotal();
                        numCabinBookings++;
                    }
                    if(obVal.getType()==BookingType.Site)
                    {
                        siteIncome+=obVal.getTotal();
                        numSiteBookings++;
                    }

                }
                netIncome=grossIncome-expensesGiven;
            }
            if(rad6.isSelected()) //Specific Year
            {
                //Specific year, use cbYear as filter
                ArrayList<Booking> List=complexFilter(obWholeBookingList,cabinSite,"",cbYear.getValue().toString());
                for(Booking obVal:List)
                {
                    grossIncome+=obVal.getTotal();
                    discountsGiven+=obVal.getTotal()*(1-(obVal.getDiscount()/100)); //not accurate, is based off of current total, rather than total before discount given
                    totalBookings++;
                    if(obVal.getType()== BookingType.Cabin)
                    {
                        cabinIncome+=obVal.getTotal();
                        numCabinBookings++;
                    }
                    if(obVal.getType()==BookingType.Site)
                    {
                        siteIncome+=obVal.getTotal();
                        numSiteBookings++;
                    }

                }
                netIncome=grossIncome-expensesGiven;
            }
            if(rad7.isSelected()) //Specific Year and Month
            {
                //Specific year and month, use cbMonth and cbYear as filter
                ArrayList<Booking> List=complexFilter(obWholeBookingList,cabinSite,cbMonth.getValue().toString(),cbYear.getValue().toString());
                for(Booking obVal:List)
                {
                    grossIncome+=obVal.getTotal();
                    discountsGiven+=obVal.getTotal()*(1-(obVal.getDiscount()/100)); //not accurate, is based off of current total, rather than total before discount given
                    totalBookings++;
                    if(obVal.getType()== BookingType.Cabin)
                    {
                        cabinIncome+=obVal.getTotal();
                        numCabinBookings++;
                    }
                    if(obVal.getType()==BookingType.Site)
                    {
                        siteIncome+=obVal.getTotal();
                        numSiteBookings++;
                    }

                }
                netIncome=grossIncome-expensesGiven;
            }

            //sets the text in the textarea to show all parameters based on results from previous
            obResults.setText("Gross Income: $"+grossIncome+"\n"
                             +"Net Income: $"+netIncome+"\n"
                             +"Income from Cabins: $"+cabinIncome+"\n"
                             +"Income from Sites: $"+siteIncome+"\n"
                             +"Discounts Given: $"+discountsGiven+"\n"
                             +"Number of Cabin Bookings: "+numCabinBookings+"\n"
                             +"Number of Site Bookings: "+numSiteBookings+"\n"
                             +"Total Number of Bookings: "+totalBookings);

        });

        //All radio button click handlers manage whether or not the text and combo boxes for specific year and month are visible
        rad1.setOnAction(e->{
            cbMonth.setVisible(false);
            cbYear.setVisible(false);
            txtMonth.setVisible(false);
            txtYear.setVisible(false);
        });

        rad2.setOnAction(e->{
            cbMonth.setVisible(false);
            cbYear.setVisible(false);
            txtMonth.setVisible(false);
            txtYear.setVisible(false);
        });

        rad3.setOnAction(e->{
            cbMonth.setVisible(false);
            cbYear.setVisible(false);
            txtMonth.setVisible(false);
            txtYear.setVisible(false);
        });

        rad4.setOnAction(e->{
            cbMonth.setVisible(false);
            cbYear.setVisible(false);
            txtMonth.setVisible(false);
            txtYear.setVisible(false);
        });

        rad5.setOnAction(e->{
            cbMonth.setVisible(false);
            cbYear.setVisible(false);
            txtMonth.setVisible(false);
            txtYear.setVisible(false);
        });

        rad6.setOnAction(e->{
            cbYear.setVisible(true);
            txtYear.setVisible(true);
            cbMonth.setVisible(false);
            txtMonth.setVisible(false);
        });

        rad7.setOnAction(e->{
            cbMonth.setVisible(true);
            cbYear.setVisible(true);
            txtYear.setVisible(true);
            txtMonth.setVisible(true);
        });


    }

    /**
     * This method performs a complex filter of an arrayList of Bookings, and returns an arrayList of bookings based on what
     * filters are passed into it. sCabinSite = Cabin, Site, or Both,
     * sMonth is the month selected, sYear is the year selected, if none were selected, they are "" by default
     * @param list
     * @param sCabinSite
     * @param sMonth
     * @param sYear
     * @return
     */
    public ArrayList<Booking> complexFilter(ArrayList<Booking> list,String sCabinSite, String sMonth, String sYear)
    {
        ArrayList<Booking> obReturn;
        int nMonth;
        int nYear;

        //sets up nMonth based on the passed in month string, -1 by default
        switch(sMonth)
        {
            case "January":
                nMonth=0;
                break;
            case "February":
                nMonth=1;
                break;
            case "March":
                nMonth=2;
                break;
            case "April":
                nMonth=3;
                break;
            case "May":
                nMonth=4;
                break;
            case "June":
                nMonth=5;
                break;
            case "July":
                nMonth=6;
                break;
            case "August":
                nMonth=7;
                break;
            case "September":
                nMonth=8;
                break;
            case "October":
                nMonth=9;
                break;
            case "November":
                nMonth=10;
                break;
            case "December":
                nMonth=11;
                break;
            default:
                nMonth=-1;
                break;
        }

        if(sYear.equalsIgnoreCase(""))
        {
            nYear=0; //if no year given, year is set to 0
        }
        else
        {
            nYear=Integer.parseInt(sYear);
        }

        //series of if/else statements that call different methods in BookingHelper based on what values were passed into this method
        if(nYear==0)
        {
            if(nMonth==-1)
            {
                if(sCabinSite.equalsIgnoreCase("Cabin"))
                {
                    obReturn=bookingHelper.getCabinOnly(list);
                }
                else
                {
                    if(sCabinSite.equalsIgnoreCase("Site"))
                    {
                        obReturn=bookingHelper.getSiteOnly(list);
                    }
                    else //sCabinSite=="Both"
                    {
                        obReturn=list;
                    }
                }
            }
            else //nMonth!=-1
            {
                ArrayList<Booking> obTemp=bookingHelper.getBookingListByMonth(list,nMonth);
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
        else //nYear!=0
        {
            if(nMonth==-1)
            {
                ArrayList<Booking> obTemp=bookingHelper.getBookingListByYear(list,nYear);
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
            else //nMonth!=-1
            {
                ArrayList<Booking> obTemp=bookingHelper.getBookingList(list,nYear,nMonth);
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
