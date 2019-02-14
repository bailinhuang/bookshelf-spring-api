package com.brainstation.practice3.model;

import java.util.List;

public class CustomResponseObject {

    Object content;
    String message;

    public CustomResponseObject(Object content) {
        this.content = content;
    }

    public CustomResponseObject(Object content, String message) {
        this.content = content;
        this.message = message;
    }


    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
