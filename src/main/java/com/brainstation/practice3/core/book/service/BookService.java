package com.brainstation.practice3.core.book.service;

import com.brainstation.practice3.model.Book;

public interface BookService {

    public Book getBook(String id, String customer);

    public Book addBook(Book book, String customer);

    public boolean deleteBook(String id, String customer);

    public Book editBook(String customer, Book book);
}
