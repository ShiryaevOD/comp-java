package hello;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Set;



@Entity
@Table(name = "ORDERCLIENT")
public class Order {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ORDERTEXT")
    private String ordertext;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToMany
    private Set<Product> products;

    public Order() {
    }

    public Order(String ordertext, Integer status, Set<Product> products) {
        this.ordertext = ordertext;
        this.status = status;
        this.products = products;
    }

    // Getters and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdertext() {
        return ordertext;
    }

    public void setOrdertext(String ordertext) {
        this.ordertext = ordertext;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
} // end Order