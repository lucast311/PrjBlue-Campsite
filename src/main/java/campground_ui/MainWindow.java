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

        while (currUser.getPermissions() < 1)
        {
            LoginWindow lw = new LoginWindow(primaryStage);
            lw.showAndWait();
        }

        ManageOwnerWindow mw = new ManageOwnerWindow(primaryStage, currUser);
        mw.showAndWait();

        BorderPane obPane = new BorderPane();
        primaryStage.setScene(new Scene(obPane, 600, 600));
        primaryStage.show();


    }

    public static void setUser(Owner user)
    {
        currUser = user;
    }
}
