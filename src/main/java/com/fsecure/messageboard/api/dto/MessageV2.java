package com.fsecure.messageboard.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "item")
public class MessageV2 extends MessageV1 {

    @URL(message = "{validation.message.url}")
    @ApiModelProperty(notes = "Message URL")
    private String url;

}
