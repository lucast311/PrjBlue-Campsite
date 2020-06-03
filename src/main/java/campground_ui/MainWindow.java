package campground_ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainWindow extends Application {

    private VBox obVPane;
    private Button btnTest;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage obStage)
    {
        obVPane = new VBox();
        btnTest = new Button("Test");


        obVPane.getChildren().addAll(btnTest);
        obVPane.setAlignment(Pos.CENTER);

        btnTest.setOnAction(e -> {
            BookingManagerWindow bookingManagerWindow = new BookingManagerWindow(obStage);
            bookingManagerWindow.showAndWait();

        });

        obStage.setScene(new Scene(obVPane, 100, 100));
        obStage.show();

    }
}
