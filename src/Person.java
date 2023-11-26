
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Person {

    private static List<Person> userProfiles = new ArrayList<>();
    public static List<Person> getUserProfiles() {
        return userProfiles;
    }
    private String name;
    private Image image;
    private String status;
    private List<Person> friends;

    // Constructor
    public Person() {
        this.friends = new ArrayList<>();
    }

    // Constructor with parameters
    public Person(String name, Image image, String status) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.friends = new ArrayList<>();
    }


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



    public void addFriend(Person friend) { // ************** Add a friend to this person's friends list ********************
        if (friend != null && !this.friends.contains(friend)) {
            this.friends.add(friend);
            if (!friend.getFriends().contains(this)) {
                friend.getFriends().add(this);
            }
        }
    }
    public static boolean addProfile(String name, Image image, String status) {
        for (Person profile : userProfiles) {
            if (profile.getName().equals(name)) {
                return false; // Profile with this name already exists
            }
        }
        Person newUser = new Person(name,  image,  status);
        userProfiles.add(newUser);
        return true;
    }



}



