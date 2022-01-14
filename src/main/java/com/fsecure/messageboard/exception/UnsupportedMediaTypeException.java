package com.fsecure.messageboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class UnsupportedMediaTypeException extends HttpStatusCodeException {

    public UnsupportedMediaTypeException() {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    public String getMessage() {
        return "Accept header media type not supported";
    }
}
