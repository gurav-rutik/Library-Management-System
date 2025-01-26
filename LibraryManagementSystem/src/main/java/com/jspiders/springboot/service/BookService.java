package com.jspiders.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springboot.entity.Book;
import com.jspiders.springboot.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Integer id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setYear(updatedBook.getYear());
            book.setGenre(updatedBook.getGenre());
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
