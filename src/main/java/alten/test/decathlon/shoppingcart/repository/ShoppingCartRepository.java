package alten.test.decathlon.shoppingcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import alten.test.decathlon.auth.entity.User;
import alten.test.decathlon.shoppingcart.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findById(Long id);

    Optional<ShoppingCart> findByUser (User user );
}
