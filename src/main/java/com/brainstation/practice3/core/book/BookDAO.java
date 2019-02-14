package com.brainstation.practice3.core.book;

import com.brainstation.practice3.model.Book;

import java.util.*;

public class BookDAO {
    private HashMap<String, List<Book>> bookRepository;

    public BookDAO(){
        this.bookRepository = new HashMap<>();
    }

    public BookDAO(HashMap<String, List<Book>> bookList){
        this.bookRepository = bookList;
    }

    public HashMap<String, List<Book>> getBookRepository() {
        return bookRepository;
    }

    public Book getBook(String id, String customer){
        return bookRepository.get(customer).stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    public Book addBook(Book book, String customer){
        book.setId(UUID.randomUUID().toString());
        if(!bookRepository.containsKey(customer)){
            bookRepository.put(customer, new ArrayList<>());
        }
        bookRepository.get(customer).add(book);
        return book;
    }

    public boolean deleteBook(String id, String customer){
        Optional<Book> bookToDelete = bookRepository.get(customer).stream().filter(book -> book.getId().equals(id)).findFirst();
        if(bookToDelete.isPresent()){
            return bookRepository.get(customer).remove(bookToDelete.get());
        } else {
            return false;
        }
    }

    public Book editBook(String customer, Book book){
        int index = 0;
        boolean bookFound = false;
        for(Book bookInLoop : bookRepository.get(customer)){
            if(bookInLoop.getId().equals(book.getId())){
                bookFound = true;
                break;
            }
            index++;
        }
        bookRepository.get(customer).indexOf(book);
        if(bookFound){
            bookRepository.get(customer).get(index).setAuthor(book.getAuthor());
            bookRepository.get(customer).get(index).setName(book.getName());
            return bookRepository.get(customer).get(index);
        } else {
            return null;
        }
    }

    public void addCustomer(String customer){
        bookRepository.put(customer, new ArrayList<>());
    }
}
