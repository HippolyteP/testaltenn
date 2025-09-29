package alten.test.decathlon.product.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import alten.test.decathlon.shoppingcart.entity.ShoppingCart;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Double quantity;
    private String internalReference;
    private long shellId;
    private String inventoryStatus;
    private Double rating;
    @CreatedDate
    @Column(nullable= false, updatable = false)
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;

    @ManyToMany(mappedBy = "products")
    private List<ShoppingCart> shoppingcarts = new ArrayList<>();


    public Product(){}
    public Product(String code, String name, String description, String category, Double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

}
