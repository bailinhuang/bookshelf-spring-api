package com.brainstation.practice3.core.book.controller;

import com.brainstation.practice3.core.book.service.BookServiceImpl;
import com.brainstation.practice3.model.Book;
import com.brainstation.practice3.model.CustomResponseObject;
import com.brainstation.practice3.utils.ResponseMessages;
import jdk.management.resource.internal.ResourceNatives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<CustomResponseObject> getBook(@RequestHeader("customer") String customer) {
        List<Book> bookList = bookService.getAllBooks(customer);
        if (!customer.equals(null)) {
            return new ResponseEntity<>(new CustomResponseObject(bookList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomResponseObject(bookList, ResponseMessages.CUSTOMER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<CustomResponseObject> postBook(
            @RequestHeader("customer") String customer,
            @RequestBody Book book) {
        if (!customer.equals(null)) {
            Book bookResult = bookService.addBook(book, customer);
            return new ResponseEntity<>(new CustomResponseObject(bookResult), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomResponseObject(null, ResponseMessages.CUSTOMER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponseObject> deleteBook(
            @RequestHeader("customer") String customer,
            @PathVariable String id) {
        if (!customer.equals(null)) {
            boolean isDeleted = bookService.deleteBook(id, customer);
            if (isDeleted) {
                return new ResponseEntity<>(new CustomResponseObject(null, ResponseMessages.BOOK_DELETED), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CustomResponseObject(null, ResponseMessages.BOOK_NOT_DELETED), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new CustomResponseObject(null, ResponseMessages.CUSTOMER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
    public ResponseEntity<CustomResponseObject> editBook(
            @RequestHeader("customer") String customer,
            @RequestBody Book book,
            @PathVariable String id) {
        if (!customer.equals(null)) {
            book.setId(id);
            Book bookResult = bookService.editBook(customer, book);
            if (bookResult != null) {
                return new ResponseEntity<>(new CustomResponseObject(null, ResponseMessages.BOOK_EDITED), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CustomResponseObject(null, ResponseMessages.BOOK_NOT_EDITED), HttpStatus.BAD_REQUEST);
            }
        } else{
             return new ResponseEntity<>(new CustomResponseObject(null, ResponseMessages.CUSTOMER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
    }
}
