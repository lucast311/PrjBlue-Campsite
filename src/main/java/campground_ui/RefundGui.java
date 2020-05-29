package campground_ui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RefundGui extends Application {

    Button buttonyes;
    Button buttonno;
    Text confirm;
    Text remainder;
    Button buttonsubmit;
    Button buttoncancel;
    TextField inputtext;

    //below is temporary for checking
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Modify Booking Refund");

        buttonyes = new Button();
        buttonyes.setText("Yes");


        buttonno = new Button();
        buttonno.setText("No");

        buttonsubmit = new Button();
        buttonsubmit.setText("Submit");

        buttoncancel = new Button();
        buttoncancel.setText("Cancel");

        inputtext = new TextField();


        confirm = new Text();
        confirm.setText("would you like to refund for remaining days?");

        remainder = new Text();
        remainder.setText("Remainder:");

        BorderPane borderPane = new BorderPane();

        //HBox statusbar = new HBox();
        //Node appContent = new AppContentNode();
        //borderPane.setTop(confirm);
        //borderPane.setCenter(buttonyes);
        //borderPane.setCenter(buttonno);
        //borderPane.setCenter(inputtext);
        //borderPane.setCenter(remainder);
        //borderPane.setBottom(buttonsubmit );
        //borderPane.setBottom(buttoncancel );

        borderPane.setPadding(new Insets(50));
        VBox paneCenter = new VBox();
        HBox buttonbar1 = new HBox();
        HBox buttonbar2 = new HBox();
        HBox remainderbar = new HBox();
        VBox centerbar = new VBox();
        paneCenter.setSpacing(15);
        borderPane.setTop(paneCenter);
        borderPane.setCenter(centerbar);
        //borderPane.setCenter(remainderbar);
        //borderPane.setRight(remainderbar);
        borderPane.setBottom(buttonbar2);
        paneCenter.getChildren().add(confirm);
        buttonbar1.getChildren().add(buttonyes);
        buttonbar1.getChildren().add(buttonno);
        remainderbar.getChildren().add(remainder);
        remainderbar.getChildren().add(inputtext);
        buttonbar2.getChildren().add(buttonsubmit);
        buttonbar2.getChildren().add(buttoncancel);
        centerbar.getChildren().addAll(buttonbar1, remainderbar);
        centerbar.setAlignment(Pos.CENTER);
        buttonbar1.setAlignment(Pos.CENTER);
        buttonbar2.setAlignment(Pos.CENTER);
        remainderbar.setAlignment(Pos.CENTER);
        paneCenter.setAlignment(Pos.CENTER);
        buttonyes.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

            }
        });
        buttonno.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

            }
        });

        //borderPane.getChildren().addAll(confirm,buttonyes, buttonno, buttonsubmit, buttoncancel, inputtext, remainder);

        Scene scene = new Scene(borderPane, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
