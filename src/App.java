
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
       
        // Declare background As a BorderPane, left bar as vbox, top bar as hbox, Center
        // Section as BorderPane
        BorderPane background = new BorderPane();
        VBox leftBar = new VBox();
        HBox topBar = new HBox();
        BorderPane centerSection = new BorderPane();

        background.setMargin(centerSection, new Insets(20));


        // Set Dimensions and Colors for topBar and leftBar

        leftBar.setPadding(new Insets(20));
        topBar.setPadding(new Insets(20));

        background.setLeft(leftBar);
        background.setTop(topBar);
        background.setCenter(centerSection);

        leftBar.setStyle("-fx-background-color: lightgrey;");
        topBar.setStyle("-fx-background-color: lightgrey;");





        // ####################### topBar Contents ######################

        HBox nameBox = new HBox(10); // Spacing of value 10
        nameBox.setAlignment(Pos.CENTER); // set to center

        // **** TextField Implementation ****
        TextField nameTextField = new TextField();
        Label nameLabel = new Label("Name");
        nameTextField.setPrefWidth(175);
        nameLabel.setFont(new Font(15));

        // **** Add Button Implementation ****
        Button addBtn = new Button("Add");
        addBtn.setPrefWidth(80);

        // **** Delete Button Implementation ****
        Button delBtn = new Button("Delete");
        delBtn.setPrefWidth(80);

        // **** Look Up Button Implementation ****
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

        // set the dimensions and append the nodes to changeStatBox
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

        VBox leftCenterBox = new VBox(20);
        VBox rightCenterBox = new VBox(5);
        StackPane bottomCenterBox = new StackPane();

        centerSection.setLeft(leftCenterBox);
        centerSection.setBottom(bottomCenterBox);
        centerSection.setCenter(rightCenterBox);




        // **** leftCenterBox Implmetation ****

        // Profile Name
        Label profileName = new Label("Moh Ali");
        profileName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));

        // Profile Picture
        StackPane profilePic = new StackPane();
        noImgPreview(profilePic); // set a noImg preview when no picture provided

        // Profile Status
        Label profileStatus = new Label("No current status");
        profileStatus.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

        // add all nodes to leftCenterBox
        leftCenterBox.getChildren().addAll(profileName, profilePic, profileStatus);





        // **** bottomCenterBox Implementation ****

        // updates bar label
        Label updatesBarText = new Label("NONE");
        updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

        bottomCenterBox.getChildren().addAll(updatesBarText);



        // **** rightCenterBox Implementation ****
        centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70));

        Label friendsText = new Label("Friends:");
        friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

        rightCenterBox.setAlignment(Pos.TOP_LEFT);
        rightCenterBox.getChildren().addAll(friendsText);

        // centerSection DONE!



        // ############## Set scene and Display the Stage ######################

        Scene scene = new Scene(background, 900, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("FaceLite");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // left bar construction submethod (helper method)
    public static void setLeftBarContents(VBox vbox, TextField txt, Button btn) {
        txt.setPrefWidth(200);
        btn.setPrefWidth(200);

        vbox.getChildren().add(txt);
        vbox.getChildren().add(btn);
    }

    // update img to noImg preview (helper method)
    public static void noImgPreview(StackPane profilePic) {

        // rectangle as a frame
        Rectangle rect = new Rectangle(225, 225);
        rect.setFill(null);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);

        // No Image text
        Label noImg = new Label("No Image");
        noImg.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));

        profilePic.getChildren().clear(); // clear any other children
        profilePic.getChildren().addAll(rect, noImg);
    }

}
