package com.findar.findar;

import com.findar.findar.models.Book;
import com.findar.findar.repository.BookRepository;
import com.findar.findar.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        bookRepository.deleteAll();
        // Mocking repository to return a list of books
        when(bookRepository.findAll()).thenReturn(Arrays.asList(
                new Book( "Book 1", "Author 1", false),
                new Book( "Book 2", "Author 2", true)
        ));

        List<Book> books = bookService.getAllBooks();

        // Verify that the service returns the expected list of books
        assertEquals(2, books.size());
        assertEquals("Book 1", books.get(0).getTitle());
        assertEquals("Author 2", books.get(1).getAuthor());

        // Verify that the repository's findAll method was called once
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        // Mocking repository to return a book with ID 1
        when(bookRepository.findById(1L)).thenReturn(Optional.of(new Book( "Book 1", "Author 1", false)));

        Optional<Book> bookOptional = bookService.getBookById(1L);

        assertEquals("Book 1", bookOptional.get().getTitle());
        assertEquals("Author 1", bookOptional.get().getAuthor());

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveBook() {
        // Creating a new book
        Book newBook = new Book( "New Book", "New Author", true);

        // Mocking repository to return the saved book with an ID
        when(bookRepository.save(newBook)).thenReturn(new Book( "New Book", "New Author", true));

        Book savedBook = bookRepository.save(newBook);

        assertEquals("New Book", savedBook.getTitle());
        assertEquals("New Author", savedBook.getAuthor());


    }

    @Test
    public void testDeleteBook() {
        // Mocking repository to delete a book with ID 1
        doNothing().when(bookRepository).deleteById(1L);

        // Calling the service method to delete the book
        bookService.deleteBook(1L);

        // Verify that the repository's deleteById method was called once with the correct ID
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
