package alten.test.decathlon.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import alten.test.decathlon.shoppingcart.exceptions.UserNotFoundInShoppingCartException;
import alten.test.decathlon.shoppingcart.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {
private final ShoppingCartRepository shoppingCartRepository;
private final ProductRepository productRepository;
private final SecurityUtils securityUtils;
private final UserRepository userRepository;
public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, SecurityUtils securityUtils, ProductRepository productRepository, UserRepository userRepository){
    this.shoppingCartRepository = shoppingCartRepository;
    this.productRepository = productRepository;
    this.securityUtils = securityUtils;
    this.userRepository = userRepository;
}

public ShoppingCartResponse createShoppingCart(ShoppingCartRequest shoppingCartrequest){
        return addItem(null, shoppingCartrequest);
}
public ShoppingCartResponse addItem( Long id, ShoppingCartRequest shoppingCartrequest){
    ShoppingCart shoppingCart = null;
    User user = securityUtils.getCurrentUser();
    if (id == null) {
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

    return new ShoppingCartResponse(shoppingCart.getId());
    
   }

public ShoppingCartResponse deleteItem(Long id, ShoppingCartRequest shoppingCartrequest){
    ShoppingCart shoppingCart =  shoppingCartRepository.findByUser(securityUtils.getCurrentUser()).orElseThrow(() -> new RuntimeException());
    Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

    List<Product> products = shoppingCart.getProducts();
    products.remove(product);
    if(products.isEmpty()){
        shoppingCartRepository.delete(shoppingCart);
    } else {
        shoppingCartRepository.save(shoppingCart);
    }
    return new ShoppingCartResponse(shoppingCart.getId());
}


}
