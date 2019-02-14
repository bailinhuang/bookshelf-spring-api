package com.brainstation.practice3.core.book;

import com.brainstation.practice3.core.book.service.BookService;
import com.brainstation.practice3.core.book.service.BookServiceImpl;
import com.brainstation.practice3.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Test
    public void test_getBook(){
        Book book = new Book("Harry Potter", "J.K. Rowling");
        Book book_NULL = new Book("", "");
        String CUSTOMER = "bailin";
        HashMap<String, List<Book>> bookList = new HashMap<>();
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(book);
        bookList.put(CUSTOMER, bookArrayList);
        BookServiceImpl bookService = new BookServiceImpl(new BookDAO(bookList));
        Book bookResult = bookService.getBook(book.getId(), CUSTOMER);
        Assert.notNull(bookResult, "Not null test getBook");
        bookResult = bookService.getBook(book_NULL.getId(), CUSTOMER);
        Assert.isNull(bookResult, "");
    }

    @Test
    public void test_addBook() {
        HashMap<String, List<Book>> bookList = new HashMap<>();
        String CUSTOMER = "bailin";
        String NULL_CUSTOMER = "";
        bookList.put(CUSTOMER, new ArrayList<>());
        BookServiceImpl bookService = new BookServiceImpl(new BookDAO(bookList));
        Book book = new Book("Harry Potter", "J.K. Rowling");
        Book bookTest = bookService.addBook(book, CUSTOMER);
        Assert.notNull(bookTest, "Test de assert not null");
        Assert.isTrue(bookTest.getAuthor().equals(book.getAuthor()), "Test de assert author");
        Assert.isTrue(bookTest.getName().equals(book.getName()), "Test de assert name");
        bookTest = bookService.addBook(book, CUSTOMER);
        Assert.isNull(bookTest, "Test de assert NULL when repeated book");
        bookTest = bookService.addBook(book, CUSTOMER);
        Assert.isNull(bookTest, "Test de assert NULL when no customer");

    }

    @Test
    public void test_deleteBook() {
        Book book = new Book("Harry Potter", "J.K. Rowling");
        Book book_NULL = new Book("", "");
        HashMap<String, List<Book>> bookList = new HashMap<>();
        String CUSTOMER = "bailin";
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(book);
        bookList.put(CUSTOMER, bookArrayList);
        BookServiceImpl bookService = new BookServiceImpl(new BookDAO(bookList));
        boolean isDeleted = bookService.deleteBook(book.getId(), CUSTOMER);
        Assert.isTrue(isDeleted, "Test de delete");
        isDeleted = bookService.deleteBook(book_NULL.getId(), CUSTOMER);
        Assert.isTrue(!isDeleted, "Test de delete");
    }

    @Test
    public void test_editBook() {
        Book book = new Book("Harry Potter", "J.K. Rowling");
        Book book_NULL = new Book("", "");
        HashMap<String, List<Book>> bookList = new HashMap<>();
        String CUSTOMER = "bailin";
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(book);
        bookList.put(CUSTOMER, bookArrayList);
        BookDAO bookDAO = mock(BookDAO.class);
        when(bookDAO.editBook(any(String.class), any(Book.class))).thenReturn(book);
        BookServiceImpl bookService = new BookServiceImpl(new BookDAO(bookList));
        Book editedResult = bookService.editBook(CUSTOMER, book);
        Assert.notNull(editedResult, "Test de delete");
        Assert.isTrue(book.equals(editedResult), "Test equals de editBook");
        editedResult = bookService.editBook(CUSTOMER, book_NULL);
        Assert.isNull(editedResult, "Test is null de editedBook from book not found");
    }

}
