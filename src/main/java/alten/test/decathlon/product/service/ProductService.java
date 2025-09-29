package alten.test.decathlon.product.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import alten.test.decathlon.product.dto.ProductRequest;
import alten.test.decathlon.product.dto.ProductResponse;
import alten.test.decathlon.product.entity.Product;
import alten.test.decathlon.product.exceptions.ProductConflictException;
import alten.test.decathlon.product.exceptions.ProductNotFoundException;
import alten.test.decathlon.product.repository.ProductRepository;

@Service
public class ProductService {
private final ProductRepository productRepository;
public ProductService(ProductRepository productRepository){
    this.productRepository = productRepository;
}

public ProductResponse create(ProductRequest productRequest){

    String code = productRequest.getCode();
    Product product = productRepository.findByCode(code).orElse(null);
    if(product != null){
        throw new ProductConflictException(code);
    }
    product = new Product(productRequest.getCode(), productRequest.getName(), productRequest.getDescription(), productRequest.getCategory(), productRequest.getPrice());
    product.setCreatedAt(LocalDateTime.now());
    product.setUpdateAt(LocalDateTime.now());
    product = productRepository.save(product);

    return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getCreatedAt(), product.getUpdateAt());
}


public ProductResponse update(Long id, ProductRequest productRequest){
    Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    product.setName(productRequest.getName());
    product.setPrice(productRequest.getPrice());
    product.setCategory(productRequest.getCategory());
    product.setCode(productRequest.getCode());
    product.setDescription(productRequest.getDescription());
    product.setImage(productRequest.getImage());
    product.setInternalReference(productRequest.getInternalReference());
    product.setInventoryStatus(productRequest.getInventoryStatus().toString());
    product.setQuantity(productRequest.getPrice());
    product.setRating(productRequest.getRating());
    product.setShellId(productRequest.getShellId());

    product = productRepository.save(product);

    return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getCreatedAt(), product.getUpdateAt());
}

public void delete (Long id) {
    if(!productRepository.existsById(id)){
        throw new ProductNotFoundException(id);
    }
    productRepository.deleteById(id);
}
}
