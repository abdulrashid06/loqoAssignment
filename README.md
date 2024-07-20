# Product Filter and Sort API with Spring Boot and MongoDB

## Objective
Create a Spring Boot application that interacts with a MongoDB database to provide an API that filters and sorts records based on specified parameters.

## Problem Statement
Develop a RESTful API that fetches product details from a MongoDB collection. The API should support filtering based on specific fields and sorting based on three designated fields.

## Specifications
1. **Setup**
   - Use Spring Boot to create the application.
   - Use MongoDB as the database.

2. **MongoDB Collection**
   Assume you have a MongoDB collection named `products` with the following structure:
   ```json
   {
     "_id": "ObjectId",
     "name": "string",
     "category": "string",
     "price": "double",
     "inStock": "boolean",
     "rating": "double",
     "createdAt": "Date"
   }
