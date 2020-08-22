package com.revature.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Customer authentication failed!");
    }

    public AuthenticationException(String message){super();}
}
