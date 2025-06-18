package com.exercise.book_rest_service.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

	List<Book> findByTitleContainingOrAuthorContainingIgnoreCase(String searchTerm, String searchTerm2);

	List<Book> findByyearPublished(Integer year);

}
