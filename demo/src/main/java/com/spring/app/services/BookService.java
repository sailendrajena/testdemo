package com.spring.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.exception.ResourceNotFoundException;
import com.spring.app.model.Book;
import com.spring.app.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public List<Book> getAllBooks(){
		List<Book> bookList = new ArrayList<Book>();  
		bookRepo.findAll().forEach(book -> bookList.add(book));
		bookList.forEach(bk->System.out.println(bk.getId()+"---"+bk.getBookName()));
		return bookList;
	}
	
	public Book getBookById(Long id) throws ResourceNotFoundException {
		Book book = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found with this id: "+ id));
		
		return book;
	}
	
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	public Book updateBook(Long id, Book book) throws ResourceNotFoundException{
		Book bookDetails = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found on this id: "+id));
		bookDetails.setBookName(book.getBookName());
		bookDetails.setPrice(book.getPrice());
		bookDetails = bookRepo.save(bookDetails);
		return bookDetails;
	}
	
	public Map<String, Boolean> deleteBook(Long id) throws ResourceNotFoundException{
		Book book = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found on this id: "+id));
		
		bookRepo.delete(book);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Book got deleted Successfully", Boolean.TRUE);
		
		return response;
	}
}
