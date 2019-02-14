package com.brainstation.practice3.core.book.service;

import com.brainstation.practice3.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks(String customer);

    public Book getBook(String id, String customer);

    public Book addBook(Book book, String customer);

    public boolean deleteBook(String id, String customer);

    public Book editBook(String customer, Book book);
}
