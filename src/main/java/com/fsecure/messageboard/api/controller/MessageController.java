package com.fsecure.messageboard.api.controller;

import com.fsecure.messageboard.api.dto.MessageV2;
import com.fsecure.messageboard.api.dto.MessageV1;
import com.fsecure.messageboard.exception.UnsupportedMediaTypeException;
import com.fsecure.messageboard.exception.InvalidVersionException;
import com.fsecure.messageboard.model.MessageEntity;
import com.fsecure.messageboard.service.MessageServiceLocator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("messageboard")
public class MessageController {

    @Autowired
    private MessageServiceLocator messageServiceLocator;

    @PostMapping(value = "/message")
    @ApiOperation(value = "Creates a new message")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Message created successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<?> createMessage(@RequestBody @Valid MessageV2 messageV2) {

        MessageEntity createdMessageId = messageServiceLocator.getMessageService().createMessage(messageV2);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdMessageId);
    }

    @GetMapping(value = "/message")
    @ApiOperation(value = "Retrieves all messages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Messages retrieved successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 415, message = "Unsupported accept header")})
    public ResponseEntity<?> listMessages(
            @ApiParam(value = "Response version", required = true, allowableValues = "v1, v2")
            @RequestParam  String version,
            @ApiParam(value = "Media Type", allowableValues = "application/json, application/xml")
            @RequestHeader("accept") String mediaType)
            throws InvalidVersionException, UnsupportedMediaTypeException {

        List<MessageV1> messages = messageServiceLocator.getMessageService(version).listMessages();
        MediaType contentType = messageServiceLocator.getMessageService(version).contentType(mediaType);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(contentType)
                .body(messages);
    }
}
