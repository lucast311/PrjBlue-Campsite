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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow extends Application {

    BorderPane obPane = new BorderPane();
    GridPane obGrid = new GridPane();
    TextField txtId, txtPass;
    Label lblId, lblPass, lblTitle;
    Button cmdLogin;
    OwnerHelper ownerHelper = new OwnerHelper();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        obPane.setStyle("-fx-background-color:aliceblue");
        cmdLogin = new Button("LOGIN");
        establishGrid(obPane);
        cmdLogin.setOnAction( e -> {
            ownerHelper.validateUser(txtId.getText(), txtPass.getText());
        });
        cmdLogin.setPrefSize(70, 40);
        primaryStage.setScene(new Scene(obPane, 400, 400));
        primaryStage.setTitle("Cest Lake - Login");
        primaryStage.show();
    }

    public void establishGrid(BorderPane pane)
    {
        pane.setCenter(this.obGrid);
        VBox top_msg = new VBox();
        top_msg.setAlignment(Pos.CENTER);
        top_msg.setPadding(new Insets(20));
        this.obGrid.setAlignment(Pos.BASELINE_CENTER);
        this.obGrid.setPadding(new Insets(5));
        this.obGrid.setVgap(20);
        this.obGrid.setHgap(5);
        lblId = new Label("UserID:");
        lblPass = new Label("Password:");
        lblTitle = new Label("Enter your UserID and Password to Log In");
        lblTitle.setStyle("-fx-font-size:15px; -fx-font-weight:bold");
        top_msg.getChildren().add(lblTitle);
        txtId = new TextField();
        txtPass = new TextField();

        obGrid.add(lblId, 0, 1);
        obGrid.add(txtId, 1, 1);
        obGrid.add(lblPass, 0, 3);
        obGrid.add(txtPass, 1, 3);
        obGrid.add(cmdLogin, 1, 4);
        obPane.setTop(top_msg);

        obPane.setCenter(this.obGrid);
    }
}

