package alten.test.decathlon.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import alten.test.decathlon.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
