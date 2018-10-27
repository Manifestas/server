package dataSets;

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

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
