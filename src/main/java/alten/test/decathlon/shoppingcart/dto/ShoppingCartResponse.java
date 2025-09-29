package alten.test.decathlon.shoppingcart.dto;

import java.util.List;

import alten.test.decathlon.product.entity.Product;

public class ShoppingCartResponse {

    private Long id;

    private List<Product> products;

    

    public ShoppingCartResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    

}
