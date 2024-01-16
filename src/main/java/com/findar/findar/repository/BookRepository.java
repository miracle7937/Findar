package com.findar.findar.repository;


import com.findar.findar.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query methods can be added here if needed
}
