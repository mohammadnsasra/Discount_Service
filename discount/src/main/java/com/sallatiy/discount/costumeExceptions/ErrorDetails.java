package com.sallatiy.discount.costumeExceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private String message;

    private String uri;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ErrorDetails() {

        this.timestamp = new Date();
    }


    public ErrorDetails(String message, String uri) {
        this();
        this.message = message;
        this.uri = uri;
    }

}
