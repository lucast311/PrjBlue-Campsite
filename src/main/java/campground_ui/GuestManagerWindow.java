package campground_ui;

import campground_data.Guest;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import java.util.ArrayList;

public class GuestManagerWindow extends Application
{
    private BorderPane borderPane = new BorderPane();
    private GridPane guestsPane = new GridPane();
    private ArrayList<Guest> guests = new ArrayList<>();
    private Text txtSearch = new Text("Search");
    private TextField txtSearchField = new TextField();
    private HBox topBox = new HBox();
    private HBox searchBox = new HBox();
    private HBox buttonsBox = new HBox();
    private Button btnAddGuest = new Button("Add Guest");
    private Button btnEditGuest = new Button("Edit Guest");
    private Button btnBack = new Button("Back");
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {

        searchBox.setSpacing(10);
        searchBox.getChildren().addAll(txtSearch,txtSearchField);

        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(50, 20, 50, 20));
        topBox.setSpacing(100);
        topBox.getChildren().addAll(searchBox, btnBack);

        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);
        buttonsBox.setPadding(new Insets(50, 0, 50, 0));
        buttonsBox.getChildren().addAll(btnAddGuest,btnEditGuest);

        guestsPane.setAlignment(Pos.CENTER);

        borderPane.setCenter(guestsPane);
        borderPane.setTop(topBox);
        borderPane.setBottom(buttonsBox);


        primaryStage.setScene(new Scene(borderPane, 400, 400));
        primaryStage.setTitle("Guest Manager");
        primaryStage.show();
    }



}
