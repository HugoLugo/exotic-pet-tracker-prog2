package com.exoticpettracker.domain;

/** Domain rule violation while creating a pet. */
public class InvalidPetException extends RuntimeException {
    public InvalidPetException(String message) {
        super(message);
    }
}
