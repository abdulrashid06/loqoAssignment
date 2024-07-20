package com.loqo.Service;

import java.util.List;

import com.loqo.model.Product;

public interface ProductService {
	
	public Product addProduct(Product product);
	public List<Product> getFilterAndSortedProduct(String category, Double minPrice, Double maxPrice, Boolean inStock, String sortField, String sortOrder);

}
