package com.fsecure.messageboard.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("health")
public class HealthController {

    @GetMapping(value = "/")
    @ApiOperation(value = "Message board health check")
    @ApiResponses({@ApiResponse(code = 200, message = "Message board is up and running")})
    public ResponseEntity<String> health(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Message board is running");
    }
}
