# EasyShop

## Description of the Project

This project is an API of a web store online shopping application. It handles the functionalities of product search according to their categories, prices, and colors. It also handles the authentication and authorization of the users for CRUD. It has the needed connecgtion to the database and the frontend of the application.
![database diagram.PNG](imgs%2Fdatabase%20diagram.PNG)

## User Stories


- As a user, I want to view the products of a specific category by ID so that I can see what it offers.
- As a user, I want to see all products in a specific category so that I can browse the items available.
- As an admin, I want to update a category's details so that I can correct or enhance its information.
- As a user, I want to view a list of all product categories so that I can browse the available options.
- As a user, I want to log in to the system using my username and password so that I can access my account securely.
- As an admin, I want to delete an unwanted category so that it no longer appears in the system.
- As a user, I want to search for products by category, price range, or color so that I can find what I’m looking for.
- As a user, I want to view detailed information about a product so that I can make an informed decision.
- As a new user, I want to register an account with a username and password so that I can start using the application.
- As an admin, I want to create a new product category so that I can organize products better.
- As an admin, I want to add new products to the inventory so that they can be available for purchase.
- As an admin, I want to update product details so that the information remains accurate.
- As an admin, I want to delete a product from the inventory so that it is no longer available for purchase.
- As a developer, I want the APIs to require proper authentication and authorization so that the system is secure.
- As a user, I want meaningful error messages so that I can understand and correct any issues.


## Setup

Instructions on how to set up and run the project using IntelliJ IDEA.

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select 'Run 'YourMainClassName.main()'' to start the application.

## Technologies Used

- Java: 17
- Libraries: 
org.apache.commons.dbcp2.BasicDataSource
   org.springframework.beans.factory.annotation.Autowired
   org.springframework.beans.factory.annotation.Value
   org.springframework.context.annotation.Bean
   org.springframework.context.annotation.Configuration
   org.springframework.http.HttpHeaders
   org.springframework.http.HttpStatus
   org.springframework.http.ResponseEntity
   org.springframework.security.access.prepost.PreAuthorize
   org.springframework.security.authentication.UsernamePasswordAuthenticationToken
   org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
   org.springframework.security.core.Authentication
   org.springframework.security.core.context.SecurityContextHolder
   org.springframework.web.bind.annotation.*
   org.springframework.web.server.ResponseStatusException
   org.springframework.beans.factory.annotation.Autowired
   org.springframework.http.HttpStatus
   org.springframework.security.access.prepost.PreAuthorize
   org.springframework.web.bind.annotation.*
   org.springframework.web.server.ResponseStatusException
   org.springframework.stereotype.Component
javax.sql.DataSource
 java.sql.*
 java.util.ArrayList
 java.util.List
## Demo

![Category filtering.PNG](imgs%2FCategory%20filtering.PNG)
![Color filtering.PNG](imgs%2FColor%20filtering.PNG)
![Postman collection 1.PNG](imgs%2FPostman%20collection%201.PNG)
![Postman collection 2.PNG](imgs%2FPostman%20collection%202.PNG)
![Price filtering.PNG](imgs%2FPrice%20filtering.PNG)
![Syntax error free api.PNG](imgs%2FSyntax%20error%20free%20api.PNG)


## Future Work


- Implement shopping cart to hold the future orders users might have
- Implement the functionality of the login page for users to log in 
- Implement user profile; User register, user login etc.
- Checkout functionality to finalize the shopping cart

## Resources

- The skeleton code provided by Raymond Maroun
## Team Members

- **Emre Akarsu** - Only member who completed every aspect of the project

## Thanks


- Thank you to [Raymond Maroun] for continuous support and guidance.
