package com.spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
