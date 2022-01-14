package com.fsecure.messageboard.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    public static final List<MediaType> supportedMediaTypes =
            Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
