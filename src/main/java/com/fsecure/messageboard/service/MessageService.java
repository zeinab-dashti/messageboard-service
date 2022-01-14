package com.fsecure.messageboard.service;

import com.fsecure.messageboard.api.dto.MessageV1;
import com.fsecure.messageboard.api.dto.MessageV2;
import com.fsecure.messageboard.exception.UnsupportedMediaTypeException;
import com.fsecure.messageboard.model.MessageEntity;
import org.springframework.http.MediaType;

import java.util.List;

public interface MessageService {

    MessageEntity createMessage(MessageV2 messageV2);
    List<MessageV1> listMessages();
    MediaType contentType(String mediaTypeStr) throws UnsupportedMediaTypeException;
}
