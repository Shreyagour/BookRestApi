package com.exercise.book_rest_service.book;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name="book_details")
public class Book {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=2, message="Minimum 2 char required")
    private String title;
	@Size(min=2, message="Minimum 2 char required")
    private String author;
//	@NotNull(message="The year cannot be null")
//	@NotBlank(message="The year cant be blank")
    private int yearPublished;
    protected Book() {}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYearPublished() {
		return yearPublished;
	}
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	public Book(Long id, String title, String author, int yearPublished) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", yearPublished=" + yearPublished + "]";
	}
	//id is not in sequence, @NotNull validation not working on yearPublished
    
}
