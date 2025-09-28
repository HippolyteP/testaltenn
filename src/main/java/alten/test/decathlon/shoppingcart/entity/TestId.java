package alten.test.decathlon.shoppingcart.entity;

import java.io.Serializable;

import alten.test.decathlon.auth.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import alten.test.decathlon.product.entity.Product;;

@Embeddable
public class TestId implements Serializable {
    @Column(name = "user_id")
    private User user;

    @Column(name = "product_id")
    private Product Product;
}
