# Book REST Service

This project is a Spring Boot-based RESTful API for managing a collection of books. It provides endpoints for performing CRUD operations on books, searching books by title or author, fetching books by ID, and sorting books by title or publication year.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete operations for managing books.
- **Search Functionality**: Search books by title or author.
- **Sort Books**: Sort books alphabetically by title or by publication year.
- **HATEOAS Support**: Utilizes HATEOAS to provide links to related resources.

## Endpoints

- `GET /books`: Retrieve all books.
- `GET /books/{id}`: Fetch a book by its ID.
- `POST /books`: Create a new book.
- `DELETE /books/{id}`: Delete a book by its ID.
- `GET /search/{name}`: Search for books by title or author.
- `GET /searchyear/{year}`: Search for books published in a specific year.
- `GET /sorted`: Retrieve all books sorted alphabetically by title.
- `GET /sortedyear`: Retrieve all books sorted by publication year.

## Technologies Used

- **Spring Boot**: Framework for creating RESTful APIs.
- **Spring Data JPA**: Simplifies data access and manipulation.
- **HATEOAS**: Hypermedia as the Engine of Application State for RESTful services.
