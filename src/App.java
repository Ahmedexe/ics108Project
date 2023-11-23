
import javax.swing.plaf.ButtonUI;

import javafx.application.Application;
import javafx.scene.Node;
// import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.Border;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        /*
         * // Outer StackPane
         * StackPane outerPane = new StackPane();
         * 
         * // Inner StackPane
         * StackPane innerPane = new StackPane();
         * innerPane.setStyle("-fx-background-color: lightblue;");
         * 
         * // Button inside the inner StackPane
         * Button button = new Button("Click me!");
         * 
         * // Adding the Button to the inner StackPane
         * innerPane.getChildren().add(button);
         * 
         * // Adding the inner StackPane to the outer StackPane
         * outerPane.getChildren().add(innerPane);
         * 
         * // Create a Scene and set it on the Stage
         * Scene scene = new Scene(outerPane, 300, 200);
         * primaryStage.setScene(scene);
         * 
         * // Set the title and show the Stage
         * primaryStage.setTitle("Nested Pane Example");
         * primaryStage.show();
         * 
         * 
         * 
         * BorderPane bp = new BorderPane();
         * HBox leftP = new HBox();
         * VBox topP = new VBox();
         * 
         * int x = 30;
         * 
         * topP.setPadding(new Insets(x, x, x, x));
         * leftP.setPadding(new Insets(x, x, x, x));
         * 
         * 
         * 
         * topP.setStyle("-fx-background-color: lightblue;");
         * leftP.setStyle("-fx-background-color: lightblue;");
         * 
         * bp.setCenter(new Label("Any"));
         * bp.setLeft(leftP);
         * bp.setTop(topP);
         * 
         * Scene scene = new Scene(bp, 500, 500);
         * primaryStage.setScene(scene);
         * 
         */



        // Declare background As a BorderPane, left bar as vbox, top bar as hbox, Center Section as BorderPane
        BorderPane background = new BorderPane();
        VBox leftBar = new VBox();
        HBox topBar = new HBox();
        BorderPane centerSection = new BorderPane();


        // Set Dimensions and Colors for topBar and leftBar

        leftBar.setPadding(new Insets(20));
        topBar.setPadding(new Insets(20));

        background.setLeft(leftBar);
        background.setTop(topBar);

        leftBar.setStyle("-fx-background-color: lightgrey;");
        topBar.setStyle("-fx-background-color: lightgrey;");

        // ####################### topBar Contents ######################

        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.CENTER);

        TextField nameTextField = new TextField();
        Label nameLabel = new Label("Name");
        nameTextField.setPrefWidth(175);
        nameLabel.setFont(new Font(15));

        Button addBtn = new Button("Add");
        addBtn.setPrefWidth(80);

        Button delBtn = new Button("Delete");
        delBtn.setPrefWidth(80);

        Button lookUpBtn = new Button("Look Up");
        lookUpBtn.setPrefWidth(80);

        nameBox.getChildren().add(nameLabel);
        nameBox.getChildren().add(nameTextField);

        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(20);

        topBar.getChildren().add(nameBox);
        topBar.getChildren().add(addBtn);
        topBar.getChildren().add(delBtn);
        topBar.getChildren().add(lookUpBtn);



        // -- top bar DONE!

        // ###################### leftBar contents ################


        VBox changeStatBox = new VBox(10); // Container for the TextField and the Button
        TextField changeStatTextField = new TextField(); // TextField
        Button changeStatBtn = new Button("Change Status"); // Button

        // set the dimenstions and append the nodes to changeStatBox
        setLeftBarContents(changeStatBox, changeStatTextField, changeStatBtn);


        // As the previous
        VBox changePicBox = new VBox(10);
        TextField changePicTextField = new TextField();
        Button changePicBtn = new Button("Change Picture");

        setLeftBarContents(changePicBox, changePicTextField, changePicBtn);


        // As the previous
        VBox addFriendBox = new VBox(10);
        TextField addFriendTextField = new TextField();
        Button addFriendBtn = new Button("Add Friend");

        setLeftBarContents(addFriendBox, addFriendTextField, addFriendBtn);


        // As the previous
        leftBar.setAlignment(Pos.CENTER);
        leftBar.setSpacing(100);
        leftBar.getChildren().addAll(changeStatBox, changePicBox, addFriendBox);


        // -- leftBar DONE!


        // ################ Center Section Contents ####################
        /* 
         * Divide the center to 3 subsections as follow:-
         * 
         * 
         * left ---> for profile name, profile img, status
         * 
         * center ---> friends
         * 
         * bottom ---> interactive text
         */

         


        // ############## Set scene and Display the Stage ######################

        Scene scene = new Scene(background, 900, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("FaceLite");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setLeftBarContents(VBox vbox, TextField txt, Button btn) {
        txt.setPrefWidth(200);
        btn.setPrefWidth(200);

        vbox.getChildren().add(txt);
        vbox.getChildren().add(btn);
    }

}
