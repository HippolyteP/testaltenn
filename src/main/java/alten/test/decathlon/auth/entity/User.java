package alten.test.decathlon.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String firstname;
    @Column(unique = true)
    private String email;
    private String password;

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
    public String getemail() {return email;}
    public void setemail(String email) {this.email = email;}
        public String getfirstname() {return firstname;}
    public void setfirstname(String firstname) {this.firstname = firstname;}
        public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
