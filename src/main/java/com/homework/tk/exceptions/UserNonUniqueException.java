package com.homework.tk.exceptions;

public class UserNonUniqueException extends RuntimeException {
    public UserNonUniqueException() {
    }

    public UserNonUniqueException(String message) {
        super(message);
    }
}
