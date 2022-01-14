package com.fsecure.messageboard.service;

import com.fsecure.messageboard.api.dto.MessageV1;
import com.fsecure.messageboard.api.dto.MessageV2;
import com.fsecure.messageboard.conf.AppConfig;
import com.fsecure.messageboard.exception.UnsupportedMediaTypeException;
import com.fsecure.messageboard.persistence.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("v2")
public class MessageServiceV2Impl extends MessageServiceV1Impl {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper messageMapper;

    @Override
    public List<MessageV1> listMessages() {

        return messageRepository.findAll()
                .stream()
                .map(messageEntity -> messageMapper.map(messageEntity, MessageV2.class))
                .collect(Collectors.toList());
    }

    @Override
    public MediaType contentType(String mediaTypeStr) throws UnsupportedMediaTypeException {
        MediaType mediaType;
        try { mediaType = MediaType.valueOf(mediaTypeStr); }
        catch (Exception e){ throw new UnsupportedMediaTypeException(); }

        if(!AppConfig.supportedMediaTypes.contains(mediaType))
            throw new UnsupportedMediaTypeException();

        return mediaType;
    }
}
