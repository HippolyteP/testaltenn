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

import org.springframework.http.HttpStatus;
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
public ResponseEntity<ShoppingCartResponse> create(@RequestBody ShoppingCartRequest request) {
    ShoppingCartResponse shoppingCart = shoppingCartService.createShoppingCart(request);
    return new ResponseEntity<>(shoppingCart,HttpStatus.CREATED);
}

@PutMapping("/addItem/{id}")
public ShoppingCartResponse addItem(@PathVariable Long id, @RequestBody ShoppingCartRequest request) {
    return shoppingCartService.addItem(id,request);
}

@PutMapping("/deleteItem/{id}")
public ShoppingCartResponse deleteItem(@PathVariable Long id) {
    return shoppingCartService.deleteItem(id);
    }
}
