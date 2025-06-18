package com.exercise.book_rest_service.book;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(String msg) {
		super(msg);
	}

}
