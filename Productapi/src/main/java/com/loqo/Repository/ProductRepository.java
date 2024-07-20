package com.loqo.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.loqo.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	@Query("{'products' : ?0}")
	Optional<Product> findByProduct(Product product);

}
