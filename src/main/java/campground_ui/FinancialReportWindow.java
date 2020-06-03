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
        cbMonth.setVisible(false);
        final ComboBox cbYear=new ComboBox(FXCollections.observableArrayList(saYears)); //ComboBox built from list of years
        cbYear.getSelectionModel().selectFirst();  //By default the first item in the list is selected, which in this case is blank
        cbYear.setVisible(false);

        //TextField and label stuff
        TextField tfExpenses=new TextField();
        Text txtExpenses=new Text("Expenses for Selected Time Period (optional): $");
        Text txtYear=new Text("Specific Year:");
        txtYear.setVisible(false);
        Text txtMonth=new Text("Specific Month:");
        txtMonth.setVisible(false);
        Text txtBlank1=new Text("  ");
        Text txtBlank2=new Text("  ");

        //TextArea Stuff
        TextArea obResults=new TextArea();
        obResults.setEditable(false);
        obResults.setWrapText(true);
        obResults.setText("");
        obResults.setPrefColumnCount(86);
        obHBox.getChildren().add(obResults);

        //Layout Stuff
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

        obStage.setScene(new Scene(obBP,1000,800));
        obStage.setTitle("Financial Report");
        obStage.show();

        btnClose.setOnAction(e->{
            obStage.close();
        });

        btnGenerate.setOnAction(e->{
            double expensesGiven=0;
            String expenseTemp="";
            for(int i=0;i<tfExpenses.getText().length();i++)
            {
                if(tfExpenses.getText().isEmpty())
                {
                    break;
                }
                if(tfExpenses.getText().matches("\\D*"))
                {
                    continue;
                }
                else
                {
                    expenseTemp+=tfExpenses.getText().charAt(i);
                }
            }
            //expensesGiven=Double.parseDouble(expenseTemp);
            System.out.println(expensesGiven);

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
        });

        rad7.setOnAction(e->{
            cbMonth.setVisible(true);
            cbYear.setVisible(true);
            txtYear.setVisible(true);
            txtMonth.setVisible(true);
        });


    }
}
