package com.fsecure.messageboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class InvalidVersionException extends HttpStatusCodeException {

    public InvalidVersionException() {
        super(HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "Only v1 or v2 is valid";
    }

}
