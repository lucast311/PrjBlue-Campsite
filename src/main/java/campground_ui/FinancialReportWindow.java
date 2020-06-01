package campground_ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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


        obBP.setTop(obGrid);

        obStage.setScene(new Scene(obBP,1000,700));
        obStage.setTitle("Financial Report");
        obStage.show();
    }
}
