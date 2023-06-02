package com.spring.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.exception.ResourceNotFoundException;
import com.spring.app.model.Book;
import com.spring.app.services.BookService;

@RestController
@RequestMapping("/api/v1/")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping("books")
	public List<Book> getAllBooks(){
		return service.getAllBooks();
	}
	
	@GetMapping("books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) throws ResourceNotFoundException{
		Book book = service.getBookById(id);
		
		return ResponseEntity.ok().body(book);
	}
	
	@PostMapping("books")
	public Book addBook(@RequestBody Book book) {
		return service.addBook(book);
	}
	
	@PutMapping("books/{id}")
	public ResponseEntity<Book> updateBook(Long id, Book book) throws ResourceNotFoundException{
		Book bookDetails = service.updateBook(id, book);
		return ResponseEntity.ok(bookDetails);
	}
	
	@DeleteMapping("books/{id}")
	public Map<String, Boolean> deleteBook(@PathVariable("id") Long id) throws ResourceNotFoundException{
		Map<String, Boolean> response = service.deleteBook(id);
		
		return response;
	}
}
