package com.loqo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loqo.Service.ProductServiceImpl;
import com.loqo.model.Product;

@RestController
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addProducts(@RequestBody Product product){
		try {
			Product prod = productService.addProduct(product);
			return new ResponseEntity<>(prod, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/products")
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Boolean inStock,
            @RequestParam(defaultValue = "price") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        try {
			List<Product> prod = productService.getFilterAndSortedProduct(category, minPrice, maxPrice, inStock, sortField, sortOrder);
			return new ResponseEntity<>(prod, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }

}
