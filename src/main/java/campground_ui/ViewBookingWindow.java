package campground_ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewBookingWindow extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage obStage) throws Exception
    {
        BorderPane obBP=new BorderPane();
        GridPane obGrid=new GridPane();
        String[] saMonths={"January","February","March","April","May","June","July","August","September","October","November","December"};
        String[] saYears={"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};

        Button obCloseBtn=new Button("Close");
        Button obViewAllBtn=new Button("View All Bookings");
        Button obViewCurrentBtn=new Button("View Current Bookings");
        Button obApply=new Button("Apply Filters");

        Text obFilters=new Text("Filters:");
        Text obFilterMonthYear=new Text("Filter by Month and/or Year:");

        ToggleGroup obGroup=new ToggleGroup();
        RadioButton obCabins=new RadioButton("View Cabins Only");
        RadioButton obSites=new RadioButton("View Sites Only");
        obCabins.setToggleGroup(obGroup);
        obSites.setToggleGroup(obGroup);

        final ComboBox cbMonth=new ComboBox(FXCollections.observableArrayList(saMonths));
        final ComboBox cbYear=new ComboBox(FXCollections.observableArrayList(saYears));

        obGrid.add(obCloseBtn,0,0);
        obGrid.add(obViewAllBtn,0,1);
        obGrid.add(obViewCurrentBtn,0,2);
        obGrid.add(obFilters,0,3);
        obGrid.add(obCabins,0,4);
        obGrid.add(obSites,0,5);
        obGrid.add(obFilterMonthYear,0,6);
        obGrid.add(cbMonth,0,7);
        obGrid.add(cbYear,0,8);
        obGrid.add(obApply,0,9);
        obGrid.setVgap(30);

        obBP.setLeft(obGrid);

        obStage.setScene(new Scene(obBP,500,500));
        obStage.setTitle("View Bookings");
        obStage.show();
    }
}
