package campground_ui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Will be creating the GUI for removing a booking
 */
public class RemoveBookingWindow extends Application {

    private BorderPane obBPane;
    private GridPane obGPane;

    private TextArea taBookingList;
    private Label lblGuestID, lblbookingID, lblAccomodationID, lblStartDate, lblEndDate, lblType, lblMemberCount,
            lblTotalPrice;
    private TextField txtGuestID, txtbookingID, txtAccomodationID, txtStartDate, txtEndDate, txtType, txtMemberCount,
            txtTotalPrice;
    private Button btnRemove, btnSave, btnClose;


    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage obStage)
    {
        obBPane = new BorderPane();

        btnRemove = new Button("Remove");
        obBPane.setBottom(btnRemove);

        obStage.setScene(new Scene(obBPane, 700, 500));
        obStage.setTitle("Cest Lake - Remove Booking");
        obStage.show();
    }
}
