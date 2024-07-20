package com.loqo.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Products")
public class Product {
	
	private String id;
    private String name;
    private String category;
    private double price;
    private boolean inStock;
    private double rating;
    private Date createdAt;

}
