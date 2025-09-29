package alten.test.decathlon.product.dto;

import java.time.LocalDateTime;

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
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public String getInternalReference() {
        return internalReference;
    }
    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }
    public long getShellId() {
        return shellId;
    }
    public void setShellId(int shellId) {
        this.shellId = shellId;
    }
    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }
    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
    
    
}
