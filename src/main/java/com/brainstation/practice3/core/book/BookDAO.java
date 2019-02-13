package com.brainstation.practice3.core.book;

import com.brainstation.practice3.model.Book;

import java.util.*;

public class BookDAO {
    private HashMap<String, List<Book>> bookList;

    public BookDAO(){
        this.bookList = new HashMap<>();
    }

    public BookDAO(HashMap<String, List<Book>> bookList){
        this.bookList = bookList;
    }

    public HashMap<String, List<Book>> getBookList() {
        return bookList;
    }

    public Book getBook(String id, String customer){
        return bookList.get(customer).stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    public Book addBook(Book book, String customer){
        book.setId(UUID.randomUUID().toString());
        bookList.get(customer).add(book);
        return book;
    }

    public boolean deleteBook(String id, String customer){
        Optional<Book> bookToDelete = bookList.get(customer).stream().filter(book -> book.getId().equals(id)).findFirst();
        if(bookToDelete.isPresent()){
            return bookList.get(customer).remove(bookToDelete.get());
        } else {
            return false;
        }
    }

    public Book editBook(String customer, Book book){
        int index = bookList.get(customer).indexOf(book);
        if(index == -1){
            bookList.get(customer).get(index).setAuthor(book.getAuthor());
            bookList.get(customer).get(index).setName(book.getName());
            return bookList.get(customer).get(index);
        } else {
            return null;
        }
    }
}
