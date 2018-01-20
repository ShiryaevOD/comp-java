package hello;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "CLIENT")
public class Client {


        @Id
        @Column(name = "ID")
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "USERNAME")
        private String username;

        @Column(name = "PASSWORD")
        private String password;

        @Column(name = "FIRSTNAME")
        private String firstname;

        @Column(name = "LASTNAME")
        private String lastname;

        @Column(name = "STATUS")
        private Integer status;


        @ManyToMany
        private Set<Order> orders;

    public Client() {

    }

    public Client(String username, String password, String firstname, String lastname, Integer status ) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.status = status;

    }

    // Getters and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
} // end Client

