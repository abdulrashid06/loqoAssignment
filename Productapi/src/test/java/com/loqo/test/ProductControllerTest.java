package com.loqo.test;

import com.loqo.Controller.ProductController;
import com.loqo.Service.ProductServiceImpl;
import com.loqo.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void testAddProductSuccess() {
        Product product = new Product("1", "Laptop", "electronics", 799.99, true, 4.5, new Date());
        when(productService.addProduct(product)).thenReturn(product);

        ResponseEntity<?> response = productController.addProducts(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testAddProductFailure() {
        Product product = new Product("1", "Laptop", "electronics", 799.99, true, 4.5, new Date());
        when(productService.addProduct(product)).thenThrow(new RuntimeException("product already exists!"));

        ResponseEntity<?> response = productController.addProducts(product);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("product already exists!", response.getBody());
    }

    @Test
    void testGetProductsSuccess() {
        Product product1 = new Product("1", "Laptop", "electronics", 499.99, true, 4.5, new Date());
        Product product2 = new Product("2", "Smartphone", "electronics", 299.99, true, 4.0, new Date());
        when(productService.getFilterAndSortedProduct("electronics", 100.0, 500.0, true, "price", "asc"))
                .thenReturn(Arrays.asList(product2, product1));  // Laptop should come after Smartphone based on price sorting

        ResponseEntity<?> response = productController.getProducts("electronics", 100.0, 500.0, true, "price", "asc");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Product> products = (List<Product>) response.getBody();
        assertEquals(2, products.size());
        assertEquals(product2.getName(), products.get(0).getName());  // Smartphone should be first
        assertEquals(product1.getName(), products.get(1).getName());  // Laptop should be second
    }


    @Test
    void testGetProductsFailure() {
        when(productService.getFilterAndSortedProduct("electronics", 100.0, 500.0, true, "price", "asc"))
                .thenThrow(new RuntimeException("No product found"));

        ResponseEntity<?> response = productController.getProducts("electronics", 100.0, 500.0, true, "price", "asc");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No product found", response.getBody());
    }
}
