package alten.test.decathlon.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import alten.test.decathlon.product.dto.ProductRequest;
import alten.test.decathlon.product.dto.ProductResponse;
import alten.test.decathlon.product.entity.Product;
import alten.test.decathlon.product.repository.ProductRepository;

public class ProductServiceTest {
    @Mock
    private ProductRepository productrepo;
    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        product = new Product("code", "name", "description", "category", 12.3);
        product.setId(1L);
    }
    @Test
    void testCreate_ShouldReturnProductResponse() {
        ProductRequest request = new ProductRequest("code", "name", "description", "category", 12.3);
        when(productrepo.save(any(Product.class))).thenReturn(product);
         ProductResponse response = productService.create(request);

        assertEquals(product.getId(), response.getId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getPrice(), response.getPrice());
    }

    @Test
    void testUpdate_ShouldThrowException_whenProductNotFound() {
        ProductRequest request = new ProductRequest("code", "name", "description", "category", 12.3);
        when(productrepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()-> productService.update(99L, request));
    }

    @Test
    void testDelete_ShouldCallRepositoryDelete() {
        when(productrepo.existsById(1L)).thenReturn(true);
        productService.delete(1L);
        verify(productrepo).deleteById(1L);

    }  
}
