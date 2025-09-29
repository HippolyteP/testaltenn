package alten.test.decathlon.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alten.test.decathlon.api.ApiResponse;
import alten.test.decathlon.auth.utils.JwtUtils;
import alten.test.decathlon.product.dto.ProductRequest;
import alten.test.decathlon.product.dto.ProductResponse;
import alten.test.decathlon.product.entity.Product;
import alten.test.decathlon.product.service.ProductService;
import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.http.HttpStatus;
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
public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    ProductResponse created = productService.create(request);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
    return(productService.update(id,request));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
}


}
