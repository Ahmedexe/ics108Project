
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
import java.util.ArrayList;

public class App extends Application {


    // global variables
    private Label statusLabel;
    private VBox friendsList;
    private Label profileNameLabel;
    private Person foundProfile;


    @Override
    public void start(Stage primaryStage) {

        // Declare background As a BorderPane, left bar as vbox, top bar as hbox, Center
        // Section as BorderPane
        BorderPane background = new BorderPane();  // Main layout pane
        VBox leftBar = new VBox(); // Vertical box for the left side of the UI
        HBox topBar = new HBox(); // Horizontal box for the top  side of the UI
        BorderPane centerSection = new BorderPane(); // Center section to hold the profile details
        statusLabel = new Label("Status: ");
        friendsList = new VBox(10); // VBox with spacing for friends list




        background.setMargin(centerSection, new Insets(20));  // Set a margin for the center section


        // Set Dimensions and Colors for topBar and leftBar

        // Set padding for the left and top bars
        leftBar.setPadding(new Insets(20));
        topBar.setPadding(new Insets(20));
        // Attach the left & top & center sections to the background
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



        // Add the name label and text field to the name box
        nameBox.getChildren().add(nameLabel);
        nameBox.getChildren().add(nameTextField);

        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(20);
        // Add the name box and buttons to the top bar
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

        // Additional feature

        HBox Home_ResetBox = new HBox(10);
        Home_ResetBox.setAlignment(Pos.BOTTOM_CENTER);
        Button HomeBtn = new Button("Home");
        Button ResetBtn = new Button("Reset");

           // Add buttons to the Home_reset HBox
        Home_ResetBox.getChildren().addAll(HomeBtn, ResetBtn);




        // As the previous
        leftBar.setAlignment(Pos.CENTER);
        leftBar.setSpacing(45);
        leftBar.getChildren().addAll(changeStatBox, changePicBox, addFriendBox, extraBtns,Home_ResetBox);

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



        addBtn.setOnAction(e -> {
            // **************** Button to add a profile ******************
            try {



                // Check if the name text field is empty or blank
                if (nameTextField.getText().toLowerCase().isEmpty() || nameTextField.getText().toLowerCase().isBlank()) {
                    throw new EmptyTextFeildException("Please Select a Profile to add it");
                }
                String name = nameTextField.getText().toLowerCase();

                // set image to null --> to set no image preview
                Image image = null;

                String status = "No current status "; // Default status


                boolean isAdded = Person.addProfile(name, image, status);
                foundProfile = findProfile(name);

                // Check if the profile was successfully added
                if (isAdded) {
                    // Clear previous content
                    leftCenterBox.getChildren().clear();
                    bottomCenterBox.getChildren().clear();
                    rightCenterBox.getChildren().clear();
                    clearTextFeilds(changePicTextField, changeStatTextField, addFriendTextField);

                    // Add profile name
                    profileNameLabel = new Label(name);
                    if (profileNameLabel.getText().length() >= 13) {
                        profileNameLabel = new Label(profileNameLabel.getText().substring(0, 12) + "...");

                    }
                    profileNameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                    profileNameLabel.setTextFill(Color.BLUE);

                    // Add the profile name label to the left center box
                    leftCenterBox.getChildren().add(profileNameLabel);


                    // Add "No Image" preview
                    noImgPreview(profilePicPane); // Assuming profilePicPane is a StackPane
                    leftCenterBox.getChildren().add(profilePicPane);

                    // Add profile status
                    Label profileStatus = new Label(status);
                    profileStatus.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                    leftCenterBox.getChildren().add(profileStatus);
                    // Set margin
                    centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70));
                    // Create and add a label indicating the friends section
                    Label friendsText = new Label("Friends:");
                    friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                    rightCenterBox.setAlignment(Pos.TOP_LEFT);
                    rightCenterBox.getChildren().addAll(friendsText);

                    Label updatesBarText = new Label("New profile created");
                    updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                    bottomCenterBox.getChildren().addAll(updatesBarText);

                } else {
                    // If the profile already exists, display a message
                    bottomCenterBox.getChildren().clear();
                    Label updatesBarText = new Label("Profile With Name " + foundProfile.getName() + " already exists");
                    updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                    bottomCenterBox.getChildren().addAll(updatesBarText);
                }
            }
            catch (EmptyTextFeildException ex1) {
                // Catch empty text feild

                // Clear content
                leftCenterBox.getChildren().clear();
                bottomCenterBox.getChildren().clear();
                rightCenterBox.getChildren().clear();
                // Display the exception message
                Label updatesBarText = new Label(ex1.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
        });

        lookUpBtn.setOnAction(e -> { // ********************* Button to look up a profile *****************************

            try {
            String name = nameTextField.getText(); // Get the name entered by the user
            Person prevPerson = foundProfile;

            if (name.isEmpty() || name.isBlank()) {
                throw new EmptyTextFeildException("Please Write a Profile Name To Look Up");
            }

            foundProfile = findProfile(name);
            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();

            if (prevPerson != foundProfile) {
                // Clear previous content

                clearTextFeilds(changePicTextField, changeStatTextField, addFriendTextField);
            }

            if (foundProfile != null) {


                // Add profile name
                profileNameLabel.setText(foundProfile.getName());
                profileNameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileNameLabel.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileNameLabel);

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

                }
                rightCenterBox.getChildren().add(friendsList);

            } else {
                // If the profile is not found, display an error message
                Label updatesBarText = new Label("A profile with name " + nameTextField.getText() + " does not exist");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
            } catch (EmptyTextFeildException ex1) {
                // Catch empty text feild

                // Clear content
                leftCenterBox.getChildren().clear();
                bottomCenterBox.getChildren().clear();
                rightCenterBox.getChildren().clear();
                // Display the exception message
                Label updatesBarText = new Label(ex1.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }   
        });



        changePicBtn.setOnAction(e -> {  //  **************************** Button to change the profile picture ***************************


            try { // Check if a profile is selected

                if (foundProfile == null) {
                    throw new NoProfileChosenException("Please Select a Profile to Change Picture");
                }

                String imagePath = changePicTextField.getText(); // get image path from the text field
                Image newImage = new Image(imagePath);

                // Clear content
                leftCenterBox.getChildren().clear();
                bottomCenterBox.getChildren().clear();
                rightCenterBox.getChildren().clear();


                profileNameLabel.setText(foundProfile.getName());   // Update the profile name label
                profileNameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileNameLabel.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileNameLabel);



                //Image newImage = new Image(imagePath);
                foundProfile.setImage(newImage);
                setProfilePic(leftCenterBox, profilePicPane, foundProfile); // Set the profile picture



                // Add profile status
                statusLabel.setText(foundProfile.getStatus());
                statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                leftCenterBox.getChildren().add(statusLabel);

                Label updatesBarText = new Label(foundProfile.getName() + " Profile Picture Has Been Changed");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);

                centerSection.setMargin(rightCenterBox, new Insets(40, 0, 0, 70)); // Set margin for the right center box
                Label friendsText = new Label("Friends:");
                friendsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                rightCenterBox.setAlignment(Pos.TOP_LEFT);
                rightCenterBox.getChildren().addAll(friendsText);

                friendsList.getChildren().clear();
                for (Person friend : foundProfile.getFriends()) {
                    friendsList.getChildren().add(new Label(friend.getName()));
                } rightCenterBox.getChildren().add(friendsList);


            } catch (IllegalArgumentException ex1) {
                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label("Picture Path is Invalid");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            } catch (NoProfileChosenException ex2) {
                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label(ex2.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
        });



        addFriendBtn.setOnAction(e -> { // ******************** Button to add a friend to a profile ********************

            try {

                String friendName = addFriendTextField.getText().toLowerCase();

                // Check if the friend's name is provided
                if (friendName.isBlank() || friendName.isEmpty()) {
                    throw new EmptyTextFeildException("Please Fill the Friend Name");
                } else if (foundProfile == null) {
                    throw new NoProfileChosenException("Please Select a Profile to Add a Friend to");
                }

                Person friendProfile = findProfile(friendName);


             // ensure that friendname is not same person
            if (foundProfile != friendProfile) {
                if (foundProfile != null && friendProfile != null) {
                    // Clear content
                    leftCenterBox.getChildren().clear();
                    bottomCenterBox.getChildren().clear();
                    rightCenterBox.getChildren().clear();

                    profileNameLabel.setText(foundProfile.getName());
                    profileNameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                    profileNameLabel.setTextFill(Color.BLUE);
                    leftCenterBox.getChildren().add(profileNameLabel);

                    // Set the profile picture
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
                    }
                    rightCenterBox.getChildren().addAll(friendsText, friendsList);


                } else {
                    // If the friend's profile doesn't exist, show an error message
                    bottomCenterBox.getChildren().clear();
                    Label updatesBarText = new Label("A Profile With Name " + friendName + " Does not Exist");
                    updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                    bottomCenterBox.getChildren().addAll(updatesBarText);
                }
            }else {
                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label("Adding yourself as a friend is not permitted");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
                }
            } catch (EmptyTextFeildException ex1) {
                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label(ex1.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            } catch (NoProfileChosenException ex2) {

                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label(ex2.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);

            }
        });

        changeStatBtn.setOnAction(e -> {
            try {

                String status = changeStatTextField.getText();

                if (foundProfile == null) { // As a previous
                    throw new NoProfileChosenException("Please Select a Profile to Change Status");
                } else if (status.isEmpty() || status.isBlank()) { //As a previous
                    throw new EmptyTextFeildException("Please Fill the Text Feild to Change Status");
                }
                foundProfile.setStatus(status);

                leftCenterBox.getChildren().clear();

                profileNameLabel = new Label(foundProfile.getName()); //As a previous
                profileNameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
                profileNameLabel.setTextFill(Color.BLUE);
                leftCenterBox.getChildren().add(profileNameLabel);


                setProfilePic(leftCenterBox, profilePicPane, foundProfile);


                // Create a new text label for the updated status
                Text statusLabel = new Text(foundProfile.getStatus());

                double wrapingWidth = 225;
                statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                statusLabel.setWrappingWidth(wrapingWidth);
                leftCenterBox.getChildren().add(statusLabel);


                bottomCenterBox.getChildren().clear();

                if (status.length() > 14) {
                    status = status.substring(0, 14) + "...";
                }
                Label updatesBarText = new Label(" Status updated to: "+ status);
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);


            } catch (NoProfileChosenException ex1) { // As a previous

                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label(ex1.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);


            } catch (EmptyTextFeildException ex2) { // As a previous
                bottomCenterBox.getChildren().clear();
                Label updatesBarText = new Label(ex2.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }

        });



        delBtn.setOnAction(e -> {  // ******************** Button to add a friend to a profile ********************
            
            try {
            
            String name = nameTextField.getText(); // Get the name entered by the user
            foundProfile = findProfile(name);

            

            if (name.isEmpty() || name.isBlank()) {
                throw new EmptyTextFeildException("Please Write a Profile Name To Delete");
            }


            if (foundProfile != null) {
                leftCenterBox.getChildren().clear();
                bottomCenterBox.getChildren().clear();
                rightCenterBox.getChildren().clear();
                foundProfile.DelFriend();

                Person.DelProfile(name);      // Apply delProfile method

                Label updatesBarText = new Label(" Profile of "+ name + " Deleted");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
                foundProfile = null;

            } else {
                Label updatesBarText = new Label(" A profile with name "+ nameTextField.getText()+" does not exist");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().clear();
                bottomCenterBox.getChildren().addAll(updatesBarText);
            }
            } catch (EmptyTextFeildException ex1) {
                // Catch empty text feild

                // Clear content
                leftCenterBox.getChildren().clear();
                bottomCenterBox.getChildren().clear();
                rightCenterBox.getChildren().clear();
                // Display the exception message
                Label updatesBarText = new Label(ex1.getMessage());
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                bottomCenterBox.getChildren().addAll(updatesBarText);
            } 
        });

        dispProf.setOnAction(e -> {

            foundProfile = null;

            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();
            clearTextFeilds(changePicTextField, changeStatTextField, addFriendTextField);
            nameTextField.clear();

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
                    Label dispprofileNameLabel = new Label(profile.getName());

                    dispprofileNameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                    rightCenterBox.getChildren().add(dispprofileNameLabel);
                }
            }


        });
        HomeBtn.setOnAction(e -> {  // **************** Button return to home background ******************


                foundProfile = null;
                // Clear  content
                leftCenterBox.getChildren().clear();
                bottomCenterBox.getChildren().clear();
                rightCenterBox.getChildren().clear();
                clearTextFeilds(changePicTextField, changeStatTextField, addFriendTextField);
                nameTextField.clear();
                Label updatesBarText = new Label("HOME PAGE");
                updatesBarText.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 30));
                bottomCenterBox.getChildren().addAll(updatesBarText);



        });

        ResetBtn.setOnAction(e -> {  // **************** Reset all prodiles info ******************
            Person.userProfiles = new ArrayList<>();  // This step to just make userProfile list updated
            foundProfile = null;

            // Clear  content
            leftCenterBox.getChildren().clear();
            bottomCenterBox.getChildren().clear();
            rightCenterBox.getChildren().clear();
            clearTextFeilds(changePicTextField, changeStatTextField, addFriendTextField);
            nameTextField.clear();
            Label updatesBarText = new Label("The program has been successfully reset");
            updatesBarText.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 30));
            bottomCenterBox.getChildren().addAll(updatesBarText);



        });

        // **** bottomCenterBox Implementation ****


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
    private static void setLeftBarContents(VBox vbox, TextField txt, Button btn) {
        txt.setPrefWidth(200);
        btn.setPrefWidth(200);

        vbox.getChildren().add(txt);
        vbox.getChildren().add(btn);
    }

    // update img to noImg preview (helper method)
    private static void noImgPreview(StackPane profilePic) {

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


    private void setProfilePic(VBox leftCenterBox, StackPane profilePicPane, Person foundProfile) {
         /* Sets the profile picture in the application.
          * If the selected profile has no picture, shows a default 'no image' icon.
          * Otherwise, displays the profile's picture.
          */

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


    private void clearTextFeilds(TextField changePicTextField, TextField changeStatTextField, TextField addFriendTextField) {
       // Clear TextField to  ""
        changePicTextField.setText("");
        changeStatTextField.setText("");
        addFriendTextField.setText("");

    }


    private Person findProfile(String name) {
        /* ********** findProfile **********
         * Finds and returns a profile by name. Returns null if not found.
         */
        for (Person profile : Person.getUserProfiles()) {
            if (profile.getName().equalsIgnoreCase(name)) {
                return profile;
            }


        }
        return null;
    }

}