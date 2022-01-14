package com.fsecure.messageboard.service;

import com.fsecure.messageboard.api.dto.MessageV1;
import com.fsecure.messageboard.api.dto.MessageV2;
import com.fsecure.messageboard.conf.AppConfig;
import com.fsecure.messageboard.exception.UnsupportedMediaTypeException;
import com.fsecure.messageboard.model.MessageEntity;
import com.fsecure.messageboard.persistence.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("v1")
public class MessageServiceV1Impl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper messageMapper;

    @Override
    public MessageEntity createMessage(MessageV2 messageV2) {
        MessageEntity message = messageMapper.map(messageV2, MessageEntity.class);
        return messageRepository.save(message);
    }

    @Override
    public List<MessageV1> listMessages() {
        return messageRepository.findAllMessagesV1()
                .stream()
                .map(messageEntity -> messageMapper.map(messageEntity, MessageV1.class))
                .collect(Collectors.toList());
    }

    @Override
    public MediaType contentType(String mediaTypeStr) throws UnsupportedMediaTypeException {
        MediaType mediaType = MediaType.valueOf(mediaTypeStr);
        if(!AppConfig.supportedMediaTypes.contains(mediaType))
            throw new UnsupportedMediaTypeException();

        return MediaType.APPLICATION_JSON;
    }
}
