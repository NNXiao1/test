import java.util.Arrays;
import java.util.Objects;
import java.math.*;

/** Represents an example of how a website might model a user. */
public class User implements Comparable<User>{

    private static int nextId = 1;

    private int id;
    private int age;
    private String name;
    private String email;


    public User(String name, String email) {
        this(nextId, name, email);
        nextId += 1;
    }

    /** Force assign an id to a created user **/
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        setAge();
    }

    public int compareTo(User otherUser) {
        if (this.id != otherUser.id) {
            return Integer.compare(this.id, otherUser.id);
        } else {
            return this.name.compareTo(otherUser.name);
        }
    }

    /** For this assignment, age is just an automatically assigned field. */
    void setAge() {
        age = (id % 13) + 20;
    }

    int getAge() {
        return age;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + "}";
    }

    /** Returns whether or not two Users are considered equal to each other. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User other = (User) o;
        if (id != other.id) {
            return false;
        } else if (!Objects.equals(name, other.name)) {
            return false;
        } else {
            return Objects.equals(email, other.email);
        }
    }

    public static void main(String[] args) {
        User[] users = {
            new User(2, "Jasmine", ""),
            new User(4, "Vanessa", ""),
            new User(5, "David", ""),
            new User(1, "Jedi", ""),
            new User(1, "Laksith", "")
        };
        Arrays.sort(users);
        for (User user : users) {
            System.out.println(user);
        }
    }
}

