package campground_ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewBookingWindow extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage obStage) throws Exception
    {
        BorderPane obBP=new BorderPane();
        Button obBtn=new Button("Ok");
        obBP.setCenter(obBtn);
        TextField obTextField=new TextField();

        obBP.setBottom(obTextField);

        obStage.setScene(new Scene(obBP,250,250));
        obStage.setTitle("View Bookings");
        obStage.show();
    }
}
