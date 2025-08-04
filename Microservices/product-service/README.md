<hr>
E-commerce Product Service
A Spring Boot RESTful service for managing products and categories in an e-commerce application.


Features
CRUD operations for products and categories
Stock management for products
Technologies
Java
Spring Boot
Maven
Setup
Clone the repository.
Run mvn clean install to build the project.
Start the application with mvn spring-boot:run.
API Endpoints

Product Endpoints
POST /products — Create a new product
GET /products/{productId} — Get product by ID
GET /products — Get all products
PATCH /products/{productId}/stock?stockQuantity={quantity} — Update product stock
DELETE /products/{productId} — Delete a product
Category Endpoints
POST /categories — Create a new category
PUT /categories/{categoryId} — Update a category
DELETE /categories/{categoryId} — Delete a category
GET /categories — Get all categories
GET /categories/{categoryId} — Get category by ID

Usage
Use tools like Postman or curl to interact with the API.
<hr>