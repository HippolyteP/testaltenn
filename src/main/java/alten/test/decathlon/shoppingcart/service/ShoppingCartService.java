package alten.test.decathlon.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import alten.test.decathlon.auth.entity.User;
import alten.test.decathlon.auth.repository.UserRepository;
import alten.test.decathlon.auth.utils.SecurityUtils;
import alten.test.decathlon.product.entity.Product;
import alten.test.decathlon.product.exceptions.ProductNotFoundException;
import alten.test.decathlon.product.repository.ProductRepository;
import alten.test.decathlon.shoppingcart.dto.ShoppingCartRequest;
import alten.test.decathlon.shoppingcart.dto.ShoppingCartResponse;
import alten.test.decathlon.shoppingcart.entity.ShoppingCart;
import alten.test.decathlon.shoppingcart.exceptions.ShoppingCartConflictException;
import alten.test.decathlon.shoppingcart.exceptions.UserNotFoundInShoppingCartException;
import alten.test.decathlon.shoppingcart.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
private final ShoppingCartRepository shoppingCartRepository;
private final ProductRepository productRepository;
private final SecurityUtils securityUtils;

public ShoppingCartResponse createShoppingCart(ShoppingCartRequest shoppingCartrequest){
        return addItem(null, shoppingCartrequest);
}
public ShoppingCartResponse addItem( Long id, ShoppingCartRequest shoppingCartrequest){
    ShoppingCart shoppingCart = null;
    User user = securityUtils.getCurrentUser();
    if (id == null) {
        shoppingCart = shoppingCartRepository.findByUser(user).orElse(null);
        if (shoppingCart != null) {
            throw new ShoppingCartConflictException(user.getId());
        }
        shoppingCart = new ShoppingCart();
        shoppingCart.setProducts(new ArrayList<Product>()); 
        shoppingCart.setUser(user); 
    } else {
        shoppingCart =  shoppingCartRepository.findByUser(user).orElseThrow(() -> new UserNotFoundInShoppingCartException(user.getId()));
    }

    String code = shoppingCartrequest.getCodeProduct();
    Product product = productRepository.findByCode(code).orElseThrow(() -> new ProductNotFoundException(code));
    shoppingCart.getProducts().add(product);

    shoppingCartRepository.save(shoppingCart);

    ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse(shoppingCart.getId());
    shoppingCartResponse.setProducts(shoppingCart.getProducts());
    return shoppingCartResponse;
    
   }

public ShoppingCartResponse deleteItem(Long id){
    ShoppingCart shoppingCart =  shoppingCartRepository.findByUser(securityUtils.getCurrentUser()).orElseThrow(() -> new RuntimeException());
    Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

    List<Product> products = shoppingCart.getProducts();
    products.remove(product);
    if(products.isEmpty()){
        shoppingCartRepository.delete(shoppingCart);
        shoppingCart = null;
    } else {
        shoppingCartRepository.save(shoppingCart);
    }
    ShoppingCartResponse shopppingCartResponse = new ShoppingCartResponse(shoppingCart.getId());
    shopppingCartResponse.setProducts(shoppingCart.getProducts());
    return shopppingCartResponse;

}

}
