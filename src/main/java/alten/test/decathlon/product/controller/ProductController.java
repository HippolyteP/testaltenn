package alten.test.decathlon.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alten.test.decathlon.api.ApiResponse;
import alten.test.decathlon.auth.utils.JwtUtils;
import alten.test.decathlon.product.dto.ProductRequest;
import alten.test.decathlon.product.dto.ProductResponse;
import alten.test.decathlon.product.service.ProductService;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/products")
public class ProductController {
private final ProductService productService;

public ProductController(ProductService productService){
    this.productService = productService;
}
@PostMapping
public ResponseEntity<ApiResponse<ProductResponse>> create(@RequestBody ProductRequest request) {
    ProductResponse product = productService.create(request);
    URI location = URI.create("/products/" + product.getId());
    return ResponseEntity.created(location).body(new ApiResponse<>(201, "Product created successfully", product));
    
}

@PutMapping("/{id}")
public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id, @RequestBody ProductRequest request) {
    ProductResponse product = productService.update(id,request);
    return ResponseEntity.ok(new ApiResponse<>(200, "Product updated successfully", product));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    
    return ResponseEntity.noContent().build();
}


}
