//package com.example.demo7;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
import javafx.scene.text.Text;
import javafx.scene.image.Image;

public class App extends Application {


    // global variables
    private Label statusLabel;
    private VBox friendsList;
    private Label profileName;
    private Person foundProfile;

    @Override
    public void start(Stage primaryStage) {

        // Declare background As a BorderPane, left bar as vbox, top bar as hbox, Center
        // Section as BorderPane
        BorderPane background = new BorderPane();
        VBox leftBar = new VBox();
        HBox topBar = new HBox();
        BorderPane centerSection = new BorderPane();
        statusLabel = new Label("Status: ");
        friendsList = new VBox(10); // VBox with spacing for friends list




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

        //Button Homebtn = new Button("Home");
        //lookUpBtn.setPrefWidth(80);

        nameBox.getChildren().add(nameLabel);
        nameBox.getChildren().add(nameTextField);

        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(20);

        topBar.getChildren().addAll(nameBox, addBtn, delBtn, lookUpBtn);


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

        // Additional feature 
        HBox extraBtns = new HBox(8);
        Button dispProf = new Button("Display Profiles");

        extraBtns.setAlignment(Pos.CENTER);
        dispProf.setPrefWidth(100);
        extraBtns.getChildren().addAll(dispProf);

        // As the previous
        leftBar.setAlignment(Pos.CENTER);
        leftBar.setSpacing(80);
        leftBar.getChildren().addAll(changeStatBox, changePicBox, addFriendBox, extraBtns);

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




        // **** leftCenterBox Implmentation ****
        StackPane profilePicPane = new StackPane();
        leftCenterBox.getChildren().add(profilePicPane);



        addBtn.setOnAction(e -> {  // **************** Button to add a profile ******************

            String name = nameTextField.getText();

            // set image to null --> to set no image preview
            Image image = null;

            String status = "No current status "; // Default status


            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();
            boolean isAdded = Person.addProfile(name, image, status);
            foundProfile = findProfile(name);


            if (isAdded) {
                // Clear previous content
                leftCenterBox.getChildren().clear();

                // Add profile name
                profileName = new Label(name);
                profileName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileName.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileName);

                // Add "No Image" preview
                noImgPreview(profilePicPane); // Assuming profilePicPane is a StackPane
                leftCenterBox.getChildren().add(profilePicPane);

                // Add profile status
                Label profileStatus = new Label(status);
                profileStatus.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                leftCenterBox.getChildren().add(profileStatus);

                centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70));
                Label friendsText = new Label("Friends:");
                friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                rightCenterBox.setAlignment(Pos.TOP_LEFT);
                rightCenterBox.getChildren().addAll(friendsText);

                Label updatesBarText = new Label("New profile created");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);

            } else {
                Label updatesBarText = new Label("Profile already exists");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
        });

        lookUpBtn.setOnAction(e -> { // ********************* Button to look up a profile *****************************
            String name = nameTextField.getText(); // Get the name entered by the user
            foundProfile = findProfile(name);
            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();
            if (foundProfile != null ) {
                // Clear previous content

                // Add profile name
                profileName.setText(foundProfile.getName());
                profileName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileName.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileName);

                // Add "No Image" preview or actual image if available

                setProfilePic(leftCenterBox, profilePicPane, foundProfile);

                // Add profile status
                statusLabel.setText(foundProfile.getStatus());
                statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                leftCenterBox.getChildren().add(statusLabel);

                Label updatesBarText = new Label("Displaying " + foundProfile.getName());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);

                centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70));
                Label friendsText = new Label("Friends:");
                friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                rightCenterBox.setAlignment(Pos.TOP_LEFT);
                rightCenterBox.getChildren().addAll(friendsText);

                friendsList.getChildren().clear();
                for (Person friend : foundProfile.getFriends()) {
                    Label friendNameLabel = new Label(friend.getName());
                    friendNameLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 15));
                    friendsList.getChildren().add(friendNameLabel);

                } rightCenterBox.getChildren().add(friendsList);

            } else {
                Label updatesBarText = new Label("A profile with name "+nameTextField.getText() +" does not exist");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
        });
        changePicBtn.setOnAction(e -> {  //  **************************** Button to change the profile picture ***************************
            //String name = nameTextField.getText(); // Get the name entered by the user
            //Person foundProfile = findProfile(name);
            String imagePath = changePicTextField.getText();

            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();
            if (foundProfile != null && imagePath != null && !imagePath.isEmpty() ) {

                profileName.setText(foundProfile.getName());
                profileName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileName.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileName);

                Image newImage = new Image(imagePath);
                foundProfile.setImage(newImage);
                setProfilePic(leftCenterBox, profilePicPane, foundProfile);



                // Add profile status
                statusLabel.setText(foundProfile.getStatus());
                statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                leftCenterBox.getChildren().add(statusLabel);

                Label updatesBarText = new Label(foundProfile.getName() + " Profile Picture Has Been Changed");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);

                centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70));
                Label friendsText = new Label("Friends:");
                friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                rightCenterBox.setAlignment(Pos.TOP_LEFT);
                rightCenterBox.getChildren().addAll(friendsText);

                friendsList.getChildren().clear();
                for (Person friend : foundProfile.getFriends()) {
                    friendsList.getChildren().add(new Label(friend.getName()));
                } rightCenterBox.getChildren().add(friendsList);

            } else {
                Label updatesBarText = new Label("NO");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
        });
        addFriendBtn.setOnAction(e -> {  // ******************** Button to add a friend to a profile ********************
            String name = nameTextField.getText(); // Get the name entered by the user
            Person foundProfile = findProfile(name);

            String friendName = addFriendTextField.getText();
            Person friendProfile = findProfile(friendName);

            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();
            if (foundProfile != null && friendProfile != null) {

                profileName.setText(foundProfile.getName());
                profileName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileName.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileName);


                setProfilePic(leftCenterBox, profilePicPane, foundProfile);



                // Add profile status
                statusLabel.setText(foundProfile.getStatus());
                statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                leftCenterBox.getChildren().add(statusLabel);

                Label updatesBarText = new Label(friendProfile.getName() + " is Added as a Friend");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);

                centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70));
                Label friendsText = new Label("Friends:");
                friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                rightCenterBox.setAlignment(Pos.TOP_LEFT);


                friendsList.getChildren().clear();
                foundProfile.addFriend(friendProfile);
                for (Person friend : foundProfile.getFriends()) {
                    Label friendNameLabel = new Label(friend.getName());
                    friendNameLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 15));

                    friendsList.getChildren().add(friendNameLabel);
                } rightCenterBox.getChildren().addAll(friendsText ,friendsList);


            } else {
                Label updatesBarText = new Label("NO");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
        });

        changeStatBtn.setOnAction(e -> {
            String status = changeStatTextField.getText();

            if (foundProfile == null) {
                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label("Please select a profile to change status");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            } else {
                foundProfile.setStatus(status);
            
                leftCenterBox.getChildren().clear();

                profileName = new Label(foundProfile.getName());
                profileName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileName.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileName);


                setProfilePic(leftCenterBox, profilePicPane, foundProfile);



                Text statusLabel = new Text(foundProfile.getStatus());

                double wrapingWidth = 225;
                statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                statusLabel.setWrappingWidth(wrapingWidth);
                leftCenterBox.getChildren().add(statusLabel);


                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label(" Status updated to: "+ status);
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
            
        });



        delBtn.setOnAction(e -> {  // ******************** Button to add a friend to a profile ********************
            String name = nameTextField.getText(); // Get the name entered by the user
            Person foundProfile = findProfile(name);
            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();
            if (foundProfile != null) {
                foundProfile.DelProfile(name);
                foundProfile.DelFriend();
                Label updatesBarText = new Label(" Profile of "+ nameTextField.getText()+" deleted");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
                foundProfile = null;

            } else {
                Label updatesBarText = new Label(" A profile with name "+ nameTextField.getText()+" does not exist");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
        });

        dispProf.setOnAction(e -> {
            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();

            Label addedProfilesLabel = new Label("Added Profiles");
            addedProfilesLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 25));
            rightCenterBox.getChildren().add(addedProfilesLabel);

            Label updatesBarText = new Label("Displaying Available Profiles");
            updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
            bottomCenterBox.getChildren().addAll(updatesBarText);

            if (Person.getUserProfiles().size() == 0) {
                Label noCurrentProfilesLabel = new Label("There is No Available Profiles Yet.");
                noCurrentProfilesLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

                rightCenterBox.getChildren().add(noCurrentProfilesLabel);
            } else {
                for (Person profile : Person.getUserProfiles()) {
                Label dispProfileName = new Label(profile.getName());

                dispProfileName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                rightCenterBox.getChildren().add(dispProfileName);
                }
            }

            
        });

        // **** bottomCenterBox Implementation ****

        // updates bar label

        // **** rightCenterBox Implementation ****
        centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70));

        Label friendsText = new Label("Friends:");
        friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));



        rightCenterBox.setAlignment(Pos.TOP_LEFT);
        centerSection.setCenter(rightCenterBox);

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

        double borderWidth = 2;
        Rectangle border = new Rectangle(
                rect.getX() + borderWidth,
                rect.getY() + borderWidth,
                rect.getWidth() - 2 * borderWidth,
                rect.getHeight() - 2 * borderWidth
        );
        border.setFill(null);
        border.setStrokeWidth(borderWidth);
        border.setStroke(Color.BLACK);

        // No Image text
        Label noImg = new Label("No Image");
        noImg.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));

        profilePic.getChildren().clear(); // clear any other children
        profilePic.getChildren().addAll(rect, border, noImg);
    }


    public void setProfilePic(VBox leftCenterBox, StackPane profilePicPane, Person foundProfile) {
        profilePicPane.getChildren().clear();
        if (foundProfile.getImage() == null) {
            noImgPreview(profilePicPane);
        } else {
            ImageView imageView = new ImageView(foundProfile.getImage());
            imageView.setFitWidth(225);
            imageView.setFitHeight(225);
            profilePicPane.getChildren().add(imageView);
        }

        leftCenterBox.getChildren().add(profilePicPane);

    }



    private Person findProfile(String name) {   // Searching the profile by name tho the list
        for (Person profile : Person.getUserProfiles()) {
            if (profile.getName().equalsIgnoreCase(name)) {
                return profile;
            }


        }
        return null;
    }

}