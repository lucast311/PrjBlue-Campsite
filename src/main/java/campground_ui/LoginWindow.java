package campground_ui;

import campground_data.OwnerHelper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {

    BorderPane obPane = new BorderPane();
    StackPane obStack = new StackPane();
    TextField txtId, txtPass;
    Label lblId, lblPass, lblTitle;
    Button cmdLogin;
    OwnerHelper ownerHelper = new OwnerHelper();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        establishGrid(obPane);
        cmdLogin = new Button("Login");
        cmdLogin.setOnAction( e -> {
            ownerHelper.validateUser(txtId.getText(), txtPass.getText());
        });
        primaryStage.setScene(new Scene(obPane, 400, 400));
        primaryStage.setTitle("Cest Lake - Login");
        primaryStage.show();
    }

    public void establishGrid(BorderPane pane)
    {
        pane.setCenter(obStack);
        obStack.setAlignment(Pos.CENTER);
        obStack.setPadding(new Insets(5));
//        obStack.setVgap(20);
//        obStack.setHgap(5);
        lblId = new Label("UserID:");
        lblPass = new Label("Password:");
        lblTitle = new Label("Enter your UserID and Password to log in");
        txtId = new TextField();
        txtPass = new TextField();

        obStack.getChildren().addAll(txtPass, lblPass, txtId, lblId, lblTitle);
//        obStack.add(lblId, 0, 4);
//        obStack.add(txtId, 1, 4);
//        obStack.add(lblPass, 0, 6);
//        obStack.add(txtPass, 1, 6);
        obPane.setTop(lblTitle);
        obPane.setCenter(obStack);
    }
}

