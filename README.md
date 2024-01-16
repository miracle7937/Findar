Certainly! If a Bearer token is required for the Bookstore API operations, you should include the token in the Authorization header for each request. Here's an updated version of the documentation reflecting the inclusion of the Bearer token:

# Bookstore API Documentation

## Introduction

Welcome to the Bookstore API! This API provides simple endpoints to manage books. Here's a quick guide on how to interact with these endpoints using a Bearer token for authentication.

## Base URL

Base URL: `http://localhost:8080/api/books`

## Authentication

To perform book operations, you must obtain a Bearer token by authenticating a user through the Authentication API.

### Authenticate User and Generate Token

- **URL:** `/authenticate`
- **Method:** `POST`
- **Description:** Authenticate a user and generate a Bearer token.

## Endpoints

All endpoints require the inclusion of a Bearer token in the Authorization header.

### 1. **Get All Books**

- **URL:** `/api/books`
- **Method:** `GET`
- **Description:** Retrieve a list of all available books.

### 2. **Get Book by ID**

- **URL:** `/api/books/{id}`
- **Method:** `GET`
- **Description:** Retrieve a specific book by its ID.

### 3. **Add New Book**

- **URL:** `/api/books`
- **Method:** `POST`
- **Description:** Add a new book to the store.

### 4. **Update Book**

- **URL:** `/api/books/{id}`
- **Method:** `PUT`
- **Description:** Update details of an existing book.

### 5. **Delete Book**

- **URL:** `/api/books/{id}`
- **Method:** `DELETE`
- **Description:** Delete a book from the store.

##Example Usage 

Here's an example of how to use the Bookstore API with a Bearer token:

1. **Authenticate and obtain a Bearer token:**

   ```bash
   curl -X POST -H "Content-Type: application/json" -d '{"userName":"miracle.ebuka@gmail.com","password":"Miracle3"}' http://localhost:8080/authenticate
   ```

   The response will include a Bearer token.

2. **Use the Bearer token for Bookstore API operations:**

   ```bash
   curl -H "Authorization: Bearer YOUR_TOKEN" http://localhost:8080/api/books
   ```

   Replace `YOUR_TOKEN` with the actual Bearer token obtained in step 1.

Feel free to adapt this documentation to your specific needs and add more details as necessary.


## Testing Bookstore API
Introduction
Testing is a crucial aspect of software development, ensuring that your application functions as expected and meets the specified requirements. This write-up covers the testing approach for the Bookstore API, specifically focusing on unit testing using the Spring Boot framework.