package campground_data;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {

    public static void main(String[] args) {
       Application.launch(args);
    }

    BorderPane obPane = new BorderPane();
    GridPane obGrid = new GridPane();
//    TextField txtId, txtPass;
//    Label lblId, lblPass;

    @Override
    public void start(Stage primaryStage) {

//        TextField txtId = new TextField();
//        Button cmdOK = new Button("OK");
//        Label lblId = new Label("UserID:");

//        obGrid.setAlignment(Pos.CENTER);
//        obGrid.setPadding(new Insets(5));
//        obGrid.setVgap(5);
//        obGrid.setHgap(5);
//
//        obPane.setCenter(obGrid);

        primaryStage.setScene(new Scene(obPane, 400, 400));
        primaryStage.setTitle("Cest Lake - Login");
        primaryStage.show();
    }

//    public void establishGrid(BorderPane pane)
//    {
//        pane.setCenter(obGrid);
//        obGrid.setAlignment(Pos.CENTER);
//        obGrid.setPadding(new Insets(5));
//        obGrid.setVgap(5);
//        obGrid.setHgap(5);
//        lblId = new Label("UserID:");
//        lblPass = new Label("Password:");
//        txtId = new TextField();
//        txtPass = new TextField();

//    }
}
