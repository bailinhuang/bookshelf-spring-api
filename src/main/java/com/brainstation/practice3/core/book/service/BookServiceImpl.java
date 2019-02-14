package com.brainstation.practice3.core.book.service;

import com.brainstation.practice3.core.book.BookDAO;
import com.brainstation.practice3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAllBooks(String customer){
        return bookDAO.getBookRepository().get(customer);
    }

    public Book getBook(String id, String customer) {
        return bookDAO.getBook(id,  customer);
    }

    public Book addBook(Book book, String customer) {
        List<Book> bookList = bookDAO.getBookRepository().get(customer);
        if (bookList == null || !bookList.contains(book)) {
            if(bookList == null){
                bookDAO.addCustomer(customer);
            }
            return bookDAO.addBook(book, customer);
        } else {
            return null;
        }
    }

    public boolean deleteBook(String id, String customer) {
        Optional<Book> bookToDelete = bookDAO.getBookRepository().get(customer).stream().filter(book -> book.getId().equals(id)).findFirst();
        if (bookToDelete.isPresent()) {
            return bookDAO.deleteBook(id, customer);
        } else {
            return false;
        }
    }

    public Book editBook(String customer, Book book) {
        int index = 0;
        if (bookDAO.getBook(book.getId(), customer) != null){
            bookDAO.editBook(customer, book);
            return book;
        } else {
            return null;
        }
    }
}
