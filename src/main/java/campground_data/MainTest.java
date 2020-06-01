package campground_data;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainTest extends Application {
    private GridPane obPane;
    private DatabaseFile dbFile = new DatabaseFile();
    private ArrayList<Guest> guestList = new ArrayList<>();
    private Guest obGuest = new Guest();

    private Text obText = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btnTest = new Button();
        btnTest.setText("Test");

        Button btnRefresh = new Button();
        btnRefresh.setText("Refresh");

        obPane = new GridPane();

        obPane.add(btnTest,0 ,0);
        obPane.add(btnRefresh,1,0);

        int nIndex = 0;

        guestList = dbFile.readGuests();
        obGuest = guestList.get(nIndex);
        obText.setText(obGuest.toString());
        obPane.add(obText, 1, 1);

        btnTest.setOnAction(e -> {
            guestList = dbFile.readGuests();
            obGuest = guestList.get(nIndex);
            EditGuestWindow guestWindow = new EditGuestWindow(obGuest, nIndex);
            guestWindow.initModality(Modality.WINDOW_MODAL);
            guestWindow.initOwner(primaryStage);

            guestWindow.show();
        });

        btnRefresh.setOnAction(e -> {
            ArrayList<Guest> guestList2 = dbFile.readGuests();
            obPane.getChildren().remove(obText);
            this.obText.setText(guestList2.get(0).toString());
            obPane.add(this.obText, 1, 1);
        });

        primaryStage.setScene(new Scene(obPane, 800, 800));
        primaryStage.setTitle("Test");
        primaryStage.show();
    }
}
