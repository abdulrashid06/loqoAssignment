#loqoAssignment

The API provides a robust solution for managing products, with features like filtering and sorting to tailor the product data to specific needs. The combination of Spring Boot and MongoDB ensures a scalable and efficient system. Explore the source code and contribute to the project to enhance its capabilities.

## Features

- **Product Management**: Add, retrieve, and manage product details.
- **Filtering and Sorting**: Filter products by category, price range, and stock availability, and sort them by various fields.
- **Exception Handling**: Global exception handling with custom error messages.

## Technologies Used

- **Spring Boot**: Framework for building the application.
- **Spring Data MongoDB**: ORM for MongoDB interactions.
- **MongoDB**: Database for storing product information.
- **Lombok**: Reduces boilerplate code for data models.
- **JUnit & Mockito**: For unit testing the application.

## MongoDB Collection

The MongoDB collection named `products` should have the following structure:

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
```

## Prerequisites

**Java 17**: Ensure you have JDK 17 installed.
**Maven**: For building and running the application.

## Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/loqoAssignment.git

   
2. **Setup the Database**

   - Ensure that MongoDB is installed and running. Create a database named products.

   

3. **API Endpoints**
      - POST /add
      - Add a new product.
      - Request Body:
        
      ```bash
         {
        "name": "pen",
        "category": "stationary",
        "price": 10.0,
        "inStock": true,
        "rating": 3.0,
        "createdAt": "2023-07-20T03:00:00.000+00:00"
    }
      ```
     
      - Response:
         ```bash
            {
        "id": "669b83d206ec12145999ca34",
        "name": "pen",
        "category": "stationary",
        "price": 10.0,
        "inStock": true,
        "rating": 3.0,
        "createdAt": "2023-07-20T03:00:00.000+00:00"
    }
         ```
      - GET /products
      - Retrieve all the products.
      - Query Parameters:
        
         - category (optional)
         - minPrice (optional)
         - maxPrice (optional)
         - inStock (optional)
         - sortField (optional, default: "createdAt")
         - sortOrder (optional, default: "asc")

        Response:
      ```bash
            [
              {
                "id": "669b83d206ec12145999ca34",
                "name": "pen",
                "category": "stationary",
                "price": 10.0,
                "inStock": true,
                "rating": 3.0,
                "createdAt": "2023-07-20T03:00:00.000+00:00"
              },
              ...
            ]
      ```
      - GET /products?category=electronics&minPrice=100&maxPrice=1000&inStock=true&sortField=price&sortOrder=asc

      - Retrieve all the products according to filter and sorting.
      - Query Parameters:
        
         - category (required)

        Response:
      ```bash
            [
              {
                "id": "669b83d206ec12145999ca34",
                "name": "pen",
                "category": "stationary",
                "price": 10.0,
                "inStock": true,
                "rating": 3.0,
                "createdAt": "2023-07-20T03:00:00.000+00:00"
              },
              ...
            ]
      ```
     
1. **Run the Tests**

   ```bash
   ./mvnw test
   ```
