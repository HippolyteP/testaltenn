package alten.test.decathlon.product.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Double quantity;
    private String internalReference;
    private long shellId;
    private InventoryStatus inventoryStatus;
    private Double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public ProductRequest(){};
    public ProductRequest(String code, String name, String description, String category, Double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }
    
}
