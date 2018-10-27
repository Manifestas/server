package dataSets;

import accounts.UserProfile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable important for Hibernate
    private static final long serialVersionUID = 984806203776714474L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    // important for Hibernate
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {

    }

    public UsersDataSet(String name) {
        setId(-1);
        setName(name);
    }

    public UsersDataSet(long id, String name) {
        setId(id);
        setName(name);
    }

    public UsersDataSet(UserProfile userProfile) {
        setId(-1);
        setName(userProfile.getLogin());
        setPassword(userProfile.getPass());
        setEmail(userProfile.getEmail());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
