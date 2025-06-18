package com.exercise.book_rest_service.book;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class BookController {
	private BookRepository bookRepository;
	
	
	public BookController(BookRepository bookRepository) {
		this.bookRepository=bookRepository;
	}

	//get all books
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	//a particular book by id
	@GetMapping("/books/{id}")
	public EntityModel<Book> fetchBookById(@PathVariable Integer id){
		Optional<Book> b=bookRepository.findById(id);
		if(b.isEmpty()) {
			throw new BookNotFoundException("id:"+id);
		}
		EntityModel<Book> em = EntityModel.of(b.get());
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(this.getClass()).getAllBooks());
		em.add(link.withRel("all-books"));
		return em;
	}
	
	//create a book
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
		Book savedBook = bookRepository.save(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	//delete a book
	@DeleteMapping("/books/{id}")
	public void deleteBookById(@PathVariable Integer id) {
		bookRepository.deleteById(id);
	}
	//search all books using title or author
	@GetMapping("/search/{name}")
	public List<Book> getBooksByTitleOrAuthor(@PathVariable String name) {
	    return bookRepository.findByTitleContainingOrAuthorContainingIgnoreCase(name, name);
	}
	
	//search all books published in same year
	@GetMapping("/searchyear/{year}")
	public List<Book> getBooksByPublishedYear(@PathVariable Integer year) {
	    return bookRepository.findByyearPublished(year);
	}
	
	//sort books according to their titles
	@GetMapping("/sorted")
	public List<Book> getBooksSortedByTitle() {
	    return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
	}
	//sort books according to their year
	@GetMapping("/sortedyear")
	public List<Book> getBooksSortedByPublishedYear() {
	    return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "yearPublished"));
	}
}
