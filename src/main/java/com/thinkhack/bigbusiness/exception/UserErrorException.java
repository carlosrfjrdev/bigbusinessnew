package com.thinkhack.bigbusiness.exception;


public class UserErrorException extends RuntimeException {

    public UserErrorException(String message){
        super(message);
    }
}
