package campground_data;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainTest extends Application {
    private Pane obPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btnTest = new Button();
        btnTest.setText("Test");
        obPane = new Pane();

        obPane.getChildren().add(btnTest);

        Guest obGuest = new Guest("Richie", "Saysana", "Email", "PhoneNumber", PaymentType.Cash, "CreditCard",
                4, new Address(123, 123, "StreetName", "City", "State", "Country", "A1A1A1"));

        btnTest.setOnAction(e -> {
            EditGuestWindow guestWindow = new EditGuestWindow(obGuest);

            guestWindow.show();
        });

        primaryStage.setScene(new Scene(obPane, 800, 800));
        primaryStage.setTitle("Test");
        primaryStage.show();
    }
}
