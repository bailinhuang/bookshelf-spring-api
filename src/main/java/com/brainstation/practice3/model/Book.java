package com.brainstation.practice3.model;

import java.util.UUID;

public class Book {
    private String id;
    private String name;
    private String author;

    public Book(){

    }

    public Book(String name, String author) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) {
            return false;
        } else if (((Book) o).author.equals(this.author) && ((Book) o).name.equals(this.name)) {
            return true;
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
