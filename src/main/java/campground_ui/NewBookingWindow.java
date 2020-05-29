package campground_ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NewBookingWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage obStage;
    //Criteria fields
    private static TextField tfStartDate = new TextField();
    private static TextField tfEndDate = new TextField();
    private static ComboBox cbAccommodationType = new ComboBox();
    private static TextField tfMemberCount = new TextField();
    private static ComboBox cbAccommodationID = new ComboBox();
    private static TextField tfGuestID = new TextField();
    private static Button btnAdd = new Button();
    private static Button btnClear = new Button();
    private static Button btnCancel = new Button();


    @Override
    public void start(Stage primaryStage) {
        obStage = primaryStage;
        BorderPane obPane = new BorderPane();

        //Criteria fields setup
        VBox obCriteria = new VBox();
        obCriteria.setSpacing(10);
        obCriteria.setAlignment(Pos.CENTER_LEFT);
        obPane.setLeft(obCriteria);

        obCriteria.getChildren().add(new Label("Start Date (dd/mm/yyyy)"));
        obCriteria.getChildren().add(tfStartDate);

        obCriteria.getChildren().add(new Label("End Date (dd/mm/yyyy)"));
        obCriteria.getChildren().add(tfEndDate);

        obCriteria.getChildren().add(new Label("Accommodation Type"));
        cbAccommodationType.getItems().add(0, "Cabin");
        cbAccommodationType.getItems().add(1, "Site");
        obCriteria.getChildren().add(cbAccommodationType);

        obCriteria.getChildren().add(new Label("Member Count"));
        obCriteria.getChildren().add(tfMemberCount);

        obCriteria.getChildren().add(new Label("Accommodation ID"));
        obCriteria.getChildren().add(cbAccommodationID);

        obCriteria.getChildren().add(new Label("Guest ID"));
        obCriteria.getChildren().add(tfGuestID);

        btnAdd.setText("Add");
        btnAdd.setOnAction(e-> {
            addButton();
        });
        btnClear.setText("Clear");
        btnClear.setOnAction(e-> {
            clearButton();
        });
        btnCancel.setText("Cancel");
        btnCancel.setOnAction(e-> {
            cancelButton();
        });

        HBox obButtons = new HBox();
        obButtons.setSpacing(10);
        obButtons.getChildren().add(btnAdd);
        obButtons.getChildren().add(btnClear);
        obButtons.getChildren().add(btnCancel);
        obCriteria.getChildren().add(obButtons);


        primaryStage.setScene(new Scene(obPane, 800, 500));
        primaryStage.setTitle("New Booking");
        primaryStage.show();
    }

    private static void clearButton()
    {
        tfStartDate.setText("");
        tfEndDate.setText("");
        cbAccommodationType.setValue("");
        tfMemberCount.setText("");
        cbAccommodationID.setValue("");
        tfGuestID.setText("");
    }

    private static void cancelButton()
    {
        obStage.close();
    }

    private static void addButton()
    {

    }
}
