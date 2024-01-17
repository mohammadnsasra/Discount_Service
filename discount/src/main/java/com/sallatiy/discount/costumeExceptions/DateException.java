package com.sallatiy.discount.costumeExceptions;

import org.springframework.http.HttpStatus;

public class DateException extends ApiBaseException{
    public DateException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
