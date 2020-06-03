package campground_ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainWindow extends Application
{

    private Button btnGuestManager = new Button("Guest Manager");
    private Pane pane = new Pane();
    private BorderPane borderPane = new BorderPane();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {


        pane.getChildren().add(btnGuestManager);
        borderPane.setCenter(pane);

        btnGuestManager.setOnAction(e -> {
            GuestManagerWindow guestManagerWindow = new GuestManagerWindow(primaryStage);
            guestManagerWindow.showAndWait();
        });

        primaryStage.setScene(new Scene(borderPane, 400, 400));
        primaryStage.setTitle("Cest Lake");
        primaryStage.show();

    }
}
