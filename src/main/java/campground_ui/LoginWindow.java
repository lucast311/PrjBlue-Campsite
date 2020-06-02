package campground_ui;

import campground_data.Owner;
import campground_data.OwnerHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginWindow extends Stage {

    BorderPane obPane = new BorderPane();
    GridPane obGrid = new GridPane();
    TextField txtId;
    Text txtError;
    PasswordField txtPass;
    Label lblId, lblPass, lblTitle;
    Button cmdLogin;
    Button cmdClose;
    OwnerHelper ownerHelper = new OwnerHelper();
    private static Owner currUser = new Owner();

    /**
     * Constructor to create a login window when the program is first opened. It opens as a modal window,
     * meaning that you must login inorder to use the application
     * @param parent
     */
    public LoginWindow(Stage parent) {
        obPane.setStyle("-fx-background-color:aliceblue");
        cmdLogin = new Button("LOGIN");
        cmdClose = new Button("CLOSE");
        establishGrid(obPane);

        // set the functionality for the login button to call the validate user method in the ownerHelper
        // if the entered credentials are valid this function will set the currUser in the main window. Otherwise
        // it will display an error message.
        cmdLogin.setOnAction( e -> {

            currUser = ownerHelper.validateUser(txtId.getText(), txtPass.getText());
            if(currUser != null)
            {
                MainWindow.setUser(currUser);
                this.close();
            }
            else
            {
                txtError.setText("UserID or password invalid, try again");
                txtPass.setText("");
            }
        });
        cmdLogin.setPrefSize(70, 40);

        // set the functionality for the close button. When clicked it will completely close the program
        // clicking the x in the window corner just tries to close this window and since you must log in
        // to use the system you can't leave the program that way
        cmdClose.setOnAction( e -> {
            System.exit(0);
        });

        // set the functionality for pressing enter after entering a password to call the validate user method in the ownerHelper
        // if the entered credentials are valid this function will set the currUser in the main window. Otherwise
        // it will display an error message.
        txtPass.setOnKeyPressed(e -> {
            KeyCode k = e.getCode();
            if(k.equals(KeyCode.ENTER))
            {
                currUser = ownerHelper.validateUser(txtId.getText(), txtPass.getText());
                if(currUser != null)
                {
                    MainWindow.setUser(currUser);
                    this.close();
                }
                else
                {
                    txtError.setText("UserID or password invalid, try again");
                    txtPass.setText("");
                }
            }
        });
        cmdClose.setPrefSize(70, 40);
        cmdClose.setStyle("-fx-background-color:indianred");
        this.setScene(new Scene(obPane, 400, 400));
        this.setTitle("Cest Lake - Login");
        this.initOwner(parent);
        this.initModality(Modality.WINDOW_MODAL);
    }

    /**
     * Creates a gridpane and sets it as the center of the borderpane that is the main window pane.
     * @param pane
     */
    public void establishGrid(BorderPane pane)
    {
        pane.setCenter(this.obGrid);
        VBox top_msg = new VBox();
        top_msg.setAlignment(Pos.CENTER);
        top_msg.setPadding(new Insets(20));
        lblTitle = new Label("Enter your UserID and Password to Log In");
        lblTitle.setStyle("-fx-font-size:15px; -fx-font-weight:bold");
        top_msg.getChildren().add(lblTitle);
        obPane.setTop(top_msg);

        //set the layout of the grid pane
        this.obGrid.setAlignment(Pos.BASELINE_CENTER);
        this.obGrid.setPadding(new Insets(5));
        this.obGrid.setVgap(20);
        this.obGrid.setHgap(5);

        // initialize the controls that will be on the grid pane
        lblId = new Label("UserID:");
        lblPass = new Label("Password:");
        txtId = new TextField("");
        txtPass = new PasswordField();
        txtPass.setText("");
        txtError = new Text();
        txtError.setStyle("-fx-background-color:transparent");
        txtError.setFill(Color.RED);

        // set the position of each component of the grid pane
        obGrid.add(lblId, 0, 1);
        obGrid.add(txtId, 1, 1);
        obGrid.add(lblPass, 0, 2);
        obGrid.add(txtPass, 1, 2);
        obGrid.add(txtError, 0, 3, 3, 1);
        obGrid.add(cmdLogin, 1, 4);
        obGrid.add(cmdClose, 1, 5);
    }

}

