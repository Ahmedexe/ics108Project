
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Person {

    static List<Person> userProfiles = new ArrayList<>();
    // Fields to store the person's details
    private String name;
    private Image image;
    private String status;
    private List<Person> friends;

    // Constructor
    public Person() { // constructor with an empty friends list.
        this.friends = new ArrayList<>();
    }

    // Constructor with parameters to control person details.
    public Person(String name, Image image, String status) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.friends = new ArrayList<>();
    }

    public static List<Person> getUserProfiles() { // Getter to return the list of all user profiles.
        return userProfiles;
    }
        // ** Getter and Setter
    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public List<Person> getFriends() {
        return friends;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void addFriend(Person friend) {
        /* ************** Add a friend to this person's friends list ********************
         * Adds a person to the friends list if not already a friend.
         */
        if (friend != null && !this.friends.contains(friend)) {
            this.friends.add(friend);
            if (!friend.getFriends().contains(this)) {
                friend.getFriends().add(this);
            }
        }
    }

    public void DelFriend() {
        // Removes this user from all friends' lists.
        for (Person friend : new ArrayList<>(this.friends)) {
            friend.getFriends().remove(this); // Remove this user from each friend's friend list
        }

    }



    public static void DelProfile(String name) {
        /* ********** DelProfile **********
         * Removes a profile with the given name from the list of user profiles.
         */

        for (int i = 0; i < userProfiles.size(); i++) {
            if (userProfiles.get(i).getName().equals(name)) {
                userProfiles.remove(i);
            }
        }
    }
    public static boolean addProfile(String name, Image image, String status) {
        /* **** addProfile  *****
         * Adds a new profile with the given name, image, and status.
         * Returns true if successful, false if a profile with the same name already exists.
         */

        for (Person profile : userProfiles) {
            if (profile.getName().equals(name)) {
                return false; // Profile with this name already exists
            }
        }
        Person newUser = new Person(name,  image,  status);  // add a new profile if it is not there a profile with this name
        userProfiles.add(newUser);
        return true;
    }
}



