package alten.test.decathlon.shoppingcart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alten.test.decathlon.api.ApiResponse;
import alten.test.decathlon.product.dto.ProductRequest;
import alten.test.decathlon.product.dto.ProductResponse;
import alten.test.decathlon.shoppingcart.dto.ShoppingCartRequest;
import alten.test.decathlon.shoppingcart.dto.ShoppingCartResponse;
import alten.test.decathlon.shoppingcart.service.ShoppingCartService;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
private final ShoppingCartService shoppingCartService;

public ShoppingCartController(ShoppingCartService shoppingCartService){
    this.shoppingCartService = shoppingCartService;
}

@PostMapping
public ResponseEntity<ApiResponse<ShoppingCartResponse>> create(@RequestBody ShoppingCartRequest request) {
    ShoppingCartResponse shoppingCart = shoppingCartService.createShoppingCart(request);
    URI location = URI.create("/shoppingcart/" + shoppingCart.getId());
    return ResponseEntity.created(location).body(new ApiResponse<>(201, "ShoppingCart created successfully", shoppingCart));
    
}

@PutMapping("/addItem/{id}")
public ResponseEntity<ApiResponse<ShoppingCartResponse>> addItem(@PathVariable Long id, @RequestBody ShoppingCartRequest request) {
    ShoppingCartResponse shoppingCart =  shoppingCartService.addItem(id,request);
    return ResponseEntity.ok(new ApiResponse<>(200, "Item added successfully", shoppingCart));
}

@PutMapping("/deleteItem/{id}")
public ResponseEntity<ApiResponse<ShoppingCartResponse>> deleteItem(@PathVariable Long id, @RequestBody ShoppingCartRequest request) {
ShoppingCartResponse shoppingCart =  shoppingCartService.deleteItem(id, request);
    return ResponseEntity.ok(new ApiResponse<>(200, "Item deleted successfully", shoppingCart));
}


}
