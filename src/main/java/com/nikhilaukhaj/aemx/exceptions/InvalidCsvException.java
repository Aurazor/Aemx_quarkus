package com.nikhilaukhaj.aemx.exceptions;

public class InvalidCsvException extends RuntimeException{
    public InvalidCsvException(String message) {
        super(message);
    }

    public InvalidCsvException(){
        super();
    }
}
