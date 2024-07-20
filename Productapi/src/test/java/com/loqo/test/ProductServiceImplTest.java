package com.loqo.test;

import com.loqo.Exception.ProductException;
import com.loqo.Exception.ProductNotFoundException;
import com.loqo.Repository.ProductRepository;
import com.loqo.Service.ProductServiceImpl;
import com.loqo.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testAddProductThrowsExceptionIfExists() {
        Product product = new Product("1", "Laptop", "electronics", 799.99, true, 4.5, new Date());
        when(productRepository.findByProduct(product)).thenReturn(Optional.of(product));

        ProductException exception = assertThrows(ProductException.class, () -> productService.addProduct(product));
        assertEquals("product already exists!", exception.getMessage());
    }

    @Test
    void testAddProductSuccess() {
        Product product = new Product("1", "Laptop", "electronics", 799.99, true, 4.5, new Date());
        when(productRepository.findByProduct(product)).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.addProduct(product);
        assertEquals(product, savedProduct);
    }

    @Test
    void testGetAllProductsThrowsExceptionIfEmpty() {
        when(productRepository.findAll()).thenReturn(List.of());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.getAllProduct());
        assertEquals("No product found", exception.getMessage());
    }

    @Test
    void testGetAllProductsSuccess() {
        Product product1 = new Product("1", "Laptop", "electronics", 799.99, true, 4.5, new Date());
        Product product2 = new Product("2", "Smartphone", "electronics", 499.99, true, 4.0, new Date());
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProduct();
        assertEquals(2, products.size());
    }

    @Test
    void testGetFilterAndSortedProduct() {
        Product product1 = new Product("1", "Laptop", "electronics", 499.99, true, 4.5, new Date());
        Product product2 = new Product("2", "Smartphone", "electronics", 299.99, true, 4.0, new Date());
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getFilterAndSortedProduct("electronics", 100.0, 500.0, true, "price", "asc");

        assertEquals(2, products.size());
        assertEquals(product2.getName(), products.get(0).getName());
        assertEquals(product1.getName(), products.get(1).getName());
    }
}