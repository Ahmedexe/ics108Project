# FaceLite - Social Media Mimic App

## Description

FaceLite is a JavaFX-based social media mimic app that provides users with a simulated social media experience. Users can create profiles, perform profile-specific tasks, and engage with various features through an intuitive graphical interface.

## Table of Contents

1. [Installation](#installation)
2. [Technologies used](#technologies-used)
3. [Design Overview](#design-overview)
4. [Usage](#usage)
5. [Contact Information](#contact-information)

## Installation

To run FaceLite, follow these steps:

1. **Install the Java SDK:**
   - Ensure that you have the Java SDK installed on your machine. If not, you can download and install it from the [official Java SE Downloads page](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Install the JavaFX SDK:**
   - FaceLite uses JavaFX for the graphical user interface. Download and install the JavaFX SDK from the [official JavaFX website](https://openjfx.io/).
   - Follow the installation instructions provided for your operating system.

3. **Clone the FaceLite repository:**
```
git clone https://github.com/Ahmedexe/ics108Project.git
cd ics108Project
```

4. **Compile the Java files:**


5. **Run the application:**

- Replace "/path/to/javafx-sdk-VERSION" with the actual path to your JavaFX SDK installation directory.
- Adjust the module version (e.g., "javafx-sdk-16.0.2") based on the version you installed.

6. **Alternatively, use an IDE:**
- If you're using an Integrated Development Environment (IDE) like IntelliJ or Eclipse, import the FaceLite project and run the `App` class.

## Technologies used

List of technologies/frameworks/tools used in FaceLite:

- Java
- JavaFX
- Visual Studio Code (VSCode) - IDE for development (Optional)
  - [Download VSCode](https://code.visualstudio.com/)
  - Java Extension Pack - Install the Java Extension Pack for Java development in VSCode
- IntelliJ IDEA - IDE for development (Optional)
  - [Download IntelliJ IDEA](https://www.jetbrains.com/idea/)



## Design Overview

### App Class

The `App` class is the main class for the FaceLite application, extending the JavaFX `Application`. It serves as the entry point for the GUI and contains the logic for handling user interactions.

#### Variables

- `statusLabel`: Label for displaying the status.
- `friendsList`: VBox for displaying the friends list.
- `profileNameLabel`: Label for displaying the profile name.
- `foundProfile`: Reference to the currently selected profile.
- `background`: BorderPane serving as the main layout pane.
- `leftBar`: VBox for the left side of the UI.
- `topBar`: HBox for the top side of the UI.
- `centerSection`: BorderPane for the center section holding profile details.
- `nameTextField`: TextField for entering profile names.
- `addBtn`: Button for adding a profile.
- `delBtn`: Button for deleting a profile.
- `lookUpBtn`: Button for looking up a profile.
- `changeStatBox`: VBox for changing profile status.
- `changePicBox`: VBox for changing profile picture.
- `addFriendBox`: VBox for adding a friend to the profile.
- `extraBtns`: HBox for additional buttons.
- `Home_ResetBox`: HBox for Home and Reset buttons.
- `updatesBarText`: Label for displaying updates and messages.
- `dispProf`: Button for displaying available profiles.
- `HomeBtn`: Button for returning to the home page.
- `ResetBtn`: Button for resetting all profile information.
- `nameBox`: HBox for organizing name-related components.
- `changeStatTextField`: TextField for changing profile status.
- `changeStatBtn`: Button for confirming status changes.
- `changePicTextField`: TextField for changing profile picture.
- `changePicBtn`: Button for confirming picture changes.
- `addFriendTextField`: TextField for adding friends.
- `addFriendBtn`: Button for confirming friend additions.
- `extraBtns`: HBox for additional buttons.
- `dispProf`: Button for displaying available profiles.


#### Methods

- `start(Stage primaryStage)`: The method to start the JavaFX application, configuring the UI and handling events.
- `setLeftBarContents(VBox vbox, TextField txt, Button btn)`: Helper method for constructing the left bar.
- `noImgPreview(StackPane profilePic)`: Helper method to update the image to a noImg preview.
- `setProfilePic(VBox leftCenterBox, StackPane profilePicPane, Person foundProfile)`: Method to set the profile picture.
- `clearTextFeilds(TextField changePicTextField, TextField changeStatTextField, TextField addFriendTextField)`: Method to clear text fields.
- `findProfile(String name)`: Method to find and return a profile by name. Returns null if not found.

#### Event Handlers

The class includes event handlers for various buttons, such as adding a profile, looking up a profile, changing the profile picture, adding a friend, changing the status, deleting a profile, displaying available profiles, returning to the home page, and resetting all profile information.


### 2. Person.java

The `Person` class represents individual profiles within FaceLite. It encapsulates profile information and defines methods for managing user profiles and their interactions.

#### Variables

- `name`: Represents the name of the person.
- `image`: Represents the image associated with the person.
- `status`: Represents the status or description of the person.
- `friends`: Represents the list of friends associated with the person.
- `userProfiles` (static): Represents the list of all user profiles.

#### Constructors

- `Person()`: Default constructor initializing the `friends` list.
- `Person(String name, Image image, String status)`: Constructor with parameters for creating a person with specified details.

#### Getters and Setters

- Getters (`getName()`, `getImage()`, `getStatus()`, `getFriends()`, `getUserProfiles()`): Retrieve the values of the corresponding variables.
- Setters (`setName()`, `setImage()`, `setStatus()`): Set the values of the corresponding variables.

#### Methods

- `addFriend(Person friend)`: Adds a friend to the person's friends list and ensures mutual friendship.
- `DelFriend()`: Removes this person from the friends list of all friends.
- `DelProfile(String name)`: Removes a user profile by name.
- `addProfile(String name, Image image, String status)`: Adds a new user profile if a profile with the same name does not exist.


### 3. NoProfileChosenException.java

The `NoProfileChosenException` class is an exception thrown when a profile-specific task is attempted without selecting a profile.

#### Constructors

- `NoProfileChosenException()`: Default constructor with a predefined message "No Profile Has Been Chosen to Do the Specified Task."
- `NoProfileChosenException(String s)`: Constructor that allows customizing the exception message.

### 4. EmptyTextFieldException.java

The `EmptyTextFeildException` class is an exception class that extends the base `Exception` class. It is used in the FaceLite application to handle scenarios where a text field is expected to contain information, but it is found empty.

#### Constructor

- `EmptyTextFieldException()`: Default constructor with a predefined message "Please Fill the Text Field."
- `EmptyTextFieldException(String s)`: Constructor that allows customizing the exception message.

## Usage

- Upon launching FaceLite, you will be presented with a GUI that allows you to create profiles and perform profile-specific tasks.
- Once a profile is created, you can select it and perform various tasks using the provided buttons including:
  - Adding a Profile Picture
  - Adding a Status
  - Adding Friends

**Interface Example:**
[!Interface Pic](interface.png)

- After creating a profile, changing the profile picture and changing the status the GUI will look like:

[!Modified profile](profileExample.png)


## Contact Information

For support or collaboration, please feel free to reach out:

- Email: alzuhairahmed@gmail.com
