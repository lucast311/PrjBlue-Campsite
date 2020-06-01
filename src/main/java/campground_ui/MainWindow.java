package campground_ui;

import campground_data.Owner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application {


    private static Owner currUser = new Owner();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane obPane = new BorderPane();
        primaryStage.setScene(new Scene(obPane, 600, 600));
        primaryStage.show();

        Platform.runLater(new Runnable () {
            public void run() {
                new LoginWindow().start(new Stage());
            }
        });

//        LoginWindow lw = new LoginWindow();
//        lw.showAndWait();
    }

    public static void displayMain(Owner user)
    {
        currUser = user;
    }
}
