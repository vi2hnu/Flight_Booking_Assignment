package org.example.booking.exception;

public class UsersNotFoundException extends RuntimeException {
    public UsersNotFoundException(String message) {
        super(message);
    }
}
