package alten.test.decathlon.shoppingcart.dto;

import java.util.List;

import alten.test.decathlon.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartResponse {

    private Long id;

    private List<Product> products;

    public ShoppingCartResponse(Long id) {
        this.id = id;
    }

}
