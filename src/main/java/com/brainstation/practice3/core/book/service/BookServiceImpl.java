package com.brainstation.practice3.core.book.service;

import com.brainstation.practice3.core.book.BookDAO;
import com.brainstation.practice3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book getBook(String id, String customer) {
        return bookDAO.getBook(id,  customer);
    }

    public Book addBook(Book book, String customer) {
        List<Book> bookList = bookDAO.getBookList().get(customer);
        if (bookList != null && !bookList.contains(book)) {
            return bookDAO.addBook(book, customer);
        } else {
            return null;
        }
    }

    public boolean deleteBook(String id, String customer) {
        Optional<Book> bookToDelete = bookDAO.getBookList().get(customer).stream().filter(book -> book.getId().equals(id)).findFirst();
        if (bookToDelete.isPresent()) {
            return bookDAO.deleteBook(id, customer);
        } else {
            return false;
        }
    }

    public Book editBook(String customer, Book book) {
        if (bookDAO.getBookList().get(customer).contains(book)){
            bookDAO.editBook(customer, book);
            return book;
        } else {
            return null;
        }
    }
}
