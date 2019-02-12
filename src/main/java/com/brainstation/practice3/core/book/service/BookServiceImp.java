package com.brainstation.practice3.core.book.service;

import com.brainstation.practice3.core.book.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImp implements BookService {

    private BookDAO bookDAO;

    @Autowired
    public BookServiceImp(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }
}
