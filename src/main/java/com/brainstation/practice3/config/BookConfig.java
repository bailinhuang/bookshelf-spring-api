package com.brainstation.practice3.config;

import com.brainstation.practice3.core.book.BookDAO;
import com.brainstation.practice3.core.book.service.BookService;
import com.brainstation.practice3.core.book.service.BookServiceImpl;
import com.brainstation.practice3.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

@ComponentScan("com.brainstation.practice3.core")
@Configuration
public class BookConfig {

    @Bean
    public BookDAO getBookDao(){
        return new BookDAO();
    }

    @Bean
    public BookService getBookService() {
        return new BookServiceImpl(getBookDao());
    }
}
