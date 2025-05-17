# Book Management System

This is a Spring Boot application for managing books in a library.  
Users can add, view, update, and delete books with validation to ensure ISBN uniqueness.

---

## Features

- Add new books with Title, Author, ISBN, and Price.
- View a list of all books.
- Edit existing book details.
- Delete books.
- ISBN uniqueness validation to avoid duplicates.
- Simple and clean UI built with Thymeleaf templates.

---

## Technologies Used

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- Thymeleaf  
- Maven  
- H2 Database (in-memory for dev) / MySQL (prod)

---

## Configuration

Edit `src/main/resources/application.properties` with your database settings:

```properties
spring.application.name=SpringLearn

# MySQL configuration
spring.datasource.url=jdbc:mysql://localhost:3306/assign
spring.datasource.username=root
spring.datasource.password=root

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


git clone https://github.com/Vandanarajput/Assignmnet.git

cd Assignmnet

mvn spring-boot:run

http://localhost:8080/books

