package Belousov.Spring.SpringSecurity.Model;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "table_security")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "the Name must not be empty")
    @Size(min = 2, max = 100, message = "wrong")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
