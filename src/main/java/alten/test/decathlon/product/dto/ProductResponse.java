package alten.test.decathlon.product.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    

    public ProductResponse(Long id, String name, Double price, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
    
}
