# Electronic Store

A Java-based electronic store application where users can search for items, purchase them, and where the store administrators can manage products (POST, UPDATE, and DELETE items). This project is designed with a focus on Object-Oriented Programming (OOP), a 3-tier architecture, and database integration using MySQL.

## Project Overview

The primary goal of this project was to practice:

- Object-Oriented Programming (OOP) principles to enhance usability and maintain organized code.
- Connecting the layers of the program using a 3-tier architecture (Controller, Service, and Data Access layers).
- Efficient database management by integrating the application with a MySQL database.

## Features

### Implemented:
- Search Functionality: Users can search for electronic items they wish to buy.
- Purchase Items: Users can buy items directly from the store.
- Product Management: The store can:
POST: Add new items to the inventory.
UPDATE: Modify details of existing items.
DELETE: Remove items from the inventory.

## Design Patterns:
### 3-Tier Architecture: 
Separates the application into three layers:
- Controller Layer: Handles HTTP requests and responses.
- Service Layer: Contains business logic.
- Data Access Layer: Manages interaction with the MySQL database.
### Singleton Design Pattern: 
Ensures that certain components (like services) have a single instance across the application for better performance and resource management.

## Additional Technologies:
- Spring Boot: Used to build the back-end application and manage RESTful APIs.
- MySQL: Stores and retrieves item data efficiently.
- Gradle: Used as the build tool for dependency management and project configuration.

## Tech Stack
- Programming Language: Java
- Framework: Spring Boot
- Database: MySQL
- Build Tool: Gradle

## Installation

Clone the repository:
```
git clone https://github.com/your-username/electronic-store.git  
cd electronic-store
```

Set up the database (MySQL):
```
spring.datasource.url=jdbc:mysql://localhost:3306/electronic_store  
spring.datasource.username=your_username  
spring.datasource.password=your_password  
spring.jpa.hibernate.ddl-auto=update
```

Build the project using Gradle:
```
./gradlew build
```

Run the application:
- used postman to check if API is correctly working.

## Future Improvements
- Add user authentication for a personalized experience.
- Implement a shopping cart feature.
- Add support for advanced search and filtering options.
- Enhance error handling and validation for a smoother user experience.
- Improve the front-end to provide a graphical user interface (GUI).

![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![mysql](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
