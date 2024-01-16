package com.findar.findar.controller;

import com.findar.findar.models.Book;
import com.findar.findar.models.CustomResponse;
import com.findar.findar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
            return ResponseEntity.ok(book);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity addBook(@RequestBody Book book) {
        try {
            Book addedBook = bookService.addBook(book);
            if(addedBook != null){
             return    ResponseEntity.status(HttpStatus.CREATED).body( new CustomResponse(true, "Book added successfully"));

            }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new CustomResponse(false, "Book fail to add"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new CustomResponse(false, "Book fail to add"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return ResponseEntity.ok(book);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new CustomResponse(false, "Book not found"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(   new CustomResponse(true, "Book deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(   new CustomResponse(true, "Book not found with id: " + id) );
        }
    }
}