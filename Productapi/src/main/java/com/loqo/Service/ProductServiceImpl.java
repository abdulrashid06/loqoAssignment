package com.loqo.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.loqo.Exception.ProductException;
import com.loqo.Exception.ProductNotFoundException;
import com.loqo.Repository.ProductRepository;
import com.loqo.model.Product;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	/**
	 * Adds a new product to the repository.
	 * Throws ProductException if the product already exists.
	 *
	 * @param product the product to add
	 * @return the added product
	 */
	@Override
	public Product addProduct(Product product) {
		// Check if the product already exists
				Optional<Product> pr = productRepository.findByProduct(product);
				if(pr.isPresent()) {
					throw new ProductException("product already exists!");
				}
				
				// Save the new product
				return productRepository.save(product);
	}

	
	
	
	/**
	 * Retrieves products from the repository based on given filters and sorting parameters.
	 *
	 * @param category the category to filter by
	 * @param minPrice the minimum price to filter by
	 * @param maxPrice the maximum price to filter by
	 * @param inStock  the stock status to filter by
	 * @param sortField the field to sort by
	 * @param sortOrder the order to sort by (asc or desc)
	 * @return a list of filtered and sorted products
	 */
	@Override
	public List<Product> getFilterAndSortedProduct(String category, Double minPrice, Double maxPrice, Boolean inStock, String sortField, String sortOrder) {
		List<Product> products = productRepository.findAll();
		
        return products.stream()
            .filter(product -> category == null || product.getCategory().equalsIgnoreCase(category))
            .filter(product -> minPrice == null || product.getPrice() >= minPrice)
            .filter(product -> maxPrice == null || product.getPrice() <= maxPrice)
            .filter(product -> inStock == null || product.isInStock() == inStock)
            .sorted((p1, p2) -> {
                int result = 0;
                if (sortField != null) {
                    switch (sortField) {
                        case "price":
                            result = Double.compare(p1.getPrice(), p2.getPrice());
                            break;
                        case "rating":
                            result = Double.compare(p1.getRating(), p2.getRating());
                            break;
                        case "createdAt":
                            result = p1.getCreatedAt().compareTo(p2.getCreatedAt());
                            break;
                    }
                }
                return sortOrder != null && sortOrder.equalsIgnoreCase("desc") ? -result : result;
            })
            .collect(Collectors.toList());
    }
	


}
