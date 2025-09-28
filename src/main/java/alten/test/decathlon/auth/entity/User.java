package alten.test.decathlon.auth.entity;

import alten.test.decathlon.shoppingcart.entity.ShoppingCart;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String firstname;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShoppingCart shoppingCart;

    public User(){};
    public User(String username, String password, String firstname, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
    }

    public Long getId() {return id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
        public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
        public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
}
