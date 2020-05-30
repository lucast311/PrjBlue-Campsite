package campground_ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
        VBox vBox=new VBox();
        String[] saMonths={"January","February","March","April","May","June","July","August","September","October","November","December"};
        String[] saYears={"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};

        Button obCloseBtn=new Button("Close");
        Button obViewAllBtn=new Button("View All Bookings");
        obViewAllBtn.setMinHeight(50);
        Button obViewCurrentBtn=new Button("View Current Bookings");
        obViewCurrentBtn.setMinHeight(50);
        Button obApply=new Button("Apply Filters");
        obApply.setMinHeight(50);

        Text obFilters=new Text("Filters:");
        Text obFilterMonthYear=new Text("Filter by Month and/or Year:");

        TextArea obResults=new TextArea();
        obResults.setEditable(false);
        obResults.setWrapText(true);
        obResults.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\nbbaaaaaa\naaaaaaaa\naaaa\naaaaaaaa\naaaaa\naaaaaa\naaa\naaaa\naaaaaaaa\naaaa\naaaa\naaaan\naaa\naaa\naa\naaa\naaaa\naaa\naaa\naaaa\naaaaa\naaaa\naaaa\naaa\naaaa\naaaa\naaaa\naaaa\naaaa\nnaa\naaa\naaaa\naaaa\naaa");
        obResults.setPrefRowCount(40); //40 rows can be written to textarea before scrolling required
        obResults.setPrefColumnCount(100);//75 characters wide textarea per 40, 100= ~185-190 characters wide
        vBox.getChildren().add(obResults);
        obBP.setRight(vBox);

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
        obGrid.setHgap(30);
        obGrid.setTranslateY(20);
        obGrid.setTranslateX(20);

        obBP.setCenter(obGrid);

        obStage.setScene(new Scene(obBP,1400,675)); //width,height
        obStage.setTitle("View Bookings");
        obStage.show();
    }
}
