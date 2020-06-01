package campground_ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FinancialReportWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage obStage) throws Exception
    {
        BorderPane obBP=new BorderPane();
        GridPane obGrid=new GridPane();
        HBox obHBox=new HBox();


        String[] saMonths={"","January","February","March","April","May","June","July","August","September","October","November","December"}; //List of months for filtering with
        String[] saYears={"","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"}; //list of years for filtering with between 2010 and 2030

        //Button Stuff
        Button btnClose=new Button("Close");
        Button btnGenerate=new Button("Generate Report");

        //RadioButton Stuff
        ToggleGroup obGroup=new ToggleGroup();
        RadioButton rad1=new RadioButton("");
        RadioButton rad2=new RadioButton("");
        RadioButton rad3=new RadioButton("");
        RadioButton rad4=new RadioButton("");
        RadioButton rad5=new RadioButton("");
        RadioButton rad6=new RadioButton("");
        RadioButton rad7=new RadioButton("");

        rad1.setToggleGroup(obGroup);
        rad2.setToggleGroup(obGroup);
        rad3.setToggleGroup(obGroup);
        rad4.setToggleGroup(obGroup);
        rad5.setToggleGroup(obGroup);
        rad6.setToggleGroup(obGroup);
        rad7.setToggleGroup(obGroup);

        //ComboBox Stuff
        final ComboBox cbMonth=new ComboBox(FXCollections.observableArrayList(saMonths)); //ComboBox built from list of months
        cbMonth.getSelectionModel().selectFirst(); //By default the first item in the list is selected, which in this case is blank
        final ComboBox cbYear=new ComboBox(FXCollections.observableArrayList(saYears)); //ComboBox built from list of years
        cbYear.getSelectionModel().selectFirst();  //By default the first item in the list is selected, which in this case is blank

        //TextField and label stuff
        TextField tfExpenses=new TextField();
        Text txtExpenses=new Text("Expenses for Selected Time Period (optional): $");

        //TextArea Stuff
        TextArea obResults=new TextArea();
        obResults.setEditable(false);
        obResults.setWrapText(true);
        obResults.setText("");
        obHBox.getChildren().add(obResults);

        obBP.setTop(obGrid);
        obBP.setCenter(obHBox);

        obStage.setScene(new Scene(obBP,1000,700));
        obStage.setTitle("Financial Report");
        obStage.show();
    }
}
